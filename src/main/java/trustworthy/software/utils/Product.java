package trustworthy.software.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product {

    private String vendorName;
    private String productName;
    private String versionNo = "";
    private String executablePath;
    private boolean isParallelize = false;
    private boolean checkNaive = true;

    private double CVSSScore;
    private double securityScore;
    private double safetyScore;
    private double availabilityScore;
    private double trustworthyScore;
    private String verdict;



}
