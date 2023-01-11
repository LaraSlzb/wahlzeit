package org.wahlzeit.model.landscape;

import org.junit.Test;

import static org.junit.Assert.*;

public class LandscapeTypeTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor(){
        String value1 = "abc";

        LandscapeType landscapeType = new LandscapeType(value1);
        assertEquals(value1, landscapeType.getValue());

        new LandscapeType(null);
    }

    @Test
    public void testsuperTypeGetSetAndIsSubType(){
        LandscapeType landscapeType = new LandscapeType("test");
        LandscapeType supertype = new LandscapeType("super");

        assertNull(landscapeType.getSuperType());
        assertFalse(landscapeType.isSubtype());

        landscapeType.setSuperType(supertype);
        assertEquals(supertype, landscapeType.getSuperType());
        assertTrue(landscapeType.isSubtype());
    }

    @Test
    public void testSubTypeAddAndGet(){
        LandscapeType landscapeType = new LandscapeType("test");
        assertFalse(landscapeType.getSubTypeIterator().hasNext());

        LandscapeType subType = new LandscapeType("subType");
        landscapeType.addSubType(subType);
        assertTrue(landscapeType.getSubTypeIterator().hasNext());
        assertEquals(subType, landscapeType.getSubTypeIterator().next());
    }

    @Test
    public void testInstanceCreateAndHas(){
        LandscapeType landscapeType = new LandscapeType("test");
        Landscape landscape = new Landscape(new LandscapeType("landscape"));
        assertFalse(landscapeType.hasInstance(landscape));

        Landscape landscapeInstance = landscapeType.createInstance();
        assertEquals(landscapeType, landscapeInstance.getLandscapeType());
        assertTrue(landscapeType.hasInstance(landscapeInstance));
    }
}
