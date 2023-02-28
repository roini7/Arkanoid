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
 * The type Level 2.
 */
public class Level2 implements LevelInformation {
    private static final int NUM_OF_BALLS = 10, ANGLE = 280, BALL_SPEED = 8, PADDLE_SPEED = 10, PADDLE_WIDTH = 600,
            FRAME_WIDTH = 800, FRAME_HEIGHT = 600, NUM_OF_BLOCKS = 15, BLOCK_WIDTH = 49, BLOCK_HEIGHT = 20;

    /**
     * Level colors list.
     *
     * @return the list
     */
    public List<Color> levelColors() {
        List<Color> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.PINK);
        colors.add(Color.CYAN);
        return colors;
    }

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        int angleUpdate = 18;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(ANGLE + (i * angleUpdate), BALL_SPEED));
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
        return "Rainbow";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            private int counter = 0;
            private int timeOut = 0;

            @Override
            public void drawOn(DrawSurface d) {
                for (int i = 0; i < 60; i++) {
                    d.setColor(new Color(51, 153 + i, 255));
                    d.fillRectangle(30, 30 + (540 / 51) * i, 740, 540 / 51);
                }
                List<Color> rainBow = levelColors();
                int currColor = 0, iterator = 0;
                currColor += counter;
                for (Color color : rainBow) {
                    d.setColor(rainBow.get(currColor % rainBow.size()));
                    if (iterator == rainBow.size() - 1) {
                        d.setColor(new Color(51, 170, 255));
                    }
                    d.fillCircle(FRAME_WIDTH / 2, FRAME_HEIGHT + 20 * (iterator), 500);
                    currColor++;
                    iterator++;
                }
            }

            @Override
            public void timePassed() {
                ++timeOut;
                if (timeOut % 5 == 0) {
                    counter++;
                }
            }

            @Override
            public void addToGame(GameLevel gameLevel) {
                gameLevel.addSprite(this);
            }
        };
    }


    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        List<Color> blockColors = levelColors();
        int counter = -1;
        for (int i = 0, j = 0; i < NUM_OF_BLOCKS; i++, j++) {
            Block block = new Block(new Rectangle(new Point(32 + BLOCK_WIDTH * i, 300), BLOCK_WIDTH, BLOCK_HEIGHT));
            if (j % 2 == 0 && j != 8) {
                ++counter;
            }
            block.getCollisionRectangle().setColor(blockColors.get(counter % blockColors.size()));
            blocks.add(block);
            if (i == 8) {
                counter++;
                j--;
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
