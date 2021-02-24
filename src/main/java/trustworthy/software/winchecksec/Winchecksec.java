package trustworthy.software.winchecksec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static trustworthy.software.utils.Constants.*;

public class Winchecksec {

    public static void getWinCheckSecScores(String productExePath) throws IOException, JSONException {

        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(WINCHECKSEC_EXE + " --json " + productExePath );
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));

        // Read the output from the command
        String s;
        String output = "";

        //Get the output from the command line
        while ((s = stdInput.readLine()) != null) {
            output = output.concat(s);
        }
        // Format response to rqd JSON format
        output = output.substring(1, output.length() - 1);

        // Set the response to the response object
        JSONObject jsonResponse = new JSONObject(output);
        Gson gson = new GsonBuilder().create();
        gson.fromJson(String.valueOf(jsonResponse), WinchecksecResponse.class);

    }

}
