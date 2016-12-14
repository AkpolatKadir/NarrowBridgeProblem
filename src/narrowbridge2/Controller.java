/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package narrowbridge2;

import java.applet.Applet;
import static java.lang.Math.random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kadir
 */
public class Controller extends Thread {

    Road myRoad1, myRoad2, myRoad3;
    Applet myApplet;

    public Controller(Road road1, Road road2, Road road3, Applet fatherApplet) {
        this.myRoad1 = road1;
        this.myRoad2 = road2;
        this.myRoad3 = road3;
        this.myApplet = fatherApplet;
    }

    @Override
    public void run() {
        while (true) {

            incrementGreenTimeElapsed();

            checkIfRoadIsEmpty();

            checkRoadFullStatus();

            myApplet.repaint();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void checkRoadFullStatus() {
        if (myRoad1.getLight() == false && myRoad1.isRoadFull()) {

            if (myRoad2.getElapsedTime() > myRoad3.getElapsedTime() && myRoad2.getElapsedTime() >= 10) {
                myRoad2.changeLight();
                myRoad1.changeLight();
            } else if (myRoad3.getElapsedTime() >= 10 && myRoad3.getElapsedTime() >= myRoad2.getElapsedTime()) {
                myRoad3.changeLight();
                myRoad1.changeLight();
            }

        } else if (myRoad2.getLight() == false && myRoad2.isRoadFull()) {
            if (myRoad1.getElapsedTime() >= myRoad3.getElapsedTime() && myRoad1.getElapsedTime() >= 10) {
                myRoad1.changeLight();
                myRoad2.changeLight();
            } else if (myRoad3.getElapsedTime() >= 10 && myRoad3.getElapsedTime() >= myRoad1.getElapsedTime()) {
                myRoad3.changeLight();
                myRoad2.changeLight();
            }

        } else if (myRoad3.getLight() == false && myRoad3.isRoadFull()) {

            if (myRoad2.getElapsedTime() >= myRoad1.getElapsedTime() && myRoad2.getElapsedTime() >= 10) {
                myRoad2.changeLight();
                myRoad3.changeLight();
            } else if (myRoad1.getElapsedTime() >= 10 && myRoad1.getElapsedTime() >= myRoad2.getElapsedTime()) {
                myRoad1.changeLight();
                myRoad3.changeLight();
            }

        }

    }

    public void checkIfRoadIsEmpty() {
        //If road is empty and it's light is green then make it red and open another road.
        if (myRoad1.getCarNumber() == 0 && myRoad1.getLight() == true) {
            myRoad1.changeLight();
            if (myRoad2.getLight() == false) {
                myRoad2.changeLight();
            } else if (myRoad3.getLight() == false) {
                myRoad3.changeLight();
            }

        }

        if (myRoad2.getCarNumber() == 0 && myRoad2.getLight() == true) {

            myRoad2.changeLight();
            if (myRoad1.getLight() == false) {
                myRoad1.changeLight();
            } else if (myRoad3.getLight() == false) {
                myRoad3.changeLight();
            }

        }

        if (myRoad3.getCarNumber() == 0 && myRoad3.getLight() == true) {
            myRoad3.changeLight();
            if (myRoad1.getLight() == false) {
                myRoad1.changeLight();
            } else if (myRoad2.getLight() == false) {
                myRoad2.changeLight();
            }

        }
    }

    public void incrementGreenTimeElapsed() {
        if (myRoad1.getLight()) {
            myRoad1.incrementGreenTime();
        }
        if (myRoad2.getLight()) {
            myRoad2.incrementGreenTime();
        }
        if (myRoad3.getLight()) {
            myRoad3.incrementGreenTime();
        }

    }
}
