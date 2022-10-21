package org.wahlzeit.model;

/**
 * Location for a photo
 */
public class Location {

    /**
     * cartesian coordinates of the location
     */

    protected Coordinate coordinate;

    public Location(Coordinate coordinate){
        if(coordinate == null){
            this.coordinate = new Coordinate(0,0,0);
        }
        else{
            this.coordinate = coordinate;
        }
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
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
