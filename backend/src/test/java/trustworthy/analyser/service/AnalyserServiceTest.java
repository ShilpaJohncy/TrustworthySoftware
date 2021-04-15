package trustworthy.analyser.service;

import org.junit.Test;
import trustworthy.analyser.data.AnalyserResponseObject;
import trustworthy.analyser.utils.Product;

import static org.junit.jupiter.api.Assertions.*;
import static trustworthy.analyser.utils.Constants.*;

public class AnalyserServiceTest {
    Product product = new Product();
    public void setUp(){

        product.setSecurity(20);
        product.setSafety(20);
        product.setAvailability(20);
        product.setReliability(20);
        product.setResiliency(20);
    }

    @Test
    public void testHighlyUntrustworthyVerdict(){
        setUp();
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setSecurityScore(20);
        responseObject.setSafetyScore(0);
        responseObject.setAvailabilityScore(0);
        responseObject.setResiliencyScore(0);
        responseObject.setReliabilityScore(0);
        AnalyserService service = new AnalyserService();
        service.calculateVerdict(responseObject, product);
        assertEquals(VERY_LOW, responseObject.getVerdict());

    }
    @Test
    public void testUntrustworthyVerdict(){
        setUp();
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setSecurityScore(100);
        responseObject.setSafetyScore(20);
        responseObject.setAvailabilityScore(0);
        responseObject.setResiliencyScore(0);
        responseObject.setReliabilityScore(0);
        AnalyserService service = new AnalyserService();
        service.calculateVerdict(responseObject, product);
        assertEquals(LOW, responseObject.getVerdict());
    }

    @Test
    public void testInconclusiveVerdict(){
        setUp();
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setSecurityScore(100);
        responseObject.setSafetyScore(20);
        responseObject.setAvailabilityScore(100);
        responseObject.setResiliencyScore(0);
        responseObject.setReliabilityScore(0);
        AnalyserService service = new AnalyserService();
        service.calculateVerdict(responseObject, product);
        assertEquals(INCONCLUSIVE, responseObject.getVerdict());

    }

    @Test
    public void testTrustworthyVerdict(){
        setUp();
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setSecurityScore(100);
        responseObject.setSafetyScore(100);
        responseObject.setAvailabilityScore(20);
        responseObject.setResiliencyScore(20);
        responseObject.setReliabilityScore(100);
        AnalyserService service = new AnalyserService();
        service.calculateVerdict(responseObject, product);
        assertEquals(HIGH, responseObject.getVerdict());

    }
    @Test
    public void testHighlyTrustworthyVerdict(){
        setUp();
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setSecurityScore(100);
        responseObject.setSafetyScore(100);
        responseObject.setAvailabilityScore(100);
        responseObject.setResiliencyScore(100);
        responseObject.setReliabilityScore(100);
        AnalyserService service = new AnalyserService();
        service.calculateVerdict(responseObject, product);
        assertEquals(VERY_HIGH, responseObject.getVerdict());

    }
}