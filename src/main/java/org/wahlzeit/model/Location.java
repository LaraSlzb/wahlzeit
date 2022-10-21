package org.wahlzeit.model;

/**
 * Location for a photo
 */
public class Location {

    /**
     * cartesian coordinates of the location
     */

    protected Coordinate coordinate;

    public Location(double x, double y, double z){
        this.coordinate = new Coordinate(x, y, z);
    }

    /**
     *
     * @methodtype=get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     *
     * methodtype=set
     */
    public void setCoordinate(double x, double y, double z) {
        this.coordinate = new Coordinate(x, y, z);
    }
}
