package org.wahlzeit.model.landscape;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.PhotoUtil;

import java.awt.*;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LandscapePhotoUtilTest {

    @Test
    public void createPhoto() throws Exception {
        File file = mock(File.class);
        Image image = mock(Image.class);
        PhotoId photoId = mock(PhotoId.class);
        LandscapePhotoFactory landscapePhotoFactory = mock(LandscapePhotoFactory.class);
        LandscapePhotoFactory.setInstance(landscapePhotoFactory);
        LandscapePhoto photo = new LandscapePhoto();
        when(landscapePhotoFactory.createPhoto(photoId)).thenReturn(photo);
        MockedStatic<PhotoUtil> photoUtilMockedStatic = mockStatic(PhotoUtil.class);
        photoUtilMockedStatic.when(() -> PhotoUtil.createImageFiles(eq(file), eq(photoId))).thenReturn(image);
        when(image.getWidth(null)).thenReturn(10);
        when(image.getHeight(null)).thenReturn(20);

        LandscapePhoto result = LandscapePhotoUtil.createPhoto(file, photoId);

        assertEquals(photo, result);
        assertEquals(10, photo.getWidth());
        assertEquals(20, photo.getHeight());
    }


}
