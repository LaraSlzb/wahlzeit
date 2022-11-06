package org.wahlzeit.model.landscape;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LandscapePhoto extends Photo {

    protected String country;
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

    /**
     *
     */
    public void readFrom(ResultSet rset) throws SQLException{
        super.readFrom(rset);
        country = rset.getString("country");
    }
    /**
     *
     */
    public void writeOn(ResultSet rset) throws SQLException{
        super.writeOn(rset);
        rset.updateString("country", country);
    }

    /**
     *
     * @methodtype get
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @methodtype set
     */
    public void setCountry(String newCountry) {
        country = newCountry;
        incWriteCount();
    }
}
