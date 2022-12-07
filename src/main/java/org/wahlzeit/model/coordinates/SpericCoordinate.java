package org.wahlzeit.model.coordinates;

public class SpericCoordinate extends AbstractCoordinate{

    /**
     *
     */
    private final double latitude;
    private final double longitude;
    private final double radius;
    private String keyCartesian;
    protected SpericCoordinate(double latitude, double longitude, double radius){
        if(radius < 0 || (longitude < 0 || longitude > 2*Math.PI) || (latitude < - Math.PI || latitude > Math.PI)){
            throw new IllegalArgumentException("convention of SpericCoordinates are violated");
        }
        this.latitude = CoordinateManager.unifyDouble(latitude);
        this.longitude = CoordinateManager.unifyDouble(longitude);
        this.radius = CoordinateManager.unifyDouble(radius);

        assertClassInvariants();
    }

    /**
     *
     * @methodtype=get
     */
    public double getLatitude(){
        assertClassInvariants();

        return this.latitude;
    }

    /**
     *
     * @methodtype=get
     */
    public double getLongitude(){
        assertClassInvariants();

        return this.longitude;
    }

    /**
     *
     * @methodtype=get
     */
    public double getRadius() {
        assertClassInvariants();

        return this.radius;
    }

    /**
     *
     * @methodtype=get
     */
    protected String getKeyCartesian(){
        return keyCartesian;
    }

    /**
     *
     * @methodtype=set
     */
    protected void setKeyCartesian(String keyCartesian){
        this.keyCartesian = keyCartesian;
    }

    /**
     * Converts spericCoordinate to cartesianCoordinate
     * @return cartesianCoordinate
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariants();

        CartesianCoordinate result = null;
        if(getKeyCartesian() != null){
            result = CoordinateManager.getCartesianCoordinate(getKeyCartesian());
        }
        if(result == null){
            double x = getRadius() * Math.cos(getLatitude()) * Math.cos(getLongitude());
            double y = getRadius() * Math.cos(getLatitude()) * Math.sin(getLongitude());
            double z = getRadius() * Math.sin(getLatitude());

            result = CoordinateManager.getCartesianCoordinate(x, y, z);
            result.setKeySperic(CoordinateManager.getKeySperic(getLatitude(), getLongitude(), getRadius()));
        }

        assert result != null: "result cannot be null";
        assertClassInvariants();
        return result;
    }

    /**
     * Converts to spericCoordinate
     * @return itself
     */
    @Override
    public SpericCoordinate asSpericCoordinate() {
        assertClassInvariants();

        return this;
    }

    @Override
    protected void assertClassInvariants() {
        if(this.radius < 0 || (this.longitude < 0 || this.longitude > 2*Math.PI) || (this.latitude < - Math.PI || this.latitude > Math.PI)){
            throw new IllegalStateException("convention of SpericCoordinates are violated");
        }
    }

}
