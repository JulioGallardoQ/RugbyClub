/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

import java.util.List;

/**
 *
 * @author julio
 */
public class Under15Squad extends Team{
    //type of Team named Under15Squad that extends Team
  
    //this is the constructor for the class Under15Squad that takes the constructor of the super class Team
    public Under15Squad(String name, List<Player> players, List<Coach> coach) {
        //the Under15Squad name,players,coach are inherit properties from the class Team
        super(name,players,coach);
        
      
    }
   
    
}
