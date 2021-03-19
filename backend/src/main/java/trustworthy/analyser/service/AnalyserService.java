package trustworthy.analyser.service;

import org.springframework.stereotype.Service;
import trustworthy.analyser.dataObjects.AnalyserResponseObject;
import trustworthy.analyser.utils.Product;

import static trustworthy.analyser.scoreCalculators.AvailabilityScore.runAvailabilityTest;
import static trustworthy.analyser.scoreCalculators.ResiliencyScore.runResiliencyTest;
import static trustworthy.analyser.scoreCalculators.SafetyScore.runSafetyTests;
import static trustworthy.analyser.scoreCalculators.SecurityScore.runSecurityTests;
import static trustworthy.analyser.utils.Constants.*;

@Service
public class AnalyserService {

    public AnalyserResponseObject runTests(Product product){
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        if(product.getSecurity() > 0){
            responseObject.setSecurityScore(runSecurityTests(product));
        }
        if(product.getSafety() > 0){
            responseObject.setSafetyScore(runSafetyTests(product));
        }
        if(product.getResiliency() > 0){
            responseObject.setResilienceScore(runResiliencyTest(product));
        }
        if(product.getAvailability() > 0){
            responseObject.setAvailabilityScore(runAvailabilityTest(product));
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
}
