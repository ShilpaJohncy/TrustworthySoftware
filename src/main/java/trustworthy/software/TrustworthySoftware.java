package trustworthy.software;

import trustworthy.software.utils.Product;

import java.io.IOException;

import static trustworthy.software.availabilityTest.AvailabilityTests.runAvailabilityTest;
import static trustworthy.software.safetyTest.calculateSafetyScore.runSafetyTests;
import static trustworthy.software.securityTest.calculateSecurityScore.runSecurityTests;
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

    }
}
