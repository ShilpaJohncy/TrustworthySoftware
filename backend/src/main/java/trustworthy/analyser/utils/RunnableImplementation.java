package trustworthy.analyser.utils;

import lombok.Getter;
import lombok.Setter;

import static trustworthy.analyser.utils.ExecuteApplication.runExecutable;

@Getter
@Setter
/**
 * For implementation of multiple threads.
 */
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
