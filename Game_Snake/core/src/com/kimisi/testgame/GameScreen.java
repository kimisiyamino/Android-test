package com.kimisi.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class GameScreen extends ScreenAdapter {
    SpriteBatch batch;
    private Texture snakeHead, snakeBody;
    private Texture shit;

    private Array<BodyPart> bodyPartsArray = new Array<>();

    private int shitX = 0, shitY = 0;
    private boolean shitAvailable = false;

    private static final float MOVE_TIME = 1F;
    private float timer = MOVE_TIME;

    private static final int SNAKE_MOVEMENT =  32;
    private int snakeX = 0, snakeY = 0;

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    private int snakeDirection = RIGHT;

    private int snakeXBeforeUpdate = 0, snakeYBeforeUpdate = 0;


    private void updateBodyPartsPosition() {
        if (bodyPartsArray.size > 0) {
            BodyPart bodyPart = bodyPartsArray.removeIndex(0);
            bodyPart.updateBodyPosition(snakeXBeforeUpdate,
                    snakeYBeforeUpdate);
            bodyPartsArray.add(bodyPart);
        }
    }

    @Override
    public void show () {
        batch = new SpriteBatch();
        snakeHead = new Texture("snakehead.png");
        snakeBody = new Texture(Gdx.files.internal("snakebody.png"));
        shit = new Texture(Gdx.files.internal("shi.png"));
    }

    @Override
    public void render (float delta) {
        queryInput();
        timer -= delta;
        System.out.println(delta + " " + timer);

        if (timer <= 0) {
            timer = MOVE_TIME;
            moveSnake();
            checkForOutOfBounds();
            updateBodyPartsPosition();
        }
        checkAppleCollision();
        checkAndPlaceApple();

        clearScreen();
        draw();

    }

    @Override
    public void dispose () {
        batch.dispose();
        snakeHead.dispose();
    }

    private void checkAppleCollision() {
        if (shitAvailable && shitX == snakeX && shitY == snakeY) {
            BodyPart bodyPart = new BodyPart(snakeBody);
            bodyPart.updateBodyPosition(snakeX, snakeX);
            bodyPartsArray.insert(0, bodyPart);
            shitAvailable = false;
        }
    }

    private void checkForOutOfBounds() {
        if (snakeX >= Gdx.graphics.getWidth()) {
            snakeX = 0;
        }
        if (snakeX < 0) {
            snakeX = Gdx.graphics.getWidth() - SNAKE_MOVEMENT;
        }
        if (snakeY >= Gdx.graphics.getHeight()) {
            snakeY = 0;
        }
        if (snakeY < 0) {
            snakeY = Gdx.graphics.getHeight() - SNAKE_MOVEMENT;
        }
    }

    private void clearScreen(){
        Gdx.gl.glClearColor(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b, Color.WHITE.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void draw(){
        batch.begin();

        batch.draw(snakeHead, snakeX, snakeY, 32, 32);

        for (BodyPart bodyPart : bodyPartsArray) {
            bodyPart.draw(batch);
        }

        if (shitAvailable) {
            batch.draw(shit, shitX, shitY, 32, 32);
        }
        batch.end();
    }

    private void moveSnake() {
        snakeXBeforeUpdate = snakeX;
        snakeYBeforeUpdate = snakeY;

        switch (snakeDirection) {
            case RIGHT: {
                snakeX += SNAKE_MOVEMENT;
                return;
            }
            case LEFT: {
                snakeX -= SNAKE_MOVEMENT;
                return;
            }
            case UP: {
                snakeY += SNAKE_MOVEMENT;
                return;
            }
            case DOWN: {
                snakeY -= SNAKE_MOVEMENT;
                return;
            }
        }

    }

    private void queryInput() {
        boolean lPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean uPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if (lPressed) snakeDirection = LEFT;
        if (rPressed) snakeDirection = RIGHT;
        if (uPressed) snakeDirection = UP;
        if (dPressed) snakeDirection = DOWN;
    }

    private void checkAndPlaceApple() {
        if (!shitAvailable) {
            do {
                shitX = MathUtils.random(Gdx.graphics.getWidth()
                        / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
                shitY = MathUtils.random(Gdx.graphics.getHeight()
                        / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;

                shitAvailable = true;
            } while (shitX == snakeX && shitY == snakeY);
        }
    }

    private class BodyPart {
        private int x, y;
        private Texture texture;

        public BodyPart(Texture texture) {
            this.texture = texture;
        }

        public void updateBodyPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void draw(Batch batch) {
            if (!(x == snakeX && y == snakeY))
                batch.draw(texture, x, y, 32,32);
        }
    }
}


