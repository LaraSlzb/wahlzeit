package org.wahlzeit.model;

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
    public Location(double x, double y, double z, int id){
        this.id = id;
        this.coordinate = new Coordinate(x, y, z);
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
    public void setCoordinate(double x, double y, double z) {
        this.coordinate = new Coordinate(x, y, z);
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
        this.coordinate = new Coordinate(rset.getDouble("x"),
                rset.getDouble("y"), rset.getDouble("z"));
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateInt("id", this.id);
        rset.updateDouble("x", this.coordinate.getX());
        rset.updateDouble("y", this.coordinate.getY());
        rset.updateDouble("z", this.coordinate.getZ());
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        stmt.setInt(pos, this.id);
    }
}
