package trustworthy.analyser.utils;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RunnableImplementationTest {

    @Test
    public void setFields(){
        RunnableImplementation implementation = new RunnableImplementation("Some path");
        assertEquals("Some path", implementation.getProductExePath());
        implementation.setProductExePath("New path");
        assertEquals("New path", implementation.getProductExePath());
        implementation.setSuccessfulRuns(2);
        assertEquals(2, implementation.getSuccessfulRuns());
    }
}