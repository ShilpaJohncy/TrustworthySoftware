package trustworthy.analyser.calculators;

import org.json.JSONException;
import trustworthy.analyser.utils.Product;

import java.io.IOException;

import static trustworthy.analyser.helpers.winchecksec.Winchecksec.getWinCheckSecScores;
import static trustworthy.analyser.utils.Constants.NAIVE_TIMEOUT;
import static trustworthy.analyser.utils.ExecuteApplication.executeApplication;
import static trustworthy.analyser.utils.Constants.NO_OF_TRIES;

public class ReliabilityScore {

    /**
     * Runs the test to check reliability as a function of the failure rate of the application
     *
     * @param product - The product who's reliability is to be tested
     * @return reliabilityScore - The reliability score of the application
     */
    public static double runReliabilityTests(Product product) {
        double failureRate = calculateFailureRate(product);
        double reliabilityscore = failureRate + getWeightedWinchecksecScores(product);
        product.setReliabilityConfidence((product.getReliabilityConfidence()/300) * 100);
        return reliabilityscore;
    }

    private static double calculateFailureRate(Product product){
        executeApplication(product);
        int noOfFailures = NO_OF_TRIES - product.getSuccessfulRuns();
        long totalTimeRun = (NAIVE_TIMEOUT/1000) * NO_OF_TRIES;
        double failureRate = (double)(noOfFailures)/(double)totalTimeRun;
        product.setReliabilityConfidence(product.getReliabilityConfidence() + 95);
        return ( (1 - failureRate)* 80);
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
            product.setReliabilityConfidence(product.getReliabilityConfidence() + 200);
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
