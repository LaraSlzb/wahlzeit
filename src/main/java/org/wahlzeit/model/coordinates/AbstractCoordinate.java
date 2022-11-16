package org.wahlzeit.model.coordinates;

import java.util.Objects;

public abstract class AbstractCoordinate implements Coordinate {

    public abstract CartesianCoordinate asCartesianCoordinate();

    public abstract SpericCoordinate asSpericCoordinate();

    protected static final double E = 0.00001;

    /**
     * Calculates the Cartesian distance between two coordinates
     *
     * @param coordinate to compare with
     * @return cartesian distance
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        CartesianCoordinate cartesianCoordinate1 = this.asCartesianCoordinate();
        CartesianCoordinate cartesianCoordinate2 = coordinate.asCartesianCoordinate();
        double delta_x = cartesianCoordinate2.getX() - cartesianCoordinate1.getX();
        double delta_y = cartesianCoordinate2.getY() - cartesianCoordinate1.getY();
        double delta_z = cartesianCoordinate2.getZ() - cartesianCoordinate1.getZ();
        return Math.sqrt(delta_x * delta_x + delta_y * delta_y + delta_z * delta_z);
    }

    /**
     * Calculates the centralAngel between two coordinates
     * @param coordinate
     * @return centralAngel
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        SpericCoordinate coordinate1 = this.asSpericCoordinate();
        SpericCoordinate coordinate2 = coordinate.asSpericCoordinate();
        if (Math.abs(coordinate1.getRadius() - coordinate2.getRadius()) > E) {
            throw new IllegalArgumentException("coordinates have different radius");
        }
        double deltaLongitude = Math.abs(coordinate2.getLongitude() - coordinate1.getLongitude());
        double deltaLatitude = Math.abs(coordinate2.getLatitude() - coordinate1.getLatitude());
        return 2 * Math.asin(Math.sqrt(hav(deltaLatitude) +
                (1 - hav(deltaLatitude) - hav(coordinate1.getLatitude() + coordinate2.getLatitude())) * hav(deltaLongitude)));
    }

    /**
     * Helper Method to calculate hav-function
     * @param x
     * @return hav(x)
     */
    private double hav(double x) {
        return Math.sin(x / 2) * Math.sin(x / 2);
    }

    /**
     * Tests if 2 cartesian coordinates are equal
     * @param coordinate to compare with
     * @return true if distance is 0
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null) {
            return false;
        }
        CartesianCoordinate cartesianCoordinate = this.asCartesianCoordinate();
        return cartesianCoordinate.getCartesianDistance(coordinate) <= E;
    }

    /**
     * hashCode has to be overwritten as equals was overwritten
     * @return hash from x, y, z
     */
    @Override
    public int hashCode(){
        CartesianCoordinate coordinate = this.asCartesianCoordinate();
        return Objects.hash(coordinate.getX(), coordinate.getY(), coordinate.getZ());
    }

    /**
     * Forward equals to isequal
     * @param o to compare with
     * @return true if o is a coordinate and isequal(o)
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Coordinate){
            return isEqual((Coordinate) o);
        }
        return false;
    }
}


