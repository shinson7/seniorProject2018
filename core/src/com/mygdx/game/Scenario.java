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
    public boolean initialExplanationPassed = false;
    public boolean choicePassed = false;
    public boolean finalVideoPassed = false;
    public boolean finalExplanationPassed = false;

    public String iv;
    public String ie;
    public String o1;
    public String o2;
    public String o3;
    public String o4;
    public int ca;
    public String fv;
    public String fe;
    
    
    public Scenario(String iv, String ie, String o1, String o2, String o3, String o4, int ca, String fv, String fe) {
        this.iv = iv;
        this.ie = ie;
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
        this.o4 = o4;
        this.ca = ca;
        this.fv = fv;
        this.fe = fe;
    }

}
