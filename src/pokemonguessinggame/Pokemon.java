

package pokemonguessinggame;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Pokemon {  //Class for each individual pokemon  
    String name;
    boolean[] attributes = new boolean[17];//The attributes of the pokemon are heald in this array of booleans
    boolean isCandidate;//If pokemon is a candidate and still in game, it is true, and false otherwise
  
    
    public Pokemon( String n, boolean[] a) {
        this.name = n;
        this.attributes = a;
        this.isCandidate = true;//All pokemon are candidates at start so isCandidate is true for all
    }   
}

