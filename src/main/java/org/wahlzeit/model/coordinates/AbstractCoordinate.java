package org.wahlzeit.model.coordinates;

public abstract class AbstractCoordinate implements Coordinate {

    public abstract CartesianCoordinate asCartesianCoordinate();

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

    public abstract SpericCoordinate asSpericCoordinate();

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

    public abstract boolean isEqual(Coordinate coordinate);

    public abstract int hashCode();

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


