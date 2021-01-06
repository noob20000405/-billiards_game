import java.awt.*;

public class Trou extends Materiel implements Rond {
    public Trou(int x, int y, int r) {
        super(x, y, r * 2, r * 2, new Color(0, 0, 0));
    }

    public int getR() {
        return width / 2;
    }
    
    public int getCentreX() {
        return getX() + getR();
    }
    
    public int getCentreY() {
        return getY() + getR();
    }

    public void afficher() {
        System.out.println("Trou");
    }
}
