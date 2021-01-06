import java.awt.*;

public class Queue extends Materiel {
    /** Coordonnées de la souris */
    private int sourisX;
    private int sourisY;
    /** La bille pointée par cette queue */
    private Bille but;
    /** Si cette queue est visible */
    private boolean visible;

    public Queue(Color color) {
        super(Blanche.getSingletonBlanche().getX(), Blanche.getSingletonBlanche().getY(), 0, 0, color);
        sourisX = java.awt.MouseInfo.getPointerInfo().getLocation().x;
        sourisY = java.awt.MouseInfo.getPointerInfo().getLocation().y;
        but = Blanche.getSingletonBlanche();
        visible = false;
    }

    /** Viser selon le changement de position de la souris */
    public void viser() {
        sourisX = java.awt.MouseInfo.getPointerInfo().getLocation().x;
        sourisY = java.awt.MouseInfo.getPointerInfo().getLocation().y;
        x = but.x + Global.billeR;
        y = but.y + Global.billeR;
    }

    /** Frapper le but */
    public void frapper(int force) {
        if (visible && y != sourisY && x != sourisX) {
            double angle = Math.atan((double)(y - sourisY) / (double)(x - sourisX));
            if (sourisX > x) angle += Global.pi;
            but.etreFrappee(1 + Global.uniteVitesse * force, angle);
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

    public void afficher() {
        System.out.println("Queue");
      }
}
