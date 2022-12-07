package org.wahlzeit.model.coordinates;

import java.text.DecimalFormat;
import java.util.HashMap;

public class CoordinateManager {
    protected static final DecimalFormat df = new DecimalFormat("0.0000");
    private static HashMap<String, CartesianCoordinate> allCartesianCoordinate = new HashMap<>();
    private static HashMap<String, SpericCoordinate> allSpericCoordinate = new HashMap<>();

    /**
     * Unifies doubles to a specific format
     */
    public static double unifyDouble(double d){
        String s = df.format(d).replace(",", ".");
        double result = Double.parseDouble(s);

        return result;
    }

    /**
     * Returns key for Cartesian Coordinate
     */
    public static String getKeyCartesian(double x, double y, double z){
        return "x = " + df.format(x) + " y = " + df.format(y) +" z = " + df.format(z);
    }

    /**
     * Returns key for Speric Coordinate
     */
    public static String getKeySperic(double latitude, double longitude, double radius){
        return "latitude = " + df.format(latitude) + " longitude = " + df.format(longitude) + " radius = " + df.format(radius);
    }

    /**
     * Returns speric coordinate from key if it already exists
     */
    public static SpericCoordinate getSpericCoordinate(String key){
        return allSpericCoordinate.get(key);
    }

    /**
     *Returns speric coordinate from latitude, longitude and radius if already exists
     */
    public static SpericCoordinate getSpericCoordinate(double latitude, double longitude, double radius){
        String key = getKeySperic(latitude, longitude, radius);
        SpericCoordinate result = allSpericCoordinate.get(key);
        if ( result == null ){
            synchronized (CoordinateManager.class){
                result = allSpericCoordinate.get(key);
                if (result == null){
                    result = new SpericCoordinate(latitude, longitude, radius);
                    allSpericCoordinate.put(key, result);
                }
            }
        }
        return result;
    }

    /**
     * Returns cartesian coordinate from key if it already exists
     */
    public static CartesianCoordinate getCartesianCoordinate(String key){
        return allCartesianCoordinate.get(key);
    }

    /**
     *Returns speric coordinate from x, y and z if already exists
     */
    public static CartesianCoordinate getCartesianCoordinate(double x, double y, double z){
        String key = getKeyCartesian(x, y, z);
        CartesianCoordinate result = allCartesianCoordinate.get(key);
        if ( result == null ){
            synchronized (CoordinateManager.class){
                result = allCartesianCoordinate.get(key);
                if (result == null){
                    result = new CartesianCoordinate(x, y, z);
                    allCartesianCoordinate.put(key, result);
                }
            }
        }
        return result;
    }
}
