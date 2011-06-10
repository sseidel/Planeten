/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package planten;

import java.awt.*;
import java.util.*;
import javax.swing.*;

//import java.lang;


/**
 *
 * @author sebastian seidel
 */
public class DrawWindow extends JPanel {

    protected int[] drawsun,drawearth,drawmoon,sun,earth,moon;
    private double counter;
    ArrayList<Graphenknoten> planeten;
    private Engine myEngine;

    public DrawWindow(){
        super();
        this.setVisible(true);
        counter=0.0;
        this.sun=new int[]{this.getWidth()/2,this.getWidth()/2};
        this.earth=new int[]{100,0};
        this.moon=new int[]{20,0};
        this.myEngine=new Engine(this.counter);
    }

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        //int[] drawearth,drawmoon,drawsun;
        counter +=0.01;
        this.sun[0]=this.getWidth()/2;
        this.sun[1]=this.getHeight()/2;
        
        drawsun=berchneGedrehteKoordinaten(this.sun,0,null,new int[]{0,0});
        drawearth=berchneGedrehteKoordinaten(this.earth,1,new double[]{1},drawsun);
        drawmoon=berchneGedrehteKoordinaten(this.moon,2,new double[]{10,1},drawearth);
        /*int[] drawearth2=benchEarth(this.earth);
        int[] drawmoon2=benchMoon(benchEarth(this.moon));
        drawmoon2[0]+=drawearth[0];//+=this.sun[0];
        drawmoon2[1]+=drawearth[1];//+=this.sun[1];*/
        g.setColor(Color.red);
        g.fillOval(drawsun[0]-5, drawsun[1]-5 ,10, 10);
        g.setColor(Color.BLUE);
        g.fillOval(drawearth[0]-5, drawearth[1]-5, 10, 10);
        g.setColor(Color.green);
        g.fillOval(drawmoon[0]-3, drawmoon[1]-3, 6, 6);
        /*g.setColor(Color.green);
        g.fillOval(drawmoon2[0]-3, drawmoon2[1]-3, 6, 6);
        this.earth[0]-=this.sun[0];
        this.earth[1]-=this.sun[1];*/
    }

    private int[] benchEarth(int[] koordinaten){
        double[][] drehmatrix = {{Math.cos(-this.counter),-Math.sin(-this.counter)},
        {Math.sin(-this.counter),Math.cos(-this.counter)}};
        //={//point.x,point.y};
        int[] zielKoordinaten=new int[2];
        zielKoordinaten[0]=(int) (drehmatrix[0][0] * koordinaten[0] + drehmatrix[0][1] * koordinaten[1]);
        zielKoordinaten[1]=(int) (drehmatrix[1][0] * koordinaten[0] + drehmatrix[1][1] * koordinaten[1]);
        return zielKoordinaten;
    }

    private int[] benchMoon(int[] koordinaten){
        double[][] drehmatrix = {{Math.cos(-this.counter*10),-Math.sin(-this.counter*10)},
        {Math.sin(-this.counter*10),Math.cos(-this.counter*10)}};
        int[] zielKoordinaten=new int[2];
        zielKoordinaten[0]=(int) (drehmatrix[0][0] * koordinaten[0] + drehmatrix[0][1] * koordinaten[1]);
        zielKoordinaten[1]=(int) (drehmatrix[1][0] * koordinaten[0] + drehmatrix[1][1] * koordinaten[1]);
        return zielKoordinaten;
    }
    /**
     *
     * @param bezugspunkte
     * @param anzahlBezugspunkte
     * @param rotationsgeschwindigkeit
     * @return
     */
    private int[] berechneDrehmatrixergebnis(int[] bezugspunkte,int anzahlBezugspunkte, double rotationsgeschwindigkeit[]){
        if(anzahlBezugspunkte<1) return bezugspunkte;
        if(1!=anzahlBezugspunkte){
           bezugspunkte=berechneDrehmatrixergebnis(bezugspunkte,anzahlBezugspunkte-1,rotationsgeschwindigkeit);
            //return berechneDrehmatrixergebnis(temp,anzahlBezugspunkte,rotationsgeschwindigkeit);
        }


        double[][] drehmatrix = {{Math.cos(-this.counter*rotationsgeschwindigkeit[rotationsgeschwindigkeit.length-anzahlBezugspunkte/*anzahlBezugspunkte-1*/]),-Math.sin(-this.counter*rotationsgeschwindigkeit[rotationsgeschwindigkeit.length-anzahlBezugspunkte/*anzahlBezugspunkte-1*/])},
           {Math.sin(-this.counter*rotationsgeschwindigkeit[rotationsgeschwindigkeit.length-anzahlBezugspunkte/*anzahlBezugspunkte-1*/]),Math.cos(-this.counter*rotationsgeschwindigkeit[rotationsgeschwindigkeit.length-anzahlBezugspunkte/*anzahlBezugspunkte-1*/])}};
           int[] zielKoordinaten=new int[2];
            zielKoordinaten[0]=(int) (drehmatrix[0][0] * bezugspunkte[0] + drehmatrix[0][1] * bezugspunkte[1]);
            zielKoordinaten[1]=(int) (drehmatrix[1][0] * bezugspunkte[0] + drehmatrix[1][1] * bezugspunkte[1]);
            return zielKoordinaten;
    }

    /**
     *
     * @param bezugspunkte
     * @param anzahlBezugspunkte
     * @param rotationsgeschwindigkeit
     * @param koordinatenVater
     * @return
     */
    protected int[] berchneGedrehteKoordinaten(int[] bezugspunkte,int anzahlBezugspunkte, double rotationsgeschwindigkeit[],int[] koordinatenVater){
        int[] koordinaten =berechneDrehmatrixergebnis(bezugspunkte,anzahlBezugspunkte,rotationsgeschwindigkeit);
        koordinaten[0]+=koordinatenVater[0];
        koordinaten[1]+=koordinatenVater[1];
        
        return koordinaten;
    }
    
    private void knotenErstellen(){
    	this.planeten=new ArrayList<Graphenknoten>();
    	this.planeten.add(new Graphenknoten("sun",new int[]{this.getWidth()/2,this.getHeight()/2}));
    	this.planeten.add(new Graphenknoten("earth",new int[]{100,0}));
    	this.planeten.add(new Graphenknoten("moon",new int[]{20,0}));
    }
    
    private void kantenErstellen(){
    	Iterator<Graphenknoten> it =this.planeten.iterator();
    	do{
    		Graphenknoten gk=it.next();
    		//gk.
    	}while(it.hasNext());
    }
}
