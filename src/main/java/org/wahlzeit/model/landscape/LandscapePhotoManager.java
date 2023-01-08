package org.wahlzeit.model.landscape;

import org.wahlzeit.model.*;
import org.wahlzeit.utils.PatternInstance;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

@PatternInstance(
        patternName = "Singleton",
        participiants = {"Singleton"}
)
public class LandscapePhotoManager extends PhotoManager{

    protected static LandscapePhotoManager instance = new LandscapePhotoManager();

    /**
     *
     */
    public static LandscapePhotoManager getInstance() {
        return instance;
    }

    /**
     *
     */
    protected LandscapePhoto createObject(ResultSet rset) throws SQLException {
        return LandscapePhotoFactory.getInstance().createPhoto(rset);
    }

    /**
     *
     */
    public LandscapePhoto createPhoto(File file) throws Exception {
        PhotoId id = PhotoId.getNextId();
        LandscapePhoto result = LandscapePhotoUtil.createPhoto(file, id);
        addPhoto(result);
        return result;
    }
}
