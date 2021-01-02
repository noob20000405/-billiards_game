import javax.swing.*;
import java.awt.*;

public class Noir extends Bille implements Mobile {
    // 黑球的这个class几乎和白球的一样 除了最后的collisiontrou
    private int precedentX;
    private int precedentY;

    public Noir(int x, int y) {
        super(x, y, Global.billeR, new Color(0, 0, 0), 0, 0);
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

    // 如果一玩家再打进7球后打进黑球 则获胜 否则提前打进黑球  则黑球返回原位 交换击球
    public void collisionTrou(Trou[] trous, Joueur j, Joueur j1) {
        for (int i = 0 ; i < trous.length ; i++) {
            double dis = Math.sqrt((trous[i].centreX - this.centreX) * (trous[i].centreX - this.centreX) +  (trous[i].centreY - this.centreY) * (trous[i].centreY - this.centreY));
            if (dis < 30) {
                vitesse = 0;
                if (j.getPoche().getNbBilles() == 7 && j.estEnTour()) {
                    j.getPoche().marquer(this);
                } else if (j1.getPoche().getNbBilles() == 7 && j1.estEnTour()) {
                    j1.getPoche().marquer(this);
                } else {
                    x = precedentX;
                    y = precedentY;
                }
            }
        }
    }
}
