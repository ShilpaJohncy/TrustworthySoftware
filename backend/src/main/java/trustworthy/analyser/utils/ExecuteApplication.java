package trustworthy.analyser.utils;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static trustworthy.analyser.utils.Constants.*;

public class ExecuteApplication {

    /**
     * Depending on the input runs parallel or serial execution of the app to calculate its successful run count.
     *
     * @param product - The product who's availability is to be tested
     * @return successfulRuns - The number of runs the application ran without any failures
     */
    public static void executeApplication(Product product) {
        if(!product.isExecutedApp()){
            int successfulRuns;
            if(!product.isParallelize()){
                successfulRuns = serialExecutionTest(product);
            }else{
                try {
                    successfulRuns = parallelExecutionTest(product);
                } catch (InterruptedException e) {
                    successfulRuns = 0;
                }
            }
            product.setSuccessfulRuns(successfulRuns);
            product.setExecutedApp(true);
        }
    }

    /**
     * This function is called if the product is expected to run in parallel.
     *
     * @param product - The product who's availability is to be tested.
     * @throws InterruptedException - When the thread is interrupted.
     * @return the number of successful runs
     */
    private static int parallelExecutionTest(Product product) throws InterruptedException {
        RunnableImplementation runnable = new RunnableImplementation(product.getExecutablePath());
        Thread[] threads = new Thread[NO_OF_TRIES];
        for(int threadCount = 0; threadCount < NO_OF_TRIES; threadCount++){
            Thread thread = new Thread(runnable);
            thread.start();
            threads[threadCount]=thread;
        }
        //Wait for all threads to be closed
        for(Thread thread:threads){
            thread.join();
        }
        return runnable.getSuccessfulRuns();
    }

    /**
     * This function is called when the product is not expected to run in parallel.
     *
     * @param product - The product who's availability is to be tested.
     * @return the number of successful runs
     */
    private static int serialExecutionTest(Product product){
        int successfulRuns = 0;
        for(int runCount = 1; runCount <= NO_OF_TRIES; runCount++){
            if(runExecutable(product.getExecutablePath())){
                successfulRuns++;
            }
        }
        return successfulRuns;
    }

    /**
     * Function that executes the program once.
     *
     * @param productExePath - The path to the executable of the product to be tested.
     * @return {@code true} if the process ran without any issues and {@code false} if
     *              the process was unable to run for the set timeout time/ if the process got interrupted.
     */
    public static boolean runExecutable(String productExePath) {
        ProcessBuilder builder = new ProcessBuilder(productExePath);
        Process process;
        try {
            // Start the process
            process = builder.start();

            // If the process is null or if it didn't stay alive for the timeout period, it is not available
            if(process == null || process.waitFor(NAIVE_TIMEOUT, TimeUnit.MILLISECONDS)){
                killProcess(process);
                return false;
            }

        } catch (InterruptedException | IOException e) {
            return false;
        }

        // Reaches here if the process ran without any problem
        // Get the PID of the process and kill it
        killProcess(process);
        return true;
    }

    /**
     * Private function to ensure that the process and its children are killed.
     *
     * @param process - The process to be killed
     */
    private static void killProcess(Process process){
        if(process != null){
            long pid = process.pid();
            Optional<ProcessHandle> optionalProcessHandle = ProcessHandle.of(pid);
            optionalProcessHandle.ifPresent(ProcessHandle::destroy);
            if (process.isAlive()) {
                process.destroyForcibly();
            }
        }
    }
}
