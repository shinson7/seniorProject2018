/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 *
 * @author shinson
 */
public class Scenario {

    public boolean initialVideoPassed = false;
    public boolean initialExplainationPassed = false;
    public boolean choicePassed = false;
    public boolean finalVideoPassed = false;
    public boolean finalExplainationPassed = false;

    public String ie;
    public String o1;
    public String o2;
    public String o3;
    public String o4;
    public String fe;
    
    public Scenario(String ie, String o1, String o2, String o3, String o4, String fe) {
        this.ie = ie;
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
        this.o4 = o4;
        this.fe = fe;
    }

}
