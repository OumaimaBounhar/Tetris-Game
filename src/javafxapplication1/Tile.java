/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

/**
 *
 * @author oumaima
 */
public class Tile {

    private Tetrimino tetrimino;
    private int state;

    public Tile(Tetrimino tetrimino, int state) {
        this.state = state;
        this.tetrimino = tetrimino;
    }
    
    public int[][] getConfig(){
        return tetrimino.getState(state);
    }

    public int getState() {
        return state;
    }
    
}
