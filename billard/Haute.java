import javax.swing.*;
import java.awt.*;
public class Haute extends Bille {
    // haute球花纹的属性
    private final Color colorRayure = new Color(245, 245, 245);
    private final int widthRayrue = 10;

    public Haute(int x, int y, Color color) {
        super(x, y, Global.billeR, color, 0, 0);
    }

    public Color getColorRayure() {
        return colorRayure;
    }

    public int getWidthRayure() {
        return widthRayrue;
    }

    public Haute clone() {
        return new Haute(x, y, color);
    }
}
