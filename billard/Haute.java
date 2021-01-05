import java.awt.*;

public class Haute extends Bille {
    /** Couleur et taille de la rayure */
    private final Color colorRayure = new Color(245, 245, 245);
    private final int widthRayrue = 10;

    public Haute(int x, int y) {
        super(x, y, Global.billeR, new Color(0, 0, 255), 0, 0);
    }

    public Color getColorRayure() {
        return colorRayure;
    }

    public int getWidthRayure() {
        return widthRayrue;
    }

    public Haute clone() {
        return new Haute(x, y);
    }

    public void afficherColor() {
        System.out.println("Bleue et blanche");
    }
}
