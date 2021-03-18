package trustworthy.analyser.resiliencyTest;

import org.json.JSONException;
import trustworthy.analyser.utils.Product;

import java.io.IOException;

import static trustworthy.analyser.utils.Constants.RESILIENCE_WEIGHTAGE;
import static trustworthy.analyser.winchecksec.Winchecksec.getWinCheckSecScores;

public class CalculateResiliencyScore {

    /**
     * Function to run all tests required to extract a score for the resiliency of a given product
     * @param product - The application who's safety is to be determined
     */
    //TODO: To deal with the winchecksec score of -1.
    public static void runResiliencyTest(Product product){
        double resiliencyScore = 0;
        int winchecksecScore = getWeightedWinchecksecScores(product);
        resiliencyScore = winchecksecScore;
        resiliencyScore = resiliencyScore/20;
        resiliencyScore *= RESILIENCE_WEIGHTAGE;
        product.setResilienceScore(resiliencyScore);
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
            wincheckScore += 5;
        }
        if(product.isForceIntegrity()){
            wincheckScore += 3;
        }
        if(product.isSafeSEH()){
            wincheckScore += 7;
        }
        if(product.isSeh()){
            wincheckScore += 5;
        }
        return wincheckScore;
    }
}
