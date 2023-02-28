import Animations.AnimationRunner;
import Game.GameFlow;
import Levels.LevelInformation;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import Levels.Level4;
import ListenerNotifier.Counter;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

//ID : 206235228

/**
 * A class to initialize and run game from.
 */
public class Ass6Game {
    /**
     * static method to create and instance of game , initialise and run it.
     *
     * @param args arguments from user
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        for (String string : args) {
            switch (string) {
                case "1":
                    levels.add(new Level1());
                    break;
                case "2":
                    levels.add(new Level2());
                    break;
                case "3":
                    levels.add(new Level3());
                    break;
                case "4":
                    levels.add(new Level4());
                    break;
                default:
                    System.out.println("running base game");
            }
        }
        if (levels.isEmpty()) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }
        GUI gui = new GUI("Arkanoid", 800, 600);
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui, 30),
                gui.getKeyboardSensor(), new Counter(0));
        gameFlow.runLevels(levels);
    }
}