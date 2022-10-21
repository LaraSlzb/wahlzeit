package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void testLocation(){
        Location location = new Location(-1, 5, 3.2);
        assertNotNull(location.getCoordinate());

        location.setCoordinate(1, 2, 3);
        assertNotNull(location.getCoordinate());
    }
}
