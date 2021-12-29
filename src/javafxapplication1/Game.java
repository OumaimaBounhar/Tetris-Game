package javafxapplication1;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author oumaima
 */
public class Game {
    
    private Point[] pts;
        
    //Mettre une nouvelle pièce aléatoire dans la grille
 
    public static Tetrimino randomTetrimino() {  //D'abord on récupère aléatoirement le type de la pièce
        Random rand = new Random();
        int randNum = rand.nextInt(Tetrimino.values().length);
        return Tetrimino.values()[randNum];
    }
    
    public static int randomState() { //Ensuite on choisit aléatoirement l'état du type choisi
        Random rand = new Random();
        return rand.nextInt(4);
    }
    
    public static Tile randomTile(){
       return new Tile( randomTetrimino(), randomState());
   }
}
    
    //Drop tile in grid
    public void dropTile( Point[] pts){
        Tile tile = new Tile.randomTile();
        
    }