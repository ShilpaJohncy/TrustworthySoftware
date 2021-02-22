package trustworthy.software.winchecksec;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseJson {
    private String path;
    private ResponseEntities mitigations;
}
