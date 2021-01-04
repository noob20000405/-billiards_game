import javax.swing.*;
import java.awt.*;

public class Blanche extends Bille implements Mobile {
    /** 白球上一次在桌上静止时的位置 */
    private int precedentX;
    private int precedentY;
    private static Blanche singletonBlanche = new Blanche(250, 250);

    private Blanche(int x, int y) {
        super(x, y, Global.billeR, new Color(255, 255, 255), 0, 0);
    }

    public static Blanche getSingletonBlanche() {
        return singletonBlanche;
    }

    /** 白球移动 */
    @Override
    public void deplacer() {
        x += vitesse * Math.cos(direction);
        y += vitesse * Math.sin(direction);
        centreX = getX() + getR();
        centreY = getY() + getR();
    
        if (vitesse > 0) {
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

    /** 如果白球进洞 则回到桌上上一次球静止的位置 */
    public void collisionTrou(Trou[] trous, Joueur j, Joueur j1) {
        for (int i = 0 ; i < trous.length ; i++) {
            double dis = Math.sqrt((trous[i].centreX - this.centreX) * (trous[i].centreX - this.centreX) +  (trous[i].centreY - this.centreY) * (trous[i].centreY - this.centreY));
            if (dis < 30) {
              vitesse = 0;
              x = precedentX;
              y = precedentY;
            }
        }
    }

    public void afficherColor() {
        System.out.println("Blanche");
    }
}
