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
    public boolean videoPrefacePassed = false;
    public boolean choicePassed = false;
    public boolean finalVideoPassed = false;
    public boolean finalExplaination1Passed = false;
    public boolean finalExplaination2Passed = false;

    public String iv;
    public String ie;
    public String vp;
    public String o1;
    public String o2;
    public String o3;
    public String o4;
    public int ca;
    public String fv;
    public String fe1;
    public String fe2;
    
    
    public Scenario(String iv, String ie,String vp, String o1, String o2, String o3, String o4, int ca, String fv, String fe1, String fe2) {
        this.iv = iv;
        this.ie = ie;
        this.vp = vp;
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
        this.o4 = o4;
        this.ca = ca;
        this.fv = fv;
        this.fe1 = fe1;
        this.fe2 = fe2;
    }

}
