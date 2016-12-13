/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package narrowbridge2;

import static java.lang.Math.random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kadir
 */
public class Controller extends Thread {

    Road myRoad1, myRoad2, myRoad3;

    public Controller(Road road1, Road road2, Road road3) {
        this.myRoad1 = road1;
        this.myRoad2 = road2;
        this.myRoad3 = road3;
    }

    public void run() {
        while (true) {

            if (myRoad1.getLight()) {
                myRoad1.incrementGreenTime();
            }
            if (myRoad2.getLight()) {
                myRoad2.incrementGreenTime();
            }
            if (myRoad3.getLight()) {
                myRoad3.incrementGreenTime();
            }

            if (myRoad1.getLight() == false && myRoad1.isRoadFull()) {

                myRoad1.changeLight();
                if (myRoad2.getElapsedTime() >= myRoad2.getElapsedTime()) {
                    myRoad2.changeLight();
                } else {
                    myRoad3.changeLight();
                }

            } else if (myRoad2.getLight() == false && myRoad2.isRoadFull()) {

                myRoad2.changeLight();
                if (myRoad1.getElapsedTime() >= myRoad3.getElapsedTime()) {
                    myRoad1.changeLight();
                } else {
                    myRoad3.changeLight();
                }

            } else if (myRoad3.getLight() == false && myRoad3.isRoadFull()) {

                myRoad3.changeLight();
                if (myRoad2.getElapsedTime() >= myRoad1.getElapsedTime()) {
                    myRoad2.changeLight();
                } else {
                    myRoad1.changeLight();
                }
            }
            
            if(myRoad1.getCarNumber()==0 && myRoad1.getLight()==true)
            {
                myRoad1.changeLight();
                if(myRoad2.getLight()==false)
                {
                    myRoad2.changeLight();
                }
                else if(myRoad3.getLight()==false)
                {
                    myRoad3.changeLight();
                }
            }
            
             if(myRoad2.getCarNumber()==0 && myRoad2.getLight()==true)
            {
                myRoad2.changeLight();
                if(myRoad1.getLight()==false)
                {
                    myRoad1.changeLight();
                }
                else if(myRoad3.getLight()==false)
                {
                    myRoad3.changeLight();
                }
            }
             
              if(myRoad3.getCarNumber()==0 && myRoad3.getLight()==true)
            {
                myRoad3.changeLight();
                if(myRoad1.getLight()==false)
                {
                    myRoad1.changeLight();
                }
                else if(myRoad2.getLight()==false)
                {
                    myRoad2.changeLight();
                }
            }
          
            
            
            try {
                Thread.sleep(1050);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
