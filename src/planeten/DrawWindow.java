/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package planeten;

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
        //this.knotenErstellen();
        //this.kantenErstellen();
        
    }

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g){
        //super.paint(g);
        this.drawByEngine(g);
        
    }
    
    void drawByOwn(Graphics g){
    	counter +=0.01;
    	this.sun[0]=this.getWidth()/2;
        this.sun[1]=this.getHeight()/2;
        
        this.drawsun=berchneGedrehteKoordinaten(this.sun,0,null,new int[]{0,0});
        this.drawearth=berchneGedrehteKoordinaten(this.earth,1,new double[]{1},drawsun);
        this.drawmoon=berchneGedrehteKoordinaten(this.moon,2,new double[]{10,1},drawearth);
        g.setColor(Color.red);
        g.fillOval(drawsun[0]-5, drawsun[1]-5 ,10, 10);
        g.setColor(Color.BLUE);
        g.fillOval(drawearth[0]-5, drawearth[1]-5, 10, 10);
        g.setColor(Color.green);
        g.fillOval(drawmoon[0]-3, drawmoon[1]-3, 6, 6);
    }
    
    void drawByEngine(Graphics g){
    	this.myEngine.counter+=0.01;
    	this.sun[0]=this.getWidth()/2;
        this.sun[1]=this.getHeight()/2;
        this.drawsun=this.myEngine.berchneGedrehteKoordinaten(sun, 0, null, new int[]{0,0});
        this.drawearth=this.myEngine.berchneGedrehteKoordinaten(this.earth,1,new double[]{1},drawsun);
        this.drawmoon=this.myEngine.berchneGedrehteKoordinaten(this.moon,2,new double[]{10,1},drawearth);
        g.setColor(Color.red);
        g.fillOval(drawsun[0]-5, drawsun[1]-5 ,10, 10);
        g.setColor(Color.BLUE);
        g.fillOval(drawearth[0]-5, drawearth[1]-5, 10, 10);
        g.setColor(Color.green);
        g.fillOval(drawmoon[0]-3, drawmoon[1]-3, 6, 6);
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


        double[][] drehmatrix = {{2*Math.cos(-this.counter*rotationsgeschwindigkeit[rotationsgeschwindigkeit.length-anzahlBezugspunkte/*anzahlBezugspunkte-1*/]),-Math.sin(-this.counter*rotationsgeschwindigkeit[rotationsgeschwindigkeit.length-anzahlBezugspunkte/*anzahlBezugspunkte-1*/])},
           {Math.sin(-this.counter*rotationsgeschwindigkeit[rotationsgeschwindigkeit.length-anzahlBezugspunkte/*anzahlBezugspunkte-1*/]),2*Math.cos(-this.counter*rotationsgeschwindigkeit[rotationsgeschwindigkeit.length-anzahlBezugspunkte/*anzahlBezugspunkte-1*/])}};
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
    	/*Iterator<Graphenknoten> it =this.planeten.iterator();
    	do{
    		Graphenknoten gk=it.next();
    		//gk.
    	}while(it.hasNext());*/
    	this.planeten.get(2).root=this.planeten.get(1);
    	this.planeten.get(1).root=this.planeten.get(0);
    }
    
    private void startThreads(){
    	LinkedList<Thread> threads=new LinkedList<Thread>();
    	for(Iterator it=this.planeten.iterator();it.hasNext();){
    		Graphenknoten myGk=(Graphenknoten) it.next();
    		if(myGk.root==null){
    			threads.add(new Thread(new ThreadEarth(myGk,this.myEngine,this.planeten)));
    			threads.getLast().start();
    		}
    	}
    	while(!threads.isEmpty()){
    		try{
    			threads.removeFirst().join();
    		}
    		catch(Exception e){ System.out.println(e.getMessage());}
    	}
    }
    
    void drawThreadResults(Graphics g){
    	this.planeten.get(0).zeichnen[0]=this.getWidth()/2;
    	this.planeten.get(0).zeichnen[1]=this.getHeight()/2;
        this.startThreads();
    	g.setColor(Color.red);
        g.fillOval(this.planeten.get(0).zeichnen[0]-5, this.planeten.get(0).zeichnen[1]-5 ,10, 10);
        g.setColor(Color.BLUE);
        g.fillOval(this.planeten.get(1).zeichnen[0]-5, this.planeten.get(1).zeichnen[1]-5, 10, 10);
        g.setColor(Color.green);
        g.fillOval(this.planeten.get(2).zeichnen[0]-3, this.planeten.get(2).zeichnen[1]-3, 6, 6);
    }
}
