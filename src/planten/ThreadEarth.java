/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
package planten;

/**
 *
 * @author sebastian seidel
 */
public class ThreadEarth implements Runnable{
    //private DrawWindow myDrawWindow;
    private Graphenknoten myPlanet;
    private ArrayList<Graphenknoten> graphen;
    private Engine myEngine;
    /*ThreadEarth(DrawWindow myDrawWindow){
        this.myDrawWindow=myDrawWindow;
    }*/

    @Override public void run(){
    	LinkedList<Thread> threads = new LinkedList<Thread>();
        //this.myDrawWindow.drawearth=this.myDrawWindow.berchneGedrehteKoordinaten(this.myDrawWindow.earth, 1,new double[]{1}, this.myDrawWindow.sun);
        this.myPlanet.zeichnen=this.myEngine.berchneGedrehteKoordinaten(this.myPlanet.koordinaten, this.myPlanet.getNiveau(), this.myPlanet.getRotationSpeed(), this.myPlanet.root.koordinaten);
        for(Iterator it=this.graphen.iterator();it.hasNext();){
        	Graphenknoten gk =(Graphenknoten) it.next();
        	if(gk.root==this.myPlanet){
        		threads.add(new Thread(new ThreadEarth(gk,this.myEngine,this.graphen)));
        	}
        }
        while(!threads.isEmpty()){
        	threads.removeFirst().start();
        }
    }
    
    ThreadEarth(Graphenknoten myPlanet,Engine myEngine,ArrayList<Graphenknoten> graphen){
    	this.myPlanet=myPlanet;
    	this.myEngine=myEngine;
    	this.graphen=graphen;
    }
}
