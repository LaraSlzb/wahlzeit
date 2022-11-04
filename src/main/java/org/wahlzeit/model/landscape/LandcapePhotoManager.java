package org.wahlzeit.model.landscape;

import org.wahlzeit.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LandcapePhotoManager extends PhotoManager{
    /**
     *
     */
    protected static final LandcapePhotoManager instance = new LandcapePhotoManager();


    /**
     *
     */
    protected Photo createObject(ResultSet rset) throws SQLException {
        return LandscapePhotoFactory.getInstance().createPhoto(rset);
    }
}
