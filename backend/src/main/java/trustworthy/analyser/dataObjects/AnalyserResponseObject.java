package trustworthy.analyser.dataObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalyserResponseObject {
    private double securityScore;
    private double safetyScore;
    private double availabilityScore;
    private double resilienceScore;
    private double reliabilityScore;
    private int trustworthyScore;
    private String verdict;
}
