package trustworthy_software;

import trustworthy_software.utils.Product;

import static trustworthy_software.availabilityTest.CalculateAvailabilityScore.runAvailabilityTest;
import static trustworthy_software.resiliencyTest.CalculateResiliencyScore.runResiliencyTest;
import static trustworthy_software.safetyTest.CalculateSafetyScore.runSafetyTests;
import static trustworthy_software.securityTest.CalculateSecurityScore.runSecurityTests;
import static trustworthy_software.utils.Constants.*;

public class TrustworthySoftware {
    
    public static void main(String[] args) {

        Product product = new Product();
        product.setExecutablePath(NOTEPAD_EXE);
        product.setVendorName("notepad-plus-plus");
        product.setProductName("Notepad\\+\\+");
        product.setVersionNo("7.6.6");
        product.setParallelize(true);

        runSecurityTests(product);
        runSafetyTests(product);
        runAvailabilityTest(product);
        runResiliencyTest(product);

        calculateVerdict(product);

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
