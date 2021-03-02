package trustworthy.software;

import trustworthy.software.utils.Product;

import static trustworthy.software.safetyTest.calculateSafetyScore.runSafetyTests;
import static trustworthy.software.securityTest.calculateSecurityScore.runSecurityTests;
import static trustworthy.software.utils.Constants.*;

public class TrustworthySoftware {
    public static void main(String[] args){

        Product product = new Product();
        product.setExecutablePath(ZOOM_EXE);
        product.setVendorName("Zoom");
        product.setProductName("zoom");
        product.setParallelize(false);

        runSecurityTests(product);
        runSafetyTests(product);
//        try {
//            runAvailabilityTest(product);
//            runAvailabilityTest(product1);
//            getWinCheckSecScores(NOTEPAD_EXE);
//
//        } catch (IOException | JSONException |InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
