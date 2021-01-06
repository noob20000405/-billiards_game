import java.awt.*;

public class Blanche extends Bille implements Rond {
    /** Coordonnées de la dernier position (précédente) de la bille blanche quand elle était immobile */
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

    public int getR() {
        return width / 2;
    }
    
    public int getCentreX() {
        return getX() + getR();
    }
    
    public int getCentreY() {
        return getY() + getR();
    }

    /** Déplacement de la bille blanche */
    @Override
    public void deplacer() {
        x += vitesse * Math.cos(direction);
        y += vitesse * Math.sin(direction);

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

    /** Retourner les coordonnées de la dernier position (précédente) de la bille blanche quand elle était immobile */
    public int getPrecedentX() {
        return precedentX;
    }

    public int getPrecedentY() {
        return precedentY;
    }

    /** Collision avec les trous */
    @Override
    public void collisionTrou(Trou[] trous, Joueur j, Joueur j1) {
        for (int i = 0 ; i < trous.length ; i++) {
            double dis = Math.sqrt((trous[i].getCentreX()- this.getCentreX()) * (trous[i].getCentreX() - this.getCentreX()) +  (trous[i].getCentreY() - this.getCentreY()) * (trous[i].getCentreY() - this.getCentreY()));
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
