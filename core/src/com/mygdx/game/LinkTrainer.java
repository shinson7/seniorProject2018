package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.ArrayList;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LinkTrainer extends ApplicationAdapter implements InputProcessor {

    SpriteBatch batch;
    Texture img;
    Texture img2;
    Texture turf;
    Sprite player;
    Sprite turfBack;
    Sprite defender;
    BitmapFont font;
    ShapeRenderer render;
    boolean correctAnswerSelected = false;
    public Scenario currentScenario;
    public ArrayList scenarios;
    public int scenarioIndex = 0;
    public int scenarioCount = 0;
    public boolean dn = false;
    public boolean prefaceDone = false;
    public String prefaceText = "This is a Frisbee Strategy Trainer. You will be shown multiple scenarios and asked to select the best strategic course of action. Each scenario will provide you with a brief explaination followed by a clip of EPS's Ultimate Team's regular season that demonstrates how the sitation might come about in a game. You will then pick from four options. If you select the correct answer you will receive an explaination explaining why that is best, a video clip from the season of your strategy being executed, and possibly a first person animation of the cut you select. If you are ready to continue press ENTER";
    public boolean showVideo = false;

    // THIS BE NEW
    // for different sized screens
    public float xScl = 1;
    public float yScl = 1;
    // tags whether to animate or not
    public boolean animate = false;
    // used to scale player to sizes we want
    public float scale = 10;
    // original x and y of player sprite
    public float orX;
    public float orY;
    // original x and y of disc
    public float orX2;
    public float orY2;
    // original x and y of field
    public float orX3;
    public float orY3;
    // determines how far over to move player
    public float angle = 0;
    public float thro = 10;

    // scenario 2
    public float sideline = 250;
    public boolean huck = false;

    // disc obj
    Texture frisbee;
    Sprite disc;

    @Override
    public void create() {
        // set global list of scenarios to pull from
        scenarios = initializeScenarios();
        currentScenario = (Scenario) scenarios.get(0);

        // for drawing boxes and lines
        render = new ShapeRenderer();

        // System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // //Checks the current directory you're in, for reference
        batch = new SpriteBatch();
        img = new Texture("images/Handler.png");
        img2 = new Texture("images/Defense.png");
        font = new BitmapFont();

        turf = new Texture("images/Background.png");
        turfBack = new Sprite(turf);
        orX3 = turfBack.getWidth() * scale;
        orY3 = turfBack.getHeight() * scale;
        // turfBack.setScale(0.125f);
        // turfBack.setPosition(-2275, -1400);
        turfBack.setSize(640 * 2, 480 * 2);
        turfBack.setPosition(-640 / 2, -480 / 2);

        defender = new Sprite(img2);
        defender.setSize(defender.getWidth() / scale, defender.getHeight() / scale);

        player = new Sprite(img);
        // player.setPosition(Gdx.graphics.getWidth() / 2 - player.getWidth() / 2,
        // -200);
        // player.setScale(0.1f);
        orX = player.getWidth();
        orY = player.getHeight();
        player.setSize(player.getWidth() / scale, player.getHeight() / scale);
        player.setPosition(Gdx.graphics.getWidth() / 2 - player.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - player.getHeight() / 2);
        defender.setPosition(player.getX() - 20, player.getY() - 20);
        frisbee = new Texture("images/frisbee.jpg");
        disc = new Sprite(frisbee);
        orX2 = disc.getWidth();
        orY2 = disc.getHeight();
        disc.setSize(disc.getWidth() / (scale * 2), disc.getHeight() / (scale * 2));
        disc.setPosition(player.getX() - disc.getWidth() / 2, player.getY() + 100);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.823f, .412f, .118f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        xScl = (float) Gdx.graphics.getWidth() / 640;
        yScl = (float) Gdx.graphics.getHeight() / 480;
        batch.begin();
        if (animate) {
            //IGNORE THIS PART ITS BROKEN
            if (scenarioIndex == 0) {
                // handler upline.
                // first we need to have the player move towards the camera
                /*turfBack.draw(batch);
				player.draw(batch);
				disc.draw(batch);
				defender.draw(batch);
				if (scale > 5) {
					scale -= .1;
					player.setSize((orX / scale), (orY / scale));
					player.setPosition((640 / 2 - player.getWidth() / 2), (480 / 2 - player.getHeight() / 2));
					disc.setSize(xScl * (orX2 / (scale * 2)), (orY2 / (scale * 2)) * yScl);
					disc.setPosition((player.getX() - disc.getWidth() / 2), (480 / 2 - disc.getHeight() / 2));
				} else if (angle < 300) {
					angle += 1;
					player.setPosition(640 / 2 - player.getWidth() / 2 + angle, 480 / 2 - player.getHeight() / 2);
					disc.setPosition(player.getX() - disc.getWidth() / 2, 480 / 2 - disc.getHeight() / 2);
				} else if (thro < 200) {
					scale -= .1;
					disc.setPosition(disc.getX() - thro, disc.getY() - thro);
					disc.setSize(orX2 / scale, orY2 / scale);
				}*/
            } else if (scenarioIndex == 1) {
                turfBack.draw(batch);
                player.draw(batch);
                disc.draw(batch);
                defender.draw(batch);
                if (scale > 6) {
                    scale -= .05;
                    player.setSize((orX / scale), (orY / scale));
                    player.setPosition((640 / 2 - player.getWidth() / 2), (480 / 2 - player.getHeight() / 2));
                    disc.setSize((orX2 / (scale * 2)), (orY2 / (scale * 2)));
                    disc.setPosition((player.getX() - disc.getWidth() / 2), (480 / 2 - disc.getHeight() / 2));
                    defender.setSize((orX / scale), (orY / scale));
                    defender.setPosition(player.getX() + (200 / scale), player.getY() - (200 / scale));
                    turfBack.setSize(orX3 / scale, orY3 / scale);
                    turfBack.setCenter(640 / 2, 480 / 2);
                } else if (angle < 400) {
                    angle += 5;
                    if (angle == 120) {
                        huck = true;
                    }
                    player.setPosition((640 / 2 - player.getWidth() / 2) - angle, (480 / 2 - player.getHeight() / 2));
                    disc.setPosition((player.getX() - disc.getWidth() / 2), (480 / 2 - disc.getHeight() / 2));
                    defender.setPosition(player.getX() + (200 / scale), player.getY() - (200 / scale));
                    turfBack.setCenter(640 / 2 - angle / 2, 480 / 2);
                    sideline = 250 - angle;
                }
                if (huck && thro < 200) {
                    thro += 5;
                    disc.setPosition((player.getX() - disc.getWidth() / 2) - thro, (480 / 2 - disc.getHeight() / 2));
                }
            } else if (scenarioIndex == 2) {

            }
            batch.end();
            render.begin(ShapeType.Filled);
            if (scenarioIndex == 1) {
                render.setColor(1, 0, 0, 1);
                render.rect(sideline, 130, 5, 98);
            }
            if (scenarioIndex == 2) {
                render.setColor(1, 0, 0, 1);
                render.rect(0, 135, 640, 5);
            }
            render.setColor(.823f, .412f, .118f, 1);
            render.rect(0, 0, 640, 130);
            render.end();
            batch.begin();
            // add more if needed
        } //THIS CODE WILL RUN IF NOT ACTIVELY ANIMATING --> PASSIVE DRAW
        else {
            turfBack.draw(batch);
            //probably redundant but please don't delete
            if (scenarioIndex == 0) {
                player.draw(batch);
                disc.draw(batch);
                defender.draw(batch);
            } else if (scenarioIndex == 1) {
                player.draw(batch);
                disc.draw(batch);
                defender.draw(batch);
            } else if (scenarioIndex == 2) {
                player.draw(batch);
                disc.draw(batch);
                defender.draw(batch);
            }
            batch.end();
            //this draws rectangles
            render.begin(ShapeType.Filled);
            if (scenarioIndex == 1) {
                render.setColor(1, 0, 0, 1);
                render.rect(sideline, 130, 5, 98);
            }
            if (scenarioIndex == 2) {
                render.setColor(1, 0, 0, 1);
                render.rect(0, 135, 640, 5);
            }
            render.setColor(.823f, .412f, .118f, 1);
            render.rect(0, 0, 640, 130);
            render.end();
            batch.begin();
        }

        // turfBack.draw(batch);
        // player.draw(batch);
        if (prefaceDone == false) {
            font.draw(batch, prefaceText, 75, 100, 500, 10, true);
            font.getData().setScale(.75f);
        }

        // boolean chain to determine what gets shown
        if (currentScenario.initialExplainationPassed == false && prefaceDone) {
            //kill previous animation
            animate = false;
            // set up animation
            if (scenarioIndex == 0) {
                scale = 10;
                angle = 0;
                thro = 0;
                defender.setSize(orX / scale, orY / scale);
                player.setSize(orX / scale, orY / scale);
                player.setPosition(640 / 2 - player.getWidth() / 2,
                        480 / 2 - player.getHeight() / 2);
                defender.setPosition(player.getX() - 20, player.getY() - 20);
                disc.setSize(orX2 / (scale * 2), orX2 / (scale * 2));
                disc.setPosition(player.getX() - disc.getWidth() / 2, player.getY() + player.getHeight() / 2 - disc.getHeight() / 2);
            } else if (scenarioIndex == 1) {
                defender.setSize(orX / scale, orY / scale);
                player.setSize(orX / scale, orY / scale);
                disc.setSize(orX2 / (scale * 2), orX2 / (scale * 2));
                player.setPosition(640 / 2 - player.getWidth() / 2,
                        480 / 2 - player.getHeight() / 2);
                defender.setPosition(player.getX() + 20, player.getY() - 20);
                disc.setPosition(player.getX() - disc.getWidth() / 2,
                        player.getY() + player.getHeight() / 2 - disc.getHeight() / 2);
            } else if (scenarioIndex == 2) {
                defender.setSize(orX / scale, orY / scale);
                player.setSize(orX / scale, orY / scale);
                disc.setSize(orX2 / (scale * 2), orX2 / (scale * 2));
                player.setPosition(640 / 2 - player.getWidth() / 2,
                        480 / 2 - player.getHeight() / 2);
                defender.setPosition(player.getX() - 20, player.getY() - 20);
                disc.setPosition(player.getX() + player.getWidth() - disc.getWidth() / 2,
                        player.getY() + player.getHeight() / 2 - disc.getHeight() / 2);
            }
            //END ANIMATION BLOCK ENTER BOOLEAN CHAIN TO DECIDE DEPLOYMENT ORDER
            
            // Present initial explanation
            font.draw(batch, currentScenario.ie + " Press ENTER to continue.", 50, 100, 550, 10, true);
            font.getData().setScale(.75f);
        } else if (currentScenario.videoPrefacePassed == false && currentScenario.initialExplainationPassed
                && dn == false) {
            font.draw(batch, currentScenario.vp + " Press ENTER to play the video.", 50, 100, 550, 10, true);
            font.getData().setScale(.75f);
        } else if (currentScenario.initialVideoPassed == false && currentScenario.videoPrefacePassed
                && currentScenario.initialExplainationPassed && dn == false) {
            System.out.println("arrived");
            // open video
            try {
                Desktop.getDesktop().open(new File(currentScenario.iv)); // This opens the video
                currentScenario.initialVideoPassed = true;
            } catch (IOException e) {
                System.err.println("Error was caught.");
            }
            currentScenario.initialVideoPassed = true;
        } else if (currentScenario.initialVideoPassed && currentScenario.choicePassed == false && dn == false) {
            // give our four options
            font.draw(batch, currentScenario.o1 + " Press 1 to select this option.", 50, 100, 100, 10, true);
            font.draw(batch, currentScenario.o2 + " Press 2 to select this option.", 200, 100, 100, 10, true);
            font.draw(batch, currentScenario.o3 + " Press 3 to select this option.", 350, 100, 100, 10, true);
            font.draw(batch, currentScenario.o4 + " Press 4 to select this option.", 500, 100, 100, 10, true);
            font.getData().setScale(.5f);
        } else if (currentScenario.initialExplainationPassed && currentScenario.choicePassed
                && currentScenario.finalExplaination1Passed == false && dn == false) {
            //trigger animation, if there is one
            animate = true;
            // show fe1
            font.draw(batch, currentScenario.fe1 + " Please press ENTER to find out why the other options were wrong.",
                    50, 100, 550, 10, true);
            font.getData().setScale(.75f);
        } else if (currentScenario.initialExplainationPassed && currentScenario.choicePassed
                && currentScenario.finalExplaination1Passed && currentScenario.finalExplaination2Passed == false
                && dn == false) {
            // show fe2
            font.draw(batch, currentScenario.fe2
                    + "Please press ENTER to watch the video of this being executed. When you have watched the video please press ENTER again to advance to the next scenario.",
                    50, 100, 550, 10, true);
            font.getData().setScale(.75f);
            // need to check for the video while still in our final explaination
            if (currentScenario.finalVideoPassed == false && showVideo && currentScenario.initialExplainationPassed
                    && currentScenario.choicePassed && dn == false) {
                // open video
                try {
                    Desktop.getDesktop().open(new File(currentScenario.fv)); // This is the important line
                    currentScenario.finalVideoPassed = true;
                } catch (IOException e) {
                    System.err.println("Error was caught.");
                }
                currentScenario.finalVideoPassed = true;
            }
        }

        // end of scenarios
        if (dn == true) {
            font.draw(batch,
                    "Congradulations! You have completed all availible Scenarios! Please press ENTER to resart the scenarios",
                    125, 100, 400, 10, true);
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
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/correct.mp3"));
                    sound.play(1.0f);
                    currentScenario.choicePassed = true;
                } else if (currentScenario.initialExplainationPassed && currentScenario.initialVideoPassed
                        && currentScenario.choicePassed == false) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/wrong.mp3"));
                    sound.play(1.0f);
                }
                break;
            case Keys.NUM_2:
                if (currentScenario.ca == 2) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/correct.mp3"));
                    sound.play(1.0f);
                    currentScenario.choicePassed = true;
                } else if (currentScenario.initialExplainationPassed && currentScenario.initialVideoPassed
                        && currentScenario.choicePassed == false) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/wrong.mp3"));
                    sound.play(1.0f);
                }
                break;
            case Keys.NUM_3:
                if (currentScenario.ca == 3) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/correct.mp3"));
                    sound.play(1.0f);
                    currentScenario.choicePassed = true;
                } else if (currentScenario.initialExplainationPassed && currentScenario.initialVideoPassed
                        && currentScenario.choicePassed == false) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/wrong.mp3"));
                    sound.play(1.0f);
                }
                break;
            case Keys.NUM_4:
                if (currentScenario.ca == 4) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/correct.mp3"));
                    sound.play(1.0f);
                    currentScenario.choicePassed = true;
                } else if (currentScenario.initialExplainationPassed && currentScenario.initialVideoPassed
                        && currentScenario.choicePassed == false) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/wrong.mp3"));
                    sound.play(1.0f);
                }
                break;
            case Keys.ENTER:
                System.out.println("hi");
                if (prefaceDone == false) {
                    prefaceDone = true;
                } else if (currentScenario.initialExplainationPassed == false) {
                    // if you have just seen the initial explaination
                    currentScenario.initialExplainationPassed = true;
                    System.out.println("ie");
                } else if (currentScenario.videoPrefacePassed == false) {
                    // if you have just seen the video preface
                    currentScenario.videoPrefacePassed = true;
                    // System.out.println("vp");
                } else if (currentScenario.finalExplaination1Passed == false && currentScenario.choicePassed) {
                    currentScenario.finalExplaination1Passed = true;
                } else if (currentScenario.finalVideoPassed == false && currentScenario.finalExplaination1Passed) {
                    // if its time to show the video
                    showVideo = true;
                } else if (currentScenario.finalVideoPassed) {
                    // THEY HAVE FINISHED A SCENARIO
                    if (scenarioIndex + 1 < scenarioCount) {
                        // we still have scenarios so advance
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
        // this is the function where you will make your scenarios
        // the order is as follows: setup video, initial explanation, option 1, option
        // 2, option 3, option 4, correct option NUMBER, action video, final explanation
        Scenario s1 = new Scenario( /* This is the handler upline cut */
                /* Setup video */"video/uplineSetup.mp4",
                /* Setup text */ "In this scenario, you will be taking the place of the breakside handler on offense. Currently, your team is attempting to move the disc up the field which is to the left of your perspective. The person you see is the handler that has the disc. They were looking upfield but now that it is stall four, they have turned to you to get you to make a move. You will choose what is the best offensive move you can make.",
                /* Video Preface */ "In the following video the player holding the disc is on the far sideline and is trying to move the disc up the field which is to the left of you screen. Watch the player in white wearing a black backwards hat.",
                /* Option 1 */ "Option 1: Cut to the left and then come back for the disc. This would look like a v where you would be cutting straight up the field then stopping and coming towards the handler at an angle.",
                /* Option 2 */ "Option 2: Stay where you are and call for the disc. Trust that the handler with the disc will break the mark and that your defender will not be prepared to stop the break throw.",
                /* Option 3 */ "Option 3: Cut towards the handler with the disc for a few steps before shifting into an upline cut where you will move at a 45 degree angle to the handler trying to move across into the open space.",
                /* Option 4 */ "Option 4: Cut towards the handler with the disc for a few steps before shifting to come behind them to give them the easy dump option.",
                /* Correct option */ 3, /* Action video */ "video/uplineAction.mp4",
                /* Explaination text (correct options) */ "Correct! Option 3 is the best strategic decision. Since you are on the break side your cut will bring you into the open side, making it very easy for the handler with the disc to throw it to you. The 45 degree angle helps you lose your defender. Additionally, upline cuts put you in the power position. When you get the disc your defender will be behind you, you will have full view of the field, and your momentum will be going towards the endzone so you can easily throw a huck.",
                /* Explaination text (incorrect options) */ "Option 1 is incorrect because you would wind up clogging the cutting space and leaving only 2 handlers. Option 2 is incorrect because it may be a high stall or the handler with the disc may have problems getting around the mark. Finally, option 4 is not necessarily a bad choice but the upline cut in option 3 is better.");
        scenarios.add(s1);

        Scenario s2 = new Scenario( /* This is the sideline play */
                /* Setup video */"video/sidelineSetup.mp4",
                /* Setup text */ "In this scenario, you are a cutter on the open-side sideline right in front of the disc. The disc is not coming in immediately, so you have a bit of time to set up. You have the handler with the disc in your vision, and the rest of the cutters are lined up in a horizontal stack. You now have the opportunity to choose your move. Press ENTER to continue.",
                /* Video Preface */ "In the following video the frisbee has just been turned over in the endzone. A handler is walking up to the endzone line with the frisbee. You will be following number 30 in white.",
                /* Option 1 */ "Option 1: Stay where you are and call for the disc, trusting the handler to give you a nice pass to space.",
                /* Option 2 */ "Option 2: Run straight deep and call for it - there is a lot of wide-open deep space.",
                /* Option 3 */ "Option 3: Run diagonally to the break side, and then cut hard back to the open side. Depending on the force, this would look like a greater-than sign (if the force is forehand) or a less-than sign (if the force is backhand).",
                /* Option 4 */ "Option 4: Run straight towards the handler for a few steps, and then run hard away from the sideline. This could act as either a cut to clear space or a break-side cut.",
                /* Correct option */ 4, /* Action video */ "video/sidelineAction.mp4",
                /* Explaination text (correct options) */ "Great work! You correctly chose option 4. This cut is called an 'L-cut'. The L-cut is very useful since it both clears space and offers the potential for the handler to break the mark and center the disc. In the first case, the hanlder then has open space to look for a deep cut. If they break the mark and center it to you on your L-cut then you will have the whole open side to throw to.",
                /* Explaination text (incorrect options) */ "Option 1 is not a good choice because you remain stagnant in the open space and clog it. Your handler also may not be able to throw something easy to space. Option 2 is not completely wrong, but still incorrect - the throw your handler would have to make to hit you running straight away from them is a very tough one. And, option 3 is sub-optimal because although you first clear out of the space, you re-enter it and end up clogging the open side. Even if you get the disc, it is still on the sideline - exactly where the defense wants you to be - and your defender likely is close behind you.");
        scenarios.add(s2);

        Scenario s3 = new Scenario( /* This is the endzone play */
                /* Setup video */"video/endzoneSetup.mp4",
                /* Setup text */ "Here, the disc has been turned over in the middle of the field on the endzone line. You have called for a vertical stack in the endzone, so four cutters are lined up behind you, and you can see the handler in the middle of the field about to pick up the disc. You now have the opportunity to choose your next move. Press ENTER to continue.",
                /* Video Preface */ "In the following video the disc has been turned over near the opposing team's goal line. Number 24 is our handler walking to pick up the disc. You will be taking the postion of the player in the front of the stack wearing a red and white hat.",
                /* Option 1 */ "Option 1: Make a jab step, take three hard steps to the break side, and then quickly burst to the open side. If you pull this cut off, the handler will have a very easy time getting it to you.",
                /* Option 2 */ "Option 2: Cut directly towards the handler. Although you will drive into your defender, this could set you up for a continuation cut away from the handler.",
                /* Option 3 */ "Option 3: Make a few jab steps and then run hard to the open space on the break side. Trust that your handler will have the throws to get around their mark.",
                /* Option 4 */ "Option 4: Stay where you are. This gives others a chance to cut, and allows you to act as a continual anchor for the stack so that players do not get out of position.",
                /* Correct option */ 1, /* Action video */ "video/endzoneAction.mp4",
                /* Explaination text (correct options) */ "Nicely done! Option 1 is the best choice in this situation. The first step, making a jab step at your defender, could potentially get them on their heels – this is a very unbalanced position to be in. If so, they will overcommit to your second move: taking three hard steps to the break side. Even if they aren’t on their heels, the fact that they are so far behind you makes it very likely for your defender to run as hard as they can to catch up to you. In this stage, a defender is very vulnerable to your third move: quickly bursting to the open side. This progression of moves will result in a very high chance of you being open for an easy pass on the open side.",
                /* Explaination text (incorrect options) */ " Option 2 is incorrect because you end up clogging the throwing lane for your handler; you introduce two new bodies for them to deal with. Option 3 is incorrect because your handler may not be able to get a break-mark throw off. And, while option 4 is not entirely incorrect, the first option yields the best chance of scoring quickly.");
        scenarios.add(s3);

        // lastly set the total number of scenarios
        scenarioCount = scenarios.size();

        return scenarios;
    }
}
