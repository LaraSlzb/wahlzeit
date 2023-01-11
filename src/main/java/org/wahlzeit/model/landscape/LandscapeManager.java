package org.wahlzeit.model.landscape;

import org.wahlzeit.utils.CustomAsserts;

import java.util.HashMap;

public class LandscapeManager {

    private LandscapeType root;
    private HashMap<Integer, Landscape> landscapes = new HashMap<>();
    private HashMap<String, LandscapeType> landscapeTypes = new HashMap<>();

    protected static LandscapeManager instance = null;


    public LandscapeManager(){
        initializeLandscapeTypes();
    }

    public static LandscapeManager getInstance() {
        if(instance == null){
            instance = new LandscapeManager();
        }
        return instance;
    }

    /**
     *
     * @methodtype get
     */
    public Landscape getLandscape(Integer id){
        return landscapes.get(id);
    }

    /**
     *
     * @methodtype get
     */
    public LandscapeType getLandscapeType(String value){
        return landscapeTypes.get(value);
    }
    /**
     * creates a new landscape instance
     * @param landscapeType type of the landscape
     * @return new landscape instance
     */
    public Landscape createLandscape(String landscapeType){
        CustomAsserts.assertNotNull(landscapeType);

        LandscapeType type = landscapeTypes.get(landscapeType);
        if(type == null){
           type = createLandscapeType(landscapeType, null);
        }
        Landscape result = type.createInstance();
        landscapes.put(result.getId(), result);
        return result;
    }

    /**
     * creates a new landscapeType
     * @param typeString value of the landscapeType
     * @param superTypeString supertype of it, default is root
     * @return new landscapeType, a subtype of the superTypeString
     */
    public LandscapeType createLandscapeType(String typeString, String superTypeString){
        LandscapeType result = new LandscapeType(typeString);
        LandscapeType superType = landscapeTypes.get(superTypeString);
        if(superType != null){
            superType.addSubType(result);
        }
        else{
            root.addSubType(result);
        }
        landscapeTypes.put(typeString, result);
        return result;
    }

    /**
     * creates the initial hierachy of landscapeTypes
     */
    private void initializeLandscapeTypes(){
        for(LandscapeTypesValues values : LandscapeTypesValues.values()){
            landscapeTypes.put(values.toString(), new LandscapeType(values.toString()));
        }
        this.root = landscapeTypes.get(LandscapeTypesValues.WORLD.toString());
        landscapeTypes.get(LandscapeTypesValues.WORLD.toString()).addSubType(landscapeTypes.get(LandscapeTypesValues.NATURE.toString()));
        landscapeTypes.get(LandscapeTypesValues.WORLD.toString()).addSubType(landscapeTypes.get(LandscapeTypesValues.URBAN.toString()));
        landscapeTypes.get(LandscapeTypesValues.NATURE.toString()).addSubType(landscapeTypes.get(LandscapeTypesValues.MOUNTAINS.toString()));
        landscapeTypes.get(LandscapeTypesValues.NATURE.toString()).addSubType(landscapeTypes.get(LandscapeTypesValues.SEA.toString()));
        landscapeTypes.get(LandscapeTypesValues.NATURE.toString()).addSubType(landscapeTypes.get(LandscapeTypesValues.DESERT.toString()));
        landscapeTypes.get(LandscapeTypesValues.URBAN.toString()).addSubType(landscapeTypes.get(LandscapeTypesValues.CITY.toString()));
        landscapeTypes.get(LandscapeTypesValues.URBAN.toString()).addSubType(landscapeTypes.get(LandscapeTypesValues.TOWN.toString()));
    }
}
