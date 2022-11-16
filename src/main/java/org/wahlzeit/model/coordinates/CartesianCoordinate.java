package org.wahlzeit.model.coordinates;

import java.util.Objects;

/**
 * cartesian coordinates for location
 */
public class CartesianCoordinate extends AbstractCoordinate{
   private final double x;
   private final double y;
   private final double z;

   public CartesianCoordinate(double x, double y, double z) {
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
    * Converts coorinate to a CartesianCoordinate
    * @return itself
    */
   @Override
   public CartesianCoordinate asCartesianCoordinate() {
      return this;
   }

   /**
    * Converts CartesianCoorinate to a spericCoordinate
    * @return spericCoordinate
    */
   @Override
   public SpericCoordinate asSpericCoordinate() {
      if(getX() == 0 && getY() == 0 && getZ() == 0){
         return new SpericCoordinate(0,0,0);
      }
      double radius = this.getCartesianDistance(new CartesianCoordinate(0, 0, 0));
      double latitude = Math.asin(getZ()/radius);
      double longitude = getArcTan(getY(), getX());
      return new SpericCoordinate(latitude, longitude, radius);
   }

   /**
    * helpermethod to calculate arcTan
    * @param y
    * @param x
    * @return arcTan from (X/y)
    */
   private double getArcTan(double y, double x){
      if (x == 0){
         if(y > 0){
            return Math.PI / 2;
         }
         if ( y < 0){
            return - Math.PI / 2;
         }
         throw new IllegalArgumentException("latitude could not be calculated as x and y are both 0");
      }
      if (x < 0){
         if (y >= 0){
            return Math.atan(y/x) + Math.PI;
         }
         return Math.atan(y/x) - Math.PI;
      }
      return Math.atan(y/x);
   }


}
