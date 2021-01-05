import java.awt.*;

public abstract class Cercle extends Materiel {
    /** Rayon du cercle */
    protected int rayon;
    /** Coordonnées de la centre du cercle */
    protected int centreX;
    protected int centreY;

    public Cercle(int x, int y, int r, Color color) {
        super(x, y, r * 2, r * 2, color);
        centreX = x + r;
        centreY = y + r;
        rayon = r;
    }

    public int getR() {
        return rayon;
    }
}
