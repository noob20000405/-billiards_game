import javax.swing.*;
import java.awt.*;
public class Basse extends Bille {
    public Basse(int x, int y, Color color) {
        super(x, y, Global.billeR, color, 0, 0);
    }

    public Basse clone() {
        return new Basse(x, y, color);
    }
}