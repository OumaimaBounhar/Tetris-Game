/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.animation.AnimationTimer;
import javafx.util.converter.NumberStringConverter;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafxapplication1.Grid;
import javafxapplication1.Point;
import javafxapplication1.Tetrimino;
import javafxapplication1.Tile;


/**
 *
 * @author Noah
 */
//class group 
//sous class de rectangle
//animation : timeline (seperate)
//message box

// add css for style
public class Lobby {
   
   
    public Lobby(Stage stage) {


        //Game Scene creation
        final int L = 38*17;        
        final int H = 28*24;

        final GridPane gp = new GridPane();
        final Scene gameScene = new Scene(gp, L, H); //1 case = 38*38 pxl
        
        gp.add(bigLeftGrid(),1,1,1,4);
        gp.add(bigRightGrid(), 2,1, 1, 4);
        gp.setBackground(createBackground(Color.GREY));
        

        //Game Lobby Scene creation
        final GridPane lobbyLayout = new GridPane();
        final Scene lobbyScene = new Scene(lobbyLayout, L,H);
        lobbyLayout.setBackground(createBackground(Color.GREY));
        
        
        //Switching Buttons between the scenes
        Button btLeave = createButton("LEAVE");
        btLeave.setOnAction((e) -> stage.setScene(lobbyScene));
        
        Button btStart = createButton("START");
        btStart.setOnAction((e) -> stage.setScene(gameScene));
        
        
        TilePane tilePane = new TilePane(Orientation.VERTICAL);
        Label leaveTilePane = new Label("DO YOU WANT TO LEAVE?");
        
        Popup popup = new Popup();
        
        btLeave.setOnAction((e) -> popup.show(stage)); //AJOUTER A L'EVENT : PAUSE LE JEU 
        
        
        TilePane tPChoice = new TilePane(Orientation.HORIZONTAL);
        
        //Choice Button Creation
        Button btYes = createButton("YES");
        btYes.setPrefSize(100, 50);
        Button btNo = createButton("NO");
        btNo.setPrefSize(100, 50);
        
        //Event Handler when the buttons are clicked on
        btNo.setOnAction((e) -> popup.hide());
        btYes.setOnAction((e) -> {stage.setScene(lobbyScene); 
        popup.hide();
        });
        
        //Def Caracteristics of the popUp
        tPChoice.getChildren().addAll(btYes,btNo);
        tPChoice.setHgap(25);
        tPChoice.setPrefColumns(2);
        tPChoice.setPrefRows(1);
        
        
        //Def Caracteristics of the tilePane containing label + buttons
        tilePane.getChildren().addAll(leaveTilePane,tPChoice);
        tilePane.setPrefRows(2);
        tilePane.setPrefColumns(1);
        tilePane.setBackground(createBackground(Color.CORAL));
        tilePane.setPrefSize(300, 135);
               
        popup.getContent().addAll(tilePane);
        
        gp.add(btLeave,2,5);   
        
        
        Button btLeaveStage = createButton("LEAVE");
        btLeaveStage.setOnAction((e) -> stage.close());
        
        
        lobbyLayout.add(btLeaveStage,2,3,1,1);
        lobbyLayout.add(btStart,2,1,1,1);
        lobbyLayout.add(lobbyPane(),2,2);
        
        lobbyLayout.setAlignment(Pos.CENTER);
        //lobbyLayout.setPrefSize(38*5, 38*10);        
        
        stage.setScene(lobbyScene);
        stage.setResizable(false);
        stage.setTitle("Tetris");
        stage.show();

    }   
    
    
    private void initGrid(Grid gameGrid, GridPane gridPane){
        
        int width = gameGrid.grid.length;
        int height = gameGrid.grid[0].length;
        
        System.out.println("w :"+width);
        System.out.println("h :"+height);

         for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                
                // Create a new TextField in each Iteration
                TextField tf = new TextField();
                tf.setPrefHeight(50);
                tf.setPrefWidth(50);
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(false);

                // Iterate the Index using the loops
                gridPane.setRowIndex(tf,y);
                gridPane.setColumnIndex(tf,x);    
                gridPane.getChildren().add(tf);
            }
        }
    }


    //Grille où les pièces tombent
    private GridPane bigLeftGrid() {
        
        // display the grid game here
        GridPane gridPane = new GridPane();
        Grid gameGrid = new Grid();

        
        //gridPane MGMT
        
            //LIEN AVEC LE CODE PRINCIPALE ICI
            
         //gridPane.addColumn(0, new Label("HOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"));

        //gridPane.setAlignment(Pos.CENTER);
        
        
        initGrid(gameGrid, gridPane);       
         
        Point[] pts = gameGrid.dropTile();
        
        
        for (Point p:pts){
            TextField tf = new TextField("X");
            gridPane.setRowIndex(tf,p.getY());
            gridPane.setColumnIndex(tf,p.getX()); 
            gridPane.getChildren().add(tf);
        }


        //Ajout des Elmts dans gridPane
        //gridPane.add(new javafx.scene.shape.Rectangle(38*10, 38*22, Color.WHITE),2,1,1,1);
        //gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.GOLD),0,0,1,1); 
        //gridPane.add(createRect(38, 38, Color.GOLD),3,0,1,1); 

        return gridPane;
    }
    
    
    
    private Pane lobbyPane(){
        Pane lobbyPane = new Pane();
        
        lobbyPane.setBackground(createBackground(Color.MAGENTA));
        
        
        Button lvlSelect = createButton("LEVEL");
        
        
        lobbyPane.getChildren().addAll(lvlSelect);
        
        return lobbyPane;
    }
    
    
    
    
    
    private GridPane bigRightGrid(){
        GridPane gridPane = new GridPane();
        
        // Création de la HBox NEXT TILE
        Label nextTile = new Label("NEXT TILE");
        nextTile.setFont(Font.font("arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 30));
        HBox nextTileBox = createHBox(0, 0, 0, 0, nextTile);
        nextTile.setBackground(createBackground(Color.BROWN));
        
        // Création de la HBox contenant le score
        //Il faudra get le score pour le mettre ici
        Label scoring = new Label("SCORE"+"\n"+"'getScore'");
        scoring.setFont(Font.font("arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 30));
        HBox score = createHBox(0, 0, 0, 0, scoring);
        score.setBackground(createBackground(Color.CORAL));
        

        // Création de la HBox contenant le niveau
        Label level = new Label("LEVEL"+"\n"+"'getLvl'");
        level.setFont(Font.font("arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 30));
        HBox lvlBox = createHBox(0, 0, 0, 0, level);
        lvlBox.setBackground(createBackground(Color.ALICEBLUE));
        
        
        // Création de la HBox Pause
        Label pause = new Label("PAUSE");
        pause.setFont(Font.font("arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 25));
        HBox pauseBox = createHBox(0, 0, 0, 0, pause);
        pauseBox.setBackground(createBackground(Color.AQUA));
        
                // Création de la HBox Leave
        Label leave = new Label("LEAVE");
        leave.setFont(Font.font("arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 25));
        HBox leaveBox = createHBox(0, 0, 0, 0, leave);
        leaveBox.setBackground(createBackground(Color.BISQUE));
        
        
        Button btPause = new Button();
        //btPause.setOnAction((e) -> ); //Ouvrir une popUp de pause et pause le jeu
        btPause.setGraphic(pauseBox);
        btPause.setBorder(Border.EMPTY);
        btPause.setPrefSize(38*4, 38);
        
        
        Button btLeave = new Button();
        //btLeave.setOnAction((e) -> stage.setScene(scene2));
        /**
         * satge non déclarer ici. Ajouter le button dans le Lobby pour pour setScene() ?
         * 
         * Doit mettre ouvrir une popUp avec deux buttons "Do you want to leave?" "YES" "NO"
         * Anchor la popUp à la scene. "YES" arrete la partie et affiche la mainScene. "NO" start un timer de 3s et reprend la partie.
         */



        
        btLeave.setGraphic(leaveBox);
        btLeave.setBorder(Border.EMPTY);
        btLeave.setPrefSize(38*4, 38);
        
        
        
        gridPane.add(createRect(38, 38, Color.OLIVE),2,0,1,1);
        gridPane.add(nextTileBox,1,1,1,1);gridPane.add(createRect(38, 38, Color.GOLDENROD),2,1,1,1);
        gridPane.add(createRect(38, 38, Color.ORANGE),2,1,1,2);
        gridPane.add(nextTileGrid(),1,2);
        gridPane.add(createRect(38, 76, Color.GOLD),2,3,1,1);
        gridPane.add(score,1,4,1,2); gridPane.add(createRect(38, 38*2, Color.GOLDENROD),2,4,2,2);
        gridPane.add(createRect(38, 76, Color.GOLD),2,6,1,1);
        gridPane.add(lvlBox,1,7,1,2); gridPane.add(createRect(38, 38*2, Color.GOLDENROD),2,7,2,2);
        gridPane.add(createRect(38, 38*6, Color.GOLD),2,9,1,1);
        gridPane.add(btPause,1,10);  gridPane.add(createRect(38, 38, Color.GOLDENROD),2,10,1,1);
        //gridPane.add(createRect(38, 38, Color.GOLD),2,11,1,1);        
        //gridPane.add(btLeave,1,12);  gridPane.add(createRect(38, 38, Color.GOLDENROD),2,12,1,1);
       
        

        return gridPane;
  
    }
    
    
    private GridPane nextTileGrid(){
        GridPane gridPane = new GridPane();
        
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.GREEN),1,1,1,1); 
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.RED),1,2,1,1);
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.GREEN),1,3,1,1);
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.RED),1,4,1,1);
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.RED),2,1,1,1); 
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.GREEN),2,2,1,1);
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.RED),2,3,1,1);
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.GREEN),2,4,1,1);
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.GREEN),3,1,1,1); 
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.RED),3,2,1,1);
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.GREEN),3,3,1,1);
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.RED),3,4,1,1);
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.RED),4,1,1,1); 
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.GREEN),4,2,1,1);
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.RED),4,3,1,1);
        gridPane.add(new javafx.scene.shape.Rectangle(38, 38, Color.GREEN),4,4,1,1);

        

        return gridPane;
        
    }
    
    private HBox createHBox(double top, double right, double bottom, double left, Label title) {
        HBox hbox = new HBox();
        hbox.getChildren().addAll(title);
        hbox.setPadding(new Insets(top, right, bottom, left));
        hbox.setAlignment(Pos.TOP_CENTER);
        return hbox;
    }
    
    
    private Background createBackground(Color color){
        return new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
        
    }
    
    private Rectangle createRect(int length,int height,Color color){
        return new javafx.scene.shape.Rectangle(length, height, color);
    }
    
    private Button createButton(String name){
        Button button = new Button();
        Label label = new Label(name);
        button.setGraphic(label);
        
        return button;
    }
}
