package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.coordinates.CartesianCoordinate;
import org.wahlzeit.model.coordinates.CoordinateManager;
import org.wahlzeit.model.coordinates.SpericCoordinate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PhotoTest {
    private Photo photo;

    @Before
    public void initPhoto() {
        photo = new Photo();
    }

    @Test
    public void testLocationWithCartesianCoordinate(){
        assertNull(photo.getLocation());

        CartesianCoordinate cartesianCoordinate = CoordinateManager.getCartesianCoordinate(0, 0, 1);
        Location location = new Location(cartesianCoordinate,  1);
        photo.setLocation(location);
        assertEquals(location, photo.getLocation());
        assertEquals(1, photo.getLocation_id());
    }
}
