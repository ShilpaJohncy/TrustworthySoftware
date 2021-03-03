package trustworthy.software;

import trustworthy.software.utils.Product;

import java.io.IOException;

import static trustworthy.software.availabilityTest.CalculateAvailabilityScore.runAvailabilityTest;
import static trustworthy.software.resiliencyTest.CalculateResiliencyScore.runResiliencyTest;
import static trustworthy.software.safetyTest.CalculateSafetyScore.runSafetyTests;
import static trustworthy.software.securityTest.CalculateSecurityScore.runSecurityTests;
import static trustworthy.software.utils.Constants.*;

public class TrustworthySoftware {
    public static void main(String[] args) throws IOException {

        Product product = new Product();
        product.setExecutablePath(ZOOM_EXE);
        product.setVendorName("Zoom");
        product.setProductName("zoom");
        product.setParallelize(false);

        Product product2 = new Product();
        product2.setExecutablePath(NOTEPAD_EXE);
        product2.setParallelize(false);

        runSecurityTests(product);
        runSafetyTests(product);
        runAvailabilityTest(product2);
        runResiliencyTest(product);

    }
}
