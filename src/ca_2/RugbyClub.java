/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ca_2;

/**
 *
 * @author Julio Cesar Gallardo Quintero - 2023149
 */
public interface RugbyClub {
    
    // This interface is the main menu that the user will interact with
    // This interface needs to contain (list) all possible options for the user
    
    // We will need to enumerate the options for the user
    enum MenuOption{
    //Enums allows us to enumerate a list of funtions giving them numeric order
    //List all the function that you'd like the user to use 
        SORT_SEARCH_PEOPLE,
        SEARCH_PLAYER,
        LIST_PLAYERS,
        NUMBER_PLAYERS_TEAM,
        ADD_PLAYERS,
        ADD_RANDOM_PLAYER,
        EXIT
    
    }
    
    //main methods in my rugby club interface that are will be Override in the implementation
    void sortSearchPeople();
    void searchPlayer();
    void listPlayer();
    void teamPlayer();
    void addPlayer(Player player);
    void addRandomPlayer();
    
    
}
