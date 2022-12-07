package org.wahlzeit.model.coordinate;

import org.junit.Test;
import org.wahlzeit.model.coordinates.CartesianCoordinate;
import org.wahlzeit.model.coordinates.CoordinateManager;
import org.wahlzeit.model.coordinates.SpericCoordinate;

import static org.junit.Assert.*;

public class CoordinateManagerTest {
    @Test
    public void testUnifyDouble(){
        assertEquals(0, CoordinateManager.unifyDouble(0), 0);
        assertEquals(0.2345, CoordinateManager.unifyDouble(0.234521), 0);
        assertEquals(-1.0001, CoordinateManager.unifyDouble(-1.00005), 0);
    }



    @Test
    public void testgetSpericCoordinateWithKey(){
        double longitude = 0;
        double latitude = -1.324343434;
        double radius = 1.23;
        String key = CoordinateManager.getKeySperic(latitude, longitude, radius);

        assertNull(CoordinateManager.getSpericCoordinate(key));

        SpericCoordinate coordinate = CoordinateManager.getSpericCoordinate(latitude, longitude, radius);
        assertEquals(coordinate, CoordinateManager.getSpericCoordinate(key));

    }

    @Test
    public void testgetSpericCoordinateWithAttributes(){
        double longitude = 1;
        double latitude = -1.324343434;
        double radius = 1.23;

        assertNotNull(CoordinateManager.getSpericCoordinate(latitude, longitude, radius));
    }

    @Test
    public void testgetCartesianCoordinateWithKey(){
        double x = 0;
        double y = -1.324343434;
        double z = 1.23;
        String key = CoordinateManager.getKeyCartesian(x, y, z);

        assertNull(CoordinateManager.getCartesianCoordinate(key));

        CartesianCoordinate coordinate = CoordinateManager.getCartesianCoordinate(x, y, z);
        assertEquals(coordinate, CoordinateManager.getCartesianCoordinate(key));

    }

    @Test
    public void testgetCartesianCoordinateWithAttributes(){
        double x = 1;
        double y = -1.324343434;
        double z = 1.23;

        assertNotNull(CoordinateManager.getCartesianCoordinate(x, y, z));
    }

}
