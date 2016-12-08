/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package narrowbridge2;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Kadir
 */
public class CarApplet extends Applet {

    Car[] Road1cars = new Car[20];
    Car[] Road2cars = new Car[20];
    Car[] Road3cars = new Car[20];
    Road Road1, Road2, Road3;

    @Override
    public void init() {

        Road1 = new Road( true, 1);
        Road2 = new Road( true, 2);
        Road3 = new Road( false, 3);

        for (int i = 0; i < 20; i++) {
            Road1cars[i] = new Car(this, 50, 115,Road1);
            Road2cars[i] = new Car(this, 50, 225,Road2);
            Road3cars[i] = new Car(this, 50, 335,Road3);
            
            Road1cars[i].start();
            Road2cars[i].start();
            Road3cars[i].start();
        }

    }

    @Override
    public void paint(Graphics g) {

        //Fixed Roads Drawing.
        g.setColor(Color.BLACK);

        g.drawLine(
                50, 100, 200, 100);
        g.drawLine(
                50, 130, 200, 130);
        g.drawLine(
                50, 210, 200, 210);
        g.drawLine(
                50, 240, 200, 240);

        g.drawLine(
                50, 320, 200, 320);
        g.drawLine(
                50, 350, 200, 350);

        //Road 1 Light.
        if (Road1.getLight()) {
            g.setColor(Color.GREEN);
            g.fillOval(180, 70, 20, 20);
        } else {
            g.setColor(Color.RED);
            g.fillOval(180, 70, 20, 20);

        }

        //Road 2 Light.
        if (Road2.getLight()) {
            g.setColor(Color.GREEN);
            g.fillOval(180, 180, 20, 20);

        } else {
            g.setColor(Color.RED);
            g.fillOval(180, 180, 20, 20);

        }
        //Road 3 Light. 

        if (Road3.getLight()) {
            g.setColor(Color.GREEN);
            g.fillOval(180, 290, 20, 20);

        } else {
            g.setColor(Color.RED);
            g.fillOval(180, 290, 20, 20);
        }
     
      
     for (int i = 0; i < 20; i++) {
            Road1cars[i].paint(g);
            Road2cars[i].paint(g);
            Road3cars[i].paint(g);
        }

    }
}
