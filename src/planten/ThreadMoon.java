/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package planten;
//import java.lang.Thread;
/**
 *
 * @author sebastian seidel
 */
public class ThreadMoon implements Runnable{

    DrawWindow myWindow;

    public ThreadMoon(DrawWindow myWindow){
        this.myWindow=myWindow;
    }


    @Override public void run(){
        this.myWindow.drawmoon=this.myWindow.berchneGedrehteKoordinaten(this.myWindow.moon,2,new double[]{1,10},this.myWindow.drawearth);
    }

}
