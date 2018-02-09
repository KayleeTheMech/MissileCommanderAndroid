package org.kittyinboots.missilecommanderandroid.core;

import com.google.common.eventbus.EventBus;


import org.kittyinboots.missilecommanderandroid.controller.Controller;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.GameObject;
import org.kittyinboots.missilecommanderandroid.events.GameEvent;
import org.kittyinboots.missilecommanderandroid.events.GameEventMetaData;
import org.kittyinboots.missilecommanderandroid.events.GameEventType;
import org.kittyinboots.missilecommanderandroid.gui.GamePanelView;

import java.util.List;
import java.util.Observer;

import static org.kittyinboots.missilecommanderandroid.events.GameEventType.NEW_GAME_HAS_BEGUN;

public class SceneDirector {
    public static final int FRAME_RATE_SCALING = 1;
    public static final int BASE_CHANCE_FOR_ENEMY_THIS_FRAME = 1;
    public static final int NUMBER_OF_FRAMES_PER_ROUND = 1000;
    public static final int NUMBER_OF_FRAMES_OF_ACTIVE_WAVE = 750;
    private int frames;
    private int difficulty;

    //fixme inject the eventBus
    private EventBus eventBus;
    private Core core;
    private Controller controller;
    private SceneAssistant assistant;

    public SceneDirector(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public Controller getController(){
        return  controller;
    }
    /**
     * Calculates the next frame. Is executed by the Controller.
     */
    public void newFrame() {
        frames++;
        if (!assistant.isPlayerAlive()) {
            maybeExplodeSomewhere();
        } else {
            gameRound();
        }
    }

    /**
     * Calculate one game round.
     */
    private void gameRound() {
        if (frames % NUMBER_OF_FRAMES_PER_ROUND * FRAME_RATE_SCALING == 0) {
            // reset round counter
            frames = 1;
            // increase level
            difficulty++;
            // inform everyone who's interested
            eventBus.post(new GameEvent(GameEventType.NEW_ATTACK_WAVE_INCOMING));
        }

        if (frames % NUMBER_OF_FRAMES_OF_ACTIVE_WAVE * FRAME_RATE_SCALING == 0) {
            eventBus.post(new GameEvent(GameEventType.ATTACK_WAVE_IS_OVER, new GameEventMetaData<>("level", difficulty)));
        }

        if (frames < NUMBER_OF_FRAMES_OF_ACTIVE_WAVE * FRAME_RATE_SCALING && 100 * Math.random() * FRAME_RATE_SCALING < (difficulty + BASE_CHANCE_FOR_ENEMY_THIS_FRAME)) {
            // X percent to create enemy while playing
            assistant.createEnemy(difficulty);
        }
    }

    /**
     * Throws the dice whether an explosion should occur on the ground.
     */
    private void maybeExplodeSomewhere() {
        if (100 * Math.random() < 5 / FRAME_RATE_SCALING) {
            // GAME_OVER_SIMULATION: X percent chance to for an explosion on surface per frame
            assistant.randomExplosionOnSurface();
        }
    }

    /**
     * Executes the deactivation routine and returns the object of the same type.
     *
     * @param oldDeactivatable the object to deactivate
     * @param object           the object to return
     * @param <T>              T implements IDeactivate
     * @return object
     */
    private <T extends IDeactivate> T deactivateAndReturnObject(T oldDeactivatable, T object) {
        if (oldDeactivatable != null) {
            oldDeactivatable.deactivate();
        }
        return object;
    }

    /**
     * Starts a new game.
     */
    public void newGame() {
        frames = 0;
        core = deactivateAndReturnObject(core, new Core(eventBus));
        assistant = deactivateAndReturnObject(assistant, new SceneAssistant(eventBus, core));
        controller = deactivateAndReturnObject(controller, new Controller(core, this));

        eventBus.post(NEW_GAME_HAS_BEGUN);
        difficulty = 1;
    }

    /**
     * Adds observers to game core
     *
     * @param object an observer
     */
    public void addObserver(Observer object) {
        core.addObserver(object);
    }

    /**
     * Pauses the game
     */
    public void pause() {
        if (controller != null) {
            controller.pause();
        }
    }

    /**
     * Resumes from pause
     */
    public void resume() {
        if (controller != null) {
            controller.resume();
        }
    }

    /**
     * Handle a mouseclick on the game board
     *
     * @param boardPosition Position on the (internal) gameboard
     */
    public void mouseClick(Position boardPosition) {
        fireMissile(boardPosition);
    }

    private void fireMissile(Position p) {
        if (!controller.isPaused()) {
            assistant.shootMissile(p);
        } else {
            resume();
        }
    }

    /**
     * @return the current game score
     */
    public int getScore() {
        return assistant.getScore();
    }

    /**
     * Returns a list of all objects currently in the game.
     *
     * @return List of all GameObjects
     */
    public List<GameObject> getGameObjects() {
        return core.getGameObjects();
    }

    /**
     * @return true if player alive, false otherwise
     */
    public boolean isGameOngoing() {
        return assistant.isPlayerAlive();
    }
}
