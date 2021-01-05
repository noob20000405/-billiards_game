import java.awt.*;

public class Blanche extends Bille implements Mobile {
    /** Coordonnées de la précedent position quand la bille est immobile */
    private int precedentX;
    private int precedentY;
    /** Créer le singleton */
    private static Blanche singletonBlanche = new Blanche(250, 250);

    private Blanche(int x, int y) {
        super(x, y, Global.billeR, new Color(255, 255, 255), 0, 0);
    }

    public static Blanche getSingletonBlanche() {
        return singletonBlanche;
    }

    /** Déplacement de la bille blanche */
    @Override
    public void deplacer() {
        x += vitesse * Math.cos(direction);
        y += vitesse * Math.sin(direction);
        centreX = getX() + getR();
        centreY = getY() + getR();
    
        if (vitesse > 0) {
          vAcceleree -= 1;
          if (vAcceleree == 0) {
            vitesse -= 1;
            vAcceleree = 10;
          }
        } else { // Renouveler la position précedente
            precedentX = x;
            precedentY = y;
        }
    }

    public int getPrecedentX() {
        return precedentX;
    }

    public int getPrecedentY() {
        return precedentY;
    }

    /** Collision avec les trous */
    public void collisionTrou(Trou[] trous, Joueur j, Joueur j1) {
        for (int i = 0 ; i < trous.length ; i++) {
            double dis = Math.sqrt((trous[i].centreX - this.centreX) * (trous[i].centreX - this.centreX) +  (trous[i].centreY - this.centreY) * (trous[i].centreY - this.centreY));
            if (dis < 30) { // Repositionner la bille blanche à la position précedente
              vitesse = 0;
              x = precedentX;
              y = precedentY;
            }
        }
    }

    public void afficherColor() {
        System.out.println("Blanche");
    }
}
