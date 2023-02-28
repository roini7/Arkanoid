package Levels;

import Game.GameLevel;
import Geometry.Point;
import Geometry.Rectangle;
import Physics.Velocity;
import Sprites.Block;
import Sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Level 4.
 */
public class Level4 implements LevelInformation {
    private static final int NUM_OF_BALLS = 3, ANGLE = 330, BALL_SPEED = 10, PADDLE_SPEED = 10, PADDLE_WIDTH = 600,
            FRAME_WIDTH = 800, FRAME_HEIGHT = 600, NUM_OF_BLOCKS = 7 * 15, BLOCK_HEIGHT = 30, BLOCK_WIDTH = 49,
            ROWS = 7, COLUMNS = 15;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0, j = 0; i < 3; i++, j += 30) {
            velocities.add(Velocity.fromAngleAndSpeed(ANGLE + j, BALL_SPEED));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Red Carpet";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            private int cameraFlashCounter = 0;

            @Override
            public void drawOn(DrawSurface d) {
                Random random = new Random();
                d.setColor(new Color(51, 51, 51));
                d.fillRectangle(30, 30, FRAME_WIDTH - 60, FRAME_HEIGHT - 30);
                d.setColor(new Color(153, 0, 0));
                d.fillRectangle(300, 30, 200, 570); //carpet
                d.setColor(new Color(255, 250, 200));
                List<Integer> flashSizes = new ArrayList<>();
                for (int i = 0; i < 10; i++) { // 0 -15
                    flashSizes.add(random.nextInt(15));
                }
                for (int i = 0; i < 10; i++) {
                    d.fillCircle(280, 100 + i * 50, flashSizes.get(i));
                }
                for (int i = 0; i < 10; i++) {
                    d.fillCircle(520, 100 + i * 50, flashSizes.get(i));
                }
            }

            @Override
            public void timePassed() {
                cameraFlashCounter++;
                if (cameraFlashCounter == 10) {
                    cameraFlashCounter = 0;
                }
            }

            @Override
            public void addToGame(GameLevel gameLevel) {
                gameLevel.addSprite(this);
            }
        };
    }

    /**
     * Level colors list.
     *
     * @return the list
     */
    public List<Color> levelColors() {
        List<Color> levelColors = new ArrayList<>();
        levelColors.add(Color.DARK_GRAY);
        levelColors.add(Color.RED);
        levelColors.add(Color.YELLOW);
        levelColors.add(Color.GREEN);
        levelColors.add(Color.WHITE);
        levelColors.add(Color.PINK);
        levelColors.add(Color.CYAN);
        return levelColors;
    }

    @Override
    public List<Block> blocks() {
        List<Color> blockColors = levelColors();
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Block block = new Block(new Rectangle(new Point(32 + j * BLOCK_WIDTH, 150 + i * BLOCK_HEIGHT),
                        BLOCK_WIDTH, BLOCK_HEIGHT));
                block.getCollisionRectangle().setColor(blockColors.get(i));
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
