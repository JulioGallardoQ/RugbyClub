/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ca_2;


import static com.oracle.nio.BufferSecrets.instance;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author julio
 */
public class RugbyClubImpTest {
    private final InputStream SystemIn = System.in;
    private final PrintStream SystemOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private RugbyClubImp instance;
    
  
    
    @Before
    public  void setUp() throws FileNotFoundException {
        System.setOut(new PrintStream(outputStream));
        instance = new RugbyClubImp();
        initializeTestData();
        // Set up any initial data or configuration needed for your tests.
    }
    private void initializeTestData() {
        instance.people.add(new Person("Tobie","Arnaez","tarnaez0@stumbleupon.com","Male"));
        instance.people.add(new Person("Averil","Crasford","acrasford1@twitpic.com","Male"));
        instance.people.add(new Person("Wenda","Daniell","wdaniell2@ox.ac.uk","Female"));
        instance.people.add(new Person("Humfrey","Stowe","hstowe3@squidoo.com","Male"));
        instance.people.add(new Person("Tulley","Bonney","tbonney4@usnews.com","Male"));
        instance.people.add(new Person("Bibbie","Balfre","bbalfre5@miibeian.gov.cn","Female"));
        instance.people.add(new Person("Ronnie","Codeman","rcodeman6@prweb.com","Female"));
        instance.people.add(new Person("Pryce","Gadsby","pgadsby7@123-reg.co.uk","Male"));
        instance.people.add(new Person("Mellisa","Lathleiffure","mlathleiffure8@columbia.edu","Female"));
        instance.people.add(new Person("Anette","Miche","amiche9@jalbum.net","Genderqueer"));
        instance.people.add(new Person("Huberto","de Guise","hdeguisea@mapy.cz","Male"));
        instance.people.add(new Person("Sarajane","Reimer","sreimerb@facebook.com","Non-binary"));
        instance.people.add(new Person("Frieda","Axton","faxtonc@gmpg.org","Female"));
        instance.people.add(new Person("Gilburt","Hatwell","ghatwelld@google.com.hk","Male"));
        instance.people.add(new Person("Fiorenze","Twinterman","ftwintermane@oaic.gov.au","Female"));
        instance.people.add(new Person("Marv","Flann","mflannf@stanford.edu","Male"));
        instance.people.add(new Person("Tanya","Corkish","tcorkishg@psu.edu","Female"));
        instance.people.add(new Person("Carissa","Still","cstillh@wired.com","Female"));
        instance.people.add(new Person("Puff","Cahill","pcahilli@answers.com","Male"));
        instance.people.add(new Person("Tanya","Corkish","tcorkishg@psu.edu","Female"));
        instance.people.add(new Person("Marita","Golton","mgoltonj@cdbaby.com","Female"));
    }
    @Test
    public void testSortPeople() throws  IOException {
        System.out.println("sortPeople");

       
        //String fileName = "test2.txt";
        //GeneralMethods.testReadFile(testFile);
        instance.sortPeople();
         
        
        List<Person> sortedData = instance.people;
        
        String expectedSortedData = new String("Anette, Miche, amiche9@jalbum.net, Genderqueer"
+"Averil, Crasford, acrasford1@twitpic.com, Male"
+"Bibbie, Balfre, bbalfre5@miibeian.gov.cn, Female"
+"Carissa, Still, cstillh@wired.com, Female"
+"Fiorenze, Twinterman, ftwintermane@oaic.gov.au, Female"
+"Frieda, Axton, faxtonc@gmpg.org, Female"
+"Gilburt, Hatwell, ghatwelld@google.com.hk, Male"
+"Huberto, de Guise, hdeguisea@mapy.cz, Male"
+"Humfrey, Stowe, hstowe3@squidoo.com, Male"
+"Marita, Golton, mgoltonj@cdbaby.com, Female"
+"Marv, Flann, mflannf@stanford.edu, Male"
+"Mellisa, Lathleiffure, mlathleiffure8@columbia.edu, Female"
+"Pryce, Gadsby, pgadsby7@123-reg.co.uk, Male"
+"Puff, Cahill, pcahilli@answers.com, Male"
+"Ronnie, Codeman, rcodeman6@prweb.com, Female"
+"Sarajane, Reimer, sreimerb@facebook.com, Non-binary"
+"Tanya, Corkish, tcorkishg@psu.edu, Female"
+"Tobie, Arnaez, tarnaez0@stumbleupon.com, Male"
+"Tulley, Bonney, tbonney4@usnews.com, Male"
+"Wenda, Daniell, wdaniell2@ox.ac.uk, Female");
        assertEquals(expectedSortedData,sortedData);
        
      
    }

    /**
     * Test of searchPlayer method, of class RugbyClubImp.
     */
    @Test
    public void testSearchPlayer() {
        System.out.println("searchPlayer");
        RugbyClubImp instance = new RugbyClubImp();
        instance.searchPlayer();
        // TODO review the generated test code and remove the default call to fail.
      
    }

    /**
     * Test of listPlayer method, of class RugbyClubImp.
     */
    @Test
    public void testListPlayer() {
        System.out.println("listPlayer");
        RugbyClubImp instance = new RugbyClubImp();
        instance.listPlayer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of teamPlayer method, of class RugbyClubImp.
     */
    @Test
    public void testTeamPlayer() {
        System.out.println("teamPlayer");
        RugbyClubImp instance = new RugbyClubImp();
        instance.teamPlayer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPlayer method, of class RugbyClubImp.
     */
    @Test
    public void testAddPlayer() {
        System.out.println("addPlayer");
        Player player = null;
        RugbyClubImp instance = new RugbyClubImp();
        instance.addPlayer(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRandomPlayer method, of class RugbyClubImp.
     */
    @Test
    public void testAddRandomPlayer() {
        System.out.println("addRandomPlayer");
        RugbyClubImp instance = new RugbyClubImp();
        instance.addRandomPlayer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    
    
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
    
    @After
    public void tearDown() {
       System.setOut(SystemOut);  
    }

    /**
     * Test of sortPeople method, of class RugbyClubImp.
     */
    
  private String readTestFile(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString().trim();
    }
    /**
     * Test of main method, of class RugbyClubImp.
     */
//    @Test
//    public void testMain() throws Exception {
//        System.out.println("main");
//        String[] args = null;
//        RugbyClubImp.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
