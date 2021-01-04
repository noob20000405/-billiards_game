import javax.swing.*;
import java.awt.*;

public class Joueur {
    // 玩家的球竿 球带
    private Bande bande;
    private Poche poche;
    // 是否轮到此人击球
    private boolean enTour;
    // 此人是否获胜
    private boolean gagneur;
    // 玩家id
    private int id;
    private static int cpt = 0;

    public Joueur(boolean enTour) {
        cpt++;
        id = cpt;
        this.enTour = enTour;
        gagneur = false;

        if (id == 1) {
            bande = new Bande(new Color(255, 255, 255));
            poche = new Poche(50, 580);
        } else if (id == 2) {
            bande = new Bande(new Color(0, 0, 0));
            poche = new Poche(Global.tabWidth - 50 - Global.pocheWidth, 580);
        }
    }

    // 交换击球
    public void changerTour() {
        if (enTour) {
            enTour = false;
        } else {
            enTour = true;
        }
    }

    // 是否轮到此人
    public boolean estEnTour() {
        return enTour;
    }

    public int getId() {
        return id;
    }

    // 让此人胜利
    public void gagner() {
        gagneur = true;
    }

    // 此人是否获胜
    public boolean estGagneur() {
        return gagneur;
    }

    // 获取此人球带
    public Poche getPoche() {
        return poche;
    }

    public Bande getBande() {
        return bande;
    }

}
