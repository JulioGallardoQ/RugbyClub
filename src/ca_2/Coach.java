/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julio
 */
public class Coach {
    //Private variable String specialist that will take the type of specialist of the coach
    private String specialist;
    // This is the constructor for the Coach class which takes a String specialist
    public Coach (String specialist){
     
    this.specialist = specialist;
    
    
    }
    //Creating getters and setters for all added details
    
    //SPECIALIZATION
      public String getSpecialization(){
    //this getter allows us to get the coach specialization from the coach class
        return specialist;
     
}
    
    public void setSpecialization(String specialist){
    //overwrite the specialization of the coach with this value
        this.specialist = specialist;
        
    }
    

}
