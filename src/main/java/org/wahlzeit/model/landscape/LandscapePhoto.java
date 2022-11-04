package org.wahlzeit.model.landscape;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LandscapePhoto extends Photo {
    /**
     *
     */
    public LandscapePhoto() {
        id = PhotoId.getNextId();
        incWriteCount();
    }

    /**
     *
     * @methodtype constructor
     */
    public LandscapePhoto(PhotoId myId) {
        id = myId;

        incWriteCount();
    }

    /**
     *
     * @methodtype constructor
     */
    public LandscapePhoto(ResultSet rset) throws SQLException {
        readFrom(rset);
    }
}
