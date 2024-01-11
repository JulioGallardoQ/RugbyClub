/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

/**
 *
 * @author julio
 */
public class Back extends Player{
    //type of Player Back that extends player
  
    //this is the constructor for the class Back that takes the constructor of the class Player
    public Back (String name, String lastName, String email, String gender,int age,String position,Coach coach,Team team) {
    //the Back name,lastName,email,gender, age,position,coach,team are inherit properties from the class player
       super(name, lastName, email, gender,age,position,coach,team);
       
    }
    
}
