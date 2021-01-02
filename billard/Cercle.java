import javax.swing.*;
import java.awt.*;

public abstract class Cercle extends Materiel {
    /** 圆形的半径 */
    protected int rayon;
    /** 圆心的位置 因为在graphic里他设定的xy是图形左上角的坐标 所以这里加一个圆心的位置 */
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
