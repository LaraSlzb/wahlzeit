package org.wahlzeit.model.landscape;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LandscapeTest {
    @Test
    public void testLandscapeTypeGetAndSet(){
        String landscapeTypeValue1 = "xxx";
        String landscapeTypeValue2 = "xyz";
        LandscapeType landscapeType1 = new LandscapeType(landscapeTypeValue1);
        LandscapeType landscapeType2 = new LandscapeType(landscapeTypeValue2);
        Landscape landscape = new Landscape(landscapeType1);

        assertEquals(landscapeType1, landscape.getLandscapeType());

        landscape.setLandscapeType(landscapeType2);

        assertEquals(landscapeType2, landscape.getLandscapeType());
    }

    @Test
    public void testId(){
        Landscape landscape = new Landscape(new LandscapeType("test"));

        assertNotNull(landscape.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNullArgument(){
        new Landscape(null);
    }
}
