/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Julio Cesar Gallardo Quintero - 2023149
 */
public class generalMethods {
//This Java class contains all the Methods that are not the main methods in our interface
//all the task that needs to do repeatedly like, ask user to introduce an integer
//read the file of data, Sort and Searching algorithms as well as validations to mitigate
//errors at the user input.

    /**
     * Ask the user to enter an integer value by issuing a prompt
     * The input must be an integer - if not, display an error and re-prompt
     * The method will not enforce any restriction on the integer
     * @param prompt - a question or prompt to display
     * @param inRange that will help us test if the number input is in a range
     * using Predicate function 
     * @return an integer entered by the user
     */

    public static int askUserforInt(String prompt, Predicate<Integer> inRange){
       //creating a scanner to take input from the user
       Scanner myKB = new Scanner(System.in);
       //our int variable where we are gonna store the value.
       int userInt;
        do {            
           
        //keep going while input is NOT an integer
        while (!myKB.hasNextInt()){
        System.out.println(prompt); //issue prompt
        System.out.println("Enter numbers only");
        myKB.next(); //read input from user 
       }
        //when the user enter a integer then we stored the value in this variable
        userInt = myKB.nextInt();
           //then we are gonna check if this value is in the range that we need
           //depent on our necesities in our different methods in the implementation
           //if the condition its true we are gonna promt again becasue that is not a valid option 
           //for our necesity (this to create a generic method to ask the user for an integer)
           if (!inRange.test(userInt)) {
               System.out.println("");
               System.out.println("Invalid option!");
               System.out.println(prompt);
           }
           //we will finish the do while when the user enter a number that is in the range that
           //we need
         } while (!inRange.test(userInt));
        
       //this return the user number to use it in the menu.
       return userInt;
   }
    
    
    /**
     * Read a file and  let the user introduce the name of the file
     * @param args and will 
     * @return an ArrayList of data with the information of the applicants that want to enter to the rugby club.
     * @throws java.io.FileNotFoundException if there are any error or don't find the file.
     */
   
        
    public static  ArrayList<String> readFile(String args) throws FileNotFoundException {
        
        
    // Create a scanner to read from the command line
    Scanner input = new Scanner (System.in);
    // Get the name of the file to read from the user input
    System.out.println("Enter the name of your file (including file extension): ");
    String filename = input.next();
    //initializing the ArrayList data
    ArrayList <String> data = new ArrayList<>();
    try {

     /*
     * Create a new Scanner object which will read the data from the file passed in. 
     * While there are Strings left in the file (i.e. while scanner.hasNextLine() is true), 
     * keep reading them.
     */
     Scanner in = new Scanner(new File(filename));
     //creating the variables that we are gonna use in this method 
     String value;
     //reading the first line that contains the headers (skipping the line)
     in.nextLine();
     // while strings values remain in the file
     while (in.hasNextLine()) {  
         // take in the next string value from the file
         value = in.nextLine();
         //if the value is not empty then add that value to the data list
         if (!value.isEmpty()) {
             data.add(value);
         } 
         
     }
     //if the user introduce the correct file name and this is read successfully
     //then will be print a message of success and return the ArrayList with the data
     System.out.println("\nFile read Successfully\n");
     return data;
     
    } catch (FileNotFoundException exception) {
     //if the user file is not found then we will deploy a message informed that
     System.out.println("That file was not found. Program terminating...\n");
     //and we terminate the program in a status (0) that means normal termination
     //just to end the program in a good looking without show errors.
     System.exit(0);
    } 
      return null;
    }
    
    //this is just a version of the readfile method that I used to do some testing in Junit
    public static  ArrayList<String> testReadFile(String filename) throws FileNotFoundException {
        
    // Create a scanner to read from the command line
    ArrayList <String> data = new ArrayList<>();
    try {  
     /*
     * Create a new Scanner object which will read the data from the file passed in. 
     * While there are Strings left in the file (i.e. while scanner.hasNextLine() is true), 
     * keep reading them.
     */
     Scanner in = new Scanner(new File(filename));
     //creating the variables that we are gonna use in this method 
     String value;

     // while strings values remain in the file
     while (in.hasNextLine()) {  
         // take in the next string value from the file
         value = in.nextLine();
         //if the value is not empty then add that value to teh data list
         if (!value.isEmpty()) {
             data.add(value);
         } 
         
     }
     //if the user introduce the correct file name and this is read successfully
     //then will be print a message of success and return the ArrayList with the data
     System.out.println("\nFile read Successfully\n");
     return data;
     
    } catch (FileNotFoundException exception) {
     //if the user file is not found then we will deploy a message informed that
     System.out.println("That file was not found. Program terminating...\n");
     //and we terminate the program in a status (0) that means normal termination
     //just to end the program in a good looking without show errors.
     System.exit(0);
    } 
      return null;
    }
       
    /**
     * This method named partition takes  
     * @param list as
     * parameter and defines a range with 
     * @param low and 
     * @param high values, range where we are gonna iterate through,
     * @return an in as the pointer value 
     */
    
    public static  int partition( List<? extends Person>list,int low, int high){
    
    //setting our pivot element as the highest for our range 
    //setting this as String type letting us storing the name in the highest position  
    String pivot = list.get(high).getName();  
      
    
    //setting our pointer as low (that will be the start of our range)
    int pointer = low;
    
        //for loop that iterates trought all the elements in the range that we settled
        for (int i = low ; i < high; i++) {
           
            
            //our condition that is going to compare ignoring the case 
            //if the names inside the list are less, equal or greater
            //than the name that is in our pivot position
            //if this is less than the pivot name this is gonna be true.
            if ( (list.get(i).getName().compareToIgnoreCase(pivot)) < 0 ) {
               
                //and is gonna swap all the information of the person respectively in that index
                //for the pointer position 
                Collections.swap(list, i, pointer);
                
                
                //then the pointer is gonna increment.     
                pointer++;
              }
           
        
        }
        //then after we iterated on all the elements we need to put the pivot at its correct sorted position in the array
        //changing the pivot for the pointer element position.
        Collections.swap(list, pointer, high);
            
        //returning the position that we gonna use in QuickSortBTA method.
        return pointer;
    
    }
   
    
    /**
     * This method named QuickSort takes  
     * @param list
     *  as parameter and defines a range with 
     * @param low and 
     * @param high values, range where we are gonna iterate through
     * then compares if the low value is less than the higher if it is, it will proceed 
     * with the partition method storing the value of the pointer from that method 
     * in the variable p and will recursively do the same but from before the pivot and 
     * after the pivot that we taken from the partition. 
     */  
    
    public static void quickSort( List<? extends Person>list,int low, int high) {
       
       //condition to check if the low value is less than the higher if it is then
       //its gonna proceed with the partition method.
        if (low < high) {
        int p = partition(list, low, high);
         //recursive method for the specific section in the array but before the pivot 
         //that we have taken from the partition.
         quickSort(list, low, p-1);
        //recursive method for the specific section in the array but after the pivot 
         //that we have taken from the partition.
         quickSort(list, p+1, high);
        
        }
        
}
    
   
    
    /*
     isNumeric method, help us checking if a string
     is numeric or not, using matches with a regex 
     */
    
     public static boolean isNumeric(String str){
    
    return str.matches("-?\\d+(\\.\\d+)?");
    
    }
    
    /**
     * binarySearch to use with class Person
     *  that use as 
     * @param list of Person objects and
     * @param target an Array of strings where will be the target to reach.
     * @return the index position of the target or -1 if is not there
     */
     
    public static int binarySearch(List<Person> list, String [] target) {
       //sorting the data before pass to the searching algorithm 
       mergeSort(list, 0, list.size()-1);
         
        //creating the variables
        //setting the middle as the middle of the list.
        int middle = list.size()/2;
        //setting the left pointer at start
        int leftPointer = 0;
        //setting the right pointer at the end of the list
        int rightPointer = list.size()-1;
          
        //while loop that is gonna continue until the left point is greater than the right point
        while (leftPointer <= rightPointer) {   
            //Storing the result of the comparing in the variables name and last name
            //that will be use in the conditions
            //this condition use the java String method compareTo 
            //which is gonna compare if the name on the middle of list
            //is less, equal or greater than the target if this is less than the target
            //will give us a -1, if its equal 0 and if its greater 1
            //and toLowerCase(to make it at the comparation as lower case)using this because it was not working properly 
            //the compare to ignorecase some times.
            int name = list.get(middle).getName().toLowerCase().compareToIgnoreCase(target[0].trim().toLowerCase());
            int lastName = list.get(middle).getlastName().toLowerCase().compareToIgnoreCase(target[1].trim().toLowerCase());
            
            //if the names result is -1 or the name is equal to 0(that means that the name is already found and  lastname is -1 
            if ( name < 0 || (name == 0 && lastName < 0) ){
               //leftpointer moves one position after the middle
                leftPointer = middle +1;
                
            //if the names result is 1 or the name is equal to 0(that means that the name is already found and  lastname is 1
            } else if (name > 0 || (name == 0 && lastName > 0 )){
                //rightpointer moves one position  before the middle
                rightPointer = middle -1;   
            //if neather of the conditions above are true,it returns middle
            //that means that the target is at the middle position.
            }else {
                return middle;
            }
            //every time that do another iteration its gonna change the middle potition
            //respectively to the news pointer positions.
            middle = (leftPointer + rightPointer)/2;
            
        }//if the target is not found in the data set this is gonna return -1.
        return -1;
    }

    /**
     * binarySearch to use with class Player
     * that use as 
     * @param list of Player objects and
     * @param target an Array of strings where will be the target to reach.
     * @return the index position of the target or -1 if is not there
     */
     
    public static int binarySearchPlayer(List<Player> list, String [] target) {
       //sorting the data before pass to the searching algorithm 
       mergeSortP(list, 0, list.size()-1);
         
        //creating the variables
        //setting the middle as the middle of the list.
        int middle = list.size()/2;
        //setting the left pointer at start
        int leftPointer = 0;
        //setting the right pointer at the end of the list
        int rightPointer = list.size()-1;
          
        //while loop that is gonna continue until the left point is greater than the right point
        while (leftPointer <= rightPointer) {   
            //Storing the result of the comparing in the variables name and last name
            //that will be use in the conditions
            //this condition use the java String method compareTo 
            //which is gonna compare if the name on the middle of list
            //is less, equal or greater than the target if this is less than the target
            //will give us a -1, if its equal 0 and if its greater 1
            //and toLowerCase(to make it at the comparation as lower case)using this because it was not working properly 
            //the compare to ignorecase some times.
            int name = list.get(middle).getName().toLowerCase().compareToIgnoreCase(target[0].trim().toLowerCase());
            int lastName = list.get(middle).getlastName().toLowerCase().compareToIgnoreCase(target[1].trim().toLowerCase());
            
            //if the names result is -1 or the name is equal to 0(that means that the name is already found and  lastname is -1 
            if ( name < 0 || (name == 0 && lastName < 0) ){
               //leftpointer moves one position after the middle
                leftPointer = middle +1;
                
            //if the names result is 1 or the name is equal to 0(that means that the name is already found and  lastname is 1
            } else if (name > 0 || (name == 0 && lastName > 0 )){
                //rightpointer moves one position  before the middle
                rightPointer = middle -1;   
            //if neather of the conditions above are true,it returns middle
            //that means that the target is at the middle position.
            }else {
                return middle;
            }
            //every time that do another iteration its gonna change the middle potition
            //respectively to the news pointer positions.
            middle = (leftPointer + rightPointer)/2;
            
        }//if the target is not found in the data set this is gonna return -1.
        return -1;
    }

    
    public static boolean isEmail(String email) {
        // Pattern to validate an email, case insensitive to not take into count if are capital leters or not
        //the patter is defined as letters, numbers or (_) and (.) before an @ after letters and (.) and more than 2 letters 
        Pattern pattern = Pattern.compile("^[A-Z0-9+_.-]+@[A-Z0-9.-]+\\.[a-z]+${2,}", Pattern.CASE_INSENSITIVE);
        
        //Matcher where we are gonna match if our email introduced by the user match with the patter that we are stablished
        Matcher matcher = pattern.matcher(email);   
        
        //if this matchs return that is true
        if (matcher.find() == true) {
           
            return true;
        } else {
            //else print a message that says that is an invalid email.
            System.out.println("");
            System.out.println("Invalid Email.");
            
        }//and return false
        return false;
    }
    
    /**
     * validInfo method that will help us to validate the information
     * when the user introduce a the inputs of the information
     * when add a new player takes as 
     * @param players objects list 
     * @return the information as an Array of strings with the 5 
     * data required to fill the information of a player 
     */
    public static String [] validInfo(List<Player>players) {
            //initializing the scanner object that let us take the input from the user
            Scanner myKB = new Scanner(System.in);
            //initializing the Array of string that will have the lenght of 5 elements 
            //with the name of playerDetails 
            String [] playerDetails = new String[5]; 
            //initialize the flags for the different data that we want to collect 
            //let it us if they are correct or not
            boolean age = false;
            boolean email = false;
            boolean name = false;
            boolean lastName = false;
            boolean gender = false;
            
            //asking to the user for the name of the new player
            System.out.println("Please input the name: ");
            //while, that will continue till the name that the user introduce is correct
             while (!name){
                 //storing this input from the user in our space index position 0
                 //of the playerDetails Array
                 playerDetails[0] = myKB.nextLine();
                 
                 //condition that will check that this playerDetails[0] is not empty
                 //and matches the regex that will check if are only letters in the input
                 if (!playerDetails[0].isEmpty() && playerDetails[0].matches("[a-zA-Z]+")) {
                     //if this is true then we change the flag to true and continue asking the next 
                     //playerDetails
                    name = true; 
                 }else {
                     //otherwise we let the user know that the input entered is not a valid name
                     System.out.println("");
                     System.out.println("Please enter a valid name(enter just letters)");
                 }
            }
            //asking to the user for the last name of the new player
            System.out.println("\nPlease input the last name: "); 
            //while, that will continue till the name that the user introduce is correct
             while (!lastName){
                 //storing this input from the user in our space index position 1
                 //of the playerDetails Array
                 playerDetails[1] = myKB.nextLine();
                 //condition that will check that this playerDetails[1] is not empty
                 //and matches the regex that will check if are only letters in the input
                 if (!playerDetails[1].isEmpty() && playerDetails[1].matches("[a-zA-Z]+")) {
                     //if this is true then we change the flag to true and continue asking the next 
                     //playerDetails
                    lastName = true; 
                 }else {
                     //otherwise we let the user know that the input entered is not a valid name
                     System.out.println("");
                     System.out.println("Please enter a valid last name(enter just letters)");
                 }
             }
            //asking to the user for the email of the new player
            System.out.println("\nPlease input Email(i.e Rugby@Club.com) :");
                //while, that will continue till the email that the user introduce is correct
                while (!email) {            
                 //storing this input from the user in our space index position 2
                 //of the playerDetails Array  
                  playerDetails[2] = myKB.nextLine();
                //condition that will check if the email is valid with the method is Email
                //that is in this java class
            if (!isEmail(playerDetails[2].trim())) {
                //if the condition is true, that means that the email is not valid
                //displaying a message to re enter the email and example of one valid email
                System.out.println("Please enter a valid email i.e : rugby._@info.com");
                
            }else {
             //otherwise we change the flag of the email to true let us know continue
             //to the next detail
             email = true;
            }
            } 
            //asking to the user for the Gender of the new player
            System.out.println("\nPlease input the Gender : ");    
            //while, that will continue till the name that the user introduce is correct
            while (!gender){
                //storing this input from the user in our space index position 3
                 //of the playerDetails Array
                 playerDetails[3] = myKB.nextLine();
                 
                 //condition that will check that this playerDetails[3] is not empty
                 //and matches the regex that will check if are only letters in the input
                 if (!playerDetails[3].isEmpty() && playerDetails[3].matches("[a-zA-Z]+")) {
                    gender = true; 
                 }else {
                     //otherwise we let the user know that the input entered is not a valid name
                     System.out.println("");
                     System.out.println("Please enter a valid Gender(enter just letters)");
                 }
             }
             //asking to the user for the age of the new player
             System.out.println("\nPlease input the Age : ");
             //while, that will continue till the name that the user introduce is correct
            while (!age) {
            //storing this input from the user in our space index position 5
            //of the playerDetails Array  
            playerDetails[4] = myKB.nextLine();
             
            //condition that will check that this playerDetails[5] is not empty
            //and is the input is numeric
            if (playerDetails[4].isEmpty() || !isNumeric(playerDetails[4].trim())) {
                System.out.println("");
                System.out.println("Invalid input for Age");
                
                } else if ((Integer.parseInt(playerDetails[4].trim()) >= 10 && (Integer.parseInt(playerDetails[4].trim()) <= 51) )) {
                   //if the user introduce numbers and  is not empty
                   //it will check if the user introduce numbers between 10 and 51
                   //changing the flag to true, let it us continue
                    age = true;  
                }else{
                 //otherwise send a message to the user that the values must be between 10 and 51    
                System.out.println("Please input numeric values between 10 and 51 only !");
                }
 
               

    }   //if all the player details are correct, then we will return the player details
        return playerDetails;
    }
    
     /**
     * Recursive Method printPeople that use as parameters
     * @param people and a number
     * @param n this method is a recursive, use a base case to define 
     * when stop print people and it will continue print until reach that 
     * in this case our n is ==0 and every time that print one person is gonna add 1
     * until gets to n = 20.
     */
    
    public static void printPeople(List<Person>people, int n) {

        //create the base case: if the count reaches 20 will terminate
        if(n == 20){
        //prints to show when the print is already finish and back to the previous menu
        System.out.println("print complete!\n");
   
    }else {
            //Recursive case : keeps printing the people until we reach the base
            System.out.println("Person Name: " + people.get(n).getName()+" "+ people.get(n).getlastName());
            System.out.println("Person Email: "+ people.get(n).getEmail());
            System.out.println("Person Gender: " + people.get(n).getGender());
            
            System.out.println("-------------------------------------------------");
            printPeople(people, n+1);//calling the method
        }
    }
    
/**
     * This method named mergeSort takes  
     * @param list from the class Person
     *  as parameter and defines a range with 
     * @param left 
     * @param right values, range where we are gonna iterate through
     * then compares if the value in the left position is less than the value in the right position 
     * if it is, it means that there are 2 elements in this range that will be sorted
     * then will proceed with the partition, using the middle index position dividing the range into two
     * using the mergeSort itself recursively on the left half and doing the same with the right side
     * 
     */     
public static void mergeSort(List<Person> list, int left, int right) {
    //checking if the value in the left position is less 
    //than the value in the right position
    //if this is true, means that there are 2 elements in this range that will be sorted
    if (left < right) {
        //getting the middle index position, adding the left value plus the right value 
        //in our cases will starts from the index 0(left) and index list.size()-1(right)
        //dividing this by 2, getting the middle point
        int mid = (left + right) / 2;
        //using recursively the same mergeSort method in the left part of the partition
        mergeSort(list, left, mid);
        //using recursively the same mergeSort method in the right part of the partition
        mergeSort(list, mid + 1, right);
        //using the merge method to join the sorted left and right
        merge( list, left, mid, right);
    }
      
}

/* This method named merge takes  
     * @param list from the class Player
     *  as parameter and defines a range with 
     * @param left 
     * @param right values, range where we are gonna iterate through
     * then compares if the value in the left position is less than the value in the right position 
     * if it is, it means that there are 2 elements in this range that will be sorted
     * then will proceed with the partition, using the middle index position dividing the range into two
     * using the mergeSort itself recursively on the left half and doing the same with the right side
     * 
     */  
private static void merge(List<Person> people, int left, int mid, int right) {
    //Creating a temporary list from the class Person that will store 
    List<Person> tempList = new ArrayList<>(right - left + 1);
    //initializing our left and right point (for the range)
    int i = left;
    int j = mid + 1;
    
    //this while will help us to merge our lists
    //its gonna continue until i will be greater than the mid position
    //and j greater than the right, where mid represents the end of the left list
    //and right the end of the right list
    while (i <= mid && j <= right) {
        
        //then we will compare the names of the person in the current position 
        //of both lists, using the compareTo method of the strings, we can compare which 
        //of the names comes before the oher, if the first name comes first it will give us -1
        //if the 2 names are equals is gonna give us 0 and if the second name is before the 
        //first one its gonna give us 1
        if (people.get(i).getName().compareTo(people.get(j).getName()) <= 0) {
            //if this condition is true, that means that the first name of the person comes first
            //and then we will add the person to the tempList
            tempList.add(people.get(i));
            //and moving to the next position on the left side of the elements.(left list)
            i++;
           
        } else {
            //otherwise if the person in the right half list
            //is before the person name in the first half list 
            //we add that person to our tempList 
            tempList.add(people.get(j));
            //and moving to the next position on the right side of the elemetns (right list)
            j++;
            
        }
    }
    //with the process that we did above, its not 100% sure  that all the people
    //that are in the both lists were compared due to the size of the list
    //if one of the sublist is greater than the other sublist, the elements in that 
    //sublist cannot be compared and we need to add them to the list
    //this next while is helping us with the first half list 
    while (i <= mid) {
        //adding the people that left in the left side to our tempList
        tempList.add(people.get(i));
        //and moving to the next position on the left side of the elements.(left list)
        i++;
    }
    //this next while is helping us with the second half list 
    while (j <= right) {
        //adding the people that left in the right side to our tempList
        tempList.add(people.get(j));
        //and moving to the next position on the right side of the elements.(right list)
        j++;
    } 
    //to end with this section, we have all our elements sorted in our tempList
    //and we need to add them to our main people list 
    //creating a for loop that will iterate trought our tempList
    //and add them to our main list
    for (int k = 0; k < tempList.size(); k++) {
        people.set(left + k, tempList.get(k));
    }// as this is a recursively method this will happen until the entire list is sorted
        
    
}

/**
     * This method named mergeSortP takes  
     * @param list from the class player
     *  as parameter and defines a range with 
     * @param left 
     * @param right values, range where we are gonna iterate through
     * then compares if the value in the left position is less than the value in the right position 
     * if it is, it means that there are 2 elements in this range that will be sorted
     * then will proceed with the partition, using the middle index position dividing the range into two
     * using the mergeSort itself recursively on the left half and doing the same with the right side
     * 
     */     
public static void mergeSortP(List<Player> list, int left, int right) {
    //checking if the value in the left position is less 
    //than the value in the right position
    //if this is true, means that there are 2 elements in this range that will be sorted
    if (left < right) {
        //getting the middle index position, adding the left value plus the right value 
        //in our cases will starts from the index 0(left) and index list.size()-1(right)
        //dividing this by 2, getting the middle point
        int mid = (left + right) / 2;
        //using recursively the same mergeSort method in the left part of the partition
        mergeSortP(list, left, mid);
        //using recursively the same mergeSort method in the right part of the partition
        mergeSortP(list, mid + 1, right);
        //using the merge method to join the sorted left and right
        mergePlayer(list, left, mid, right);
    }
      
}

/* This method named mergePlayer takes  
     * @param list from the class Person
     *  as parameter and defines a range with 
     * @param left 
     * @param right values, range where we are gonna iterate through
     * then compares if the value in the left position is less than the value in the right position 
     * if it is, it means that there are 2 elements in this range that will be sorted
     * then will proceed with the partition, using the middle index position dividing the range into two
     * using the mergeSort itself recursively on the left half and doing the same with the right side
     * 
     */  
private static void mergePlayer(List<Player> player, int left, int mid, int right) {
    //Creating a temporary list from the class Player that will store 
    List<Player> tempList = new ArrayList<>(right - left + 1);
    //initializing our left and right point (for the range)
    int i = left;
    int j = mid + 1;
    
    //this while will help us to merge our lists
    //its gonna continue until i will be greater than the mid position
    //and j greater than the right, where mid represents the end of the left list
    //and right the end of the right list
    while (i <= mid && j <= right) {
        
        //then we will compare the names of the Player in the current position 
        //of both lists, using the compareTo method of the strings, we can compare which 
        //of the names comes before the other, if the first name comes first it will give us -1
        //if the 2 names are equals is gonna give us 0 and if the second name is before the 
        //first one its gonna give us 1
        if (player.get(i).getName().compareTo(player.get(j).getName()) <= 0) {
            //if this condition is true, that means that the first name of the player comes first
            //and then we will add the player to the tempList
            tempList.add(player.get(i));
            //and moving to the next position on the left side of the elements.(left list)
            i++;
           
        } else {
            //otherwise if the player in the right half list
            //is before the player name in the first half list 
            //we add that player to our tempList 
            tempList.add(player.get(j));
            //and moving to the next position on the right side of the elemetns (right list)
            j++;
            
        }
    }
    //with the process that we did above, its not 100% sure  that all the players
    //that are in the both lists were compared due to the size of the list
    //if one of the sublist is greater than the other sublist, the elements in that 
    //sublist cannot be compared and we need to add them to the list
    //this next while is helping us with the first half list 
    while (i <= mid) {
        //adding the players that left in the left side to our tempList
        tempList.add(player.get(i));
        //and moving to the next position on the left side of the elements.(left list)
        i++;
    }
    //this next while is helping us with the second half list 
    while (j <= right) {
        //adding the players that left in the right side to our tempList
        tempList.add(player.get(j));
        //and moving to the next position on the right side of the elements.(right list)
        j++;
    } 
    //to end with this section, we have all our elements sorted in our tempList
    //and we need to add them to our main player list 
    //creating a for loop that will iterate trought our tempList
    //and add them to our main list
    for (int k = 0; k < tempList.size(); k++) {
        player.set(left + k, tempList.get(k));
    }// as this is a recursively method this will happen until the entire list is sorted
        
    
}

    /**
     * linearSearch method 
     * that use as parameter a
     * @param list of players and and array of strings as
     * @param key 
     * @return 
     */
     
      public static int linearSearch(List<Player>list, String [] key) {
        //sorting the data before pass to the searching algorithm 
          mergeSortP(list, 0, list.size()-1);
        //iterating trough each element on the list.   
        for (int i = 0; i < list.size(); i++) {
            
            //Storing the result of the comparing in the variables name and last name
            //that will be use in the conditions
            //this condition use the java String method compareTo 
            //which is gonna compare if the name on the position i of the  list
            //is less, equal or greater than the target if this is less than the target
            //will give us a -1, if its equal 0 and if its greater 1 
            int name = list.get(i).getName().compareToIgnoreCase(key[0].trim());
            int lastName = list.get(i).getlastName().compareToIgnoreCase(key[1].trim());
            
            //if the names result is equal to 0 and  lastname is 0(equal to the key)(that means that the name is already found 
            if ( (name == 0 && lastName == 0)) {
                
                return i;
            }
        }
        return -1;
        }     
      
        /**
        * method playerAlreadyExist
        * that use as parameters
        * @param players list  and 
        * @param playerDetails arrays of strings and 
        * @return a boolean if a player already exist in the list of players or not
        */
      public static boolean playerAlreadyExist(List<Player> players, String [] playerDetails) {
          //for loop that iterates trough the players list
          //checking every player object to see
          for (Player player : players) { 
              //if the player name and last name aready exist in the list
              //if this is true will return true
                            if (player.getName().equalsIgnoreCase(playerDetails[0].trim()) && 
                             player.getlastName().equalsIgnoreCase(playerDetails[1].trim())) {
                            return true;
    }    
    }//if the player doesnt exist yet, it will return false
        return false;
    

      }
      
}