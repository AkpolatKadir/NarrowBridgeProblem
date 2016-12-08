/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package narrowbridge2;

import java.applet.Applet;
import java.awt.Graphics;
import static java.lang.Math.random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Kadir
 */
public class Car extends Thread {

    Applet father;
    Road fatherRoad;
    int speed = 5;
    int posX;
    int posY;
    boolean Lock = true;//Car is locked and won't move.
    int arriveTime = (int) (random() * 40 + 1);
    int currentTime;//Will passed from road class.
    static int carOnRoad = 0;
    boolean isOnRoad = false;
    int created=0;
    
    
    public Car(Applet mainApplet, int posX, int posY, Road myRoad) {
        father = mainApplet;
        this.posX = posX;
        this.posY = posY;
        fatherRoad = myRoad;
    }

    @Override
    public void run() {

        while (true) {
            
            if (!isOnRoad) {
                if (fatherRoad.getLight() == false || fatherRoad.isRoadFull())//false means red light.
                {
                    Lock = true;
                } else {
                    Lock = false;
                }
            }
            else //car is on the road so we  only care if the light is red. 
            {
                if(fatherRoad.getLight()==false)
                {
                    Lock=true;
                }
            }

            if (Lock == false && arriveTime >= currentTime) {//Entered the road.
                posX += speed;
                isOnRoad = true;
                created++;
                father.repaint();
            }
            if(created==1)// constraint for increment function to just work one time
            {
                fatherRoad.incrementCarNumber();
            }

            try {
                Thread.sleep(1000);
                currentTime++;

            } catch (InterruptedException ex) {
                Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean isLock() {
        return Lock;
    }

    public void setLock(boolean waitLock) {
        this.Lock = waitLock;
    }

    public void setCurrent(int currentTime) {
        this.currentTime = currentTime;
    }

    public int getX() {
        return this.posX;
    }

    public int getY() {
        return this.posY;
    }

    public void paint(Graphics g) {
        g.drawLine(posX, posY, posX + 10, posY);
    }
}
