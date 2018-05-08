/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.cowsaysmoo;

import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jared
 */
public class ShapeShift {
    public void doTransaction(String amount, String withdrawal, String pair, String returnAddress){
        try {
            HttpURLConnection con = (HttpURLConnection) ((new URL("shapeshift.io/shift").openConnection()));
            con.setDoOutput(true);
           
            con.setRequestMethod("POST");
            con.connect();
            
            StringReader reader = new StringReader("{}");
        } catch (MalformedURLException ex) {
            Logger.getLogger(ShapeShift.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ShapeShift.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
