package org.wahlzeit.model.landscape;

import org.wahlzeit.model.*;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

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
