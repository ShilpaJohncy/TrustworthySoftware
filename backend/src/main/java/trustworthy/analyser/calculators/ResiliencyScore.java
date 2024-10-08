package trustworthy.analyser.calculators;

import org.json.JSONException;
import trustworthy.analyser.utils.Product;

import java.io.IOException;

import static trustworthy.analyser.helpers.winchecksec.Winchecksec.getWinCheckSecScores;

public class ResiliencyScore {

    /**
     * Function to run all tests required to extract a score for the resiliency of a given product
     * @param product - The application who's safety is to be determined
     */
    public static double runResiliencyTests(Product product){
        double resiliencyScore = 0;
        int winchecksecScore = getWeightedWinchecksecScores(product);
        resiliencyScore = winchecksecScore;
        resiliencyScore = resiliencyScore/10;
        product.setResiliencyConfidence((product.getResiliencyConfidence()/100)* 100);
        return(resiliencyScore*100);
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
            product.setResiliencyConfidence(product.getResiliencyConfidence() + 100);
        } catch (IOException | JSONException e) {
            //Return dump value for PoC
            return -1;
        }

        int wincheckScore = 0;
        if(product.isSafeSEH()){
            wincheckScore += 10;
        }
        if(product.isSeh()){
            wincheckScore += 10;
        }
        return wincheckScore;
    }
}
