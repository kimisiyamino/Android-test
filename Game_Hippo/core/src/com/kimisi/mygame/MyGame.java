package com.kimisi.mygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Iterator;


public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture nosolog, chipsTexture;
	Sound chipsEat;
	//Sprite nosologSprite;

	OrthographicCamera camera;
	Rectangle rect;
	Logger log;
	FPSLogger fpslogger;

	Vector3 touchPos;

	//Viewport viewport;
	//ShapeRenderer sh;
	Pixmap pixmap, pixmapN, pixmapChips;
	BitmapFont bitmap, bitmapFontFPS, bitmapFontCoord, bitmapScore;
	Texture pixTexture, pixTextureN;

	//Array<Rectangle> chipsDropsArray;
	Array<Chips> chipsDropsArray;
	long lastTime;

	long Score, Lose;
	boolean isStart = true;
	boolean isTouched;

	Stage stage;

	public class NosologActor extends Actor {
		public void draw (Batch batch, float parentAlpha) {
			batch.draw(nosolog, getX(), getY(), getWidth(), getHeight());
		}

	}

	public class TouchListener extends InputListener {
		public void touchDragged (InputEvent event, float x, float y, int pointer) {
			event.getListenerActor().setX(x);
		}
	}

	@Override
	public void create () {
		log = new Logger("Application", Logger.INFO);
		fpslogger = new FPSLogger();

		bitmap = new BitmapFont();
		bitmap.setColor(Color.GREEN);
		bitmap.getData().setScale(0.5f);

		bitmapFontFPS = new BitmapFont();
		bitmapFontFPS.setColor(Color.BLUE);
		bitmapFontFPS.getData().setScale(1.5f);

		bitmapFontCoord = new BitmapFont();
		bitmapFontCoord.setColor(Color.RED);
		bitmapFontCoord.getData().setScale(0.5f);

		bitmapScore = new BitmapFont();
		bitmapScore.setColor(Color.GOLD);
		bitmapScore.getData().setScale(2.5f);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800,480 );
		camera.update();
		//viewport = new FitViewport(1014, 540, camera);

		batch = new SpriteBatch();

		touchPos = new Vector3();

		NosologActor nosologActor = new NosologActor();
		nosologActor.addListener(new TouchListener());

		pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
		pixmap.setColor(0, 1, 0, 1);
		//pixmap.fillCircle(32, 32, 32);
        pixmap.fillRectangle(0,0,1,1);
		pixTexture = new Texture(pixmap);

		pixmapN = new Pixmap(64, 64, Pixmap.Format.RGB888);
		pixmapN.setColor(1,0,0,1);
		//pixmapN.fillRectangle(0,0,64,64);
		pixmapN.drawRectangle(0,0,64,64);
		pixTextureN = new Texture(pixmapN);


		nosolog = new Texture("nosolog.png");
		chipsTexture = new Texture("chips.png");

		//nosologSprite = new Sprite(nosolog);

		//Sprite sprite = new Sprite();

		chipsEat = Gdx.audio.newSound(Gdx.files.internal("hrum.mp3"));

		rect = new Rectangle();
		rect.x = 800 / 2 - 64 / 2;
		rect.y = 50;
		rect.width = 64;
		rect.height = 64;

		chipsDropsArray = new Array<Chips>();
		spawnChips();

		log.info("hello");
		log.info("Height = " + Gdx.graphics.getHeight());
		log.info("Width = " + Gdx.graphics.getWidth());
		log.info("camera Height = " + camera.viewportHeight);
		log.info("camera Width = " + camera.viewportWidth);

	}

	public void spawnChips(){
		Chips chips = new Chips(MathUtils.random(0, 800-32), 480, 32,32, MathUtils.random(50,500));
		chipsDropsArray.add(chips);
		lastTime = TimeUtils.nanoTime();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Gdx.gl.glViewport(0,0,1024,768);
		camera.update();

		// Сказать SpriteBatch использовать систему координат камеры
		batch.setProjectionMatrix(camera.combined);

		batch.begin();


		// Отображение Координат сетки
		for(int x = 0; x < 800; x+=32) {
			for(int y = 0; y < 480; y+=32){

				bitmap.draw(batch, "(" + x + ", " + y + ")", x + 3, y + 3);
			}
		}

		// Отображение сетки
		for(int x = 0; x < 800; x+=32) {
			for(int y = 0; y < 480; y+=32){

				batch.draw(pixTexture, x, y);
			}
		}


		/*for(int x = 0; x < 800; x++)
			batch.draw(pixTexture, x, 0);

		for(int y = 0; y < 480; y++)
			batch.draw(pixTexture, 0, y);

		for(int x = 0;  x < 800; x++)
			batch.draw(pixTexture, x, 479);

		for(int y = 0;  y < 480; y++)
			batch.draw(pixTexture, 799, y);*/



		// Отобразить рамку вокруг Персонажа
		batch.draw(pixTextureN, rect.x, rect.y, rect.width, rect.height);


		// Отобразить координаты Персонажа
		bitmapFontCoord.draw(batch, "(" + rect.x + ", " + rect.y + ")", rect.x - 3, rect.y - 3);


		// Показать FPS
		bitmapFontFPS.draw(batch, Integer.toString(Gdx.graphics.getFramesPerSecond()), 10, 460);


		// Показать Score
		bitmapScore.setColor(Color.GOLD);
		bitmapScore.draw(batch, Long.toString(Score), 50,460);
		// Показать Lose
		bitmapScore.setColor(Color.RED);
		bitmapScore.draw(batch, Long.toString(Lose), 700,460);


		// Отобразить персонажа
		batch.draw(nosolog, rect.x, rect.y, rect.width, rect.height);


		for(Chips chips : chipsDropsArray){
			// Отобразить рамку вокруг Drop
			batch.draw(pixTextureN, chips.x, chips.y, chips.width, chips.height);

		}for(Chips chips : chipsDropsArray){
		// Отобразить Drop
		batch.draw(chipsTexture, chips.x, chips.y, chips.width, chips.height);}
		for(Chips chips : chipsDropsArray){
			// Отобразить Coord чипсов
			bitmapFontCoord.draw(batch, "(" + chips.x + ", " + chips.y + ")", chips.x - 3, chips.y - 3);
		}
		batch.end();



		if(Gdx.input.isTouched()) {
			// Получить координаты прикосновения
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

			// Преобразование координат input в координаты Камеры
			camera.unproject(touchPos);

			if(touchPos.x >= rect.x && touchPos.x <= rect.x+64 ) {

				isTouched = true;

				//rect.x = (int) (touchPos.x) - 64 / 2;
			//rect.y = (int) (touchPos.y );
				rect.setX((int) (touchPos.x) - 64 / 2);
			}else if(isTouched) {
				rect.setX((int) (touchPos.x) - 64 / 2);
			}


		}else{
				isTouched = false;

		}




		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
				rect.x -= 1500 * Gdx.graphics.getDeltaTime();
			}else{
				rect.x -= 500 * Gdx.graphics.getDeltaTime();
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
				rect.x += 1500 * Gdx.graphics.getDeltaTime();
			}else{
				rect.x += 500 * Gdx.graphics.getDeltaTime();
			}
		}

		if(!isStart) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				isStart = true;
			}
		}else{
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				isStart = false;
			}
		}


		if(rect.x < 0) rect.x = 0;
		if(rect.x > 736) rect.x = 736;

		if(isStart)
			if(TimeUtils.nanoTime() - lastTime > 2500000) spawnChips();

		Iterator<Chips> iterator = chipsDropsArray.iterator();
		while (iterator.hasNext()){
			Chips chips = iterator.next();
			chips.y -= chips.speed * Gdx.graphics.getDeltaTime();
			if(chips.y + 64 < 0){
				Lose+=1;
				iterator.remove();
			}
			if(chips.overlaps(rect)){
				//rect.width+=1;
				//rect.height+=1;
				Score+=1;
				chipsEat.play();
				iterator.remove();
			}
		}


		class MyInputListener implements InputProcessor{

				@Override
				public boolean keyDown(int keycode) {
					return false;
				}

				@Override
				public boolean keyUp(int keycode) {
					return false;
				}

				@Override
				public boolean keyTyped(char character) {
					return false;
				}

				@Override
				public boolean touchDown(int screenX, int screenY, int pointer, int button) {
					return false;
				}

				@Override
				public boolean touchUp(int screenX, int screenY, int pointer, int button) {
					return false;
				}

				@Override
				public boolean touchDragged(int screenX, int screenY, int pointer) {
					return false;
				}

				@Override
				public boolean mouseMoved(int screenX, int screenY) {
					return false;
				}

				@Override
				public boolean scrolled(int amount) {
					return false;
				}
		}
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();

		chipsEat.dispose();

		nosolog.dispose();
		chipsTexture.dispose();
		pixTexture.dispose();
		pixTextureN.dispose();

		pixmap.dispose();
		pixmapN.dispose();

		bitmap.dispose();
		bitmapFontFPS.dispose();
		bitmapFontCoord.dispose();
	}


}

class Chips extends Rectangle{
	int speed;
	public Chips(float x, float y, float width, float height, int speed){
		super(x, y, width, height);
		this.speed = speed;
	}
}
