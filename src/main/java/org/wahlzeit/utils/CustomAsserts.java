package org.wahlzeit.utils;

public class CustomAsserts {
    public static void assertNotNull(Object object){
        if(object == null){
            throw new IllegalArgumentException("Object cannot be null");
        }
    }

    public static void assertDoubleIsFinite(double x){
        if(!Double.isFinite(x)){
            throw new IllegalArgumentException(x + " needs to be finite");
        }
    }
}
