package org.wahlzeit.model;

import org.junit.Test;
import org.wahlzeit.model.coordinates.CartesianCoordinate;
import org.wahlzeit.model.coordinates.Coordinate;
import org.wahlzeit.model.coordinates.CoordinateManager;
import org.wahlzeit.model.coordinates.SpericCoordinate;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void testLocationCoordinate(){
        double x = -1;
        double y = 5;
        double z = 3.2;
        int id = 0;
        CartesianCoordinate coordinate1 = CoordinateManager.getCartesianCoordinate(x, y, z);
        Location location = new Location(coordinate1, id);

        Coordinate coordinate2 = location.getCoordinate();
        assertNotNull(coordinate2);
        assertEquals(coordinate1, coordinate2);

        double latitude = 1.5;
        double longitude = 2;
        double radius = 90;

        SpericCoordinate spericCoordinate1 = CoordinateManager.getSpericCoordinate(latitude, longitude, radius);
        location.setCoordinate(spericCoordinate1);

        Coordinate spericCoordinate2 = location.getCoordinate();
        assertNotNull(spericCoordinate2);
        assertEquals(spericCoordinate1, spericCoordinate2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorThrowsExceptionWhenCoordinateIsNull(){
        new Location(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCoordinateThrowsExceptionWhenCoordinateIsNull(){
        CartesianCoordinate coordinate = CoordinateManager.getCartesianCoordinate(1,2,3);
        Location location = new Location(coordinate, 1);

        location.setCoordinate(null);
    }
}
