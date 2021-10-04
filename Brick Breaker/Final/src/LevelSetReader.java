import biuoop.KeyboardSensor;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * LevelSetReader - this class will define a subMenu Animation
 * to receive a level file.
 *
 * @author Uriel Schwell
 * @version 13.06.2018
 */

public class LevelSetReader {
    private Reader file;
    private Map<String, List<LevelInformation>> levels;
    private Map<String, String> messages;

    /**
     * Constructor.
     *
     * @param file -the file path.
     */
    public LevelSetReader(Reader file) {
        this.file = file;
    }


    /**
     * Takes our input File and converts it into the information for each Map.
     */
    public void addLevelsToMap() {
        BufferedReader buffer = new BufferedReader(this.file);
        try {
            String line = buffer.readLine();
            while (!line.isEmpty()) {
                String key = line.substring(0);
                String levelName = line.substring(2, line.length());
                line = buffer.readLine();
                String filepath = line;
                java.io.Reader reader = new FileReader(filepath);
                List<LevelInformation> levelList = (new LevelSpecificationReader().fromReader(reader));
                levels.put(key, levelList);
                messages.put(key, levelName);
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * creates and adds a subMenu.
     *
     * @param menuAnimation -our MenuAnimation.
     * @param runner -animationRunner.
     * @param sensor -keyboard sensor.
     * @param lives -lives left.
     */
    public void addToMenu(MenuAnimation<Task<Void>> menuAnimation, AnimationRunner runner,
                          KeyboardSensor sensor, int lives) {
        for (String s : levels.keySet()) {
            Task newTask = new Task() {
                @Override
                public Object run() {
                    GameFlow flow = new GameFlow(levels.get(s), runner, sensor, lives);
                    flow.runLevels(levels.get(s));
                    return null;
                }
            };
            menuAnimation.addSelection(s, messages.get(s), newTask);
        }
    }


}
