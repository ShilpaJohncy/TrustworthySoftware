package trustworthy.analyser.calculators;

import org.json.JSONException;
import trustworthy.analyser.utils.Product;

import java.io.IOException;

import static trustworthy.analyser.helpers.winchecksec.Winchecksec.getWinCheckSecScores;
import static trustworthy.analyser.utils.ExecuteApplication.executeApplication;
import static trustworthy.analyser.utils.Constants.NAIVE_TIMEOUT;

public class AvailabilityScore {

    /**
     * Runs the test to check availability using a naive equation
     *
     * If the number of successful runs from executing the product is > 50% of the no of tries, it is available
     * If its = 50%, inconclusive
     * And if its < 50%, the result is inconclusive
     *
     * @param product - The product who's availability is to be tested
     * @return availabilityScore - The availability score based on weightage
     */
    public static double runAvailabilityTests(Product product) {
        double operationAvailability =  calculateOperationalAvailability(product);
        int winchecksecScore = getWeightedWinchecksecScores(product);
        product.setAvailabilityConfidence((product.getAvailabilityConfidence()/300) * 100);
        return operationAvailability + winchecksecScore;
    }

    private static double calculateOperationalAvailability(Product product){
        executeApplication(product);
        long upTime = product.getSuccessfulRuns() * (NAIVE_TIMEOUT/1000);
        long totalTimeRun = 5L; //(NAIVE_TIMEOUT/1000) * NO_OF_TRIES;
        product.setAvailabilityConfidence(product.getAvailabilityConfidence() + 95);
        return (((double)upTime/(double)totalTimeRun) * 80);
    }

    /**
     * Calculate a weighted winchecksec score for the given product
     * @param product - The product whose winchecksec
     * @return - the winchecksec score calculated based on the weights assigned
     *         - -1 if the winchecksec did not occur without any issues.
     */
    private static int getWeightedWinchecksecScores(Product product){
        try{
            getWinCheckSecScores(product);
            product.setAvailabilityConfidence(product.getAvailabilityConfidence() + 200);
        } catch (IOException | JSONException e) {
            //Return dump value for PoC
            return -1;
        }
        int wincheckScore = 0;
        if(product.isAuthenticode()){
            wincheckScore += 10;
        }
        if(product.isForceIntegrity()){
            wincheckScore += 10;
        }
        return wincheckScore;
    }
}
