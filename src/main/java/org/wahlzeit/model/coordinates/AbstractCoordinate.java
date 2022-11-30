package org.wahlzeit.model.coordinates;

import java.util.Objects;

import static org.wahlzeit.utils.CustomAsserts.assertNotNull;

public abstract class AbstractCoordinate implements Coordinate {

    public abstract CartesianCoordinate asCartesianCoordinate();

    public abstract SpericCoordinate asSpericCoordinate();

    protected abstract void assertClassInvariants();



    protected static final double E = 0.00001;

    /**
     * Calculates the Cartesian distance between two coordinates
     *
     * @param coordinate to compare with
     * @return cartesian distance
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        assertClassInvariants();
        assertNotNull(coordinate);

        CartesianCoordinate cartesianCoordinate1 = this.asCartesianCoordinate();
        CartesianCoordinate cartesianCoordinate2 = coordinate.asCartesianCoordinate();
        double delta_x = cartesianCoordinate2.getX() - cartesianCoordinate1.getX();
        double delta_y = cartesianCoordinate2.getY() - cartesianCoordinate1.getY();
        double delta_z = cartesianCoordinate2.getZ() - cartesianCoordinate1.getZ();
        double result = Math.sqrt(delta_x * delta_x + delta_y * delta_y + delta_z * delta_z);

        assert result >= 0 : "distance has to be greater than or equals 0";
        assertClassInvariants();
        return result;
    }

    /**
     * Calculates the centralAngel between two coordinates
     * @param coordinate
     * @return centralAngel
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        assertClassInvariants();
        assertNotNull(coordinate);

        SpericCoordinate coordinate1;
        SpericCoordinate coordinate2;
        try{
            coordinate1 = this.asSpericCoordinate();
        }
        catch(IllegalArgumentException e){
            throw new IllegalStateException("the object could not be converted to a SpericCoordina");
        }
        try{
            coordinate2 = coordinate.asSpericCoordinate();
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("coordinate could not be converted to SpericCoordinate");
        }

        if (Math.abs(coordinate1.getRadius() - coordinate2.getRadius()) > E) {
            throw new IllegalArgumentException("coordinates have different radius");
        }

        double deltaLongitude = Math.abs(coordinate2.getLongitude() - coordinate1.getLongitude());
        double deltaLatitude = Math.abs(coordinate2.getLatitude() - coordinate1.getLatitude());
        double result = 2 * Math.asin(Math.sqrt(hav(deltaLatitude) +
                (1 - hav(deltaLatitude) - hav(coordinate1.getLatitude() + coordinate2.getLatitude())) * hav(deltaLongitude)));

        assert result > -2*Math.PI && result < 2*Math.PI : "angle needs to be between -2*PI and 2*Pi";
        assertClassInvariants();
        return result;
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
        assertClassInvariants();

        boolean result;
        CartesianCoordinate cartesianCoordinate = this.asCartesianCoordinate();
        try{
            result = cartesianCoordinate.getCartesianDistance(coordinate) <= E;
        }
        catch (IllegalArgumentException e){
            result = false;
        }

        assertClassInvariants();
        return result;
    }

    /**
     * hashCode has to be overwritten as equals was overwritten
     * @return hash from x, y, z
     */
    @Override
    public int hashCode(){
        assertClassInvariants();

        CartesianCoordinate coordinate = this.asCartesianCoordinate();
        int result = Objects.hash(coordinate.getX(), coordinate.getY(), coordinate.getZ());

        assertClassInvariants();
        return result;
    }

    /**
     * Forward equals to isequal
     * @param o to compare with
     * @return true if o is a coordinate and isequal(o)
     */
    @Override
    public boolean equals(Object o){
        assertClassInvariants();

        boolean result;
        try{
            result = isEqual((Coordinate) o);
        }
        catch (IllegalArgumentException  | ClassCastException e){
            result = false;
        }

        assertClassInvariants();
        return result;
    }
}


