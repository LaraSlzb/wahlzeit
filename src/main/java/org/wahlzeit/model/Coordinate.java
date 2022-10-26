package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * cartesian coordinates for location
 */
public class Coordinate {
   private double x;
   private double y;
   private double z;

   public Coordinate(double x, double y, double z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   /**
    *
    * @methodtype=get
    */
   public double getX(){
      return x;
   }

   /**
    *
    * @methodtype=get
    */
   public double getY() {
      return y;
   }

   /**
    *
    * @methodtype=get
    */
   public double getZ() {
      return z;
   }

   /**
    * Calculates the Cartesian distance between two coordinates
    * @param coordinate to compare with
    * @return cartesian distance
    */
   public double getDistance(Coordinate coordinate){
      if(coordinate == null){
         throw new IllegalArgumentException();
      }
      double temp_x = coordinate.getX() - x;
      double temp_y = coordinate.getY() - y;
      double temp_z = coordinate.getZ() - z;
      return Math.sqrt(temp_x*temp_x + temp_y*temp_y + temp_z*temp_z);
   }

   /**
    * Tests if 2 cartesian coordinates are equal
    * @param coordinate to compare with
    * @return true if distance is 0
    */
   public boolean isEqual(Coordinate coordinate){
      if(coordinate == null){
         return false;
      }
      return this.x==coordinate.x && this.y==coordinate.y && this.z==coordinate.z;
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