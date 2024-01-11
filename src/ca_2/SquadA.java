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
public class SquadA extends Team{
   //type of Team named SquadA that extends Team
  
    //this is the constructor for the class SquadA that takes the constructor of the super class Team
    public SquadA(String name, List<Player> players, List<Coach> coach) {
     //the SquadA name,players,coach are inherit properties from the class Team    
        super(name,players,coach);
        
        
    }
   


 
}
