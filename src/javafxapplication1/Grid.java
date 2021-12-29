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
public class Grid {
    
    private Point tilePos ;  //position de la pièce dans la grille tilePosition
    private Tile tile;
    private int[][] grid = new int[10][22];
    

    public Grid() {
        grid = new int[10][22];
    }

    public boolean isEmpty(int i, int j) {
        return grid[i][j] == 0;
    }
    
    //"Convertir" la pièce en case 
    public void convertToCase(int i,int j){
        grid[i][j] = 1;  //car la grille contient que des zéros    
    }
    
   
    public Point[] tileToGrid(){ //On renvoie la liste des coordonnées des cases occupées
        Point[] pts = new Point[4];
        int n = 0;
        int x = tilePos.getX();
        int y = tilePos.getY();
        int[][] tileConfig = tile.getConfig(); //ça représente une pièce dans un certain état
        for(int i=0; i<tileConfig.length ; i++){
            for(int j=0 ; j<tileConfig[i].length; j++){
                if(tileConfig[i][j]==1){
                    pts[n++]=new Point(x+i,y+j);
                }
            }
        }
        return pts;
    }
    
    public boolean tileinGrid(){ //On renvoie si la pièce est incluse dans la grille
        Point[] pts = tileToGrid();
        int s=0;
        for(int i=0 ; i<pts.length; i++){
                int xc = pts[i].getX();
                int yc = pts[i].getY();
                if ( xc<10 && yc<22 && xc>0 && yc>0){
                    s=s+1;
                }
            }
        return s==4;
    }    
    
    //Tester si la pièce aléatoire touche d'autres pièces 
    public boolean collidesAtDown() {
        Point[] pts = tileToGrid();
        int[][] tileConfig = tile.getConfig();
        for(int i=0 ; i<pts.length; i++){
                if( grid[ pts[i].getX()+1 ][ pts[i].getY()]!=0 ){
                    return false;
                }
        }
        return true;
    }
    
    public boolean collidesAtLeft() {
        Point[] pts = tileToGrid();
        int[][] tileConfig = tile.getConfig();
        for(int i=0 ; i<pts.length; i++){
                if( grid[ pts[i].getX() ][ pts[i].getY()-1]!=0 ){
                    return false;    
                }
        } 
        return true;
    }    

    public boolean collidesAtRight() {
        Point[] pts = tileToGrid();
        int[][] tileConfig = tile.getConfig();
        for(int i=0 ; i<pts.length; i++){
                if( grid[ pts[i].getX() ][ pts[i].getY()+1]!=0 ){
                    return false;
                }
        }
        return true;
    }    
    public void autoMoveDown() {
        Point[] pts = tileToGrid();
        if ( collidesAtDown()== true){
            for( int i=0;i<pts.length;i++){
                int xi = pts[i].getX();
                xi = xi + 1;
            }
        }
    }

    public void moveLeft(){ // Il faut passer d'un état à l'autre et modifier les coordonnées dans la grille 
        Point[] pts = tileToGrid();
        if ( collidesAtLeft()== true){
            for( int i=0;i<pts.length;i++){
                int yi = pts[i].getY();
                yi = yi - 1;
            }
        }       
    }

    public void moveRight() {
        Point[] pts = tileToGrid();
        if ( collidesAtRight()== true){
            for( int i=0;i<pts.length;i++){
                int yi = pts[i].getY();
                yi = yi + 1;
            }
        } 
    }
    
    public void rotate(){
        int state=tile.getState();
        if (state<3){
            state++;
       }
        else {
            state=0;
        }
    }
}
