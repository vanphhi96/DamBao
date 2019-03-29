/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vanph
 */
public class Constants {

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public static Date getConvertDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        java.util.Date date1 = null;
        try {
            date1 = format.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (date1 != null) {
            return new java.sql.Date(date1.getTime());
        }
        return null;
    }
    
  
}
