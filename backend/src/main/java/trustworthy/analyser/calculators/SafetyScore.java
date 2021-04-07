package trustworthy.analyser.calculators;

import org.json.JSONException;
import trustworthy.analyser.utils.Product;

import java.io.IOException;

import static trustworthy.analyser.helpers.manalyzer.Manalyzer.getManalyzeReport;
import static trustworthy.analyser.helpers.winchecksec.Winchecksec.getWinCheckSecScores;

public class SafetyScore {

    /**
     * Function to run all tests required to extract a score for the safety of a given product
     * @param product - The application who's safety is to be determined
     */
    public static double runSafetyTests(Product product){
        double safetyScore = 0;
        getManalyzeReport(product);
        int winchecksecScore = getWeightedWinchecksecScores(product);
        safetyScore = product.getManalyzeScore() + winchecksecScore;
        product.setSafetyConfidence((product.getSafetyConfidence()/300 )* 100);

        return safetyScore;
    }

    /**
     * Calculate a weighted winchecksec score for the given product
     * @param product - The product whose winchecksec
     * @return - the winchecksec score calculated based on the weights assigned
     *         - -1 if the winchecksec did not occur without any issues.
     */
    private static int getWeightedWinchecksecScores(Product product){
        try {
            getWinCheckSecScores(product);
            product.setSafetyConfidence(product.getSafetyConfidence() + 200);
        } catch (IOException | JSONException e) {
            return -1;
        }

        int wincheckScore = 0;
        if(product.isAuthenticode()){
            wincheckScore += 20;
        }
        if(product.isForceIntegrity()){
            wincheckScore += 20;
        }
        return wincheckScore;
    }
}
