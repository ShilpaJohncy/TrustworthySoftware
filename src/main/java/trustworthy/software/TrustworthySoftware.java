package trustworthy.software;

import trustworthy.software.utils.Product;

import static trustworthy.software.cvss.CalculateCVSSScore.calculateCVSSScore;
import static trustworthy.software.utils.Constants.*;

public class TrustworthySoftware {
    public static void main(String[] args){

        Product product1 = new Product();
        product1.setExecutablePath(NOTEPAD_EXE);
        product1.setParallelize(true);


//        getManalyzeReport(product);
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
