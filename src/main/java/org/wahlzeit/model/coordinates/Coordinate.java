package org.wahlzeit.model.coordinates;

public interface Coordinate {
    CartesianCoordinate asCartesianCoordinate();
    double getCartesianDistance(Coordinate coordinate);
    SpericCoordinate asSpericCoordinate();
    double getCentralAngle(Coordinate coordinate);
    boolean isEqual(Coordinate coordinate);
    String asString();
}
