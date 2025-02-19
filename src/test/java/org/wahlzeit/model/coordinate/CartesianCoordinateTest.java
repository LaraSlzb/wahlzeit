package org.wahlzeit.model.coordinate;

import org.junit.Test;
import org.wahlzeit.model.coordinates.CartesianCoordinate;
import org.wahlzeit.model.coordinates.CoordinateManager;

import static org.junit.Assert.*;

public class CartesianCoordinateTest {

    double e = 0.00001;
    @Test
    public void testGet(){
        double x = -5.45491;
        double y = 0;
        double z = 3.45457;
        CartesianCoordinate coordinate = CoordinateManager.getCartesianCoordinate(x, y, z);

        assertEquals(-5.4549, coordinate.getX(), e);
        assertEquals(y, coordinate.getY(), e);
        assertEquals(3.4546, coordinate.getZ(), e);
    }

    @Test
    public void testGetDistance(){
        CartesianCoordinate coordinate1 = CoordinateManager.getCartesianCoordinate(0,0,0);
        CartesianCoordinate coordinate2 = CoordinateManager.getCartesianCoordinate(0, 3, -4);
        CartesianCoordinate coordinate3 = CoordinateManager.getCartesianCoordinate(1.2, 4.3, 3.4);
        CartesianCoordinate coordinate4 = CoordinateManager.getCartesianCoordinate(1.2,0.3,0.4);
        CartesianCoordinate coordinate5 = CoordinateManager.getCartesianCoordinate(0, 0, Double.MAX_VALUE);
        CartesianCoordinate coordinate6 = CoordinateManager.getCartesianCoordinate(0, 0, Double.MIN_VALUE);

        assertEquals(0, coordinate1.getCartesianDistance(coordinate1), e);
        assertEquals(0, coordinate2.getCartesianDistance(coordinate2), e);
        assertEquals(0, coordinate3.getCartesianDistance(coordinate3), e);
        assertEquals(0, coordinate4.getCartesianDistance(coordinate4), e);
        assertEquals(0, coordinate5.getCartesianDistance(coordinate5), e);

        assertEquals(0, coordinate2.getCartesianDistance(coordinate2.asSpericCoordinate()), e);
        assertEquals(0, coordinate3.getCartesianDistance(coordinate3.asSpericCoordinate()), e);
        assertEquals(0, coordinate4.getCartesianDistance(coordinate4.asSpericCoordinate()), e);

        assertEquals(5, coordinate1.getCartesianDistance(coordinate2), e);
        assertEquals(5, coordinate3.getCartesianDistance(coordinate4), e);
        assertEquals(Double.POSITIVE_INFINITY, coordinate5.getCartesianDistance(coordinate6), e);
        assertEquals(Double.POSITIVE_INFINITY, coordinate2.getCartesianDistance(coordinate5), e);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDistanceWithNullArgument(){
        CartesianCoordinate coordinate = CoordinateManager.getCartesianCoordinate(0, 0, 0);
        coordinate.getCartesianDistance(null);
    }

    @Test
    public void testIsEqual() {
        CartesianCoordinate coordinate0 = CoordinateManager.getCartesianCoordinate(0, 0, 0);
        CartesianCoordinate coordinate1 = CoordinateManager.getCartesianCoordinate(0, 0, 0);
        CartesianCoordinate coordinate2 = CoordinateManager.getCartesianCoordinate(0, 3, -4);
        CartesianCoordinate coordinate3 = CoordinateManager.getCartesianCoordinate(1.2, 4.3, 3.4);
        CartesianCoordinate coordinate4 = CoordinateManager.getCartesianCoordinate(1.2, 0.3, 0.4);
        CartesianCoordinate coordinate5 = CoordinateManager.getCartesianCoordinate(0, 0, Double.MAX_VALUE);
        CartesianCoordinate coordinate6 = CoordinateManager.getCartesianCoordinate(0, 0, Double.MIN_VALUE);

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
        CartesianCoordinate coordinate1 = CoordinateManager.getCartesianCoordinate(0, 0, 0);
        CartesianCoordinate coordinate2 = CoordinateManager.getCartesianCoordinate(0, 3, -4);
        CartesianCoordinate coordinate3 = CoordinateManager.getCartesianCoordinate(0, 3, -4);

        assertTrue(coordinate1.equals(coordinate1));
        assertTrue(coordinate2.equals(coordinate3));
        assertEquals(coordinate2.hashCode(), coordinate3.hashCode());
        assertFalse(coordinate1.equals(coordinate2));
        assertFalse(coordinate1.equals(null));
        assertFalse(coordinate1.equals(new Object()));
    }
    @Test
    public void testAsCartesian(){
        CartesianCoordinate coordinate2 = CoordinateManager.getCartesianCoordinate(0, 3, 4);
        CartesianCoordinate coordinate3 = CoordinateManager.getCartesianCoordinate(1.2, 2.3, 3.4);
        CartesianCoordinate coordinate4 = CoordinateManager.getCartesianCoordinate(1.2, 0.3, 0.4);

        assertEquals(coordinate2, coordinate2.asCartesianCoordinate());
        assertEquals(coordinate3,coordinate3.asCartesianCoordinate());
        assertEquals(coordinate4, coordinate4.asCartesianCoordinate());
    }

    @Test
    public void testAsSperic(){
        CartesianCoordinate coordinate1 = CoordinateManager.getCartesianCoordinate(0, 0, 0);
        CartesianCoordinate coordinate2 = CoordinateManager.getCartesianCoordinate(0, 3, 4);
        CartesianCoordinate coordinate3 = CoordinateManager.getCartesianCoordinate(1.21, 4.31, 3.41);
        CartesianCoordinate coordinate4 = CoordinateManager.getCartesianCoordinate(1.2, 0.3, 0.4);

        assertEquals(coordinate1, coordinate1.asSpericCoordinate());
        assertEquals(coordinate2, coordinate2.asSpericCoordinate());
        assertEquals(coordinate3, coordinate3.asSpericCoordinate());
        assertEquals(coordinate4, coordinate4.asSpericCoordinate());
    }

    @Test
    public void testGetCentralAngel(){
        CartesianCoordinate coordinate2 = CoordinateManager.getCartesianCoordinate(0, 3, 4);
        CartesianCoordinate coordinate3 = CoordinateManager.getCartesianCoordinate(3, 4, 0);
        CartesianCoordinate coordinate4 = CoordinateManager.getCartesianCoordinate(1.2, 0.3, 0);

        assertEquals(0, coordinate2.getCentralAngle(coordinate2), e);
        assertEquals(0, coordinate3.getCentralAngle(coordinate3), e);
        assertEquals(0, coordinate4.getCentralAngle(coordinate4), e);

        assertEquals(0, coordinate2.getCentralAngle(coordinate2.asSpericCoordinate()), e);
        assertEquals(0, coordinate3.getCentralAngle(coordinate3.asSpericCoordinate()), e);
        assertEquals(0, coordinate4.getCentralAngle(coordinate4.asSpericCoordinate()), e);

        assertNotEquals(0, coordinate2.getCentralAngle(coordinate3), e);
        assertNotEquals(0, coordinate2.getCentralAngle(coordinate3.asSpericCoordinate()), e);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCentralAngelWithDifferentRadius(){
        CartesianCoordinate coordinate3 = CoordinateManager.getCartesianCoordinate(1.2, 4.3, 4);
        CartesianCoordinate coordinate4 = CoordinateManager.getCartesianCoordinate(1.2, 0.3, 0.4);

        coordinate3.getCentralAngle(coordinate4);
    }

    @Test
    public void testEqualsExtreme(){
        CartesianCoordinate coordinate = CoordinateManager.getCartesianCoordinate(2.32434, 0, -34324.32434324);
        assertEquals(coordinate, coordinate.asSpericCoordinate().asCartesianCoordinate().asSpericCoordinate().asCartesianCoordinate().asSpericCoordinate().asCartesianCoordinate());
    }
}
