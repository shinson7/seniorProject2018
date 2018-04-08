package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LinkTrainer extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;
        Texture turf;
        Sprite player;
        Sprite turfBack;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
                turf = new Texture("turf.jpg");
                turfBack = new Sprite(turf);
                turfBack.setScale(0.8f);
                turfBack.setPosition(-175,-100);
                player = new Sprite(img);
                player.setPosition(Gdx.graphics.getWidth()/2-player.getWidth()/2, 0);
                player.setScale(0.3f);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.529f, .808f, .922f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		turfBack.draw(batch);
                player.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
