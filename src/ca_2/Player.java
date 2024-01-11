/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

/**
 *
 * @author julio
 */
public class Player extends Person{
    
   //Private set of variables that we are gonna use in our players class
   //this represent the age of the player 
   private int age;
   //this represent the position of the player
   private String position;
   //this represent the coach of the player
   private Coach coach;
   //this represent the team of the player
   private Team team;
    
   //this is the constructor for the class player that takes the constructor of the superclass Person
    public Player(String name, String lastName, String email, String gender,int age,String position,Coach coach,Team team) {
        //the Player name,lastName,email,and gender are inherit properties from the super class Person 
        super(name, lastName, email, gender);
        //the age,position,coach, team at setter here
        this.age = age;
        this.position = position;
        this.coach = coach;
        this.team = team;
    }
    
    //Creating our getters and setters for a player

    //AGE
    public int getAge(){
    //this getter allows us to get the age from a player
    return age;

}
    public void setAge(int age){
    //this will set the age of a player
    this.age = age;

}
    //COACH
     public Coach getCoach() {
        //this getter allows us to get the Coach  from the Player 
        return coach;
    }
    
     public void setCoach(Coach coach) {
     //this will set the coach of a player
        this.coach = coach;
    } 
   
     //TEAM
    public Team getTeam() {
        //this getter allows us to get the Team  from the Player 
        return team;
    }
    
     public void setTeam(Team team) {
      //this will set the teeam of a player
        this.team = team;
    } 
     
    //position
    public String getPosition() {
        //this getter allows us to get the position  from the Player 
        return position;
    }
    
     public void setPosition(String position) {
     //this will set the position of a player    
        this.position = position;
    } 
    
    
}
