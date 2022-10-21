package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void testLocationNull() {
        Location location = new Location(null);
        assertNotNull(location.getCoordinate());
    }

    @Test
    public void testLocationWithCoordinate(){
        Coordinate coordinate = new Coordinate(-1,5,3.2);
        Location location = new Location(coordinate);

        assertEquals(coordinate, location.getCoordinate());

        Coordinate newCoordinate = new Coordinate(1,2, 3);
        location.setCoordinate(newCoordinate);

        assertEquals(newCoordinate, location.getCoordinate());
    }
}
