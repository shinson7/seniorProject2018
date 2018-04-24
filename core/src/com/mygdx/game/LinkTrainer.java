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
import java.util.ArrayList;

public class LinkTrainer extends ApplicationAdapter implements InputProcessor {

    SpriteBatch batch;
    Texture img;
    Texture turf;
    Sprite player;
    Sprite turfBack;
    BitmapFont font;
    boolean correctAnswerSelected = false;
    public Scenario currentScenario;
    public ArrayList scenarios;
    public int scenarioIndex = 0;

    @Override
    public void create() {
        //set global list of scenarios to pull from
        scenarios = initializeScenarios();
        currentScenario = (Scenario) scenarios.get(0);

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
        //Present initial explaination
        //boollean chain to determine what gets shown
        if (currentScenario.initialExplainationPassed == false) {
            font.draw(batch, currentScenario.ie, 125, 100, 400, 10, true);
            font.getData().setScale(.75f);

        } else if (currentScenario.initialExplainationPassed && currentScenario.choicePassed == false) {
            //give our four options
            font.draw(batch, currentScenario.o1, 50, 100, 100, 10, true);
            font.draw(batch, currentScenario.o2, 200, 100, 100, 10, true);
            font.draw(batch, currentScenario.o3, 350, 100, 100, 10, true);
            font.draw(batch, currentScenario.o4, 500, 100, 100, 10, true);
            font.getData().setScale(.5f);
        } else if (currentScenario.initialExplainationPassed && currentScenario.choicePassed && currentScenario.finalExplainationPassed == false) {
            //say good job and now begin playing footage of what should be done Ayush
            font.draw(batch, currentScenario.fe, 125, 100, 400, 10, true);
            font.getData().setScale(.5f);
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
                if (currentScenario.ca == 1) {
                    currentScenario.choicePassed = true;
                }
                break;
            case Keys.NUM_2:
                if (currentScenario.ca == 2) {
                    currentScenario.choicePassed = true;
                }
                break;
            case Keys.NUM_3:
                if (currentScenario.ca == 3) {
                    currentScenario.choicePassed = true;
                }
                break;
            case Keys.NUM_4:
                if (currentScenario.ca == 4) {
                    currentScenario.choicePassed = true;
                }
                break;
            case Keys.ENTER:
                if (currentScenario.initialExplainationPassed == false) {
                    currentScenario.initialExplainationPassed = true;
                } else if (currentScenario.initialExplainationPassed && currentScenario.choicePassed == false) {
                    currentScenario.choicePassed = true;
                } else if (currentScenario.initialExplainationPassed && currentScenario.choicePassed && currentScenario.finalExplainationPassed == false) {
                    currentScenario = (Scenario) scenarios.get(scenarioIndex + 1);
                    scenarioIndex ++;
                }
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

    public ArrayList initializeScenarios() {
        ArrayList<Scenario> scenarios = new ArrayList<Scenario>();
        //this is the function where you will make your scenarios
        //the order is as follows: initial explaination, option 1, option 2, option 3, option 4, correct option NUMBER, final explaination
        Scenario s1 = new Scenario("In this scenario, you will be taking the place of the breakside handler on offense. Currently, your team is attempting to move the disc up the field which is to the left of your perspective. The person you see is the handler that has the disc. They were looking upfield but now that it is stall six, they have turned to you to get you to make a move. You will choose what is the best offensive move you can make. Press enter to continue.", "Option 1: Cut to the left and then come back for the disc. This would look like a v where you would be cutting straight up the field then stopping and coming towards the handler at an angle. Press 1 to select this answer.", "Option 2: Stay where you are and call for the disc. Trust that the handler with the disc will break the mark and that your defender will not be prepared to stop the break throw. Press 2 to select this answer.", "Option 3: Cut towards the handler with the disc for a few steps before shifting into an upline cut where you will move at a 45 degree angle to the handler trying to move across into the open space. Press 3 to select this answer.", "Option 4: Cut towards the handler with the disc for a few steps before shifting to come behind them to give them the easy dump option. Press 4 to select this answer.", 3, "Correct! Option 3 is the best strategic decision. Since you are on the break side your cut will bring you into the open side, making it very easy for the handler with the disc to throw it to you. The 45 degree angle helps you lose your defender. Additionally, upline cuts put you in the power position. When you get the disc your defender will be behind you, you will have full view of the field, and your momentum will be going towards the endzone so you can easily throw a huck. Option 1 is incorrect because you would wind up clogging the cutting space and leaving only 2 hanlders. Option 2 is incorrect because it may be a high stall or the handler with the disc may have problems getting around the mark. Finally, option 4 is not necessarily a bad choice but the upline cut in option 3 is better. Please press ENTER to continue to the next scenario.");
        scenarios.add(s1);
        
        Scenario s2 = new Scenario("Scenario 2 coming soon", "OPTION 1", "OPTION 2", "OPTION 3", "OPTION 4",2,"Final explaination coming soon");
        scenarios.add(s2);
        
        return scenarios;
    }
}
