import java.awt.*;

public class Joueur {
    /** Bande et poche du joueur */
    private Bande bande;
    private Poche poche;
    /** Si enTour == true, le joueur est en tour */
    private boolean enTour;
    /** Si gagneur == true, il est le gagneur */
    private boolean gagneur;
    /** L'id du joueur, on a deux joueurs dans ce jeu */
    private int id;
    /** Compteur du nombre de joueurs */
    private static int cpt = 0;

    public Joueur(boolean enTour) {
        cpt++;
        id = cpt;
        this.enTour = enTour;
        gagneur = false;

        if (id == 1) { // S'il est le premier, sa bande est blanche, sa poche est à gauche
            bande = new Bande(new Color(255, 255, 255));
            poche = new Poche(50, 580);
        } else if (id == 2) { // S'il est le deuxième, sa bande est noire, sa poche est à droite
            bande = new Bande(new Color(0, 0, 0));
            poche = new Poche(Global.tabWidth - 50 - Global.pocheWidth, 580);
        }
    }

    /** Changer leur tour */
    public void changerTour() {
        if (enTour) {
            enTour = false;
        } else {
            enTour = true;
        }
    }

    public boolean estEnTour() {
        return enTour;
    }

    public void gagner() {
        gagneur = true;
    }

    public int getId() {
        return id;
    }

    public boolean estGagneur() {
        return gagneur;
    }

    public Poche getPoche() {
        return poche;
    }

    public Bande getBande() {
        return bande;
    }

}
