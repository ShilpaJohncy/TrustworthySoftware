package trustworthy.software.cvss;

import trustworthy.software.utils.Product;

import java.util.HashMap;
import java.util.Map;

import static trustworthy.software.cvss.CVSSSeverity.*;
import static trustworthy.software.cvss.ExtractCVSS.extractCVSSScore;
import static trustworthy.software.utils.Constants.*;

public class CalculateCVSSScore {

    /**
     * Create a map in which each CVSS severity is the key
     * And the corresponding weight assigned is its value.
     * @return the map created
     */
    private static HashMap<CVSSSeverity, Integer> getWeightedMap(){
        HashMap<CVSSSeverity, Integer> weightedSeverity = new HashMap<>();
        weightedSeverity.put(CRITICAL, CRITICAL_WEIGHTAGE);
        weightedSeverity.put(HIGH, HIGH_WEIGHTAGE);
        weightedSeverity.put(MEDIUM, MEDIUM_WEIGHTAGE);
        weightedSeverity.put(LOW, LOW_WEIGHTAGE);
        return weightedSeverity;
    }

    /**
     * Calculate the weighted CVSS score for the given Product
     * @param product - The product who's CVSS score is to be calculated.
     * @return - The weighted CVSS score. If the CVSS score could no tbe extracted, returns -1.
     */
    public static double calculateCVSSScore(Product product){
        HashMap<CVSSSeverity, Integer> cvssScore = extractCVSSScore(product.getVendorName(), product.getProductName(), product.getVersionNo());
        if(cvssScore != null){
            HashMap<CVSSSeverity, Integer> weightedSeverity = getWeightedMap();
            double weightedTotal = 0;
            int totalVulnerabilityCount = 0;

            for(Map.Entry<CVSSSeverity, Integer> entry : cvssScore.entrySet()){
                weightedTotal += (entry.getValue() * weightedSeverity.get(entry.getKey()));
                totalVulnerabilityCount += entry.getValue();
            }
            weightedTotal = (weightedTotal/(totalVulnerabilityCount * CRITICAL_WEIGHTAGE)) * 100;
            return weightedTotal/10;
        }
        return -1;
    }
}
