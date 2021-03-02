package trustworthy.software.safetyTest;

import org.json.JSONException;
import trustworthy.software.utils.Product;

import java.io.IOException;

import static trustworthy.software.manalyzer.Manalyzer.getManalyzeReport;
import static trustworthy.software.utils.Constants.SAFETY_WEIGHTAGE;
import static trustworthy.software.winchecksec.Winchecksec.getWinCheckSecScores;

public class calculateSafetyScore {

    /**
     * Function to run all tests required to extract a score for the safety of a given product
     * @param product - The application who's safety is to be determined
     */
    //TODO: To deal with the winchecksec score of -1.
    public static void runSafetyTests(Product product){
        double safetyScore = 0;
        getManalyzeReport(product);
        int winchecksecScore = getWeightedWinchecksecScores(product);
        safetyScore = product.getManalyzeScore() + winchecksecScore;
        System.out.println(safetyScore);
        safetyScore = safetyScore/100;
        safetyScore *= SAFETY_WEIGHTAGE;
        product.setSafetyScore(safetyScore);
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
        } catch (IOException | JSONException e) {
            return -1;
        }

        int wincheckScore = 0;
        if(product.isAuthenticode()){
            wincheckScore += 50;
        }
        if(product.isForceIntegrity()){
            wincheckScore += 20;
        }
        return wincheckScore;
    }
}
