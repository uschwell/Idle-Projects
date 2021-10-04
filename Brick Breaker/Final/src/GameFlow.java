import biuoop.DialogManager;
import biuoop.KeyboardSensor;

import java.io.File;
import java.util.List;

/**
 * GameFlow - this class will help define our levels, and movement
 * between levels.
 *
 * @author Uriel Schwell
 * @version 22.04.2018
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private List<LevelInformation> levelInformation;
    private KeyboardSensor keyboardSensor;
    private int livesLeft;
    private File file;
    private HighScoresTable highScores;

    /**
     * \
     * constructor.
     *
     * @param levelInfo -level Information.
     * @param ar        -our animation runner.
     * @param k         -keyboard sensor.
     * @param lives     -live left.
     */
    public GameFlow(List<LevelInformation> levelInfo, AnimationRunner ar, KeyboardSensor k, int lives) {
        this.animationRunner = ar;
        this.levelInformation = levelInfo;
        this.keyboardSensor = k;
        this.livesLeft = lives;
        this.file = new File("highScores.ser");
        this.highScores = HighScoresTable.loadFromFile(this.file);
    }

    /**
     * @param levels -level to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        //our win/lose condition
        boolean winLose = true;

        //we create a Listener to track how many lives we have left.
        Counter lifeCounter = new Counter();
        lifeCounter.increase(this.livesLeft);

        Counter scoreCounter = new Counter();

        DialogManager dialog = animationRunner.getAnimationGUI().getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner,
                    this.keyboardSensor, lifeCounter, scoreCounter);
            level.initialize();
            //level has blocks left, and player has more lives, we keep playing.
            while (level.blocksLeft() > 0 && (lifeCounter.getValue() > 0)) {
                level.playOneTurn();
            }

            //if we die before getting rid of all blocks.
            if (level.blocksLeft() > 0) {
                lifeCounter.decrease(1);
            }
            //we are out of lives, we play the "you lost" animation
            if (lifeCounter.getValue() == 0) {
                winLose = false;
                break;
            }
        }
        //we create our Stoppable animations.
        Animation endgameAnimation = new EndGame(scoreCounter, this.keyboardSensor, winLose);
        Animation stoppableEndGame = new KeyPressStoppableAnimation(this.keyboardSensor, "space", endgameAnimation);

        Animation highScoreAnimation = new HighScoresAnimation(this.highScores, " ", this.keyboardSensor);
        Animation stoppableHighScore = new KeyPressStoppableAnimation(this.keyboardSensor, "space", highScoreAnimation);


        //we run the EndGame screen
        animationRunner.run(stoppableEndGame);
        ScoreInfo finalScore = new ScoreInfo(name, scoreCounter.getValue());

        //we try to add our final score to the list of high scores
        if (highScores.getRank(scoreCounter.getValue()) < highScores.size()) {
            highScores.add(finalScore);
        }

        //we now Print our current High Scores
        animationRunner.run(stoppableHighScore);
        highScores.add(new ScoreInfo("vered", 300));

        //we save our high Scores to insure all changes are kept.
        try {
            highScores.save(this.file);
            System.out.println("we have saved (in GameFlow)");
        } catch (Exception e) {
            System.out.println("Exception error:In GameFlow");
        }
    }
}

