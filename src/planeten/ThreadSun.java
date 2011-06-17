/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package planeten;

/**
 *
 * @author sebastian seidel
 */
public class ThreadSun implements Runnable{
    DrawWindow myDrawWindow;
    ThreadSun(DrawWindow myDrawWindow){
        this.myDrawWindow=myDrawWindow;
    }

    @Override public void run(){
        this.myDrawWindow.drawsun=this.myDrawWindow.berchneGedrehteKoordinaten(this.myDrawWindow.sun, 0, null, null);
    }
}
