package org.wahlzeit.model.landscape;

import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.PhotoUtil;

import java.awt.*;
import java.io.File;

public class LandscapePhotoUtil extends PhotoUtil {
    /**
     *
     */
    public static LandscapePhoto createPhoto(File source, PhotoId id) throws Exception {
        LandscapePhoto result = LandscapePhotoFactory.getInstance().createPhoto(id);

        Image sourceImage = createImageFiles(source, id);

        int sourceWidth = sourceImage.getWidth(null);
        int sourceHeight = sourceImage.getHeight(null);
        result.setWidthAndHeight(sourceWidth, sourceHeight);

        return result;
    }
}
