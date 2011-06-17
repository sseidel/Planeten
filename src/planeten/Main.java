/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package planeten;
import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author sebastian seidel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Window myWindow=new Window("malen");
        Timer timer = new Timer();
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        timer.schedule(new Task(myWindow.myDrawWindow), 1000,100);
    }

}
/**
 * 
 * @author sebastian seidel
 */
class Window extends JFrame{
    DrawWindow myDrawWindow=new DrawWindow();

    public Window(String windowname){
        super(windowname);
        this.setSize(600, 600);
        this.setLocation(100, 100);
        this.getContentPane().add(myDrawWindow);
        this.setVisible(true);
    }


}

 class Task extends TimerTask{

     DrawWindow myWindow;
     Task(DrawWindow myWindow){
         this.myWindow=myWindow;
     }

     public void run(){
         this.myWindow.repaint();
         
     }
 }
