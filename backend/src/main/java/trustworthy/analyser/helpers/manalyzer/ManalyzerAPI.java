package trustworthy.analyser.helpers.manalyzer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static trustworthy.analyser.utils.Constants.MANALYZER_URL;

public class ManalyzerAPI {

    /**
     * Obtains the results of an analysis for a given sample.
     * @param md5 - The MD5 of the sample to query.
     * @return -The JSON output of Manalyze for that sample, or null if it wasn't analyzed on the website.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject getReport(String md5) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet((MANALYZER_URL + "/json/" + md5));
            HttpResponse httpResponse = null;
            httpResponse = httpClient.execute(httpGet);

            if(httpResponse.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = httpResponse.getEntity();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                // Set the response to the response object
                JSONObject jsonResponse = new JSONObject(responseString);
                return jsonResponse;
            }else{
                System.out.println("Could not find the report for the given md5 " + md5);
                return null;
            }
        } catch (IOException |JSONException e) {
            return null;
        }
    }

    /**
     * Submits a sample to the endpoint for analysis.
     * This function can be either blocking or non-blocking.
     * In the first case, it will wait until the endpoint has finished processing the file and will return the JSON report.
     * Otherwise, it will simply return a task identifier that can be used to query the status of the analysis.
     * @param path - The path to the PE to analyze.
     * @throws Exception
     */
    public static String submitSample(String path){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(MANALYZER_URL + "/upload");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);

        // This attaches the file to the POST:
        File f = new File(path);
        try {
            builder.addBinaryBody(
                    "file",
                    new FileInputStream(f),
                    ContentType.APPLICATION_OCTET_STREAM,
                    f.getName()
            );
            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);
            CloseableHttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();

            if(response.getStatusLine().getStatusCode() != 200){
                return null;
            }
            String responseString = EntityUtils.toString(responseEntity, "UTF-8");
            JSONObject jsonResponse = new JSONObject(responseString);

            JSONObject data =  jsonResponse.getJSONObject("data");
            String  taskId = data.getString("task_id");
            return taskId;

        } catch (JSONException | IOException e) {
            return null;
        }
    }
}


