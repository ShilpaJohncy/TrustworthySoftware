package trustworthy.analyser.helpers.winchecksec;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MitigationEntityFieldsTest {

    @Test
    public void testSetFields(){
        MitigationEntityFields fields = new MitigationEntityFields();
        fields.setDescription("Mock desc");
        fields.setPresence("Present");
        assertEquals("Mock desc", fields.getDescription());
        assertEquals("Present", fields.getPresence());
    }
}