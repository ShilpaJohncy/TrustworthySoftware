package trustworthy.analyser.calculators;

import org.junit.Test;
import trustworthy.analyser.utils.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static trustworthy.analyser.calculators.AvailabilityScore.calculateOperationalAvailability;
import static trustworthy.analyser.calculators.AvailabilityScore.runAvailabilityTests;
import static trustworthy.analyser.utils.Constants.TEAMS_EXE;
import static trustworthy.analyser.utils.Constants.ZOOM_EXE;

public class AvailabilityScoreTest {

    final String word = "C:\\Program Files\\Microsoft Office\\root\\Office16\\WINWORD.EXE";
    final String failHalfTheTime = "C:\\Users\\shilp\\Desktop\\Uni\\Project\\TrustworthySoftware\\backend\\src\\test\\resources\\TestApps\\AppFailingOneOutOfTwoTimes.exe";
    final String failAllTheTime = "C:\\Users\\shilp\\Desktop\\Uni\\Project\\TrustworthySoftware\\backend\\src\\test\\resources\\TestApps\\AppFailingAllTheTime.exe";

    @Test
    public void runOperationalAvailabilityTest(){
        Product testProduct = new Product();
        testProduct.setExecutablePath(word);
        double result = calculateOperationalAvailability(testProduct);
        assertEquals(80,result);
    }

    @Test
    public void runOperationalAvailabilityTestForAppFailingHalfTheTime(){
        Product testProduct = new Product();
        testProduct.setExecutablePath(failHalfTheTime);
        double result = calculateOperationalAvailability(testProduct);
        assertFalse(result < 0);
    }

    @Test
    public void runOperationalAvailabilityTestForAppFailingAllTheTime(){
        Product testProduct = new Product();
        testProduct.setExecutablePath(failAllTheTime);
        testProduct.setParallelize(false);
        double result = calculateOperationalAvailability(testProduct);
        assertEquals(0,result);
    }
    @Test
    public void runOperationalAvailabilityTestForZoom(){
        Product testProduct = new Product();
        testProduct.setExecutablePath(ZOOM_EXE);
        testProduct.setParallelize(false);
        double result = calculateOperationalAvailability(testProduct);
        assertEquals(80,result);
    }

    @Test
    public void runAvailabilityTestForZoom(){
        Product testProduct = new Product();
        testProduct.setExecutablePath(ZOOM_EXE);
        testProduct.setParallelize(false);
        double result = runAvailabilityTests(testProduct);
        assertEquals(90,result);
    }
    @Test
    public void runAvailabilityTestForTeams(){
        Product testProduct = new Product();
        testProduct.setExecutablePath(TEAMS_EXE);
        testProduct.setParallelize(false);
        double result = runAvailabilityTests(testProduct);
        assertEquals(90,result);
    }

}