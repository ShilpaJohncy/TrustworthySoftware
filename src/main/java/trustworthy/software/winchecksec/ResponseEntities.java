package trustworthy.software.winchecksec;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseEntities {
    private ResponseEntityFields dynamicBase;
    private ResponseEntityFields aslr;
    private ResponseEntityFields highEntropyVA;
    private ResponseEntityFields forceIntegrity;
    private ResponseEntityFields isolation;
    private ResponseEntityFields nx;
    private ResponseEntityFields seh;
    private ResponseEntityFields cfg;
    private ResponseEntityFields rfg;
    private ResponseEntityFields safeSEH;
    private ResponseEntityFields gs;
    private ResponseEntityFields authenticode;
    private ResponseEntityFields dotNET;

}
