package Game;

import Animations.AnimationRunner;
import Animations.KeypressStoppableAnimation;
import Animations.LoseScreen;
import Animations.WinScreen;
import Levels.LevelInformation;
import ListenerNotifier.Counter;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar    the ar
     * @param ks    the ks
     * @param score the score
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter score) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = score;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        GameLevel lastLevel = null;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score);
            lastLevel = level;
            level.initialize();

            while (level.getRemainingBlocks().getValue() > 0 && level.getRemainingBalls().getValue() > 0) {
                level.run();
            }

            if (level.getRemainingBalls().getValue() <= 0) {
                animationRunner.run(new LoseScreen(
                        new KeypressStoppableAnimation(keyboardSensor, keyboardSensor.SPACE_KEY, level), score));
                animationRunner.getGui().close();
                break;
            }
        }
        if (lastLevel != null) {
            animationRunner.run(new WinScreen(
                    new KeypressStoppableAnimation(keyboardSensor, keyboardSensor.SPACE_KEY, lastLevel), score));
            animationRunner.getGui().close();
        }
    }
}