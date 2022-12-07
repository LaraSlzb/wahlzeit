package org.wahlzeit.model.coordinates;

import static org.wahlzeit.utils.CustomAsserts.assertDoubleIsFinite;

/**
 * cartesian coordinates for location
 */
public class CartesianCoordinate extends AbstractCoordinate{

   private String keySperic;
   private final double x;
   private final double y;
   private final double z;

   protected CartesianCoordinate(double x, double y, double z) {
      assertDoubleIsFinite(x);
      assertDoubleIsFinite(y);
      assertDoubleIsFinite(z);

      this.x = CoordinateManager.unifyDouble(x);
      this.y = CoordinateManager.unifyDouble(y);
      this.z = CoordinateManager.unifyDouble(z);;

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
    *
    * @methodtype=get
    */
   protected String getKeySperic(){
      return keySperic;
   }

   /**
    *
    * @methodtype=set
    */
   protected void setKeySperic(String keySperic){
      this.keySperic = keySperic;
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

      SpericCoordinate result = null;
      if(getKeySperic() != null){
         result = CoordinateManager.getSpericCoordinate(getKeySperic());
      }
      if(result == null){
         double latitude, longitude, radius;
         if(getX() == 0 && getY() == 0 && getZ() == 0){
            latitude = 0;
            longitude = 0;
            radius = 0;
         }
         else{
            radius = this.getCartesianDistance(new CartesianCoordinate(0, 0, 0));
            latitude = Math.asin(getZ()/radius);
            longitude = getArcTan(getY(), getX());
         }
         result =  CoordinateManager.getSpericCoordinate(latitude, longitude, radius);
         result.setKeyCartesian(CoordinateManager.getKeyCartesian(getX(), getY(), getZ()));
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
