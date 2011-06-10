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
    DrawWindow myDrawWindow;
    ThreadEarth(DrawWindow myDrawWindow){
        this.myDrawWindow=myDrawWindow;
    }

    @Override public void run(){
        this.myDrawWindow.drawearth=this.myDrawWindow.berchneGedrehteKoordinaten(this.myDrawWindow.earth, 1,new double[]{1}, this.myDrawWindow.sun);
    }

}
