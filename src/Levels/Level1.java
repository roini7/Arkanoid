package Levels;

import Game.GameLevel;
import Geometry.Point;
import Geometry.Rectangle;
import Physics.Velocity;
import Sprites.Block;
import Sprites.Sprite;
import biuoop.DrawSurface;
import Colors.Colors;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Level 1.
 */
public class Level1 implements LevelInformation {
    private static final int NUM_OF_BALLS = 1, ANGLE = 0, BALL_SPEED = 2, PADDLE_SPEED = 10, PADDLE_WIDTH = 80,
            FRAME_WIDTH = 800, FRAME_HEIGHT = 600, NUM_OF_BLOCKS = 1;
    private static final Rectangle ZERO = new Rectangle(new Point(385, 200), 30, 30);

    /**
     * Instantiates a new Level 1.
     */
    public Level1() {
    }

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        ballVelocities.add(Velocity.fromAngleAndSpeed(ANGLE, BALL_SPEED));
        return ballVelocities;
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
        return "Space";
    }

    @Override
    public Sprite getBackground() {
        Sprite sprite = new Sprite() {
            private int numOfCircles = 1;
            private int indicator = 0;

            @Override
            public void drawOn(DrawSurface d) {
                Random random = new Random();
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
                for (int i = 0; i < numOfCircles; i++) {
                    d.setColor(new Colors().getColor());
                    d.drawRectangle(385 - i, 200 + (i * 3), 30 + i * 2, 30 + i);
                }
                for (int j = 0; j < 50; j++) {
                    d.setColor(Color.WHITE);
                    d.fillCircle(random.nextInt(FRAME_WIDTH - 30) + 30,
                            random.nextInt(FRAME_HEIGHT - 30) + 30, 1);
                }

            }

            @Override
            public void timePassed() {
                if (indicator == 0) {
                    numOfCircles += 1;
                    if (numOfCircles == 80) {
                        indicator = 1;
                    }
                }
                if (indicator == 1) {
                    numOfCircles -= 1;
                    if (numOfCircles == 1) {
                        indicator = 0;
                    }
                }
            }

            @Override
            public void addToGame(GameLevel gameLevel) {
                gameLevel.addSprite(this);
            }
        };
        return sprite;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block block = new Block(ZERO);
        block.getCollisionRectangle().setColor(Color.RED);
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
