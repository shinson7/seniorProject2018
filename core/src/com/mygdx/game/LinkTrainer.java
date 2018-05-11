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
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

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
    public int scenarioCount = 0;
    public boolean dn = false;
    public boolean prefaceDone = false;
    public String prefaceText = "This is a Frisbee Strategy Trainer. You will be shown multiple scenarios and asked to select the best strategic course of action. Each scenario will provide you with a brief explaination followed by a clip of EPS's Ultimate Team's regular season that demonstrates how the sitation might come about in a game. You will then pick from four options. If you select the correct answer you will receive an explaination explaining why that is best, a video clip from the season of your strategy being executed, and possibly a first person animation of the cut you select. If you are ready to continue press ENTER";
    public boolean showVideo = false;

    @Override
    public void create() {
        //set global list of scenarios to pull from
        scenarios = initializeScenarios();
        currentScenario = (Scenario) scenarios.get(0);

//        System.out.println("Working Directory = " + System.getProperty("user.dir")); //Checks the current directory you're in, for reference
        batch = new SpriteBatch();
        img = new Texture("images/runningFigure.jpg");
        font = new BitmapFont();

        turf = new Texture("images/field.jpg");
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

        if (prefaceDone == false) {
            font.draw(batch, prefaceText, 75, 100, 500, 10, true);
            font.getData().setScale(.75f);
        }

        //boolean chain to determine what gets shown
        if (currentScenario.initialExplainationPassed == false && prefaceDone) {
            //Present initial explanation
            font.draw(batch, currentScenario.ie, 125, 100, 400, 10, true);
            font.getData().setScale(.75f);
        } else if (currentScenario.initialVideoPassed == false && currentScenario.initialExplainationPassed && dn == false) {
            //open video
            try {
                Desktop.getDesktop().open(new File(currentScenario.iv)); //This opens the video
                currentScenario.initialVideoPassed = true;
            } catch (IOException e) {
                System.err.println("Error was caught.");
            }
            currentScenario.initialVideoPassed = true;
        } else if (currentScenario.initialVideoPassed && currentScenario.choicePassed == false && dn == false) {
            //give our four options
            font.draw(batch, currentScenario.o1, 50, 100, 100, 10, true);
            font.draw(batch, currentScenario.o2, 200, 100, 100, 10, true);
            font.draw(batch, currentScenario.o3, 350, 100, 100, 10, true);
            font.draw(batch, currentScenario.o4, 500, 100, 100, 10, true);
            font.getData().setScale(.5f);
        } else if (currentScenario.initialExplainationPassed && currentScenario.choicePassed && currentScenario.finalExplainationPassed == false && dn == false) {
            font.draw(batch, currentScenario.fe, 125, 100, 400, 10, true);
            font.getData().setScale(.5f);
            //need to check for the video while still in our final explaination
            if (currentScenario.finalVideoPassed == false && showVideo && currentScenario.initialExplainationPassed && currentScenario.choicePassed && dn == false) {
                //open video
                try {
                    Desktop.getDesktop().open(new File(currentScenario.fv)); //This is the important line
                    currentScenario.finalVideoPassed = true;
                } catch (IOException e) {
                    System.err.println("Error was caught.");
                }
                currentScenario.finalVideoPassed = true;
            }
        }

        //end of scenarios
        if (dn == true) {
            font.draw(batch, "Congradulations! You have completed all availible Scenarios! Please press ENTER to resart the scenarios", 125, 100, 400, 10, true);
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
                if (prefaceDone == false) {
                    prefaceDone = true;
                } else if (currentScenario.initialExplainationPassed == false) {
                    //if you have just seen the initial explaination
                    currentScenario.initialExplainationPassed = true;
                } else if (currentScenario.initialExplainationPassed && currentScenario.initialVideoPassed && currentScenario.choicePassed && currentScenario.finalVideoPassed == false) {
                    //if its time to show the video
                    showVideo = true;
                } else if (currentScenario.initialExplainationPassed && currentScenario.choicePassed && currentScenario.finalVideoPassed) {
                    //THEY HAVE FINISHED A SCENARIO
                    if (scenarioIndex + 1 < scenarioCount) {
                        //we still have scenarios so advance
                        currentScenario = (Scenario) scenarios.get(scenarioIndex + 1);
                        scenarioIndex++;
                        showVideo = false;
                    } else if (scenarioIndex++ == scenarioCount) {
                        dn = true;
                    } else if (dn) {
                        scenarios = initializeScenarios();
                        currentScenario = (Scenario) scenarios.get(0);
                        scenarioIndex = 0;
                        dn = false;
                        prefaceDone = false;
                        showVideo = false;
                    }

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
        //the order is as follows: setup video, initial explanation, option 1, option 2, option 3, option 4, correct option NUMBER, action video, final explanation
        Scenario s1 = new Scenario( /*This is the handler upline cut*/
                /*Setup video*/"video/uplineSetup.mp4",
                /*Setup text*/ "In this scenario, you will be taking the place of the breakside handler on offense. Currently, your team is attempting to move the disc up the field which is to the left of your perspective. The person you see is the handler that has the disc. They were looking upfield but now that it is stall four, they have turned to you to get you to make a move. You will choose what is the best offensive move you can make. Press ENTER to continue.",
                /*Option 1*/ "Option 1: Cut to the left and then come back for the disc. This would look like a v where you would be cutting straight up the field then stopping and coming towards the handler at an angle. Press 1 to select this answer.",
                /*Option 2*/ "Option 2: Stay where you are and call for the disc. Trust that the handler with the disc will break the mark and that your defender will not be prepared to stop the break throw. Press 2 to select this answer.",
                /*Option 3*/ "Option 3: Cut towards the handler with the disc for a few steps before shifting into an upline cut where you will move at a 45 degree angle to the handler trying to move across into the open space. Press 3 to select this answer.",
                /*Option 4*/ "Option 4: Cut towards the handler with the disc for a few steps before shifting to come behind them to give them the easy dump option. Press 4 to select this answer.",
                /*Correct option*/ 3,
                /*Action video*/ "video/uplineAction.mp4",
                /*Explanation text*/ "Correct! Option 3 is the best strategic decision. Since you are on the break side your cut will bring you into the open side, making it very easy for the handler with the disc to throw it to you. The 45 degree angle helps you lose your defender. Additionally, upline cuts put you in the power position. When you get the disc your defender will be behind you, you will have full view of the field, and your momentum will be going towards the endzone so you can easily throw a huck. Option 1 is incorrect because you would wind up clogging the cutting space and leaving only 2 handlers. Option 2 is incorrect because it may be a high stall or the handler with the disc may have problems getting around the mark. Finally, option 4 is not necessarily a bad choice but the upline cut in option 3 is better. Please press ENTER to watch the video of this being executed. When you have watched the video please press ENTER again to advance to the next scenario."
        );
        scenarios.add(s1);

        Scenario s2 = new Scenario( /*This is the sideline play*/
                /*Setup video*/"video/sidelineSetup.mp4",
                /*Setup text*/ "In this scenario, you are a cutter on the open-side sideline right in front of the disc. The disc is not coming in immediately, so you have a bit of time to set up. You have the handler with the disc in your vision, and the rest of the cutters are lined up in a horizontal stack. You now have the opportunity to choose your move. Press enter to continue.",
                /*Option 1*/ "Option 1: Stay where you are and call for the disc, trusting the handler to give you a nice pass to space. Press 1 to select this answer.",
                /*Option 2*/ "Option 2: Run straight deep and call for it - there is a lot of wide-open deep space. Press 2 to select this answer.",
                /*Option 3*/ "Option 3: Run diagonally to the break side, and then cut hard back to the open side. Depending on the force, this would look like a greater-than sign (if the force is forehand) or a less-than sign (if the force is backhand). Press 3 to select this answer.",
                /*Option 4*/ "Option 4: Run straight towards the handler for a few steps, and then run hard away from the sideline. This could act as either a cut to clear space or a break-side cut. Press 4 to select this answer.",
                /*Correct option*/ 4,
                /*Action video*/ "video/sidelineAction.mp4",
                /*Explanation text*/ "Great work! You correctly option 4. This cut is called an 'L-cut' and is very useful since it both clears space and offers the potential for the handler to break the mark and center the disc. Option 1 is not a good choice because you remain stagnant in the open space and clog it. Your handler also may not be able to throw something easy to space. Option 2 is not completely wrong, but still incorrect - the throw your handler would have to make to hit you running straight away from them is a very tough one. And, option 3 is sub-optimal because although you first clear out of the space, you re-enter it and end up clogging the open side. Even if you get the disc, it is still on the sideline - exactly where the defense wants you to be - and your defender likely is close behind you. Please press ENTER to watch the video of this being executed. When you have watched the video please press ENTER again to advance to the next scenario."
        );
        scenarios.add(s2);

        //lastly set the total number of scenarios
        scenarioCount = scenarios.size();

        return scenarios;
    }
}
