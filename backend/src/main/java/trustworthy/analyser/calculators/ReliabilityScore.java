package trustworthy.analyser.calculators;

import trustworthy.analyser.utils.Product;

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
        int noOfFailures = NO_OF_TRIES - executeApplication(product);
        long totalTimeRun = 5L; // (NAIVE_TIMEOUT/1000) * NO_OF_TRIES;
        double failureRate = (double)(noOfFailures)/(double)totalTimeRun;
        return ( (1 - failureRate)* 100);
    }
}
