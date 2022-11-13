package org.wahlzeit.model;

import org.wahlzeit.model.coordinates.CartesianCoordinate;
import org.wahlzeit.model.coordinates.Coordinate;
import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Location for a photo
 */
public class Location extends DataObject{

    /**
     * cartesian coordinates of the location
     */

    protected int id;
    protected Coordinate coordinate;

    /**
     * Constructor, id has to be unique as it is primary key
     */
    public Location(Coordinate coordinate, int id){
        if(coordinate == null){
            throw new IllegalArgumentException("Coordinate cannot be null");
        }
        this.id = id;
        this.coordinate = coordinate;
        incWriteCount();
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
        if(coordinate == null){
            throw new IllegalArgumentException("Coordinate cannot be set null");
        }
        this.coordinate = coordinate;
        incWriteCount();
    }

    /**
     *
     * @methodtype=get
     */
    public int getId() {
        return id;
    }

    @Override
    public String getIdAsString() {
        return String.valueOf(id);
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        this.id = rset.getInt("id");
        this.coordinate = new CartesianCoordinate(rset.getDouble("x"),
                rset.getDouble("y"), rset.getDouble("z"));
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateInt("id", this.id);
        if(this.coordinate != null){
            rset.updateDouble("x", this.coordinate.asCartesianCoordinate().getX());
            rset.updateDouble("y", this.coordinate.asCartesianCoordinate().getY());
            rset.updateDouble("z", this.coordinate.asCartesianCoordinate().getZ());
        }
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        stmt.setInt(pos, this.id);
    }
}
