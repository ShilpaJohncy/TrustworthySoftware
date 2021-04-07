package trustworthy.analyser.helpers.manalyzer;

import org.json.JSONException;
import org.json.JSONObject;
import trustworthy.analyser.utils.Product;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static trustworthy.analyser.helpers.manalyzer.ManalyzerAPI.getReport;
import static trustworthy.analyser.helpers.manalyzer.ManalyzerAPI.submitSample;

public class Manalyzer {

    /**
     * Function to post an executable to Manalyze.org
     * and retrieve the results that are relevant to determining trustworthiness
     * @param product - The product who is to be analysed
     */
    public static void getManalyzeReport(Product product) {

        // Post the executable to Manalyze.org for testing
        String taskId = submitSample(product.getExecutablePath());

        //If taskId doesn't exist, set a dump value for PoC
        if(taskId == null){
            product.setManalyzeScore(24);
        }

        //Retrieve the report using the taskId and parse the jsonOutput to get the field values we want
        JSONObject manalyzeReport = getReport(taskId);
        if(manalyzeReport != null){
            ArrayList<Integer> pluginScores = getPluginScores(manalyzeReport, taskId);

            // Translate the manalyze score to the safety score
            if(pluginScores!= null){
                double manalyzeScore = 0;
                int pluginCount = pluginScores.get(pluginScores.size()-1);
                for (int i = 0; i < pluginCount; i++) {
                    switch (pluginScores.get(i)){
                        case 3:
                            manalyzeScore += 0;
                            break;
                        case 2:
                            manalyzeScore += 2;
                            break;
                        case 1:
                            manalyzeScore += 3;
                            break;
                        case 0:
                            manalyzeScore += 5;
                            break;
                        default:
                            break;
                    }
                }
                // Scale the score up/down to out of 30.
                product.setManalyzeScore(manalyzeScore/(pluginCount*5) * 60);
            }
            product.setSafetyConfidence(product.getSafetyConfidence() + 80);
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
