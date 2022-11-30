package org.wahlzeit.model.coordinates;

import static org.wahlzeit.utils.CustomAsserts.assertDoubleIsFinite;

/**
 * cartesian coordinates for location
 */
public class CartesianCoordinate extends AbstractCoordinate{
   private final double x;
   private final double y;
   private final double z;

   public CartesianCoordinate(double x, double y, double z) {
      assertDoubleIsFinite(x);
      assertDoubleIsFinite(y);
      assertDoubleIsFinite(z);

      this.x = x;
      this.y = y;
      this.z = z;

      assertClassInvariants();
   }

   /**
    *
    * @methodtype=get
    */
   public double getX(){
      assertClassInvariants();

      return x;
   }

   /**
    *
    * @methodtype=get
    */
   public double getY() {
      assertClassInvariants();

      return y;
   }

   /**
    *
    * @methodtype=get
    */
   public double getZ() {
      assertClassInvariants();

      return z;
   }

   /**
    * Converts coorinate to a CartesianCoordinate
    * @return itself
    */
   @Override
   public CartesianCoordinate asCartesianCoordinate() {
      assertClassInvariants();

      return this;
   }

   /**
    * Converts CartesianCoorinate to a spericCoordinate
    * @return spericCoordinate
    */
   @Override
   public SpericCoordinate asSpericCoordinate() {
      assertClassInvariants();

      SpericCoordinate result;
      if(getX() == 0 && getY() == 0 && getZ() == 0){
         result = new SpericCoordinate(0,0,0);
      }
      else{
         double radius = this.getCartesianDistance(new CartesianCoordinate(0, 0, 0));
         double latitude = Math.asin(getZ()/radius);
         double longitude = getArcTan(getY(), getX());
         result =  new SpericCoordinate(latitude, longitude, radius);
      }

      assert result != null : "result needs to be a SpericCoordinate";
      assertClassInvariants();
      return result;
   }

   @Override
   protected void assertClassInvariants() {
      try{
         assertDoubleIsFinite(this.x);
         assertDoubleIsFinite(this.y);
         assertDoubleIsFinite(this.z);
      }
      catch(IllegalArgumentException e ){
         throw new IllegalStateException(e.getMessage());
      }
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
