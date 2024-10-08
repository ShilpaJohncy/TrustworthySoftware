package trustworthy.analyser.calculators;

import org.json.JSONException;
import trustworthy.analyser.utils.Product;

import java.io.IOException;

import static trustworthy.analyser.helpers.cvss.CalculateCVSSScore.calculateCVSSScore;
import static trustworthy.analyser.helpers.winchecksec.Winchecksec.getWinCheckSecScores;

public class SecurityScore {
    /**
     * Run all the tests for calculating the security of a product
     * @param product - the application to be tested
     */
    public static double runSecurityTests(Product product){
        double securityScore;
        int cvssScore = getCVSSScore(product);
        int wincheckScore = getWeightedWinchecksecScores(product);
        securityScore = cvssScore + wincheckScore;
        product.setSecurityConfidence((product.getSecurityConfidence()/800 )* 100);
        return securityScore;
    }

    /**
     * Calculate a weighted winchecksec score for the given product
     * @param product - The product whose winchecksec
     * @return - the winchecksec score calculated based on the weights assigned
     *         - -1 if the winchecksec did not occur without any issues.
     */
    private static int getWeightedWinchecksecScores(Product product){
        try {
            getWinCheckSecScores(product);
            product.setSecurityConfidence(product.getSecurityConfidence() + 700);
        } catch (IOException | JSONException e) {
            // Return dump value for PoC
            return 72;
        }

        int wincheckScore = 0;
        if(product.isDotNET()){
            wincheckScore += 37;
        }
        else{
            if(product.isAslr()){
                wincheckScore += 19;
            }
            if(product.isNx()){
                wincheckScore += 18;
            }
        }

        if(product.isCfg()){
            wincheckScore += 16;
        }
        if(product.isGs()){
            wincheckScore += 13;
        }
        if(product.isRfg()){
            wincheckScore += 16;
        }
        if(product.isDynamicBase()){
            wincheckScore += 5;
        }
        if(product.isHighEntropyVA()){
            wincheckScore += 3;
        }
        return wincheckScore;
    }

    /**
     * To account for the fact that a high CVSS score indicates more vulnerabilities, and hence lesser security.
     * Since the score is out of 10 for CVSS, calculate 10 - score to return the security value of the product.
     * @param product - The product whose CVSS score is to be calculated.
     * @return - The security score based on the CVSS score
     *         - -1 if the cVSS score could not be calculated.
     */
    private static int getCVSSScore(Product product){
        calculateCVSSScore(product);
        if(product.getCvssScore() > 0){
            return (int) Math.rint(10 - product.getCvssScore());
        }else{
            return -1;
        }
    }
}
