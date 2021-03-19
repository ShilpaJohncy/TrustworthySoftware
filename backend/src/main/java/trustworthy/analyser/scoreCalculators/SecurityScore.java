package trustworthy.analyser.scoreCalculators;

import org.json.JSONException;
import trustworthy.analyser.utils.Product;

import java.io.IOException;

import static trustworthy.analyser.cvss.CalculateCVSSScore.calculateCVSSScore;
import static trustworthy.analyser.winchecksec.Winchecksec.getWinCheckSecScores;

public class SecurityScore {
    /**
     * Run all the tests for calculating the security of a product
     * @param product - the application to be tested
     */
    //TODO: To deal with the CVSS score and/or winchecksec score of -1.
    public static double runSecurityTests(Product product){
        double securityScore;
        int cvssScore = getCVSSScore(product);
        int wincheckScore = getWeightedWinchecksecScores(product);
        securityScore = cvssScore + wincheckScore;
        securityScore = securityScore/100;
        securityScore *= product.getSecurity();
        return (Math.round( securityScore * 100.0 ) / 100.0);
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
        } catch (IOException | JSONException e) {
            return -1;
        }

        int wincheckScore = 0;
        if(product.isDotNET()){
            wincheckScore += 40;
        }
        else{
            if(product.isAslr()){
                wincheckScore += 23;
            }
            if(product.isNx()){
                wincheckScore += 17;
            }
        }

        if(product.isCfg()){
            wincheckScore += 15;
        }
        if(product.isGs()){
            wincheckScore += 15;
        }
        if(product.isRfg()){
            wincheckScore += 10;
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
