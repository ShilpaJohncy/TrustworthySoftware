package trustworthy.analyser.service;

import org.springframework.stereotype.Service;
import trustworthy.analyser.data.AnalyserResponseObject;
import trustworthy.analyser.utils.Product;

import java.text.DecimalFormat;

import static trustworthy.analyser.calculators.AvailabilityScore.runAvailabilityTests;
import static trustworthy.analyser.calculators.ReliabilityScore.runReliabilityTests;
import static trustworthy.analyser.calculators.ResiliencyScore.runResiliencyTests;
import static trustworthy.analyser.calculators.SafetyScore.runSafetyTests;
import static trustworthy.analyser.calculators.SecurityScore.runSecurityTests;
import static trustworthy.analyser.utils.Constants.*;

@Service
public class AnalyserService {

    /**
     * The service that will run all tests to determine overall trustworthiness
     * @param product - The product details passed
     * @return AnalyserResponseObject - An object with the trustworthiness score and individual facet scores calculated
     */
    public AnalyserResponseObject runTests(Product product){
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        if(product.getSecurity() > 0){
            responseObject.setSecurityScore(runSecurityTests(product));
        }
        if(product.getSafety() > 0){
            responseObject.setSafetyScore(runSafetyTests(product));
        }
        if(product.getResiliency() > 0){
            responseObject.setResiliencyScore(runResiliencyTests(product));
        }
        if(product.getAvailability() > 0){
            responseObject.setAvailabilityScore(runAvailabilityTests(product));
        }
        if(product.getReliability() > 0){
            responseObject.setReliabilityScore(runReliabilityTests(product));

        }
        calculateVerdict(responseObject, product);
        calculateConfidence(responseObject, product);
        return responseObject;
    }

    /**
     * Private function to calculate the verdict for the app based on all the other test s that were run.
     * @param responseObject - The product's whose trustworthiness is being decided.
     */
    private static void calculateVerdict(AnalyserResponseObject responseObject, Product product) {
        double weightedSecurityScore = (responseObject.getSecurityScore()* product.getSecurity())/100;
        weightedSecurityScore = Math.round( weightedSecurityScore * 100.0 ) / 100.0;

        double weightedSafetyScore = (responseObject.getSafetyScore() * product.getSafety())/100;
        weightedSafetyScore = Math.round( weightedSafetyScore * 100.0 ) / 100.0;

        double weightedAvailabilityScore = (responseObject.getAvailabilityScore() * product.getAvailability())/100;
        weightedAvailabilityScore = Math.round( weightedAvailabilityScore * 100.0 ) / 100.0;

        double weightedResiliencyScore = (responseObject.getResiliencyScore() * product.getResiliency())/100;
        weightedResiliencyScore = Math.round( weightedResiliencyScore * 100.0 ) / 100.0;

        double weightedReliabilityScore = (responseObject.getReliabilityScore() * product.getReliability())/100;
        weightedReliabilityScore = Math.round( weightedReliabilityScore * 100.0 ) / 100.0;

        double sum = weightedSecurityScore + weightedSafetyScore + weightedAvailabilityScore + weightedResiliencyScore + weightedReliabilityScore;
        int trustworthyScore = (int) Math.rint(sum);
        responseObject.setTrustworthyScore(trustworthyScore);
        if (trustworthyScore < 20) {
            responseObject.setVerdict(VERY_LOW);
        }else if (trustworthyScore < 40) {
            responseObject.setVerdict(LOW);
        }else if (trustworthyScore < 60) {
            responseObject.setVerdict(INCONCLUSIVE);
        }else if (trustworthyScore < 80) {
            responseObject.setVerdict(HIGH);
        }else {
            responseObject.setVerdict(VERY_HIGH);
        }
    }
    /**
     * Private function to calculate the confidence in the result for the app based on all the other tests that were run.
     * @param responseObject - The product with a verdict and the confidence in the result calculated..
     */
    private static void calculateConfidence(AnalyserResponseObject responseObject, Product product) {
        double security = (product.getSecurityConfidence() * product.getSecurity()) / 100;
        double safety = (product.getSafetyConfidence() * product.getSafety()) / 100;
        double resiliency = (product.getResiliencyConfidence() * product.getResiliency()) / 100;
        double reliability = (product.getReliabilityConfidence() * product.getReliability()) / 100;
        double availability = (product.getAvailabilityConfidence() * product.getAvailability()) / 100;
        double confidence = security + safety + availability + reliability + resiliency;
        DecimalFormat df = new DecimalFormat("#.#");
        confidence = Double.parseDouble(df.format(confidence));
        responseObject.setConfidence(confidence);
    }

    /**
     * The service that will run tests to determine if the software is secure
     * @param product - The product details passed
     * @return AnalyserResponseObject - An object with the security score calculated
     */
    public AnalyserResponseObject runSecurityTest(Product product){
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setSecurityScore(runSecurityTests(product));
        return responseObject;
    }

    /**
     * The service that will run tests to determine if the software is safe
     * @param product - The product details passed
     * @return AnalyserResponseObject - An object with the safety score calculated
     */
    public AnalyserResponseObject runSafetyTest(Product product){
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setSafetyScore(runSafetyTests(product));
        return responseObject;
    }

    /**
     * The service that will run tests to determine if the software is available
     * @param product - The product details passed
     * @return AnalyserResponseObject - An object with the availability score calculated
     */
    public AnalyserResponseObject runAvailabilityTest(Product product){
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setAvailabilityScore(runAvailabilityTests(product));
        return responseObject;
    }

    /**
     * The service that will run tests to determine if the software is resilient
     * @param product - The product details passed
     * @return AnalyserResponseObject - An object with the resiliency score calculated
     */
    public AnalyserResponseObject runResiliencyTest(Product product){
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setResiliencyScore(runResiliencyTests(product));
        return responseObject;
    }

    /**
     * The service that will run tests to determine if the software is reliable
     * @param product - The product details passed
     * @return AnalyserResponseObject - An object with the reliability score calculated
     */
    public AnalyserResponseObject runReliabilityTest(Product product){
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setAvailabilityScore(runReliabilityTests(product));
        return responseObject;
    }
}
