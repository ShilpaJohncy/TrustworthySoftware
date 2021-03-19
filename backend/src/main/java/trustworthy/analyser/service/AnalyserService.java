package trustworthy.analyser.service;

import org.springframework.stereotype.Service;
import trustworthy.analyser.dataObjects.AnalyserResponseObject;
import trustworthy.analyser.utils.Product;

import static trustworthy.analyser.scoreCalculators.AvailabilityScore.runAvailabilityTests;
import static trustworthy.analyser.scoreCalculators.ResiliencyScore.runResiliencyTests;
import static trustworthy.analyser.scoreCalculators.SafetyScore.runSafetyTests;
import static trustworthy.analyser.scoreCalculators.SecurityScore.runSecurityTests;
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
        double trustworthyScore = responseObject.getSecurityScore() + responseObject.getAvailabilityScore() + responseObject.getSafetyScore() + responseObject.getResilienceScore();
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

    public AnalyserResponseObject runSecurityTest(Product product){
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setSecurityScore(runSecurityTests(product));
        return responseObject;
    }
    public AnalyserResponseObject runSafetyTest(Product product){
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setSafetyScore(runSafetyTests(product));
        return responseObject;
    }

    public AnalyserResponseObject runAvailabilityTest(Product product){
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setAvailabilityScore(runAvailabilityTests(product));
        return responseObject;
    }

    public AnalyserResponseObject runResiliencyTest(Product product){
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setResilienceScore(runResiliencyTests(product));
        return responseObject;
    }
}
