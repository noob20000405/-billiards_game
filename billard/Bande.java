import javax.swing.*;
import java.awt.*;

public class Bande extends Materiel {
    /** 鼠标的xy坐标 */
    private int sourisX;
    private int sourisY;
    /** 目标球 */
    private Bille but;
    /** 球竿是否隐藏（击球后隐藏直到下一回合） */
    private boolean visible;

    public Bande(Color color) {
        super(Blanche.getSingletonBlanche().getX(), Blanche.getSingletonBlanche().getY(), 0, 0, color);
        sourisX = java.awt.MouseInfo.getPointerInfo().getLocation().x;
        sourisY = java.awt.MouseInfo.getPointerInfo().getLocation().y;
        but = Blanche.getSingletonBlanche();
        visible = false;
    }

    /** 根据鼠标位置调整球竿位置 */
    public void viser() {
        sourisX = java.awt.MouseInfo.getPointerInfo().getLocation().x;
        sourisY = java.awt.MouseInfo.getPointerInfo().getLocation().y;
        x = but.x + Global.billeR;
        y = but.y + Global.billeR;
    }

    /** 击球 */
    public void frapper(int force) {
        if (visible && y != sourisY && x != sourisX) {
            double angle = Math.atan((double)(y - sourisY) / (double)(x - sourisX));
            if (sourisX > x) angle += Global.pi;
            but.etreFrappee(5 * force, angle);
        }
    }

    public int getSourisX() {
        return sourisX;
    }

    public int getSourisY() {
        return sourisY;
    }

    public boolean estVisible() {
        return visible;
    }

    public void show() {
        visible = true;
    }

    public void hide() {
        visible = false;
    }
}
