package org.wahlzeit.model.coordinates;

import java.util.Objects;

public class SpericCoordinate extends AbstractCoordinate{

    /**
     *
     */
    private final double latitude;
    private final double longitude;
    private final double radius;

    public SpericCoordinate(double latitude, double longitude, double radius){
        if(radius < 0 || (longitude < 0 || longitude > 2*Math.PI) || (latitude < - Math.PI || latitude > Math.PI)){
            throw new IllegalArgumentException("convention of SpericCoordinates are violated");
        }
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    /**
     *
     * @methodtype=get
     */
    public double getLatitude(){
        return this.latitude;
    }

    /**
     *
     * @methodtype=get
     */
    public double getLongitude(){
        return this.longitude;
    }

    /**
     *
     * @methodtype=get
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Converts spericCoordinate to cartesianCoordinate
     * @return cartesianCoordinate
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = getRadius() * Math.cos(getLatitude()) * Math.cos(getLongitude());
        double y = getRadius() * Math.cos(getLatitude()) * Math.sin(getLongitude());
        double z = getRadius() * Math.sin(getLatitude());
        return new CartesianCoordinate(x, y, z);
    }

    /**
     * Converts to spericCoordinate
     * @return itself
     */
    @Override
    public SpericCoordinate asSpericCoordinate() {
        return this;
    }

    /**
     * Tests if 2 speric coordinates are equal
     *
     * @param coordinate to compare with
     * @return true if distance is 0
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        if(coordinate == null){
            return false;
        }
        try{
            return getCentralAngle(coordinate.asSpericCoordinate()) <= E;
        }
        catch (IllegalArgumentException e){
            return false;
        }
    }

    /**
     * hashCode has to be overwritten as equals was overwritten
     * @return hash from x, y, z
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.getLatitude(), this.getLongitude(), this.getRadius());
    }
}
