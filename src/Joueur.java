import java.awt.*;

public class Joueur {
    /** Queue et poche du joueur */
    private Queue queue;
    private Poche poche;
    /** Si enTour est vrai, le joueur est en tour */
    private boolean enTour;
    /** Si gagneur est vrai, il est le gagneur */
    private boolean gagneur;
    /** L'id du joueur. On a deux joueurs dans ce jeu */
    private final int id;
    /** Compteur du nombre de joueurs */
    private static int cpt = 0;

    public Joueur(boolean enTour) {
        cpt++;
        id = cpt;
        this.enTour = enTour;
        gagneur = false;

        if (id == 1) { // S'il est le premier, sa queue est blanche, sa poche est à gauche
            queue = new Queue(new Color(255, 255, 255));
            poche = new Poche(50, 580);
        } else if (id == 2) { // S'il est le deuxième, sa queue est noire, sa poche est à droite
            queue = new Queue(new Color(0, 0, 0));
            poche = new Poche(Global.tabWidth - 50 - Global.pocheWidth, 580);
        }
    }

    /** Joueur frappe une bille dans un trou */
    public void marquer(Bille b) {
        b.x = getPoche().getX() + getPoche().getNbBilles() * (2 * Global.billeR + 10);
        b.y = getPoche().getY();
        b.surTable = false;
        getPoche().getBilles()[getPoche().getNbBilles()] = b;
        getPoche().nbBillesAugmente();
    }

    /** Changer leur tour */
    public void changerTour() {
        if (enTour) {
            enTour = false;
        } else {
            enTour = true;
        }
    }

    /** Determiner si ce joueur est en tour */
    public boolean estEnTour() {
        return enTour;
    }

    public void gagner() {
        gagneur = true;
    }

    public int getId() {
        return id;
    }

    /** Determiner si ce joueur est le gagneur */
    public boolean estGagneur() {
        return gagneur;
    }

    public Poche getPoche() {
        return poche;
    }

    public Queue getQueue() {
        return queue;
    }

}
