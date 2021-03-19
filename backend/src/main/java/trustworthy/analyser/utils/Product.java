package trustworthy.analyser.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static trustworthy.analyser.utils.Constants.*;

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
    private int security = SECURITY_WEIGHTAGE;
    private int safety = SAFETY_WEIGHTAGE;
    private int availability = AVAILABILITY_WEIGHTAGE;
    private int resiliency = RESILIENCE_WEIGHTAGE;
    private int reliability = RELIABILITY_WEIGHTAGE;

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

}
