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

/**
 * The type Level 3.
 */
public class Level3 implements LevelInformation {
    private static final int NUM_OF_BALLS = 2, ANGLE1 = 330, ANGLE2 = 30, BALL_SPEED = 6, PADDLE_SPEED = 10,
            PADDLE_WIDTH = 100, FRAME_WIDTH = 800, FRAME_HEIGHT = 600, NUM_OF_BLOCKS = 40,
            BLOCK_WIDTH = 50, BLOCK_HEIGHT = 30,
            SMALL_BLOCK_LINES = 5, SMALL_BLOCK_COLUMNS = 11;
    private static final Point BLOCKS_START_POINT = new Point(770, 150);

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(ANGLE1, BALL_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(ANGLE2, BALL_SPEED));
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
        return "Train Stop";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            private int counter = 0;

            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(0, 102, 0));
                d.fillRectangle(30, 30, FRAME_WIDTH - 60, FRAME_HEIGHT - 30);
                d.setColor(new Color(51, 51, 51));
                d.fillRectangle(30 + counter, 580, 200, 20);
                d.fillRectangle(210 + counter, 570, 10, 10); // exhaust chamber
                d.fillRectangle(230 + counter, 590, 15, 10);
                d.setColor(new Color(153, 153, 153));
                for (int i = 0; i < counter % 5; i++) {
                    d.fillCircle(215 + counter, 565 - (i * 2), 3);
                }
            }

            @Override
            public void timePassed() {
                counter++;
                if (counter == FRAME_WIDTH) {
                    counter = -200;
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
        levelColors.add(Color.GRAY);
        levelColors.add(Color.RED);
        levelColors.add(Color.YELLOW);
        levelColors.add(Color.BLUE);
        levelColors.add(Color.WHITE);
        return levelColors;
    }

    @Override
    public List<Block> blocks() {
        List<Color> blockColors = levelColors();
        List<Block> blocks = new ArrayList<>();
        /*Add blocks to game in triangular matrix order*/
        for (int i = 1; i <= SMALL_BLOCK_LINES; i++) {
            for (int j = 1; j <= SMALL_BLOCK_COLUMNS - i; j++) {
                Block block = new Block(new Rectangle(
                        new Point(BLOCKS_START_POINT.getX() - (j * BLOCK_WIDTH),
                                BLOCKS_START_POINT.getY() + (i * BLOCK_HEIGHT)),
                        BLOCK_WIDTH, BLOCK_HEIGHT));
                block.getCollisionRectangle().setColor(blockColors.get(i - 1));
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
