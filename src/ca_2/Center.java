/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

/**
 *
 * @author julio
 */
public class Center extends Player{
     //type of Player Center that extends player
  
    //this is the constructor for the class Center that takes the constructor of the class Player
    public Center (String name, String lastName, String email, String gender,int age,String position,Coach coach,Team team) {
    //the Center name,lastName,email,gender, age,position,coach,team are inherit properties from the class player
       super(name, lastName, email, gender,age,position,coach,team);
       
    }
   
    
    
}
