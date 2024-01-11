/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

/**
 *
 * @author julio
 */
public class Person {
    //super class Person
    //this represent the name of a person
    private String name;
    //represent the last name of a person
    private String lastName;
    //represent the email of a person
    private String email;
    //represent the gender of a person
    private String gender;
    
    //constructor for the class person
    public Person (String name, String lastName, String email, String gender ){
    //the person name,lastName,email,and gender  is set here
    
    this.name = name;
    this.lastName = lastName;
    this.email = email;
    this.gender = gender;
    
    }
      //Creating getters and setters for all added details
    //NAME
     public String getName(){
    //this getter allows us to get the person name from the person class
        return name;
     
}
    
    public void setName(String name){
    //overwrite the name of the person with this value
        this.name = name;
        
    }
    
    public String getlastName(){
    //this getter allows us to get the person lastName from the person class
        return lastName;
     
}
    
    public void setlastName(String lastName){
    //overwrite the lastName of the person with this value
        this.lastName = lastName;
        
    }
    
    public String getEmail(){
    //this getter allows us to get the person email from the person class
        return email;
     
}
    
    public void setEmail(String email){
    //overwrite the email of the person with this value
        this.email = email;
        
    }
    
        public String getGender(){
    //this getter allows us to get the person gender from the person class
        return gender;
     
}
    
    public void setGender(String gender){
    //overwrite the gender of the person with this value
        this.gender = gender;
        
    }
    
    
}
