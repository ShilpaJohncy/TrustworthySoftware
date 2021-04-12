package trustworthy.analyser.data;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyserResponseObjectTest {
    @Test
    public void setFields(){
        AnalyserResponseObject responseObject = new AnalyserResponseObject();
        responseObject.setSecurityScore(100);
        responseObject.setSafetyScore(100);
        responseObject.setReliabilityScore(100);
        responseObject.setResiliencyScore(100);
        responseObject.setAvailabilityScore(100);
        responseObject.setTrustworthyScore(100);
        responseObject.setConfidence(100);
        responseObject.setVerdict("Test verdict");
        assertEquals(100, responseObject.getSecurityScore());
        assertEquals(100, responseObject.getSafetyScore());
        assertEquals(100, responseObject.getResiliencyScore());
        assertEquals(100, responseObject.getReliabilityScore());
        assertEquals(100, responseObject.getAvailabilityScore());
        assertEquals(100, responseObject.getTrustworthyScore());
        assertEquals("Test verdict", responseObject.getVerdict());
        assertEquals(100, responseObject.getConfidence());
    }
}