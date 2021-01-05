import java.awt.*;

public class Tapis extends Materiel {
    /** Créer le singleton */
    private static Tapis singletonTapis = new Tapis(Global.tapOffset, Global.tapOffset, Global.tapWidth, Global.tapHeight, new Color(0, 128, 0));

    private Tapis(int x, int y, int w, int h, Color color) {
        super(x, y, w, h, color);
    }

    public static Tapis getSingletonTapis() {
        return singletonTapis;
    }
}