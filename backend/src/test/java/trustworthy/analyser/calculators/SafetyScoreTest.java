package trustworthy.analyser.calculators;

import org.junit.Test;
import trustworthy.analyser.utils.Product;

import static org.junit.jupiter.api.Assertions.*;
import static trustworthy.analyser.calculators.SafetyScore.runSafetyTests;
import static trustworthy.analyser.utils.Constants.TEAMS_EXE;
import static trustworthy.analyser.utils.Constants.ZOOM_EXE;

public class SafetyScoreTest {

    final String failAllTheTime = "C:\\Users\\shilp\\Desktop\\Uni\\Project\\TrustworthySoftware\\backend\\src\\test\\resources\\TestApps\\AppFailingAllTheTime.exe";
    @Test
    public void appThatFailsAllTheTimeSafetyTest(){
        Product testProduct = new Product();
        testProduct.setExecutablePath(failAllTheTime);
        double result = runSafetyTests(testProduct);
        assertEquals(24,result);
    }

    @Test
    public void runSafetyTestForZoom(){
        Product testProduct = new Product();
        testProduct.setExecutablePath(ZOOM_EXE);
        double result = runSafetyTests(testProduct);
        assertEquals(53.6,result);
    }
    @Test
    public void runSafetyTestForTeams(){
        Product testProduct = new Product();
        testProduct.setExecutablePath(TEAMS_EXE);
        double result = runSafetyTests(testProduct);
        assertEquals(44,result);
    }
}