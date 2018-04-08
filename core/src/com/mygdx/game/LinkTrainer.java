package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LinkTrainer extends ApplicationAdapter implements InputProcessor {

    SpriteBatch batch;
    Texture img;
    Texture turf;
    Sprite player;
    Sprite turfBack;
    BitmapFont font;
    boolean correctAnswerSelected = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("runningFigure.jpg");
        font = new BitmapFont();

        turf = new Texture("field.jpg");
        turfBack = new Sprite(turf);
        turfBack.setScale(0.125f);
        turfBack.setPosition(-2275, -1400);

        player = new Sprite(img);
        player.setPosition(Gdx.graphics.getWidth() / 2 - player.getWidth() / 2, -200);
        player.setScale(0.1f);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.823f, .412f, .118f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        turfBack.draw(batch);
        player.draw(batch);

        if (correctAnswerSelected == false) {
            //give our four options
            font.draw(batch, "Option 1: Cut to the left and then come back for the disc. This would look like a v where you would be cutting straight up the field then stopping and coming towards the handler at an angle. Press 1 to select this answer.", 50, 100, 100, 10, true);
            font.draw(batch, "Option 2: Stay where you are and call for the disc. Trust that the handler with the disc will break the mark and that your defender will not be prepared to stop the break throw. Press 2 to select this answer.", 200, 100, 100, 10, true);
            font.draw(batch, "Option 3: Cut towards the handler with the disc for a few steps before shifting into an upline cut where you will move at a 45 degree angle to the handler trying to move across into the open space. Press 3 to select this answer.", 350, 100, 100, 10, true);
            font.draw(batch, "Option 4: Cut towards the handler with the disc for a few steps before shifting to come behind them to give them the easy dump option. Press 4 to select this answer.", 500, 100, 100, 10, true);
            font.getData().setScale(.5f);
        }else {
            System.out.println("DONE");
            //say good job and now begin playing footage of what should be done Ayush
            font.draw(batch, "Correct! Option 3 is the best strategic decision. Since you are on the break side your cut will bring you into the open side, making it very easy for the handler with the disc to throw it to you. The 45 degree angle helps you lose your defender. Additionally, upline cuts put you in the power position. When you get the disc your defender will be behind you, you will have full view of the field, and your momentum will be going towards the endzone so you can easily throw a huck. Option 1 is incorrect because you would wind up clogging the cutting space and leaving only 2 hanlders. Option 2 is incorrect because it may be a high stall or the handler with the disc may have problems getting around the mark. Finally, option 4 is not necessarily a bad choice but the upline cut in option 3 is better. Please press ENTER to continue to the next scenario.",125,100, 400, 10, true);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    @Override
    public boolean keyDown(int i) {
        switch (i) {
            case Keys.NUM_1:
                System.out.println("USER SELECTED OPTION 1");
                break;
            case Keys.NUM_2:
                System.out.println("USER SELECTED OPTION 2");
                break;
            case Keys.NUM_3:
                System.out.println("USER SELECTED OPTION 3");
                correctAnswerSelected = true;
                break;
            case Keys.NUM_4:
                System.out.println("USER SELECTED OPTION 4");
                break;
            default:
                break;
        }
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
