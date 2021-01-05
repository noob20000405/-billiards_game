import java.awt.*;

public class Basse extends Bille {
    public Basse(int x, int y) {
        super(x, y, Global.billeR, new Color(255, 0, 0), 0, 0);
    }

    public Basse clone() {
        return new Basse(x, y);
    }

    /** Les billes basses sont rouges */
    public void afficherColor() {
        System.out.println("Rouge");
    }
}