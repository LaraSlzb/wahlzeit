package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    double e = 0.00001;
    @Test
    public void testGet(){
        double x = -5;
        double y = 0;
        double z = 3.45;
        Coordinate coordinate = new Coordinate(x, y, z);

        assertEquals(x, coordinate.getX(), e);
        assertEquals(y, coordinate.getY(), e);
        assertEquals(z, coordinate.getZ(), e);
    }

    @Test
    public void testGetDistance(){
        Coordinate coordinate1 = new Coordinate(0,0,0);
        Coordinate coordinate2 = new Coordinate(0, 3, -4);
        Coordinate coordinate3 = new Coordinate(1.2, 4.3, 3.4);
        Coordinate coordinate4 = new Coordinate(1.2,0.3,0.4);
        Coordinate coordinate5 = new Coordinate(0, 0, Double.MAX_VALUE);
        Coordinate coordinate6 = new Coordinate(0, 0, Double.MIN_VALUE);

        assertEquals(0, coordinate1.getDistance(coordinate1), e);
        assertEquals(0, coordinate2.getDistance(coordinate2), e);
        assertEquals(0, coordinate3.getDistance(coordinate3), e);
        assertEquals(0, coordinate4.getDistance(coordinate4), e);
        assertEquals(0, coordinate5.getDistance(coordinate5), e);

        assertEquals(5, coordinate1.getDistance(coordinate2), e);
        assertEquals(5, coordinate3.getDistance(coordinate4), e);
        assertEquals(Double.POSITIVE_INFINITY, coordinate5.getDistance(coordinate6), e);
        assertEquals(Double.POSITIVE_INFINITY, coordinate2.getDistance(coordinate5), e);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDistanceWithNullArgument(){
        Coordinate coordinate = new Coordinate(0, 0, 0);
        coordinate.getDistance(null);
    }

    @Test
    public void testIsEqual() {
        Coordinate coordinate0 = new Coordinate(0, 0, 0);
        Coordinate coordinate1 = new Coordinate(0, 0, 0);
        Coordinate coordinate2 = new Coordinate(0, 3, -4);
        Coordinate coordinate3 = new Coordinate(1.2, 4.3, 3.4);
        Coordinate coordinate4 = new Coordinate(1.2, 0.3, 0.4);
        Coordinate coordinate5 = new Coordinate(0, 0, Double.MAX_VALUE);
        Coordinate coordinate6 = new Coordinate(0, 0, Double.MIN_VALUE);

        assertTrue(coordinate0.isEqual(coordinate1));
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
    public void testEqualsAndHashCode(){
        Coordinate coordinate1 = new Coordinate(0, 0, 0);
        Coordinate coordinate2 = new Coordinate(0, 3, -4);
        Coordinate coordinate3 = new Coordinate(0, 3, -4);

        assertTrue(coordinate1.equals(coordinate1));
        assertTrue(coordinate2.equals(coordinate3));
        assertEquals(coordinate2.hashCode(), coordinate3.hashCode());
        assertFalse(coordinate1.equals(coordinate2));
        assertFalse(coordinate1.equals(null));
        assertFalse(coordinate1.equals(new Object()));
    }
}
