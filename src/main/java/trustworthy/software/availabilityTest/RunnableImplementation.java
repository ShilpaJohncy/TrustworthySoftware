package trustworthy.software.availabilityTest;

import lombok.Getter;
import lombok.Setter;

import static trustworthy.software.availabilityTest.AvailabilityTests.runExecutable;

@Getter
@Setter
public class RunnableImplementation implements Runnable {

    private int successfulRuns = 0;
    private String productExePath;

    public RunnableImplementation(String prodExePath) {
        this.productExePath = prodExePath;
    }

    @Override
    public void run() {
        if(runExecutable(productExePath)){
            successfulRuns++;
        }
    }
}
