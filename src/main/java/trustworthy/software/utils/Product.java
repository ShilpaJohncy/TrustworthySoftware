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

    private double cvssScore;
    private boolean dynamicBase;
    private boolean aslr;
    private boolean highEntropyVA;
    private boolean forceIntegrity;
    private boolean isolation;
    private boolean nx;
    private boolean seh;
    private boolean cfg;
    private boolean rfg;
    private boolean safeSEH;
    private boolean gs;
    private boolean authenticode;
    private boolean dotNET;

    private int securityScore;
    private int safetyScore;
    private int availabilityScore;
    private int trustworthyScore;
    private String verdict;

}
