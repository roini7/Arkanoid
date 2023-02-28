package Game;

import Animations.Animation;
import Animations.AnimationRunner;
import Animations.CountdownAnimation;
import Animations.KeypressStoppableAnimation;
import Animations.PauseScreen;
import Collidables.Collidable;
import Geometry.Point;
import Geometry.Rectangle;
import Levels.LevelInformation;
import ListenerNotifier.BallRemover;
import ListenerNotifier.BlockRemover;
import ListenerNotifier.Counter;
import ListenerNotifier.ScoreTrackingListener;
import Sprites.LevelName;
import Sprites.Sprite;
import Sprites.SpriteCollection;
import Sprites.Block;
import Sprites.ScoreIndicator;
import Sprites.Paddle;
import Sprites.Ball;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * A class that defines game and it's attributes.
 */
public class GameLevel implements Animation {
    private static final int FRAME_WIDTH = 800, FRAME_HEIGHT = 600, SMALL_BLOCK_WIDTH = 50, SMALL_BLOCK_HEIGHT = 30,
            SMALL_BLOCK_LINES = 6, SMALL_BLOCK_COLUMNS = 13, BALL_SIZE = 6, BALL_SPEED = 5,
            PADDLE_WIDTH = 200, PADDLE_HEIGHT = 15, BLOCKS = 57, BALLS = 3;
    private static final Color PADDLE_COLOR = Color.ORANGE;
    private static final Point BLOCKS_START_POINT = new Point(770, 150), BALL_START = new Point(400, 540);
    /*Sprites.Block Border parameters for game*/
    private static final Rectangle BORDER1 = new Rectangle(new Point(0, 0), 30, 600, Color.DARK_GRAY),
            BORDER2 = new Rectangle(new Point(30, 0), 740, 30, Color.DARK_GRAY),
            BORDER3 = new Rectangle(new Point(770, 0), 30, 600, Color.DARK_GRAY),
            BORDER4 = new Rectangle(new Point(30, 605), 740, 30, Color.BLACK);
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation gameLevel;
    private KeyboardSensor keyboardSensor;

    /**
     * Constructor for game, setting the Sprites in it and the game environment.
     *
     * @param gameLevel the game level
     */
    public GameLevel(LevelInformation gameLevel) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = new Counter(0);
        this.runner = new AnimationRunner(new GUI("Arkanoid", 800, 600), 60);
        this.running = false;
        this.gameLevel = gameLevel;
    }

    /**
     * Instantiates a new Game level.
     *
     * @param levelInfo       the level info
     * @param keyboardSensor  the keyboard sensor
     * @param animationRunner the animation runner
     * @param score           the score
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = score;
        this.runner = animationRunner;
        this.running = false;
        this.gameLevel = levelInfo;
        this.keyboardSensor = keyboardSensor;
    }

    /**
     * Gets remaining blocks.
     *
     * @return the remaining blocks
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * Gets remaining balls.
     *
     * @return the remaining balls
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * Add collidable object to game.
     *
     * @param c collidable to add to game
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite object to game.
     *
     * @param s sprite to add to game
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Getter for sprite collection.
     *
     * @return sprite collection of game
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * Getter for GameEnvironment of game.
     *
     * @return GameEnvironment of game
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Remove a collidable object from game.
     *
     * @param c is the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        getEnvironment().removeCollidable(c);
    }

    /**
     * Remove a Sprite object from game.
     *
     * @param s is the sprite to remove
     */
    public void removeSprite(Sprite s) {
        getSprites().removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks, Sprites.Ball and Sprites.Paddle
     * and add them to the game.
     */
    public void initialize() {
        remainingBlocks.increase(gameLevel.numberOfBlocksToRemove());
        remainingBalls.increase(gameLevel.numberOfBalls());
        BlockRemover br = new BlockRemover(this, remainingBlocks);
        BallRemover bar = new BallRemover(this, remainingBalls);
        ScoreTrackingListener stl = new ScoreTrackingListener(score);
        /*Border blocks*/
        gameLevel.getBackground().addToGame(this);
        new Block(BORDER1).addToGame(this);
        new Block(BORDER2).addToGame(this);
        new Block(BORDER3).addToGame(this);
        Block deathRegion = new Block(BORDER4);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(bar); // Add deathRegion listener for balls that intersect with
        for (Block block : gameLevel.blocks()) {
            /*add listener for block removing and score tracking to each block*/
            block.addHitListener(br);
            block.addHitListener(stl);
            block.addToGame(this);
        }
        new ScoreIndicator(score).addToGame(this);
        new LevelName(gameLevel).addToGame(this);
    }

    /**
     * Create balls.
     */
    public void createBalls() {
        Paddle paddle = new Paddle(runner.getGui(), gameLevel.paddleWidth(), gameLevel.paddleSpeed());
        paddle.addToGame(this);
        for (int i = 0; i < gameLevel.numberOfBalls(); i++) {
            Ball ball = new Ball(BALL_SIZE, BALL_START);
            ball.setPaddle(paddle);
            ball.setVelocity(gameLevel.initialBallVelocities().get(i));
            ball.setGameEnvironment(getEnvironment());
            ball.addToGame(this);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.createBalls();
        this.running = true;
        int ogFps = this.runner.getFramesPerSecond();
        this.runner.setFramesPerSecond((2 * 1000) / 3);
        this.runner.run(new CountdownAnimation(3, 3, getSprites()));
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.setFramesPerSecond(ogFps);
        this.runner.run(this);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        /*set background color of draw surface*/
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        /*exit game if all blocks are gone and add extra 100 points for completing level*/
        if (this.runner.getGui().getKeyboardSensor().isPressed("p")) {
            this.runner.run(new PauseScreen(new KeypressStoppableAnimation(
                    this.runner.getGui().getKeyboardSensor(), keyboardSensor.SPACE_KEY, this)));
        }
        if (remainingBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        /*exit game if all balls are gone - game over*/
        if (remainingBalls.getValue() == 0) {
            this.running = false;
        }
        /*activate draw method of all sprites in game*/
        this.sprites.drawAllOn(d);
        //this.runner.getGui().show(d);
        /*activate timePassed method on all sprites in game*/
        this.sprites.notifyAllTimePassed();
    }
}