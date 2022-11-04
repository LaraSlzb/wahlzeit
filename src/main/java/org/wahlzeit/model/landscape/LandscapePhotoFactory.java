package org.wahlzeit.model.landscape;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.SysLog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LandscapePhotoFactory extends PhotoFactory {
    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static LandscapePhotoFactory instance = null;

    /**
     * Public singleton access method.
     */
    public static synchronized PhotoFactory getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("setting generic PhotoFactory");
            setInstance(new LandscapePhotoFactory());
        }
        return instance;
    }
    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }

    /**
     * Method to set the singleton instance of PhotoFactory.
     */
    protected static synchronized void setInstance(LandscapePhotoFactory photoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize PhotoFactory twice");
        }

        instance = photoFactory;
    }

    /**
     * @methodtype factory
     */
    public Photo createPhoto() {
        return new LandscapePhoto();
    }

    /**
     *
     */
    public Photo createPhoto(PhotoId id) {
        return new LandscapePhoto(id);
    }

    /**
     *
     */
    public Photo createPhoto(ResultSet rs) throws SQLException {
        return new LandscapePhoto(rs);
    }
}
