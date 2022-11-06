package org.wahlzeit.model.landscape;

import org.junit.Test;
import org.mockito.Mockito;
import org.wahlzeit.model.PhotoId;

import static org.junit.Assert.assertTrue;

public class LandscapePhotoFactoryTest {

    @Test
    public void initializeAndGetInstance(){
        LandscapePhotoFactory.initialize();

        assertTrue(LandscapePhotoFactory.getInstance() instanceof LandscapePhotoFactory);
    }

    @Test
    public void createPhoto(){
        LandscapePhotoFactory landscapePhotoFactory = new LandscapePhotoFactory();
        PhotoId photoId = Mockito.mock(PhotoId.class);

        assertTrue(landscapePhotoFactory.createPhoto(photoId) instanceof LandscapePhoto);
    }
}
