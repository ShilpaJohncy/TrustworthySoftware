package trustworthy.analyser.helpers.winchecksec;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WinchecksecEntities {
    private MitigationEntityFields dynamicBase;
    private MitigationEntityFields aslr;
    private MitigationEntityFields highEntropyVA;
    private MitigationEntityFields forceIntegrity;
    private MitigationEntityFields isolation;
    private MitigationEntityFields nx;
    private MitigationEntityFields seh;
    private MitigationEntityFields cfg;
    private MitigationEntityFields rfg;
    private MitigationEntityFields safeSEH;
    private MitigationEntityFields gs;
    private MitigationEntityFields authenticode;
    private MitigationEntityFields dotNET;
}
