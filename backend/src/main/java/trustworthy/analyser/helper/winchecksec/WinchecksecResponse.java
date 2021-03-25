package trustworthy.analyser.helper.winchecksec;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WinchecksecResponse {
    private String path;
    private WinchecksecEntities mitigations;
}
