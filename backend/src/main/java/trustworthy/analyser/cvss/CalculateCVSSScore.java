package trustworthy.analyser.cvss;

import trustworthy.analyser.utils.Product;

import java.util.HashMap;
import java.util.Map;

import static trustworthy.analyser.cvss.CVSSSeverity.*;
import static trustworthy.analyser.cvss.ExtractCVSS.extractCVSSScore;
import static trustworthy.analyser.utils.Constants.*;

public class CalculateCVSSScore {

    /**
     * Create a map in which each CVSS severity is the key
     * And the corresponding weight assigned is its value.
     * @return the map created
     */
    private static HashMap<CVSSSeverity, Integer> getWeightedMap(){
        HashMap<CVSSSeverity, Integer> weightedSeverity = new HashMap<>();
        weightedSeverity.put(CRITICAL, CRITICAL_WEIGHTAGE);
        weightedSeverity.put(CVSSSeverity.HIGH, HIGH_WEIGHTAGE);
        weightedSeverity.put(MEDIUM, MEDIUM_WEIGHTAGE);
        weightedSeverity.put(CVSSSeverity.LOW, LOW_WEIGHTAGE);
        return weightedSeverity;
    }

    /**
     * Calculate the weighted CVSS score for the given Product
     * @param product - The product who's CVSS score is to be calculated
     * If the CVSS score could not be extracted, returns -1.
     */
    public static void calculateCVSSScore(Product product){
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
            product.setCvssScore(weightedTotal/10);
        }else{
            product.setCvssScore(-1);
        }
    }
}
