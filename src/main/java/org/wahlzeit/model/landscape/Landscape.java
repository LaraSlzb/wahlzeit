package org.wahlzeit.model.landscape;

import org.wahlzeit.utils.CustomAsserts;

public class Landscape {
    private LandscapeType landscapeType;

    /**
     *
     * @methodtype constructor
     */
    public Landscape(LandscapeType landscapeType){
        CustomAsserts.assertNotNull(landscapeType);

        this.landscapeType = landscapeType;
    }

    /**
     *
     * @methodtype set
     */
    public void setLandscapeType(LandscapeType landscapeType){
        this.landscapeType = landscapeType;
    }

    /**
     *
     * @methodtype get
     */
    public LandscapeType getLandscapeType(){
        return this.landscapeType;
    }

    /**
     *
     * @methodtype get
     */
    public Integer getId(){
        return this.hashCode();
    }
}
