package org.wahlzeit.model.landscape;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.wahlzeit.utils.CustomAsserts.assertNotNull;

public class LandscapeManagerTest {
    LandscapeManager manager = LandscapeManager.getInstance();


    @Test
    public void testCreateLandscape(){
        String typeNew = "typeTest";

        Landscape landscape = manager.createLandscape(typeNew);
        assertNotNull(landscape);
        assertEquals(landscape, manager.getLandscape(landscape.getId()));
        assertEquals(typeNew, manager.getLandscapeType(typeNew).getValue());
        assertEquals(typeNew, landscape.getLandscapeType().getValue());

        String typeNature = LandscapeTypesValues.NATURE.toString();
        assertNotNull(manager.getLandscapeType(typeNature));
        Landscape landscapeNature = manager.createLandscape(typeNature);
        assertNotNull(landscapeNature);
        assertEquals(landscapeNature, manager.getLandscape(landscapeNature.getId()));
        assertEquals(typeNature, landscapeNature.getLandscapeType().getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateLandscapeWithNullArgument(){
        LandscapeManager.getInstance().createLandscape(null);
    }

    @Test
    public void testCreateLandscapeType(){
        String type = "test";
        String type1 = "sub";

        LandscapeType landscapeType = manager.createLandscapeType(type, null);

        assertNotNull(landscapeType);
        assertNotNull(landscapeType.getSuperType());
        assertNotNull(manager.getLandscapeType(type));

        LandscapeType landscapeType1 = manager.createLandscapeType(type1, type);
        assertNotNull(landscapeType1);
        assertEquals(landscapeType, landscapeType1.getSuperType());
        assertNotNull(manager.getLandscapeType(type1));
    }

    @Test
    public void testInitializeLandscapeTypes(){
        for(LandscapeTypesValues values : LandscapeTypesValues.values()){
            LandscapeType landscapeType = manager.getLandscapeType(values.toString());
            assertNotNull(landscapeType);
            if(values != LandscapeTypesValues.WORLD){
                assertNotNull(landscapeType.getSuperType());
            }
        }
    }
}
