package trustworthy.analyser.calculators;

import trustworthy.analyser.utils.Product;

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
        int successfulRuns = executeApplication(product);
        long upTime = successfulRuns * (NAIVE_TIMEOUT/1000);
        long totalTimeRun = 5L; //(NAIVE_TIMEOUT/1000) * NO_OF_TRIES;
        return ((double)(upTime)/(double)totalTimeRun * 100);
    }
}
