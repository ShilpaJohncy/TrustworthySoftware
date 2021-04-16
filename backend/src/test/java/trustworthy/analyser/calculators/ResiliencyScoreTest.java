package trustworthy.analyser.calculators;

import org.junit.jupiter.api.Test;
import trustworthy.analyser.utils.Product;

import static org.junit.jupiter.api.Assertions.*;
import static trustworthy.analyser.calculators.AvailabilityScore.calculateOperationalAvailability;
import static trustworthy.analyser.calculators.ResiliencyScore.runResiliencyTests;

class ResiliencyScoreTest {

    final String failAllTheTime = "C:\\Users\\shilp\\Desktop\\Uni\\Project\\TestApps\\AppFailingAllTheTime.exe";

    @Test
    public void runResiliencyTest() {
        Product testProduct = new Product();
        testProduct.setExecutablePath(failAllTheTime);
        testProduct.setParallelize(false);
        double result = runResiliencyTests(testProduct);
        assertEquals(0,result);

    }
}