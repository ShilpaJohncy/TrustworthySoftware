package trustworthy.analyser.calculators;

import org.junit.Test;
import trustworthy.analyser.utils.Product;

import static org.junit.jupiter.api.Assertions.*;
import static trustworthy.analyser.calculators.ReliabilityScore.runReliabilityTests;
import static trustworthy.analyser.utils.Constants.TEAMS_EXE;
import static trustworthy.analyser.utils.Constants.ZOOM_EXE;

public class ReliabilityScoreTest {

    final String failAllTheTime = "C:\\Users\\shilp\\Desktop\\Uni\\Project\\TestApps\\AppFailingAllTheTime.exe";

    @Test
    public void appThatFailsAllTheTimeReliabilityTest(){
        Product testProduct = new Product();
        testProduct.setExecutablePath(failAllTheTime);
        double result = runReliabilityTests(testProduct);
        assertEquals(0,result);
    }

    @Test
    public void runReliabilityTestForZoom(){
        Product testProduct = new Product();
        testProduct.setExecutablePath(ZOOM_EXE);
        double result = runReliabilityTests(testProduct);
        assertEquals(90,result);
    }
    @Test
    public void runReliabilityTestForTeams(){
        Product testProduct = new Product();
        testProduct.setExecutablePath(TEAMS_EXE);
        double result = runReliabilityTests(testProduct);
        assertEquals(90,result);
    }
}