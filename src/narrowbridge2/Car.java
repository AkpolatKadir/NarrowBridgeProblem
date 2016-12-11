/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package narrowbridge2;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Math.random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Kadir
 */
public class Car extends Thread {

    Applet father;
    Road fatherRoad;
    int speed = 8;
    int posX;
    int posY;
    boolean Lock = true;//Car is locked and won't move.
    int arriveTime; //(int) (random() * 40 + 1);
    int currentTime = 0;//Will passed from road class.
    static int colorPicker = 0;
    boolean isOnRoad = false;
    int created = 0;
    boolean isOnBridge = false;

    public Car(Applet mainApplet, int posX, int posY, Road myRoad, int arriveTime) {
        father = mainApplet;
        this.posX = posX;
        this.posY = posY;
        fatherRoad = myRoad;
        this.arriveTime = arriveTime;
    }

    @Override
    public void run() {

        while (true) {
            if (currentTime >= arriveTime ) {

                if (!isOnRoad) {
                    if (fatherRoad.getLight() == false || fatherRoad.isRoadFull())//false means red light.
                    {
                        Lock = true;
                    } else {
                        Lock = false;
                    }
                } else //car is on the road so we  only care if the light is red. 
                {
                    if (fatherRoad.getLight() == false && !isOnBridge) {
                        Lock = true;
                    }
                }

                if (Lock == false) {//Entered the road.
                    posX += speed;
                    isOnRoad = true;
                    ++created;

                    if (posX > 180 && posX < 200 && fatherRoad.getLight()) {//Means it is ready to pass into the bridge.
                        fatherRoad.decrementCarNumber();
                        int choose = (int) (random() * 2 + 1);//To randomly choose which lane it is going to enter.
                        if (choose == 1) {
                            posX = 270;
                            posY = 190;
                        } else {
                            posX = 270;
                            posY = 210;
                        }
                        isOnBridge = true;
                    }

                    father.repaint();

                }
                if (created == 1)// constraint for increment function to just work one time
                {
                    fatherRoad.incrementCarNumber();
                    ++created;
                    colorPicker++;
                }

                try {
                    Thread.sleep(1000);
                    currentTime++;

                } catch (InterruptedException ex) {
                    Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
                }

            }  else {

                try {
                    Thread.sleep(1000);
                    currentTime++;

                } catch (InterruptedException ex) {
                    Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        if (isOnRoad) {
            if (colorPicker >= 0) {
                g.setColor(Color.red);
            }
            if (colorPicker >= 5) {
                g.setColor(Color.orange);
            }
            if (colorPicker >= 10) {
                g.setColor(Color.green);
            }

            g.drawLine(posX, posY, posX + 10, posY);
        }
    }

}
