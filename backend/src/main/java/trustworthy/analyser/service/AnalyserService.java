package trustworthy.analyser.service;

import org.springframework.stereotype.Service;
import trustworthy.analyser.data.AnalyserResponseObject;
import trustworthy.analyser.utils.Product;

import static trustworthy.analyser.calculators.AvailabilityScore.runAvailabilityTests;
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
            responseObject.setResilienceScore(runResiliencyTests(product));
        }
        if(product.getAvailability() > 0){
            responseObject.setAvailabilityScore(runAvailabilityTests(product));
        }
        calculateVerdict(responseObject);
        return responseObject;
    }

    /**
     * Private function to calculate the verdict for the app based on all the other test s that were run.
     * @param responseObject - The product's whose trustworthiness is being decided.
     */
    private static void calculateVerdict(AnalyserResponseObject responseObject) {
        double sum = responseObject.getSecurityScore() + responseObject.getAvailabilityScore() + responseObject.getSafetyScore() + responseObject.getResilienceScore();
        int trustworthyScore = (int) Math.rint(sum);
        responseObject.setTrustworthyScore(trustworthyScore);
        if (trustworthyScore < 20) {
            responseObject.setVerdict(VERY_LOW);
        }else if (trustworthyScore >= 20 && trustworthyScore < 40) {
            responseObject.setVerdict(LOW);
        }else if (trustworthyScore >= 40 && trustworthyScore < 60) {
            responseObject.setVerdict(INCONCLUSIVE);
        }else if (trustworthyScore >= 60 && trustworthyScore < 80) {
            responseObject.setVerdict(HIGH);
        }else if (trustworthyScore >= 80) {
            responseObject.setVerdict(VERY_HIGH);
        }
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
        responseObject.setResilienceScore(runResiliencyTests(product));
        return responseObject;
    }
}
