package trustworthy.software;

import trustworthy.software.utils.Product;

import static trustworthy.software.availabilityTest.CalculateAvailabilityScore.runAvailabilityTest;
import static trustworthy.software.resiliencyTest.CalculateResiliencyScore.runResiliencyTest;
import static trustworthy.software.safetyTest.CalculateSafetyScore.runSafetyTests;
import static trustworthy.software.securityTest.CalculateSecurityScore.runSecurityTests;
import static trustworthy.software.utils.Constants.*;

public class TrustworthySoftware {
    public static void main(String[] args) {

//        Product product = new Product();
//        product.setExecutablePath(ZOOM_EXE);
//        product.setVendorName("Zoom");
//        product.setProductName("zoom");
//        product.setParallelize(false);

        Product product2 = new Product();
        product2.setExecutablePath(NOTEPAD_EXE);
        product2.setVendorName("notepad-plus-plus");
        product2.setProductName("Notepad\\+\\+");
        product2.setVersionNo("7.6.6");
        product2.setParallelize(true);

        runSecurityTests(product2);
        runSafetyTests(product2);
        runAvailabilityTest(product2);
        runResiliencyTest(product2);

        calculateVerdict(product2);

    }

    /**
     * Private function to calculate the verdict for the app based on all the other test s that were run.
     * @param product - The product's whose trustworthiness is being decided.
     */
    private static void calculateVerdict(Product product) {
        double trustworthyScore = product.getSecurityScore() + product.getAvailabilityScore() + product.getSafetyScore() + product.getResilienceScore();
        product.setTrustworthyScore(trustworthyScore);
        if (trustworthyScore < 25) {
            product.setVerdict(NOT_TRUSTWORTHY);
        }else if (trustworthyScore >= 25 && trustworthyScore < 50) {
            product.setVerdict(INCONCLUSIVE_LOW);
        }else if (trustworthyScore >= 50 && trustworthyScore < 75) {
            product.setVerdict(INCONCLUSIVE_HIGH);
        }else if (trustworthyScore >= 75) {
            product.setVerdict(TRUSTWORTHY);
        }
    }
}
