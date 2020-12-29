import javax.swing.*;
import java.awt.*;

public class Bande extends Materiel {
    private int sourisX;
    private int sourisY;
    private Bille but;

    public Bande(Bille b) {
        super(b.x, b.y, 0, 0, new Color(255, 255, 255));
        sourisX = java.awt.MouseInfo.getPointerInfo().getLocation().x;
        sourisY = java.awt.MouseInfo.getPointerInfo().getLocation().y;
        but = b;
    }

    public void viser() {
        sourisX = java.awt.MouseInfo.getPointerInfo().getLocation().x;
        sourisY = java.awt.MouseInfo.getPointerInfo().getLocation().y;
        x = but.x + Global.billeR;
        y = but.y + Global.billeR;
    }

    public void frapper() {
        if (y != sourisY && x != sourisX) {
            double angle = Math.atan((double)(y - sourisY) / (double)(x - sourisX));
            if (sourisX > x) angle += Global.pi;

            System.out.println(angle);////////////////
            System.out.println((y - sourisY) / (x - sourisX));
            System.out.println("billex : " + x);
            System.out.println("billey : " + y);
            System.out.println("sx : " + sourisX);
            System.out.println("sy : " + sourisY);
            but.etreFrappee(10, angle);
        }
    }

    public int getSourisX() {
        return sourisX;
    }

    public int getSourisY() {
        return sourisY;
    }
}