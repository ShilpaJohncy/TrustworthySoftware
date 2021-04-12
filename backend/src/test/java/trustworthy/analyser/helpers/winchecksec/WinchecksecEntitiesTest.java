package trustworthy.analyser.helpers.winchecksec;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WinchecksecEntitiesTest {

    @Test
    public void setDynamicBaseTest(){
        WinchecksecEntities entities = new WinchecksecEntities();
        MitigationEntityFields field = new MitigationEntityFields();
        field.setDescription("Dynamic base ");
        field.setPresence("Not present");
        entities.setDynamicBase(field);
        assertEquals("Dynamic base ", entities.getDynamicBase().getDescription());
    }

    @Test
    public void setAslrTest(){
        WinchecksecEntities entities = new WinchecksecEntities();
        MitigationEntityFields field = new MitigationEntityFields();
        field.setDescription("ASLR ");
        field.setPresence("Not present");
        entities.setAslr(field);
        assertEquals("ASLR ", entities.getAslr().getDescription());
    }
    @Test
    public void setHighEntropyVATest(){
        WinchecksecEntities entities = new WinchecksecEntities();
        MitigationEntityFields field = new MitigationEntityFields();
        field.setDescription("High Entropy VA ");
        field.setPresence("Not present");
        entities.setHighEntropyVA(field);
        assertEquals("High Entropy VA ", entities.getHighEntropyVA().getDescription());
    }
    @Test
    public void setForceIntegrityTest(){
        WinchecksecEntities entities = new WinchecksecEntities();
        MitigationEntityFields field = new MitigationEntityFields();
        field.setDescription("Force Integrity ");
        field.setPresence("Not present");
        entities.setForceIntegrity(field);
        assertEquals("Force Integrity ", entities.getForceIntegrity().getDescription());
    }

    @Test
    public void setNXTest(){
        WinchecksecEntities entities = new WinchecksecEntities();
        MitigationEntityFields field = new MitigationEntityFields();
        field.setDescription("NX ");
        field.setPresence("Not present");
        entities.setNx(field);
        assertEquals("NX ", entities.getNx().getDescription());
    }
    @Test
    public void setSehTest(){
        WinchecksecEntities entities = new WinchecksecEntities();
        MitigationEntityFields field = new MitigationEntityFields();
        field.setDescription("SEH ");
        field.setPresence("Not present");
        entities.setSeh(field);
        assertEquals("SEH ", entities.getSeh().getDescription());
    }
    @Test
    public void setSafeSehTest(){
        WinchecksecEntities entities = new WinchecksecEntities();
        MitigationEntityFields field = new MitigationEntityFields();
        field.setDescription("Safe Seh ");
        field.setPresence("Not present");
        entities.setSafeSEH(field);
        assertEquals("Safe Seh ", entities.getSafeSEH().getDescription());
    }
    @Test
    public void setCfgTest(){
        WinchecksecEntities entities = new WinchecksecEntities();
        MitigationEntityFields field = new MitigationEntityFields();
        field.setDescription("CFG ");
        field.setPresence("Not present");
        entities.setCfg(field);
        assertEquals("CFG ", entities.getCfg().getDescription());
    }
    @Test
    public void setRfgTest(){
        WinchecksecEntities entities = new WinchecksecEntities();
        MitigationEntityFields field = new MitigationEntityFields();
        field.setDescription("RFG ");
        field.setPresence("Not present");
        entities.setRfg(field);
        assertEquals("RFG ", entities.getRfg().getDescription());
    }
    @Test
    public void setGsTest(){
        WinchecksecEntities entities = new WinchecksecEntities();
        MitigationEntityFields field = new MitigationEntityFields();
        field.setDescription("GS ");
        field.setPresence("Not present");
        entities.setGs(field);
        assertEquals("GS ", entities.getGs().getDescription());
    }
    @Test
    public void setAuthenticodeTest(){
        WinchecksecEntities entities = new WinchecksecEntities();
        MitigationEntityFields field = new MitigationEntityFields();
        field.setDescription("Authenticode ");
        field.setPresence("Not present");
        entities.setAuthenticode(field);
        assertEquals("Authenticode ", entities.getAuthenticode().getDescription());
    }
    @Test
    public void setDotNetTest(){
        WinchecksecEntities entities = new WinchecksecEntities();
        MitigationEntityFields field = new MitigationEntityFields();
        field.setDescription("Dot Net ");
        field.setPresence("Not present");
        entities.setDotNET(field);
        assertEquals("Dot Net ", entities.getDotNET().getDescription());
    }

}