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
//abstract class Team
public  class Team {
    
    //Private variable String name that will take the name of a team
    private String name;
    //Private variable players that is a List of player class  that with take the player in the team
    private List<Player>players;
    private List<Coach>coachs;
    
    // This is the constructor for the Team class which takes a String name 
    public Team (String name,List<Player> players, List<Coach> coach){
    //the Team name is set here
    //this is the Team name
    this.name = name;
    this.players = players;
    this.coachs = coach;
    
    
    }
    //Creating getters and setters for all added details
    //NAME
     public String getName(){
    //this getter allows us to get the coach name from the coach class
        return name;
     
}
      public void setName(String name){
    //overwrite the name of the Team with this value
        this.name = name;
        
    }
     //This getter allow us to get the players that is in team 
     public List<Player> getPlayers() {
        return players;
    } 
    //setter for set the players 
      public  void setPlayers(List<Player>players) {
        this.players = players;
    }
      
       public List<Coach> getCoach(){
    //this getter allows us to get the coach name from the coach class
        return coachs;
     
}
      
     public  void  setCoach(List<Coach> coach){
    //this getter allows us to get the coach name from the coach class
        this.coachs = coach;  
}   
  
   
}
