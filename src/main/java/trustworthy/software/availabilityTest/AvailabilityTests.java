package trustworthy.software.availabilityTest;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static trustworthy.software.utils.Constants.*;

public class AvailabilityTests{

    /**
     * Runs the test to check availability using a naive equation
     *
     * If the number of successful runs from executing the product is > 50% of the no of tries, it is available
     * If its = 50%, inconclusive
     * And if its < 50%, the result is inconclusive
     *
     * @param prodExePath - The product who's availability is to be tested
     */
    public static void runAvailabilityTest(String prodExePath) throws InterruptedException {
        RunnableImplementation runnable = new RunnableImplementation(prodExePath);

        Thread[] threads = new Thread[NO_OF_TRIES];
        for(int threadCount = 0; threadCount < NO_OF_TRIES; threadCount++){
            Thread thread = new Thread(runnable);
            thread.start();
            threads[threadCount] = thread;
        }
        // Wait for all threads to be closed
        for(Thread thread: threads){
            thread.join();
        }

        if (runnable.getSuccessfulRuns() > ((NO_OF_TRIES/2) + 1))
            System.out.println("Available");
        else if(runnable.getSuccessfulRuns() == ((NO_OF_TRIES/2) + 1))
            System.out.println("Inconclusive");
        else
            System.out.println("Not available");
    }

    /**
     * Function that executes the program once
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
            if(process == null | process.waitFor(NAIVE_TIMEOUT, TimeUnit.MILLISECONDS)){
                return false;
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return false;
        }

        // Reaches here if the process ran without any problem
        // Get the PID of the process and kill it
        long pid = process.pid();

        Optional<ProcessHandle> optionalProcessHandle = ProcessHandle.of(pid);
        optionalProcessHandle.ifPresent(processHandle -> processHandle.destroy());

        if (process.isAlive()) {
            process.destroyForcibly();
        }
        return true;
    }

}
