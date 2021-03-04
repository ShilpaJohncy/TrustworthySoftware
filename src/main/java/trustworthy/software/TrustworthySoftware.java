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
        if (trustworthyScore < 20) {
            product.setVerdict(VERY_LOW);
        }else if (trustworthyScore >= 20 && trustworthyScore < 40) {
            product.setVerdict(LOW);
        }else if (trustworthyScore >= 40 && trustworthyScore < 60) {
            product.setVerdict(INCONCLUSIVE);
        }else if (trustworthyScore >= 60 && trustworthyScore < 80) {
            product.setVerdict(HIGH);
        }else if (trustworthyScore >= 80) {
            product.setVerdict(VERY_HIGH);
        }
    }
}
