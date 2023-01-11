package org.wahlzeit.model.landscape;

import org.wahlzeit.utils.CustomAsserts;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LandscapeType {

    private String value;
    private LandscapeType superType = null;
    private Set<LandscapeType> subTypes = new HashSet<>();

    /**
     *
     * @methodtype constructor
     */
    public LandscapeType(String value){
        CustomAsserts.assertNotNull(value);

        this.value = value;
    }
    public Landscape createInstance(){
        return new Landscape(this);
    }

    /**
     *
     * @methodtype get
     */
    public String getValue(){
        return value;
    }

    /**
     *
     * @methodtype get
     */
    public LandscapeType getSuperType(){
        return this.superType;
    }

    /**
     *
     * @methodtype set
     */
    public void setSuperType(LandscapeType superType){
        this.superType = superType;
    }

    /**
     *
     * @return iterator over subTypes
     */
    public Iterator<LandscapeType> getSubTypeIterator() {
        return subTypes.iterator();
    }

    /**
     * Adds new subType
     */
    public void addSubType(LandscapeType landscapeType) {
        CustomAsserts.assertNotNull(landscapeType);
        landscapeType.setSuperType(this);
        subTypes.add(landscapeType);
    }

    /**
     * Checks if type has instances
     */
    public boolean hasInstance(Landscape landscape) {
        CustomAsserts.assertNotNull(landscape);
        if (landscape.getLandscapeType() == this) {
            return true;
        }
        for (LandscapeType type : subTypes) {
            if (type.hasInstance(landscape)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if type is subtype
     */
    public boolean isSubtype(){
        return getSuperType() != null;
    }
}
