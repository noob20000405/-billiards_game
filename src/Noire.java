import java.awt.*;

public class Noire extends Bille implements Rond {
    /** Coordonnées de la dernier position (précédente) de la bille noire quand elle était immobile */
    private int precedentX;
    private int precedentY;

    public Noire(int x, int y) {
        super(x, y, Global.billeR, new Color(0, 0, 0), 0, 0);
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
        } else {
            precedentX = x;
            precedentY = y;
        }
    }

    /** Retourner les coordonnées de la dernier position (précédente) de la bille noire quand elle était immobile */
    public int getPrecedentX() {
        return precedentX;
    }

    public int getPrecedentY() {
        return precedentY;
    }

    /** Si le joueur a marqué 7 billes, puis il frappe la bille noire dans les trous, il gagne. Sinon on repositionner la bille noire */
    @Override
    public void collisionTrou(Trou[] trous, Joueur j, Joueur j1) {
        for (int i = 0 ; i < trous.length ; i++) {
            double dis = Math.sqrt((trous[i].getCentreX()- this.getCentreX()) * (trous[i].getCentreX() - this.getCentreX()) +  (trous[i].getCentreY() - this.getCentreY()) * (trous[i].getCentreY() - this.getCentreY()));
            if (dis < 30) {
                vitesse = 0;
                if (j.getPoche().getNbBilles() == 7 && j1.estEnTour()) { // Joueur j1 est en tour, cela signifie que c'est joueur j qui a frappé la bille noire dans le trou, car les joueurs vont changer leur tour après la frappe (dans GameStart.java)
                    j.marquer(this);
                } else if (j1.getPoche().getNbBilles() == 7 && j.estEnTour()) { // Avec la meme raison
                    j1.marquer(this);
                } else {
                    x = precedentX;
                    y = precedentY;
                }
            }
        }
    }

    public void afficherColor() {
        System.out.println("Noir");
    }
}
