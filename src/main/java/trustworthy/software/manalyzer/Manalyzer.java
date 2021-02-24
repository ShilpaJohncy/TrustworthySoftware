package trustworthy.software.manalyzer;

import org.json.JSONException;
import org.json.JSONObject;
import trustworthy.software.utils.Product;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static trustworthy.software.manalyzer.ManalyzerAPI.getReport;
import static trustworthy.software.manalyzer.ManalyzerAPI.submitSample;

public class Manalyzer {

    /**
     * Function to post an executable to Manalyze.org
     * and retrieve the results that are relevant to determining trustworthiness
     * @param product - The product who is to be analysed
     */
    public static void getManalyzeReport(Product product) {

        // Post the executable to Manalyze.org for testing
        String taskId = submitSample(product.getExecutablePath());

        //Retrieve the report using the taskId and parse the jsonOutput to get the field values we want
        JSONObject manalyzeReport = getReport(taskId);
        if(manalyzeReport == null){
            System.out.println("Cannot get manalyze score");
        }
        ArrayList<Integer> pluginScores = getPluginScores(manalyzeReport, taskId);

        if(pluginScores!= null){
            // TODO: DO some calculation with these scores
            System.out.println("The no of plugins run = " + pluginScores.get(pluginScores.size()-1));
            System.out.println("The scores received = ");
            for (int i = 0; i < pluginScores.size() - 1; i++) {
                System.out.println(pluginScores.get(i));
            }
        }
    }


    /**
     * Private function to extract the scores received for each plugin
     * @param report - The report from Manalyze.org
     * @param md5 - The MD5 string of the report, which is its unique identifier
     * @return An ArrayList of the scores, where the last integer is the number of plugins run
     */
    private static ArrayList<Integer> getPluginScores(JSONObject report, String md5)  {
        try {
            JSONObject reportMD5 = report.getJSONObject(md5);
            JSONObject pluginReport = reportMD5.getJSONObject("Plugins");
            AtomicInteger pluginCount = new AtomicInteger();
            ArrayList<Integer> scores = new ArrayList<>();

            pluginReport.keys().forEachRemaining(key -> {
                pluginCount.getAndIncrement();
                try {
                    JSONObject plugin = pluginReport.getJSONObject(String.valueOf(key));
                    scores.add(plugin.getInt("level"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            scores.add(Integer.parseInt(String.valueOf(pluginCount)));
            return scores;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
