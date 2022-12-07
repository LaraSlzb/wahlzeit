package org.wahlzeit.model.coordinate;

import org.junit.Test;
import org.wahlzeit.model.coordinates.CartesianCoordinate;
import org.wahlzeit.model.coordinates.CoordinateManager;
import org.wahlzeit.model.coordinates.SpericCoordinate;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class SpericCoordinateTest {
    double e = 0.0001;
    @Test
    public void testGet(){
        double longitute = 3.45;
        double latitude = -2;
        double radius = 0;
        SpericCoordinate coordinate = CoordinateManager.getSpericCoordinate(latitude, longitute, radius);

        assertEquals(longitute, coordinate.getLongitude(), e);
        assertEquals(latitude, coordinate.getLatitude(), e);
        assertEquals(radius, coordinate.getRadius(), e);
    }

    @Test
    public void testGetDistance(){
        SpericCoordinate coordinate1 =  CoordinateManager.getSpericCoordinate(0,0,0);
        SpericCoordinate coordinate2 =  CoordinateManager.getSpericCoordinate(-3, 3, 4);
        SpericCoordinate coordinate3 =  CoordinateManager.getSpericCoordinate(1.2, 2.3, 3.4);
        SpericCoordinate coordinate4 =  CoordinateManager.getSpericCoordinate(1.2,0.3,0.4);

        assertEquals(0, coordinate1.getCartesianDistance(coordinate1), e);
        assertEquals(0, coordinate2.getCartesianDistance(coordinate2), e);
        assertEquals(0, coordinate3.getCartesianDistance(coordinate3), e);
        assertEquals(0, coordinate4.getCartesianDistance(coordinate4), e);
        assertEquals(0, coordinate1.getCartesianDistance( CoordinateManager.getCartesianCoordinate(0, 0, 0)), e);
        assertNotEquals(0, coordinate1.getCartesianDistance(CoordinateManager.getCartesianCoordinate(1,2,3)),e);
        assertNotEquals(0, coordinate1.getCartesianDistance(coordinate2),e);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDistanceWithNullArgument(){
        SpericCoordinate coordinate = CoordinateManager.getSpericCoordinate(0, 0, 0);
        coordinate.getCartesianDistance(null);
    }

    @Test
    public void testIsEqual() {
        SpericCoordinate coordinate0 = CoordinateManager.getSpericCoordinate(0, 0, 0);
        SpericCoordinate coordinate1 = CoordinateManager.getSpericCoordinate(0, 0, 0);
        SpericCoordinate coordinate2 = CoordinateManager.getSpericCoordinate(0, 3, 4);
        SpericCoordinate coordinate3 = CoordinateManager.getSpericCoordinate(1.2, 2.3, 3.4);
        SpericCoordinate coordinate4 = CoordinateManager.getSpericCoordinate(1.2, 0.3, 0.4);

        //Compares 2 equal spericcoordinates
        assertTrue(coordinate0.isEqual(coordinate1));
        assertTrue(coordinate1.isEqual(coordinate1));
        assertTrue(coordinate2.isEqual(coordinate2));
        assertTrue(coordinate3.isEqual(coordinate3));
        assertTrue(coordinate4.isEqual(coordinate4));


        //compares spericcordinate with equal cartesiancoordinate
        assertTrue(coordinate2.isEqual(coordinate2.asCartesianCoordinate()));
        assertTrue(coordinate3.isEqual(coordinate3.asCartesianCoordinate()));
        assertTrue(coordinate4.isEqual(coordinate4.asCartesianCoordinate()));

        //compares 2 not equal spericcoordinates
        assertFalse(coordinate1.isEqual(coordinate2));
        assertFalse(coordinate3.isEqual(coordinate4));

        //compares spericcordinate with not equal cartesian coordinate
        assertFalse(coordinate1.isEqual(coordinate2.asCartesianCoordinate()));
        assertFalse(coordinate3.isEqual(coordinate4.asCartesianCoordinate()));

        //compares with null
        assertFalse(coordinate1.isEqual(null));
    }

    @Test
    public void testEqualsAndHashCode(){
        SpericCoordinate coordinate1 = CoordinateManager.getSpericCoordinate(0, 0, 0);
        SpericCoordinate coordinate2 = CoordinateManager.getSpericCoordinate(0, 3, 4);
        SpericCoordinate coordinate3 = CoordinateManager.getSpericCoordinate(0, 3, 4);

        assertTrue(coordinate1.equals(coordinate1));
        assertTrue(coordinate2.equals(coordinate3));
        assertEquals(coordinate2.hashCode(), coordinate3.hashCode());


        assertFalse(coordinate1.equals(coordinate2));
        assertFalse(coordinate2.equals( CoordinateManager.getCartesianCoordinate(1,2,3)));
        assertFalse(coordinate1.equals(null));
        assertFalse(coordinate1.equals(new Object()));
    }

    @Test
    public void testAsCartesian(){
        SpericCoordinate coordinate2 =  CoordinateManager.getSpericCoordinate(0, 3, 4);
        SpericCoordinate coordinate3 =  CoordinateManager.getSpericCoordinate(1.2, 2.3, 3.4);
        SpericCoordinate coordinate4 =  CoordinateManager.getSpericCoordinate(1.2, 0.3, 0.4);

        assertEquals(coordinate2, coordinate2.asCartesianCoordinate());
        assertEquals(coordinate3,coordinate3.asCartesianCoordinate());
        assertEquals(coordinate4, coordinate4.asCartesianCoordinate());
    }

    @Test
    public void testAsSperic(){
        SpericCoordinate coordinate1 = CoordinateManager.getSpericCoordinate(0, 0, 0);
        SpericCoordinate coordinate2 = CoordinateManager.getSpericCoordinate(0, 3, 4);
        SpericCoordinate coordinate3 = CoordinateManager.getSpericCoordinate(1.2, 4.3, 3.4);
        SpericCoordinate coordinate4 = CoordinateManager.getSpericCoordinate(1.2, 0.3, 0.4);
        SpericCoordinate coordinate5 = CoordinateManager.getSpericCoordinate(0, 0, Double.MAX_VALUE);
        SpericCoordinate coordinate6 = CoordinateManager.getSpericCoordinate(0, 0, Double.MIN_VALUE);

        assertEquals(coordinate1, coordinate1.asSpericCoordinate());
        assertEquals(coordinate2, coordinate2.asSpericCoordinate());
        assertEquals(coordinate3, coordinate3.asSpericCoordinate());
        assertEquals(coordinate4, coordinate4.asSpericCoordinate());
        assertEquals(coordinate5, coordinate5.asSpericCoordinate());
        assertEquals(coordinate6, coordinate6.asSpericCoordinate());
    }

    @Test
    public void testGetCentralAngel(){
        SpericCoordinate coordinate2 =  CoordinateManager.getSpericCoordinate(0, 3, 4);
        SpericCoordinate coordinate3 =  CoordinateManager.getSpericCoordinate(1.2, 2.3, 4);
        SpericCoordinate coordinate4 =  CoordinateManager.getSpericCoordinate(1.2, 0.3, 0.4);

        assertEquals(0, coordinate2.getCentralAngle(coordinate2), e);
        assertEquals(0, coordinate3.getCentralAngle(coordinate3), e);
        assertEquals(0, coordinate4.getCentralAngle(coordinate4), e);

        assertEquals(0, coordinate2.getCentralAngle(coordinate2.asCartesianCoordinate()), e);
        assertEquals(0, coordinate3.getCentralAngle(coordinate3.asCartesianCoordinate()), e);
        assertEquals(0, coordinate4.getCentralAngle(coordinate4.asCartesianCoordinate()), e);

        assertNotEquals(0, coordinate2.getCentralAngle(coordinate3), e);
        assertNotEquals(0, coordinate2.getCentralAngle(coordinate3.asCartesianCoordinate()), e);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCentralAngelWithDifferentRadius(){
        SpericCoordinate coordinate3 = CoordinateManager.getSpericCoordinate(1.2, 4.3, 4);
        SpericCoordinate coordinate4 = CoordinateManager.getSpericCoordinate(1.2, 0.3, 0.4);

        coordinate3.getCentralAngle(coordinate4);
    }

    @Test
    public void testEqualsExtreme(){
        SpericCoordinate coordinate = CoordinateManager.getSpericCoordinate(-2.32434, 0, 34324.32434324);
        assertEquals(coordinate, coordinate.asCartesianCoordinate().asSpericCoordinate().asCartesianCoordinate().asSpericCoordinate().asCartesianCoordinate().asSpericCoordinate());
    }
}
