package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PhotoTest {
    private Photo photo;

    @Before
    public void initPhoto() {
        photo = new Photo();
    }

    @Test
    public void testLocation(){
        assertNull(photo.getLocation());

        Location location = new Location(0,0,0, 1);
        photo.setLocation(location);
        assertEquals(location, photo.getLocation());
        assertEquals(1, photo.getLocation_id());
    }
}
