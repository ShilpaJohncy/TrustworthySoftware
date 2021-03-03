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

    private boolean ranWinchecksec = false;

    private double cvssScore;
    private double manalyzeScore;
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

    private double securityScore;
    private double safetyScore;
    private double availabilityScore;
    private int trustworthyScore;
    private String verdict;

}
