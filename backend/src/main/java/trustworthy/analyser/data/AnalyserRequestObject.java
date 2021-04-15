package trustworthy.analyser.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class AnalyserRequestObject {
    @NonNull private String exe;
    @NonNull private String vendor;
    @NonNull private String product;
    private String version;
    private boolean isParallelize;
    private int security;
    private int safety;
    private int availability;
    private int resiliency;
    private int reliability;
}
