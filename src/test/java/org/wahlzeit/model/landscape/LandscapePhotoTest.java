package org.wahlzeit.model.landscape;

import org.junit.Test;
import org.mockito.Mockito;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class LandscapePhotoTest {

     @Test
    public void getAndSetCountry(){
         LandscapePhoto landscapePhoto = new LandscapePhoto();

         assertNull(landscapePhoto.getCountry());

         String germany = "Germany";
         landscapePhoto.setCountry(germany);

         assertEquals(germany, landscapePhoto.getCountry());
     }

     @Test
     public void getAndSetLandscape(){
         LandscapePhoto landscapePhoto = new LandscapePhoto();

         assertNull(landscapePhoto.getLandscape());

         String type = "WORLD";
         landscapePhoto.setLandscape(type);

         assertEquals(type, landscapePhoto.getLandscape().getLandscapeType().getValue());
     }
     @Test
    public void readFrom() throws SQLException {
         ResultSet rset = Mockito.mock(ResultSet.class);
         //needs to be initizalised because of validation
         Mockito.when(rset.getString("owner_email_address")).thenReturn("anyone@fau.de");
         Mockito.when(rset.getString("owner_home_page")).thenReturn("https://anything.com");

         LandscapePhoto landscapePhoto = new LandscapePhoto();
         landscapePhoto.readFrom(rset);

         Mockito.verify(rset, Mockito.times(1)).getString("tags");
         Mockito.verify(rset, Mockito.times(1)).getString("country");
     }

     @Test
    public void writeOn() throws SQLException, MalformedURLException {
         ResultSet rset = Mockito.mock(ResultSet.class);
         LandscapePhoto landscapePhoto = new LandscapePhoto();
         String germany = "Germany";
         landscapePhoto.setCountry(germany);
         //needs to be initizalised because of toString() operation
         landscapePhoto.setOwnerHomePage(new URL("https://www.anything.com"));

         landscapePhoto.writeOn(rset);

         Mockito.verify(rset, Mockito.times(1)).updateString("tags", landscapePhoto.getTags().asString());
         Mockito.verify(rset, Mockito.times(1)).updateString("country", landscapePhoto.getCountry());
     }
}
