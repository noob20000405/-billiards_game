import javax.swing.*;
import java.awt.*;

public class Blanche extends Bille implements Mobile {
    private int precedentX;
    private int precedentY;

    public Blanche(int x, int y) {
        super(x, y, Global.billeR, new Color(255, 255, 255), 0, 0);
    }

    @Override
    public void deplacer() {
        x += vitesse * Math.cos(direction);
        y += vitesse * Math.sin(direction);
        centreX = getX() + getR();
        centreY = getY() + getR();
    
        if (vitesse > 0.1) {
          vAcceleree -= 1;
          if (vAcceleree == 0) {
            vitesse -= 1;
            vAcceleree = 10;
          }
        } else {
            precedentX = x;
            precedentY = y;
        }
    }

    public int getPrecedentX() {
        return precedentX;
    }

    public int getPrecedentY() {
        return precedentY;
    }

    public void rebondirTrou(Trou[] trous, Poche poche) {
        for (int i = 0 ; i < trous.length ; i++) {
            double dis = Math.sqrt((trous[i].centreX - this.centreX) * (trous[i].centreX - this.centreX) +  (trous[i].centreY - this.centreY) * (trous[i].centreY - this.centreY));
            if (dis < 30) {
              vitesse = 0;
              x = precedentX;
              y = precedentY;
            }
        }
    }
}