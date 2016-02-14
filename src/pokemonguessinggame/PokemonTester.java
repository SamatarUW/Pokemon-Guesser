package pokemonguessinggame;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class PokemonTester { //Class for the array of pokemon
    Pokemon[] myPokemon = new Pokemon[50];
    String[] q = new String[17];//Array where questions are to be heald
    static int pokeLeft = 50;//Number of Pokemon still candidates (still in game)

    public void readData() throws IOException { //Reads the data from the files Pokemon Data and Pokemon Questions and fills the arrays accordingly
        FileReader f = new FileReader( "Pokemon Data.txt");//Creates a file reader for the document containing pokemon names and boolean attributes
        Scanner s = new Scanner( f );
        
        for( int i = 0; i < 50; i++) {//Loops for all the pokemon 
            String n = s.next();//Sets the variable n which stands for name equal to the name from the data file
            boolean [] atts = new boolean[17];//Creates an empty array of booleans for each pokemon
            
            for (int j = 0; j < 17; j++) {
                boolean att = s.nextBoolean();//Sets the variable att equal to the next boolean value in the data file
                atts[j] = att;//Fills the array of booleans with the attributes from the file 

            }
            myPokemon[i] = new Pokemon( n, atts);//Creates the pokemon in the array myPokemon with names and array of attributes
        }
        FileReader g = new FileReader( "Pokemon Questions .txt");//Creates a file reader for the document containing questions      
        Scanner r = new Scanner( g );
        
        for (int i = 0; i < 17; i++) {//Loops through the number of questions
            q[i] = r.nextLine();//Fills the array of questions with the questions from the file
        }
    }

    public int pickNextIndex() {//Picks the index for the next best questions that should be asked
        int min = 1000;
        int minIndex = 0;
        int f = 0;
        int t = 0;
        
        for (int c = 0; c<17;c++){//Loops through 17 first because each pokemon has 17 attributes
            for (int i = 0; i < 50; i++) {//Loops through all pokemon     
                if(myPokemon[i].isCandidate) {//Only does the rest if given pokemon is still a candidate
                    if (myPokemon[i].attributes[c] == false) {
                        f++;//Adds 1 to the int f for every false                         
                    }
                    if (myPokemon[i].attributes[c] == true) {
                        t++;//Adds 1 to the int t for every true
                    }
                }
            }
            int val =Math.abs(f-t);//Sets the variable val equal to the absoute value of the total false subtract total trues
                if (val < min) {
                    min = val;// If val is less than min, it sets min equal to val and does so until it finds the true min
                    minIndex = c;
                }
            f=0;//Resets falses to 0
            t=0;//Rests trues to 0
        }       
        return minIndex;
    }
    public void AskQuestion(){ 
        System.out.println(q[pickNextIndex()]); //Asks a questions from the array depending on the index form pickNextIndex

    }   
    public void searchLastPok() {//Prints the last pokemon remaining after user has answered the questions needed
        if (pokeLeft ==1) {
            for (int i = 0; i < 50; i++) {//Loops for all pokemon
                if (myPokemon[i].isCandidate && pokeLeft==1 ) {//Only prints if there is one pokemon left and it is a candidate
                    System.out.println("Your Pokemon is " + myPokemon[i].name + ". Hope you had fun! :)");
                }                  
            }
        }
    }
        
    public void eliminateCandidates(int index, boolean ans) {
        for (int i = 0; i < 50; i++) {//Loops for all pokemon
            if (ans != myPokemon[i].attributes[index] && myPokemon[i].isCandidate)  {
                myPokemon[i].isCandidate = false;//If the answer from user is the opposite of what the attribute is and the pokemon is a candidate, it sets isCandidate to false for that pokemon
                pokeLeft--;//Subtracts pokeleft by one every time it makes a pokemon no longer a candidate
            } 
        }
    }
    public boolean getAnswer() {
        Scanner s = new Scanner( System.in );        
        String ans = s.nextLine();
        
        if (ans.equalsIgnoreCase("yes")) {
            return true;//If the user answers yes, the answer becomes boolean true       
        }
        else  {
            return false;//If the user does not answer yes, the answer becomes the boolean false
        }
    }

    public static void main(String[] args) throws IOException{
        PokemonTester p = new PokemonTester();
        p.readData();//Calls readData to fill pokemon array and question array         
        boolean ans;//Creates a variable ans for user answers
        
        for (int i = 0; i < 50; i++) {//Loop for all pokemon
            System.out.println(p.myPokemon[i].name);//Print all pokemon names for user to pick one
        }
        System.out.println("\n" +"\n");
        System.out.println("Hello! Welcome to WHO'S THAT POKEMON! ");
        System.out.println("Think of a Pokemon from the given list above and answer the series of question to get your Pokemon." + "\n" + "Ready? START!" +"\n" + "\n" + "\n");//Give introduction and explain to user what to do
        while (pokeLeft >1) {//Keep running loop until their is one pokemon left
            p.AskQuestion();//Calls AskQuestion to print a question
            ans = p.getAnswer();//Gets users answer and makes it a boolean             
            p.eliminateCandidates(p.pickNextIndex(), ans);//Makes pokemon no longer a candidate depending on the answer and index of question
        }
        p.searchLastPok();//Prints the last pokemon left 

    }
}