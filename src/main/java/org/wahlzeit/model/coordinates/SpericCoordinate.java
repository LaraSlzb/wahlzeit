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


}
