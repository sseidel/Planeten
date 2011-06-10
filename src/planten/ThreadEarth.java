/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package planten;

/**
 *
 * @author sebastian seidel
 */
public class ThreadEarth implements Runnable{
    //private DrawWindow myDrawWindow;
    private Graphenknoten myPlanet;
    private Engine myEngine;
    /*ThreadEarth(DrawWindow myDrawWindow){
        this.myDrawWindow=myDrawWindow;
    }*/

    @Override public void run(){
        //this.myDrawWindow.drawearth=this.myDrawWindow.berchneGedrehteKoordinaten(this.myDrawWindow.earth, 1,new double[]{1}, this.myDrawWindow.sun);
        this.myPlanet.zeichnen=this.myEngine.berchneGedrehteKoordinaten(this.myPlanet.koordinaten, this.myPlanet.getNiveau(), this.myPlanet.getRotationSpeed(), this.myPlanet.root.koordinaten);
    }
    
    ThreadEarth(Graphenknoten myPlanet,Engine myEngine){
    	this.myPlanet=myPlanet;
    	this.myEngine=myEngine;
    }
}
