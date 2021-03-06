/*******************************************************************************
*  file:SwingProject
*  author:Omar Rodriguez
          Nahid Enayatzadeh
          Marc Deaso
          Christopher Santos
          Jazmin Guerrero
*   class: CS245 - Programming Graphical User Interfaces
* 
*  assignment: Swing ProjectV1
*  date last modified: October/06/2016
* 
* Purpose: This program implements the traditional game of Hangman
* Program working with following functions:
* 1)Centered the screen on 600 x 400 pixel window
* 2)Program will have a start up screen which display group name for 3 second
* 3)After showing three function buttons, play, High Score and credit on new screen
* 4)By clicking on each button take us to the new page 
* 5)Must display current time and date
* 6)This game a random word will be selected from the following list(abstract,
* cemetery, nurse,pharmacy,climbing). user may click on one of the alphabet 
* button to guess the letter which may be in selected word of guessing, the line 
* makes by the lines and the button will be display. if the guessing is not found
* it will give us alert
* 
*/
package main.java;

import java.awt.Color;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import main.java.controller.*;
import main.java.model.*;
import main.java.view.*;

public class SwingProject {
    public static final String PROJECT_NAME = "CS 245 - Swing Project v1";
   
    public static final String CONTRIBUTORS[] = {
        "Omar Rodriguez, 008796203", 
        "Nahid Enayatzadeh, 010164622",
        "Marc Deaso, 011179285",
        "Christopher Santos, ",
        "Jazmin Guerrero, 009007193"};
    
    public static final String SCORE_NUMBER[] = {
        "Omar Rodriguez..00",
        "Nahid Enayatzadeh..00",
        "Marc Deaso..00",
        "Christopher Santos..00",
        "Jazmin Guerrero..00"};
    
    public static final String SPLASH_KEY = "splashscreen";
    public static final String FUNCTION_KEY = "functionscreen";
    public static final String HIGH_SCORE_KEY = "highscorescreen";
    public static final String CREDITS_KEY = "creditsscreen";
    public static final String GAME_KEY = "gamescreen";
    public static final String GAME_OVER_KEY = "gameoverscreen";

    private MainFrameController mainFrameController;
    
    private SplashController splashController;
    private FunctionController functionController;
    private GameController gameController;
    private CreditsController creditsController;
    private GameOverController gameoverController;
    private HighScoreController highScoreController;
    
    private SwingProject(){
    }
    
    //method: setup
    //purpose: Create the various panels (game screens) for our game
    // and attach them to the main frame.
    private void setup(){
        mainFrameController = new MainFrameController(
                new MainFrameModel(PROJECT_NAME,600,400,null,EXIT_ON_CLOSE), 
                new MainFrame()
        );
        
        splashController = new SplashController(
                new SplashPanel(),
                new SplashModel(PROJECT_NAME,"REDS",Color.BLACK,3000),
                mainFrameController
        );
        
        functionController = new FunctionController(
                new FunctionPanel(),
                new FunctionModel(Color.BLACK,"Play","HighScores","Credits"),
                mainFrameController
        );
        
        gameController = new GameController(
                new GamePanel(),
                new GameModel(),
                mainFrameController
        );
        
        creditsController = new CreditsController(
                new CreditsPanel(),
                new CreditsModel("Credits", CONTRIBUTORS, Color.BLACK),
                mainFrameController
        );
        
        gameoverController = new GameOverController(
                new GameOverPanel(),
                new GameOverModel(),
                mainFrameController
        );
        highScoreController = new HighScoreController(
                new HighScorePanel(),
                new HighScoreModel("HighScore", SCORE_NUMBER, Color.BLACK),
                mainFrameController
        );
                
        mainFrameController.addPanel(splashController.getPanel(),SPLASH_KEY);
        mainFrameController.addPanel(functionController.getPanel(),FUNCTION_KEY);
        mainFrameController.addPanel(gameController.getPanel(),GAME_KEY);
        mainFrameController.addPanel(creditsController.getPanel(),CREDITS_KEY);
        mainFrameController.addPanel(gameoverController.getPanel(),GAME_OVER_KEY);
        mainFrameController.addPanel(highScoreController.getPanel(),HIGH_SCORE_KEY);
        
        functionController.setGameControllerReference(gameController);
        gameoverController.setGameControllerReference(gameController);
    }
    
    //method: setupAndStart
    //purpose: call setup method, switch to first application screen (splash)
    //then set the whole thing visible
    private void setupAndStart(){
        javax.swing.SwingUtilities.invokeLater(() -> {
            setup();
            mainFrameController.changeVisibleCard(SPLASH_KEY);
            mainFrameController.getFrame().setVisible(true);
        });
    }

    //method: main
    //purpose: the entry-point to our application
    public static void main(String[] args) {
        SwingProject game = new SwingProject();
        game.setupAndStart();
    }
    
}
