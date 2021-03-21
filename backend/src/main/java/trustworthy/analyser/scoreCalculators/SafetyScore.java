package trustworthy.analyser.scoreCalculators;

import org.json.JSONException;
import trustworthy.analyser.utils.Product;

import java.io.IOException;

import static trustworthy.analyser.manalyzer.Manalyzer.getManalyzeReport;
import static trustworthy.analyser.winchecksec.Winchecksec.getWinCheckSecScores;

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
        safetyScore = safetyScore/100;
        safetyScore *= product.getSafety();
        return (Math.round( safetyScore * 100.0 ) / 100.0);
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
            return 56;
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
