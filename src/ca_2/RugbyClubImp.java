/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Julio Cesar Gallardo Quintero - 2023149
 * This is the RugbyClubImp that implements our RugbyClub Interface.
 */
public class RugbyClubImp implements RugbyClub {
    
    //Creating data list where we are gonna store the data from the file
    List<String> data;
    //creating the people list of the class Person
    List<Person>people;
    //Creating  Player, Coach, Team List to pass in the proper types to each Player,Coach and Team Type class
    private List<Player> players;
    private List<Coach> coachs;
    private List<Team> teams;
    
    //Creating the list to store players in diferents Squads
    List<Player> squadAPlayers;
    List<Player> squadBPlayers;
    List<Player> squadUnderPlayers;
    Scanner scanner;
    public RugbyClubImp() throws FileNotFoundException {
       
        //Reading the file and store the data in the list data
        data = generalMethods.readFile("introduce the name of your file extention include");
        
        //Inizializing all the lists that we are gonna use and the scanner
        this.people= new  ArrayList<>();
        this.players = new ArrayList<>();
        this.coachs = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.squadAPlayers = new ArrayList<>();
        this.squadBPlayers = new ArrayList<>();
        this.squadUnderPlayers = new ArrayList<>();
        this.scanner= new Scanner(System.in);
        
        //List where we are gonna store coaches for the 3 different squads
        //List<Coach> coachSA;
        //List<Coach> coachSB;
        //List<Coach> coachSUnder;
        
        //looping into the data list to store the information in the array infoPerson
        for (String info : data) {
            //using the split every time that finds a comma (,) to divide all the information
            String[] infoPerson = info.split(",");
            //variables to store the information respectively and using trim to remove the rest
            String name = infoPerson[1].trim();
            String lastName = infoPerson[2].trim();
            String email = infoPerson[3].trim();
            String gender = infoPerson[4].trim();

            //adding new person to my List people to store the information in the list.
            people.add(new Person(name, lastName, email, gender));
        }   
       
        //creating the coaches for the teams namely headCoach,assistantCoach,ScrumCoach 
        //which will be assigned to the players
        HeadCoach headCoach = new HeadCoach("Head");
        AssistantCoach assistantCoach = new AssistantCoach("Assistant");
        ScrumCoach scrumCoach = new ScrumCoach("Scrum");
        
        //adding coaches to their respective list where we can add specific coaches in the future
        //coachSA = Arrays.asList(headCoach, assistantCoach, scrumCoach);
        //coachSB = Arrays.asList(headCoach, assistantCoach, scrumCoach);
        //coachSUnder = Arrays.asList(headCoach, assistantCoach, scrumCoach);
        
        //adding the coaches to the main list
        coachs.add(headCoach);
        coachs.add(assistantCoach);
        coachs.add(scrumCoach);
        
        //creating the squads which we will assign to the players
        SquadA squadA = new SquadA("SquadA", squadAPlayers, coachs);
        SquadB squadB = new SquadB("SquadB", squadBPlayers, coachs);
        Under15Squad squadUnder = new Under15Squad("Squad15Under", squadUnderPlayers, coachs);
        
        
        //adding squads to the main team list
        teams.add(squadA);
        teams.add(squadB);
        teams.add(squadUnder);
        
        
    }
    
    
    //Overriding our sortSearchPeople() method that we created 
    //in the Rugby Club interface use a list that we sort and
    //shows the first 20 elements of it.
    //we need to use the same function name from the parent class that we extended
    //in order to override that method
    @Override
    public void sortSearchPeople() {
        //Variables for the colors that we are using to format the answer 
        String red = "\033[31m";
        String blue = "\033[34m";
        String b = "\u001B[0m";
        
        //small menu, that will ask to the user if he want sort the list of people
        //search a specific person or back to the menu
        System.out.println("\nPlease select an option:");
        System.out.println("1).Sort the list of people");
        System.out.println("2).Search a specific person");
        System.out.println("3).Back to the main menu");
        //storing the int value provided by the user using the askUserforInt method which is in our generalMethods
        int option = generalMethods.askUserforInt("Please enter just numbers", (Integer i) -> i >= 1 && i <= 3);

        //initializing a switch, that will use the user input to pick a case
        switch (option) {
            //case 1 will be when the user still want to add a new player
            //giving a chance to introduce the information again wittout back to the main menu
            case 1:
                //Making list of copies of the people list, to used it in the sorting method.
                List<Person> copy1 = new ArrayList<>(people);
                List<Person> copy2 = new ArrayList<>(people);

                //sorting the List of people using our QuickSort method in generalMethods
                //Giving 0 as our lowest value and the end of the list as the highest
                //and stablishing the comparator that will take place in the partition method as a comparation 
                //in this case we are using the list people,with the class Person 
                //and getName method to get get the name of the person and compare with the pivot inside partition 
                //and if this is less than 0 we gonna swap the elements.
                long start = System.nanoTime();
                generalMethods.quickSort(copy1, 0, copy1.size() - 1);
                long end = System.nanoTime();
                long execution1 = (end - start);
                start = System.nanoTime();
                generalMethods.mergeSort(copy2, 0, copy2.size() - 1);
                end = System.nanoTime();
                long execution = (end - start);
                generalMethods.mergeSort(people, 0, people.size()-1);

                //Printing the 20 elements using the method printPeople from generalMethods class
                //which is a Recursive method, because of that has been stablished the number of elements
                System.out.println("\nList of people in the RugbyClub sorted by name: ");
                System.out.println("-------------------------------------------------");
                generalMethods.printPeople(copy2, 0);
                System.out.println("The total execution time for quickSort is: " + execution1 + " nanoseconds");
                System.out.println("The total execution time for mergeSort is: " + execution + " nanoseconds");
                System.out.println("Press enter to back to the Menu");
                scanner.nextLine();
                break;

            case 2:
                //for this case 2, as we are searching a specific player, its very important
                //take into acount that as the data base has a lot of name that are the same 
                //but the last name could be different(making them a different person), 
                //therefore we need to be able to search for the complete name of a person.
                
                //variable to store the usrInput in an array of strings that will contain the name and last name 
                //that we will search 
                String[] userInput = new String[2];

                //asking to the user for the name of the player and storing in the variable
                System.out.println("\nPlease enter the name of the person you wish to find: ");
                userInput[0] = scanner.nextLine().trim();
                System.out.println("enter the last name of the person you wish to find: ");
                userInput[1] = scanner.nextLine().trim();
                int binaryIndex = generalMethods.binarySearch(people, userInput);
                
                //condition that is gonna be true when the method return -1 so when didnt find the Target 
                if (binaryIndex == -1) {

                    //printing a message saying that the player is not in the data base or is written wrong 
                    System.out.println("\n" + red + "Sorry" + b + " that player is" + red + " not" + b + " in the data base or please check the name and try again");
                    System.out.println("Please press Enter to get back to the main menu\n");
                    scanner.nextLine();
                } else {

                    //otherwise if its found that target name and last name
                    //we gonna get the index position of that target
                    Person person = people.get(binaryIndex);
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("Person found At position " + red + binaryIndex + "\n");
                    System.out.println(b + "Person Name : " + blue + person.getName() + " " + person.getlastName() + b);
                    System.out.println(b + "Email : " + blue + person.getEmail() + b);
                    System.out.println(b + "Gender : " + blue + person.getGender() + b); 
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("Please press Enter to get back to the main menu\n");
                    scanner.nextLine();
                }
                break;
            case 3:
                //message to go back to the main menu
                System.out.println("\nPlease press Enter to get back to the main menu\n");
                scanner.nextLine();
                break;
            default:
                //default action that will happen if the user select another option that is not in the menu
                System.out.println("That is not a valid option");
        }
        
    }
    

    @Override
    public void searchPlayer() {
    //Overriding our searchPlayer() method that we created 
    //in the Rugby Club interface, use a list of player that we sort and search and
    //showing the specific information of the player that we are searching.
    //we need to use the same function name from the parent class that we extended
    //in order to override that method    
    
   
        //Variables for the colors that we are using to format the answer 
        String red = "\033[31m";
        String blue = "\033[34m";
        String b = "\u001B[0m";
        
        //for this method where we are searching a specific player, its very important
        //take into acount that as the data base has a lot of name that are the same 
        //but the last name could be different, therefore we need to be able to search for the complete name
        //of a player.
        
        //variable to store the usrInput in an array of strings that will contain the name and last name 
        //that we will search 
        String[] userInput = new String[2];


        //asking to the user for the name of the player and storing in the variable
        System.out.println("\nPlease enter the name of the person you wish to find: ");
        userInput[0] = scanner.nextLine().trim();
        //asking to the user for the last name of the player and storing in the variable
        System.out.println("enter the last name of the player you wish to find: ");
        userInput[1] = scanner.nextLine().trim();
        
        long start = System.nanoTime();
        /* the run statement for the algorithm*/
        //using our method to do the search that is on generalMethods class
        //pass in the players list where we are gonna search and the userInput as a target
       
        generalMethods.linearSearch(players, userInput);
        long end = System.nanoTime();
        /*calculate the time difference */
        long execution = (end - start);
        
        //asking to the user for the name of the player and storing in the variable         
        start = System.nanoTime();
        /* the run statement for the algorithm*/
        //using our method to do the search that is on generalMethods class
        //pass in the players list where we are gonna search and the userInput as a target
        //variable to store the usrInput
                
        int binaryIndex = generalMethods.binarySearchPlayer(players, userInput);
        end = System.nanoTime();
        /*calculate the time difference */
        long execution2 = (end - start);
        
        //condition that is gonna be true when the method return -1 so when didnt find the Target 
        if (binaryIndex == -1) {
            
            //printing a message saying that the player is not in the data base or is written wrong 
            System.out.println("\n" + red + "Sorry" + b + " that player is" + red + " not" + b + " in the data base or please check the name and try again");
            System.out.println("The total execution time for Binary search is: " + execution2 + " nanoseconds");
            System.out.println("The total execution time for Linear search is: " + execution + " nanoseconds");
            System.out.println("Please press Enter to get back to the main menu");
            scanner.nextLine();
        } 
            //otherwise if its found that target name and last name
            //we gonna get the index position of that target
            Player player = players.get(binaryIndex);
            
            //checking if player is a forward player
            if(player instanceof Forward){              
            //if this is, condition is true, we set the player object to Forward 
            //let it us access to all the information of that forward player
            //And printing their position,Name,Email,Gender,Age,Position, Coach Type and Team
            Forward forward = (Forward) player;
            System.out.println("-----------------------------------------------------------");
            System.out.println("Player found At position " + red + binaryIndex + "\n");
            System.out.println(b + "Person Name : " + blue + forward.getName() +" "+ forward.getlastName()+ b);
            System.out.println(b + "Email : " + blue + forward.getEmail()+ b);
            System.out.println(b + "Gender : " + blue + forward.getGender()+ b);
            System.out.println(b + "Age : " + blue + forward.getAge()+ b);
            System.out.println(b + "Position : " + blue + forward.getPosition()+ b);
            System.out.println(b + "Coach Type : " + blue + forward.getCoach().getSpecialization()+ b);
            System.out.println(b + "Team : " + blue + forward.getTeam().getName()+ b);
            System.out.println("-----------------------------------------------------------");
            }else if(player instanceof Back){
            //checking if player is a Back player    
            //if this is, condition is true, we set the player object to Back 
            //let it us access to all the information of that Back player
            //And printing their position,Name,Email,Gender,Age,Position, Coach Type and Team
            Back back = (Back) player;
            System.out.println("-----------------------------------------------------------");
            System.out.println("Player found At position " + red + binaryIndex + "\n");
            System.out.println(b + "Person Name : " + blue + back.getName() +" "+ back.getlastName()+ b);
            System.out.println(b + "Email : " + blue + back.getEmail()+ b);
            System.out.println(b + "Gender : " + blue + back.getGender()+ b);
            System.out.println(b + "Age : " + blue + back.getAge()+ b);
            System.out.println(b + "Position : " + blue + back.getPosition()+ b);
            System.out.println(b + "Coach Type : " + blue + back.getCoach().getSpecialization()+ b);
            System.out.println(b + "Team : " + blue + back.getTeam().getName()+ b);
            System.out.println("-----------------------------------------------------------");
            }else if(player instanceof Center){
            //checking if player is a Center player    
            //if this is, condition is true, we set the player object to Center 
            //let it us access to all the information of that Center player
            //And printing their position,Name,Email,Gender,Age,Position, Coach Type and Team
            Center center = (Center) player;
            System.out.println("-----------------------------------------------------------");
            System.out.println("Player found At position " + red + binaryIndex + "\n");
            System.out.println(b + "Person Name : " + blue + center.getName() +" "+ center.getlastName()+ b);
            System.out.println(b + "Email : " + blue + center.getEmail()+ b);
            System.out.println(b + "Gender : " + blue + center.getGender()+ b);
            System.out.println(b + "Age : " + blue + center.getAge()+ b);
            System.out.println(b + "Position : " + blue + center.getPosition()+ b);
            System.out.println(b + "Coach Type : " + blue + center.getCoach().getSpecialization()+ b);
            System.out.println(b + "Team : " + blue + center.getTeam().getName()+ b);
            System.out.println("-----------------------------------------------------------");
            }      
            System.out.println("The total execution time for Binary search is: " + execution2 + " nanoseconds");
            System.out.println("The total execution time for Linear search is: " + execution + " nanoseconds");
            System.out.println("Please press Enter to get back to the main menu\n");
            scanner.nextLine();
        
  
    }

    @Override
    public void listPlayer() {
        //Overriding our listPlayer() method that we created 
        //in the Rugby Club interface use a list of player that we sort in
        //let it us access trought the information of each player 
        //and printing it on the screen a list of all the players that we added.
        //we need to use the same function name from the parent class that we extended
        //in order to override that method  
        
        //sorting the List of players using our QuickSort method in generalMethods
        //Giving 0 as our lowest value and the end of the list as the highest
        //and stablishing the comparator that will take place in the partition method as a comparative 
        //in this case we are using the list players,with the class Player  
        //and getName method to get get the name of the player and compare with the pivot inside partition 
        //and if this is less than 0 we gonna swap the elements to get the ascendant order.
        //in this case (Player:: getName) is the short expression of (Player p)-> p.getName()+
      
        generalMethods.quickSort(players, 0, players.size() - 1);
        
        //flag to check if is already print the head part of our "table".
        boolean alreadyPrint = false;
        
        //Looping trought the players list
        //from the class Player
        for (Player player : players) {

            if (!alreadyPrint) { //checks if the header and lines have already been printed.

                for (int j = 0; j < 140; j++) { //starts a loop to print a line.
                    System.out.print("-");
                } //This line prints a formatted header row with column names.   
                System.out.printf("\n|%-25s|%-35s|%-15s|%-10s|%-10s|%-25s|%-12s|\n", "Name", "Email ", "Gender", "Age", "Position", "Coach Especialization", "Team");
                for (int j = 0; j < 140; j++) {//starts a loop to print a line.
                    System.out.print("-");
                    alreadyPrint = true;
                }
                

            }

            //list all players in respect to their class and then get their attributes i.e age position,coach and Team
            //the first order of operation is to check the players class and attributes
            //checking if player is a forward player
            if (player instanceof Forward) {

                //if this is true, we set the player object to Forward
                Forward forward = (Forward) player;

                //This will prints a formatted data in columns respectibly iterairing trought the arrays and printing the player information. 
                // The format includes name, email, gender, age, position, coachs specialization, and team name
                System.out.printf("\n|%-25s|%-35s|%-15s|%-10s|%-10s|%-25s|%-12s|\n", forward.getName() + " " + forward.getlastName(),
                        forward.getEmail(),
                        forward.getGender(),
                        forward.getAge(),
                        forward.getPosition(),
                        forward.getCoach().getSpecialization(),
                        forward.getTeam().getName());

                //checking if player is a Back player        
            } else if (player instanceof Back) {

                //if this is true, we set the player object to Back
                Back back = (Back) player;

                //This will prints a formatted data in columns respectibly iterairing trought the arrays and printing the player information. 
                // The format includes name, email, gender, age, position, coach specialization, and team name    
                System.out.printf("\n|%-25s|%-35s|%-15s|%-10s|%-10s|%-25s|%-12s|\n", back.getName() + " " + back.getlastName(),
                        back.getEmail(),
                        back.getGender(),
                        back.getAge(),
                        back.getPosition(),
                        back.getCoach().getSpecialization(),
                        back.getTeam().getName());

                //checking if player is a Back player  
            } else if (player instanceof Center) {

                //if this is true, we set the player object to Center
                Center center = (Center) player;
                
                //This will prints a formatted data in columns respectibly iterairing trought the arrays and printing the player information. 
                // The format includes name, email, gender, age, position, coach specialization, and team name    
                System.out.printf("\n|%-25s|%-35s|%-15s|%-10s|%-10s|%-25s|%-12s|\n", center.getName() + " " + center.getlastName(),
                        center.getEmail(),
                        center.getGender(),
                        center.getAge(),
                        center.getPosition(),
                        center.getCoach().getSpecialization(),
                        center.getTeam().getName());
            }

            //starts a loop to print a line.  
            for (int j = 0; j < 140; j++) {
                System.out.print("-");
            }
            System.out.println("");               
        }    
        System.out.println("\nList of players complete!");
        System.out.println("Please press Enter to get back to the main menu");
        scanner.nextLine();
    }
    
    @Override
    public void teamPlayer() {
        //Overriding our teamPlayer() method that we created 
        //in the Rugby Club interface use the lists of players that belong to each team
        //let it us access trought the size of each team 
        //and printing it on the screen the number of the players that belongs to a specific team.
        //we need to use the same function name from the parent class that we extended
        //in order to override that method  
        
        
        System.out.println("This is the list of Teams and How many Players are in each team: ");
        //Printing the quantity of player that belong to each team
        //We have easy access due to the manage and structure of our data.
        System.out.println("SQUAD A : " + squadAPlayers.size());
        System.out.println("SQUAD B : " + squadBPlayers.size());
        System.out.println("UNDER 15 SQUAD : " + squadUnderPlayers.size());
        
        System.out.println("\nPlease press Enter to get back to the main menu");
        scanner.nextLine();
        
    }
    
        //we need to use the same function name from the parent class that we extended
        //in order to override that method  
    @Override
    public void addPlayer(Player player) {
        //Overriding our addPlayer() method that we created 
        //in the Rugby Club interface use the main lists of players to add a new player there
        //and cheking if this player belong to a specific position 
        //let it us access trought the information of that player and using equalsIgnoreCase to check 
        //if the player belong to the "SquadA","SquadB" or "Under15Squad"
        //checking that we can add this specific player to that Team.

        //adding the player to the global list of players
        players.add(player);
        if (player instanceof Forward) {
            Forward forward = (Forward) player;
            if (forward.getTeam().getName().equalsIgnoreCase("SquadA")) {
               squadAPlayers.add(player);
            }else if (forward.getTeam().getName().equalsIgnoreCase("SquadB")) {
               squadBPlayers.add(player);
            }else if (forward.getTeam().getName().equalsIgnoreCase("Under15Squad")) {
               squadUnderPlayers.add(player);
            }
        }else if (player instanceof Back) {
            Back back = (Back) player;
            if (back.getTeam().getName().equalsIgnoreCase("SquadA")) {
               squadAPlayers.add(player);
            }else if (back.getTeam().getName().equalsIgnoreCase("SquadB")) {
               squadBPlayers.add(player);
            }else if (back.getTeam().getName().equalsIgnoreCase("Under15Squad")) {
               squadUnderPlayers.add(player);
            } 
        }else if (player instanceof Center) {
            Center center = (Center) player;
            if (center.getTeam().getName().equalsIgnoreCase("SquadA")) {
               squadAPlayers.add(player);
            }else if (center.getTeam().getName().equalsIgnoreCase("SquadB")) {
               squadBPlayers.add(player);
            }else if (center.getTeam().getName().equalsIgnoreCase("Under15Squad")) {
               squadUnderPlayers.add(player);
            } 
        }
        System.out.println("");
        System.out.println("Player has been successfully added to the Rugby Club!");
        
    }

    @Override
    public void addRandomPlayer() {
            
            //Initialize the random object variable
            Random random = new Random();
            
            //Creating a temporal list to store the Players that we are gonna create in each iteration
            List<Player> tempInfo = new ArrayList<>();
            //Asking the user How many players want to add to the Rugby Club Randomly 
            System.out.println("How many players do you want to add(please enter just numbers between 1 and 20):");
            
            //Using our method in generalMethods to get the number of player that we are gonna add
            //I have limited the creation of a random playeer between 1 and 20 letting the user 
            //input how many wants, this due to if the user wants to quickly populate the database
            //is faster than add 1 by 1, after finish adding a new random player this will be displayed 
            //on the screen, so in that way only 20(max) players will be displayed  on the screen ensuring 
            // not making a mess on the display with a list of too much players.
            int numberPlayer = generalMethods.askUserforInt("Please enter just numbers between 1 and 20",(Integer i) -> i >= 1 && i <= 20);
            
            //Setting the minAge that we Acept people to the Rugby club and the max to get random ages
            int minAge = 10;
            int maxAge = 41;
            
            //setting a counter that we are gonna use to surelly create at least 1 player under 15 year
            //every 5 times that we create randoms
            //due to the probability of create random player older than that age is highest 
            int counter = 0;
            
            //for loop to iterate the number of time of we wanna create a player
            for (int i = 0; i < numberPlayer; i++) {
                
                //getting the random index position from the list data,
                //data that we have taken from the file 
                //that we already Read  "Club_form.txt" 
                //and storing that index in the randomPerson variable
                int randomPerson = random.nextInt(data.size());
                
                //Using that index position to split the person information  
                //storing that in an array 
                String [] infoPerson = data.get(randomPerson).split(",");
                
                //Taking the details of that person and storing their 
                //name, lastName, email,and gender in their variables
                //using trim to remove the other information that we are not using it
                String name = infoPerson[1].trim();
                String lastName = infoPerson[2].trim();
                String email = infoPerson[3].trim();
                String gender = infoPerson[4].trim();
                //geting the random age for this iteration from the variables that we already created
                //this way we gonna get Ages between 10 to 50
                int randomAge = random.nextInt(maxAge) + minAge;
                
                //Removing this randomPerson index from the next iterations
                //and due to the data list is a global variable, removing once
                //even if the user go to another method, this ensure that the
                //random players that can be creat will be different from the players 
                //already created.
                data.remove(randomPerson);
                
                //adding one more value to the counter
                counter++;
                
                //Creating the coach variable of the class coach
                //that we are gonna use to get the random coach that we are gonna assign to 
                //this player( this getting the randon index from the coachs main list)
                //getting the information of that specific coach and storing in the variable
                Coach coach =  coachs.get(random.nextInt(coachs.size()));
                
                //now we are gonna get the random team to assign to this player 
                //as we just have 3 Different teams but to belong to the Under15Squad
                //the person needs to be under the 15 years old 
                //because of that it has been created this random index variable
                //that its iterating to get just one of the first teams that are 
                //"SquadA" and "SquadB" 
                int randomTeam = random.nextInt(2);
                //then initializing the team variable that its gonna store the Team information
                //using the Team class
                Team team;
                
                //then the conditions to get the team 
                //if the randomAge of the player is under the 15 years
                //the team is gonna be assigned as "Under15Squad"
                if (randomAge <= 15) { 
                team = teams.get(2); 
                
                //but as the probability of get under15 players is 1 of 3 
                //we set that every time that the counter is 5 that player its gonna get 
                //a randomAge between 10 and 15 and the team is gonna be "Under15Squad"
                }else if( counter == 5 ){
                randomAge = random.nextInt(5) + minAge;
                team = teams.get(2);
                }
                //otherwise we just assign the team that we get using the randomTeam
                else {
                team = teams.get(randomTeam);
                   
                }
                //Initialize the player variable from the player class
                Player player = null;
                //and using the coach type to stablished this player position
                if (coach instanceof HeadCoach) {
                //if the coach that we got randomly  belongs to the HeadCoach
                //We create a new player that is gonna be a Forward player 
                player = new Forward(name, lastName, email, gender, randomAge, "Forward",coach,team);
                }else if (coach instanceof AssistantCoach) {
                //if the coach that we got randomly  belongs to the AssistantCoach
                //We create a new player that is gonna be a Back player     
                player = new Back(name, lastName, email, gender, randomAge, "Back",coach,team);
                }else if (coach instanceof ScrumCoach){
                //if the coach that we gott randomly  belongs to the ScrumCoach
                //We create a new player that is gonna be a Center player     
                player = new Center(name, lastName, email, gender, randomAge, "Center",coach,team);
                }
                
                //after we create the new player we need to store it in our lists correctly
                //where he belongs, if its randomAge is under 15, we store it in the 
                //squadUnderPlayers List
                if (randomAge <= 15) {
                    squadUnderPlayers.add(player);
                } else if (randomTeam == 0) {
                    //if the randomTeam is equal 0 
                    //then the player belong to the first team "SquadA"
                    squadAPlayers.add(player);
                } else if (randomTeam == 1) {
                    //if the randomTeam is equal 1 
                    //then the player belong to the second team "SquadB"
                    squadBPlayers.add(player);
                }
                //after defined that, we store the player in the main players list
                players.add(player);  
                //and the tempInfo List of player class that we gonna use to print 
                //all the players that have been added in this iteration
                tempInfo.add(player);
                
        }
         //Now we are gonna show on the screen the list of players that have been added   
         System.out.println("Players have been added successfully to the Rugby Club!");
         System.out.println("This are the new Players that have been added:");     
         System.out.println("-----------------------------------------------------------");
        //using a for loop to iterate trought the tempInfo List
        //of the class player to get all the information of every player in this list 
        for (Player player : tempInfo) {
            //checking if player is a forward player
            if (player instanceof Forward) {
                //if this is true, we set the player object to Forward
                //let it us access to all the information of that forward player
                //And printing their position,Name,Email,Gender,Age,Position, Coach Type and Team
                Forward forward = (Forward) player;
                System.out.println("Player Name: " + forward.getName() + " " + forward.getlastName());
                System.out.println("Email: "+ forward.getEmail());
                System.out.println("Gender: "+ forward.getGender());
                System.out.println("Age: "+ forward.getAge());
                System.out.println("Position: "+  forward.getPosition());
                System.out.println("Coach Type: "+ forward.getCoach().getSpecialization());
                System.out.println("Team : "+ forward.getTeam().getName());
            }else if (player instanceof Back) {
                //if this is true, we set the player object to Back
                //let it us access to all the information of that Back player
                //And printing their position,Name,Email,Gender,Age,Position, Coach Type and Team
                Back back = (Back) player;
                System.out.println("Player Name: " + back.getName() + " " + back.getlastName());
                System.out.println("Email: "+ back.getEmail());
                System.out.println("Gender: "+ back.getGender());
                System.out.println("Age: "+ back.getAge());
                System.out.println("Position: "+  back.getPosition());
                System.out.println("Coach Type: "+ back.getCoach().getSpecialization());
                System.out.println("Team : "+ back.getTeam().getName());
                
            }else if (player instanceof Center) {
                //if this is true, we set the player object to Center
                //let it us access to all the information of that Center player
                //And printing their position,Name,Email,Gender,Age,Position, Coach Type and Team
                Center center = (Center) player;
                System.out.println("Player Name: " + center.getName() + " " + center.getlastName());
                System.out.println("Email: "+ center.getEmail());
                System.out.println("Gender: "+ center.getGender());
                System.out.println("Age: "+ center.getAge());
                System.out.println("Position: "+  center.getPosition());
                System.out.println("Coach Type: "+ center.getCoach().getSpecialization());
                System.out.println("Team : "+ center.getTeam().getName());
                
            }
            System.out.println("-----------------------------------------------------------");
            }   
            System.out.println("\nThat was the list of new random players added");
            System.out.println("Please press Enter to get back to the main menu");
            scanner.nextLine();
            
    }
            
    
    /**
     **Principal main method for the implement of 
     * the enum MenuOption in our RugbyClub interface 
     * where we are implementing all the functionalities 
     * of the system
     * @param args
     * @throws FileNotFoundException
     ***/
    
    public static void main(String[] args) throws FileNotFoundException {

        //create an object from the constructor,creating this new
        //instace we have easy acces to
        //the methods created in the RugbyClubImp
        RugbyClubImp RugbyClub = new RugbyClubImp();

        //Initialize scanner object to take in user input
        Scanner scanner = new Scanner(System.in);
        //Instatiate the enum MenuOption on the RugbyClub interface
        //in the variable selectOption
        RugbyClub.MenuOption selectOption = null;
        
        //Creating a Do - while  where its gonna be all the menu options from the interface enum 
         
        do {
            System.out.println(" +-+-+-+-+-+-+-+-+-+  \n" +
                             "🏉|R|u|g|b|y|C|l|u|b|🏉 \n" +
                               " +-+-+-+-+-+-+-+-+-+-+\n" +
                               "            |M|e|n|u|\n" +
                               "            +-+-+-+-+");
            //displaying the menu options to the user
            System.out.println("Please Select an Option");
            System.out.println("1. Sort and search people");
            System.out.println("2. Search specific Player");
            System.out.println("3. List of players");
            System.out.println("4. Number of player by team");
            System.out.println("5. Add a new Player");
            System.out.println("6. Add a Random Player");
            System.out.println("7. Exit");
            
            //while will help us to check if the next input
            //from the user is not a integer and will continue while 
            //the user not introduce a integer
            while (!scanner.hasNextInt()) {

                System.out.println("Enter numbers only");
                scanner.next();
            }
            //using variable option to store the value input from the user
            int option = scanner.nextInt();
            //The user choice will be stored in the option variable as an integer
            scanner.nextLine();
            //validate that the user input is within the available options.
            //using the MenuOption to get the number of options in the enum menu
            if (option < 1 || option > MenuOption.values().length) {
                System.out.println("\nPlease select from one of the option available!");
                System.out.println("Please press Enter to get back to the menu\n");
                scanner.nextLine();
                
            } else {
                //if the user takes a valid option then the corresponding MenuOption from the enum menu
                // will be assign to "selectOption"
                selectOption = MenuOption.values()[option - 1];
                
                //Then initializing a switch that will takes the case based on the user selection
                switch (selectOption) {
                    case SORT_SEARCH_PEOPLE:
                        //if the user choice "Sort people" option number 1, we call the sortPeople method
                        //from the RugbyClub 
                        RugbyClub.sortSearchPeople();
                        break;

                    case SEARCH_PLAYER:
                        //This case is for the user choice "Search specific player" option number 2
                        //we first check if the players main list is empty, if this is true
                        //we show a message saying that populate the data before select that option
                        if (RugbyClub.players.isEmpty()) {
                        System.out.println("\nSorry there are no players yet"); 
                        System.out.println("please add some using 'Add Player' or 'Add Random Player' option");
                        System.out.println("Press enter to back to the Menu\n");
                        scanner.nextLine();
                        break;
                        }
                        //if there are players we will call the searchPlayer method of the RugbyClub
                        RugbyClub.searchPlayer();
                        break;
                        
                    case LIST_PLAYERS:
                        //This case is for the user choice "List of players" option number 3
                        //we first check if the players main list is empty, if this is true
                        //we show a message saying that populate the data before select that option 
                        if (RugbyClub.players.isEmpty()) {
                            
                        System.out.println("\nSorry there are no players yet"); 
                        System.out.println("please add some using Add Player or Add Random Player option");
                        System.out.println("Press enter to back to the Menu\n");
                        scanner.nextLine();
                        break;
                        }
                        //if there are players we will call the listPlayer method of the RugbyClub
                        RugbyClub.listPlayer();
                        break;        

                    case NUMBER_PLAYERS_TEAM:
                        //This case is for the user choice "Number of players by team" option number 4
                        //we first check if the players main list is empty, if this is true
                        //we show a message saying that populate the data before select that option 
                        if (RugbyClub.players.isEmpty()) {
                        System.out.println("\nSorry there are no players yet"); 
                        System.out.println("please add some using Add Player or Add Random Player option");
                        System.out.println("Press enter to back to the Menu");
                        scanner.nextLine();
                        break;
                        }
                        //if there are players we will call the teamPlayer method of the RugbyClub
                        RugbyClub.teamPlayer();
                        break;

                    case ADD_PLAYERS:
                        //if the user enter the option number 4 "Add new player"
                        //we will ask first for the details for the new player comma separate(Name, lastName,Email,Gender,Age)
                        System.out.println("");
                        System.out.println("--------------------------------------");               
                        System.out.println("Enter the details for the new Player");                       
                        System.out.println("--------------------------------------");
                        
                        //Creating a variable where we are gonna store the player details information.(name,lastName,email,gender,age)
                        String[] playerDetails;
                        
                        
                        //storing the player details in an array, after validating in the validInfo() method in our generalMethods
                        playerDetails = generalMethods.validInfo(RugbyClub.players);
                        //boolean flag that allows us know if the player already exist in the club or not, the true or false value is given 
                        //by the method itself (playerAlreadyExist) which is in our generalMethods class
                        boolean playerExist = generalMethods.playerAlreadyExist(RugbyClub.players, playerDetails);
                       
                        //giving as condicion to while using the flag playerExist, if the player exist 
                        //this is gonna continue asking to the user if want the new player information
                        //or go back to the main menu until the player introduce a different information
                        while (playerExist) {  
                            //displaying the message that the player already exist
                                System.out.println("\nThis player already Exist\n");
                                
                                //small menu, that will ask to the user if he want add a new player or back to the menu
                                System.out.println("Please select an option:");
                                System.out.println("1).Continue adding new player");
                                System.out.println("2).Back to the main menu");
                                
                                //storing the int value provided by the user using the askUserforInt method which is in our generalMethods
                                option = generalMethods.askUserforInt("Please enter just numbers", (Integer i) -> i >= 1 && i <= 2);
                                
                                //initializing a switch, that will use the user input to pick a case
                                switch (option) {
                                    //case 1 will be when the user still want to add a new player
                                    //giving a chance to introduce the information again wittout back to the main menu
                                    case 1:
                                        //this use our validInfo from generalMethods to validate the user input 
                                        playerDetails = generalMethods.validInfo(RugbyClub.players);
                                        //this will help us check if the information introduced again is for a new player that doesn't exist 
                                        //in our data base of player, if this still true, means that the player that we want to introduce 
                                        //already exist in the data base, if this is false, it let us know that the new information that the user
                                        //introduced is for a a new player and let us continue with the rest, assigning coach and team to create
                                        //this as a new player.
                                        playerExist = generalMethods.playerAlreadyExist(RugbyClub.players, playerDetails);
                                        break;
                                        
                                    case 2:
                                        //if the user want to back to the main menu, we just change the flag to false to let us continue
                                        playerExist=false;    
                                        break;
                                    default:
                                        //default action that will happen if the user select another option that is not in the menu
                                        System.out.println("That is not a valid option");
                                }
                            } 
                        //this next condition will help us to check if the player is already in the player list 
                        //this to help us break the switch and getting us back to the main menu 
                        //without adding a new player
                        if (generalMethods.playerAlreadyExist(RugbyClub.players, playerDetails)) {
                            break;
                            //otherwise if the player doesnt exist in the club it just continue with the rest of the code and adding the new player
                        }else {
                            
                            //initializing the team object where we gonna store our team 
                            Team teamName = null;
                            //and our boolean flag that it gonna allows get the validation of the selection of the team
                            boolean validTeam = false;
                            //then if the validation is true, then ask to the user for the type of coach 
                            System.err.println("\nSelect the Coach Staff: (1. Head , (2. Scrum, (3.Assistant");
                            int type = generalMethods.askUserforInt("Please Enter just numbers", (Integer i) -> i >= 1 && i <= 3);
                            //The user choice will be stored in the type variable as an integer
                            //initialize a switch that will work based on the users choice
                            switch (type) {
                            case 1:
                                //case 1 if the user chose "Head", we get the first coach from the list of coaches
                                //storing that coach in the coachType object that we create using the Coach class
                                Coach coachType = RugbyClub.coachs.get(0);
                                
                                
                                //creating the Do-while where is gonna be our team selection switch
                                do {
                                    //then we let the user select the team that will be assigned to this new player
                                    System.err.println("\nSelect the Team: (1. SquadA , (2. SquadB, (3.Under15Squad");
                                    type = generalMethods.askUserforInt("Please Enter just numbers", (Integer i) -> i >= 1 && i <= 3);
                                     //The user choice will be stored in the type variable as an integer
                                   

                                        //initializing another switch inside our other switch which gonna allow us 
                                        //assign the team depending on the user choice
                                        switch (type) {
                                            //case 1 we store the team at index 0 in our team object
                                            //Team : "SquadA"
                                            case 1:
                                                if (Integer.parseInt(playerDetails[4].trim()) > 15) {
                                                 teamName = RugbyClub.teams.get(0); 
                                                 validTeam=true;
                                                }else {
                                                 System.out.println("\nSorry the age must be above 15 to belong to this team");
                                                 System.out.println("Please select a valid Team");
                                                 validTeam=false;
                                                 
                                                }
                                                break;
                                            case 2:
                                                //case 2 we store the team at index 1 in our team object
                                                //Team : "SquadB" 
                                                if (Integer.parseInt(playerDetails[4].trim()) > 15) {
                                                 teamName = RugbyClub.teams.get(1); 
                                                 validTeam=true;
                                                }else {
                                                 System.out.println("\nSorry the age must be above 15 to belong to this team");
                                                 System.out.println("Please select a valid Team\n");
                                                 validTeam=false;
                                                 
                                                }
                                                break;        
                                            case 3:
                                                //case 3 we store the team at index 2 in our team object
                                                //Team : "Under15Squad"
                                                //but if the player Age that has been introduced is below 15 years
                                                //its gonna back to the selection of the team screen
                                                 if (Integer.parseInt(playerDetails[4].trim()) <= 15) {
                                                 teamName = RugbyClub.teams.get(2); 
                                                 validTeam=true;
                                                }else {
                                                 System.out.println("\nSorry the age must be below 15 to belong to this team");
                                                 System.out.println("Please select a valid Team\n");
                                                 validTeam=false;
                                                 
                                                }
                                                break;
                                                   
                                             default:
                                                //if the user select another option that is not in the options
                                                //show this message and ask again 
                                                System.err.println("Invalid choice for team.");
                                                
                                               }   
                                               } while (!validTeam);
                                                //if every player detail and selection is correct
                                                //we can added this new player using the addPlayer method
                                                //of the RugbyClub assigning this new player as Forward position 
                                                //with the "Head coach" and the user selected team
                                                  RugbyClub.addPlayer(new Forward(playerDetails[0].trim(),
                                                        playerDetails[1].trim(), 
                                                        playerDetails[2].trim(), 
                                                        playerDetails[3].trim(), 
                                                        Integer.parseInt(playerDetails[4].trim()), 
                                                        "Forward", 
                                                        coachType, 
                                                        teamName));
                               
                                                System.out.println("Assigned succesfully to Coach: "+coachType.getSpecialization()+ " and team :" + teamName.getName());
                                                System.out.println("");
                                                System.out.println("Please press Enter to get back to the main menu");
                                                scanner.nextLine();
                                            break;
                                            
                                    case 2:
                                    //case 2 if the user chose "Assistant", we get the second coach from the list of coaches
                                    //storing that coach in the coachType object that we create using the Coach class    
                                    coachType = RugbyClub.coachs.get(1);
                                    
                                    //creating the Do-while where is gonna be our team selection switch
                                    do {
                                    //then we let the user select the team that will be assigned to this new player
                                    System.err.println("\nSelect the Team: (1. SquadA , (2. SquadB, (3.Under15Squad");
                                    type = generalMethods.askUserforInt("Please Enter just numbers", (Integer i) -> i >= 1 && i <= 3);
                                     //The user choice will be stored in the type variable as an integer
                                   

                                        //initializing another switch inside our other switch which gonna allow us 
                                        //assign the team depending on the user choice
                                        switch (type) {
                                            //case 1 we store the team at index 0 in our team object
                                            //Team : "SquadA"
                                            case 1:
                                                if (Integer.parseInt(playerDetails[4].trim()) > 15) {
                                                 teamName = RugbyClub.teams.get(0); 
                                                 validTeam=true;
                                                }else {
                                                 System.out.println("\nSorry the age must be above 15 to belong to this team");
                                                 System.out.println("Please select a valid Team\n");
                                                 validTeam=false;
                                                 
                                                }
                                                break;
                                            case 2:
                                                //case 2 we store the team at index 1 in our team object
                                                //Team : "SquadB" 
                                                if (Integer.parseInt(playerDetails[4].trim()) > 15) {
                                                 teamName = RugbyClub.teams.get(1); 
                                                 validTeam=true;
                                                }else {
                                                 System.out.println("\nSorry the age must be above 15 to belong to this team");
                                                 System.out.println("Please select a valid Team\n");
                                                 validTeam=false;
                                                 
                                                }
                                                break;        
                                            case 3:
                                                //case 3 we store the team at index 2 in our team object
                                                //Team : "Under15Squad"
                                                //but if the player Age that has been introduced is below 15 years
                                                //its gonna back to the selection of the team screen
                                                 if (Integer.parseInt(playerDetails[4].trim()) <= 15) {
                                                 teamName = RugbyClub.teams.get(2); 
                                                 validTeam=true;
                                                }else {
                                                 System.out.println("\nSorry the age must be below 15 to belong to this team");
                                                 System.out.println("Please select a valid Team\n");
                                                 validTeam=false;
                                                 
                                                }
                                                break;
                                                   
                                             default:
                                                //if the user select another option that is not in the options
                                                //show this message and ask again 
                                                System.err.println("Invalid choice for team.");
                                                
                                               }   
                                               } while (!validTeam);   
                                                
                                                //if every player detail and selection is correct
                                                //we can added this new player using the addPlayer method
                                                //of the RugbyClub assigning this new player as Back position 
                                                //with the "assistant coach" and the user selected team
                                                  RugbyClub.addPlayer(new Back(playerDetails[0].trim(),
                                                        playerDetails[1].trim(), 
                                                        playerDetails[2].trim(), 
                                                        playerDetails[3].trim(), 
                                                        Integer.parseInt(playerDetails[4].trim()), 
                                                        "Back", 
                                                        coachType, 
                                                        teamName));
                               
                                                System.out.println("Assigned succesfully to Coach: "+coachType.getSpecialization()+ " and team :" + teamName.getName());                   
                                                System.out.println("");
                                                System.out.println("Please press Enter to get back to the main menu");
                                                scanner.nextLine();    
                                            break;
                                             
                                    case 3:
                                    coachType = RugbyClub.coachs.get(2);
                                    
                                    //creating the Do-while where is gonna be our team selection switch
                                do {
                                    //then we let the user select the team that will be assigned to this new player
                                    System.err.println("\nSelect the Team: (1. SquadA , (2. SquadB, (3.Under15Squad");
                                    type = generalMethods.askUserforInt("Please Enter just numbers", (Integer i) -> i >= 1 && i <= 3);
                                     //The user choice will be stored in the type variable as an integer
                                   

                                        //initializing another switch inside our other switch which gonna allow us 
                                        //assign the team depending on the user choice
                                        switch (type) {
                                            //case 1 we store the team at index 0 in our team object
                                            //Team : "SquadA"
                                            case 1:
                                                if (Integer.parseInt(playerDetails[4].trim()) > 15) {
                                                 teamName = RugbyClub.teams.get(0); 
                                                 validTeam=true;
                                                }else {
                                                 System.out.println("\nSorry the age must be above 15 to belong to this team");
                                                 System.out.println("Please select a valid Team\n");
                                                 validTeam=false;
                                                 
                                                }
                                                break;
                                            case 2:
                                                //case 2 we store the team at index 1 in our team object
                                                //Team : "SquadB" 
                                                if (Integer.parseInt(playerDetails[4].trim()) > 15) {
                                                 teamName = RugbyClub.teams.get(1); 
                                                 validTeam=true;
                                                }else {
                                                 System.out.println("\nSorry the age must be above 15 to belong to this team");
                                                 System.out.println("Please select a valid Team\n");
                                                 validTeam=false;
                                                 
                                                }
                                                break;        
                                            case 3:
                                                //case 3 we store the team at index 2 in our team object
                                                //Team : "Under15Squad"
                                                //but if the player Age that has been introduced is below 15 years
                                                //its gonna back to the selection of the team screen
                                                 if (Integer.parseInt(playerDetails[4].trim()) <= 15) {
                                                 teamName = RugbyClub.teams.get(2); 
                                                 validTeam=true;
                                                }else {
                                                 System.out.println("\nSorry the age must be below 15 to belong to this team");
                                                 System.out.println("Please select a valid Team\n");
                                                 validTeam=false;
                                                 
                                                }
                                                break;
                                                   
                                             default:
                                                //if the user select another option that is not in the options
                                                //show this message and ask again 
                                                System.err.println("Invalid choice for team.");
                                                
                                               }   
                                               } while (!validTeam);   
                                                //if every player detail and selection is correct
                                                //we can added this new player using the addPlayer method
                                                //of the RugbyClub assigning this new player as Center position 
                                                //with the "Scrum coach" and the user selected team    
                                                RugbyClub.addPlayer(new Center(playerDetails[0].trim(),
                                                playerDetails[1].trim(),
                                                playerDetails[2].trim(),
                                                playerDetails[3].trim(),
                                                Integer.parseInt(playerDetails[4].trim()),
                                                "Center",
                                                coachType,
                                                teamName));
                               
                                                System.out.println("Assigned succesfully to Coach: "+coachType.getSpecialization()+ " and team :" + teamName.getName());                   
                                                System.out.println("");
                                                System.out.println("Please press Enter to get back to the main menu");
                                                scanner.nextLine(); 
                                            break;         
                                }
                                break;
                                    }
                                  
                       // break;
                        
                        
                    case ADD_RANDOM_PLAYER:
                        
                        //if the user choice "add random player" option number 6, we call the addRandomPlayer method
                        //from the RugbyClub 
                        RugbyClub.addRandomPlayer();
                        
                        break;
                    case EXIT:
                        //and for the option number 7, Exit the program
                        System.out.println("\nExiting program...");
                        System.out.println("Thank you for using our Rugby Club Management System!");
                        break;
            
                    default:
                        //default option if the user introduce not valid option from the menu
                        System.out.println("Please select a valid option");
                        scanner.nextLine();
                        break;
                 
                }
        }  
          //condition while to keep looping until the user select the Exit option
        } while (selectOption != MenuOption.EXIT);
}

}

