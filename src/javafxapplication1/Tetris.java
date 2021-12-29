/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Noah
 */
public class Tetris extends Application {

    /**
     * @param args the command line arguments
     */
    
    @Override
    public void start(Stage primaryStage) {
        new Lobby(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }

    
}
