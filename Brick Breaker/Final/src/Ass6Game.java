import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

/**
 * GameLevel - this class will run the main function and help initialize
 * the Game.
 *
 * @author Uriel Schwell
 * @version 22.04.2018
 */
public class Ass6Game {

    /**
     * main - the Main (starting) function.
     *
     * @param args - user entered arguements
     */

    public static void main(String[] args) {
        List<LevelInformation> levelList = new LinkedList<LevelInformation>();

        while (true) {
            GUI gui = new GUI("Arkanoid", 800, 600);
            KeyboardSensor keyBoard = gui.getKeyboardSensor();
            AnimationRunner animation = new AnimationRunner(gui);
            int lives = 7;

            FileReader file = null;
            if (args.length == 0) {
                try {
                    file = new FileReader("resources/definitions/level_sets.txt");
                } catch (Exception e) {
                    System.out.println("File not found");
                }
            } else {
                try {
                    file = new FileReader(args[0]);
                } catch (Exception e) {
                    System.out.println("there was an Error");
                }
            }


            MenuAnimation subMenu = new MenuAnimation(keyBoard, animation);
            LevelSetReader sets = new LevelSetReader(file);
            sets.addLevelsToMap();
            sets.addToMenu(subMenu, animation, keyBoard, lives);


            Task systemExit = new Task() {
                @Override
                public Object run() {
                    System.exit(0);
                    return null;
                }
            };
            Task loadHighScore = new Task() {
                @Override
                public Object run() {
                    File tempFile = new File("highScores.ser");
                    HighScoresTable tempHighScores = HighScoresTable.loadFromFile(tempFile);
                    Animation highScoreAnimation = new HighScoresAnimation(tempHighScores, "space", keyBoard);
                    Animation stoppableHighScore = new KeyPressStoppableAnimation(keyBoard, "space",
                            highScoreAnimation);
                    animation.run(stoppableHighScore);
                    return null;
                }
            };


            //we create our Menu
            Menu<Task> menu = new MenuAnimation<Task>(keyBoard, animation);
            menu.addSelection("h", "High Scores", loadHighScore);
            menu.addSubMenu("s", "Game", subMenu);
            menu.addSelection("q", "quit", systemExit);


            //we run the 'menu' and build our game according to that selection
            animation.run(menu);
            Task todo = menu.getStatus();
            todo.run();


            List<LevelInformation> levels = LevelSpecificationReader.fromReader(file);
            GameFlow game = new GameFlow(levelList, animation, keyBoard, lives);
            game.runLevels(levels);

        }

    }

}
