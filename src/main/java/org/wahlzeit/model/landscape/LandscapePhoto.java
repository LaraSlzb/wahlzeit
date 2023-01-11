package org.wahlzeit.model.landscape;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.utils.PatternInstance;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.wahlzeit.utils.CustomAsserts.assertNotNull;
@PatternInstance(
        patternName = "Abstract Factory",
        participiants = {"ConcreteProductA2" }
)
@PatternInstance(
        patternName = "Dekorierer",
        participiants = "ErweiterteKomponente"
)
public class LandscapePhoto extends Photo {

    protected String country;
    protected Landscape landscape;
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
        assertNotNull(myId);

        id = myId;

        incWriteCount();
    }

    /**
     *
     * @methodtype constructor
     */
    public LandscapePhoto(ResultSet rset) throws SQLException {
        assertNotNull(rset);

        readFrom(rset);
    }

    /**
     *
     */
    public void readFrom(ResultSet rset) throws SQLException{
        assertNotNull(rset);

        super.readFrom(rset);
        country = rset.getString("country");
        landscape = LandscapeManager.getInstance().createLandscape(rset.getString("landscape"));
    }
    /**
     *
     */
    public void writeOn(ResultSet rset) throws SQLException{
        assertNotNull(rset);

        super.writeOn(rset);
        rset.updateString("country", country);
        rset.updateString("landscape", landscape.getLandscapeType().getValue());
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

    /**
     *
     * @methodtype get
     */
    public Landscape getLandscape() {
        return landscape;
    }

    /**
     *
     * @methodtype set
     */
    public void setLandscape(String landscapeType) {
        landscape = LandscapeManager.getInstance().createLandscape(landscapeType);
        incWriteCount();
    }
}
