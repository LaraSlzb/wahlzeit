package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void testGet(){
        double x = -5;
        double y = 0;
        double z = 3.45;
        Coordinate coordinate = new Coordinate(x, y, z);

        assertEquals(x, coordinate.getX(), 0);
        assertEquals(y, coordinate.getY(), 0);
        assertEquals(z, coordinate.getZ(), 0);
    }

    @Test
    public void testGetDistance(){
        Coordinate coordinate1 = new Coordinate(0,0,0);
        Coordinate coordinate2 = new Coordinate(0, 3, -4);
        Coordinate coordinate3 = new Coordinate(1.2, 4.3, 3.4);
        Coordinate coordinate4 = new Coordinate(1.2,0.3,0.4);
        Coordinate coordinate5 = new Coordinate(0, 0, Double.MAX_VALUE);
        Coordinate coordinate6 = new Coordinate(0, 0, Double.MIN_VALUE);

        assertEquals(0, coordinate1.getDistance(coordinate1), 0);
        assertEquals(0, coordinate2.getDistance(coordinate2), 0);
        assertEquals(0, coordinate3.getDistance(coordinate3), 0);
        assertEquals(0, coordinate4.getDistance(coordinate4), 0);
        assertEquals(0, coordinate5.getDistance(coordinate5), 0);

        assertEquals(5, coordinate1.getDistance(coordinate2), 0);
        assertEquals(5, coordinate3.getDistance(coordinate4), 0);
        assertEquals(Double.POSITIVE_INFINITY, coordinate5.getDistance(coordinate6), 0);
        assertEquals(Double.POSITIVE_INFINITY, coordinate2.getDistance(coordinate5), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDistanceWithNullArgument(){
        Coordinate coordinate = new Coordinate(0, 0, 0);
        coordinate.getDistance(null);
    }

    @Test
    public void testIsEqual() {
        Coordinate coordinate1 = new Coordinate(0, 0, 0);
        Coordinate coordinate2 = new Coordinate(0, 3, -4);
        Coordinate coordinate3 = new Coordinate(1.2, 4.3, 3.4);
        Coordinate coordinate4 = new Coordinate(1.2, 0.3, 0.4);
        Coordinate coordinate5 = new Coordinate(0, 0, Double.MAX_VALUE);
        Coordinate coordinate6 = new Coordinate(0, 0, Double.MIN_VALUE);

        assertTrue(coordinate1.isEqual(coordinate1));
        assertTrue(coordinate2.isEqual(coordinate2));
        assertTrue(coordinate3.isEqual(coordinate3));
        assertTrue(coordinate4.isEqual(coordinate4));
        assertTrue(coordinate5.isEqual(coordinate5));
        assertTrue(coordinate6.isEqual(coordinate6));

        assertFalse(coordinate1.isEqual(coordinate2));
        assertFalse(coordinate3.isEqual(coordinate4));
        assertFalse(coordinate5.isEqual(coordinate6));
        assertFalse(coordinate2.isEqual(coordinate5));
        assertFalse(coordinate1.isEqual(null));
    }

    @Test
    public void testEqual(){
        Coordinate coordinate1 = new Coordinate(0, 0, 0);
        Coordinate coordinate2 = new Coordinate(0, 3, -4);

        assertTrue(coordinate1.equals(coordinate1));
        assertFalse(coordinate1.equals(coordinate2));
        assertFalse(coordinate1.equals(null));
        assertFalse(coordinate1.equals(new Object()));
    }
}
