package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void testLocation(){
        double x = -1;
        double y = 5;
        double z = 3.2;
        int id = 0;
        Location location = new Location(x, y, z, id);

        Coordinate coordinate = location.getCoordinate();
        assertNotNull(coordinate);
        assertEquals(x, coordinate.getX(),0);
        assertEquals(y, coordinate.getY(), 0);
        assertEquals(z, coordinate.getZ(), 0);

        x = 1;
        y = 2;
        z = 3;
        location.setCoordinate(x, y, z);

        coordinate = location.getCoordinate();
        assertNotNull(coordinate);
        assertEquals(x, coordinate.getX(),0);
        assertEquals(y, coordinate.getY(), 0);
        assertEquals(z, coordinate.getZ(), 0);
    }
}
