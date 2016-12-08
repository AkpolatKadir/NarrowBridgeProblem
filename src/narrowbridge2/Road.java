/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package narrowbridge2;



/**
 *
 * @author Kadir
 */
public class Road {

    boolean light;//Red=false,Green=true
  // boolean isRoadFull=false;
    int carNumber=0;
    public Road( boolean _light, int lineNumber) {
        this.light = _light;           

    }   
    

    public boolean getLight() {
        return light;
    }

    public void changeLight()//Switching between lights.
    {
        this.light = !this.light;
    }
    
    public boolean isRoadFull()
    {
        return carNumber==3;
        
    }
    
    public void incrementCarNumber()
    {
       ++carNumber;
    }
    
}
