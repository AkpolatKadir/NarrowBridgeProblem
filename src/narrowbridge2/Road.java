/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package narrowbridge2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kadir
 */
public class Road {
    static Semaphore semaphore = new Semaphore(1);
    static Semaphore semaphore2 = new Semaphore(1);
    boolean light;//Red=false,Green=true
    // boolean isRoadFull=false;
    int carNumber = 0;

    public Road(boolean _light, int lineNumber) {
        this.light = _light;

    }

    public boolean getLight() {
        return light;
    }

    public void changeLight()//Switching between lights.
    {
        this.light = !this.light;
    }

    public boolean isRoadFull() {
        return carNumber == 3;

    }

    public void incrementCarNumber() {
        try {
            semaphore.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Road.class.getName()).log(Level.SEVERE, null, ex);
        }
        ++carNumber;
        semaphore.release();
    }

    public void decrementCarNumber() {
        try {
            semaphore2.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Road.class.getName()).log(Level.SEVERE, null, ex);
        }
        --carNumber;
        semaphore2.release();
    }

}
