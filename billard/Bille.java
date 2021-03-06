import java.awt.*;

public abstract class Bille extends Cercle implements Mobile {
  /** Vitesse de la bille */
  protected int vitesse;
  /** Ce variable représente la vitesse accélérée mais il n'est pas vraiment la vitesse accélérée */
  protected double vAcceleree = 10;
  /** Direction du déplacement */
  protected double direction;
  /** Si surTable == true, cette bille est sur table, sinon elle est dans l'une des poches */
  protected boolean surTable;

  public Bille(int x, int y, int r, Color color, int v, double d) {
    super(x, y, r, color);
    this.vitesse = v;
    this.direction = d;
    this.vAcceleree = 10;
    this.surTable = true;
  }

  /** Mettre la bille à une certaine position */
  public void setPos(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /** Déplacer la bille, puis changer sa vitesse avec le variable vAcceleree */
  public void deplacer() {

    x += vitesse * Math.cos(direction);
    y += vitesse * Math.sin(direction);
    centreX = getX() + getR();
    centreY = getY() + getR();

    if (vitesse > 0) { // La vitesse moins un après dix fois de déplacement, jusqu'à vitesse == 0
      vAcceleree -= 1;
      if (vAcceleree == 0) {
        vitesse -= 1;
        vAcceleree = 10;
      }
    }
  }

  /** Collision avec les bordures */
  public void collisionBordure() {
    int suivantX = x + (int)(vitesse * Math.cos(direction));
    int suivantY = y + (int)(vitesse * Math.sin(direction));

    /** Eviter la condition que la bille dépasser le cadre des bordures en cours du déplacement */
    if (suivantX <= Global.tapOffset || suivantX >= Global.tapOffset + Global.tapWidth - width) {
      direction = Global.pi - direction;
    }
    if (suivantY <= Global.tapOffset || suivantY >= Global.tapOffset + Global.tapHeight - height) {
      direction = -direction;
    }

    /** Repositionner les billes qui sont dehors du cadre */
    if (surTable) {
      if (x < Global.tapOffset) x = Global.tapOffset;
      if (x > Global.tapOffset + Global.tapWidth - width) x = Global.tapOffset + Global.tapWidth - width;
      if (y < Global.tapOffset) y = Global.tapOffset;
      if (y > Global.tapOffset + Global.tapHeight - height) y = Global.tapOffset + Global.tapHeight - height;
    }
  }

  /** Collision avec une autre bille */
  public void collisionBille(Bille b) {
    if (this != b) {

      int x1 = super.centreX;
      int x2 = b.centreX;
      int y1 = super.centreY;
      int y2 = b.centreY;
      double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
      double v1x = vitesse * Math.cos(direction);
      double v1y = vitesse * Math.sin(direction);
      double v2x = b.vitesse * Math.cos(b.direction);
      double v2y = b.vitesse * Math.sin(b.direction);

      if (Math.abs(d) < Global.epsilon + 2 * getR()) { // Changer ses vitesses et ses directions

        int tmpV;
        double tmpD;

        tmpV = vitesse;
        tmpD = direction;
        vitesse = b.vitesse;
        direction = b.direction;
        b.vitesse = tmpV;
        b.direction = tmpD;

        if (vitesse > 0) {
          vitesse -= Global.perteVitesseCollision;
        }
        if (b.vitesse > 0) {
          b.vitesse -= Global.perteVitesseCollision;
        }

        deplacer();
        b.deplacer();
      }

    }
  }

  /** Collision avec les trous */
  public void collisionTrou(Trou[] trous, Joueur j, Joueur j1) {
    for (int i = 0 ; i < trous.length ; i++) {
      double dis = Math.sqrt((trous[i].centreX - this.centreX) * (trous[i].centreX - this.centreX) +  (trous[i].centreY - this.centreY) * (trous[i].centreY - this.centreY));

      if (dis < 30) {
        this.vitesse = 0;
        if (this instanceof Basse) { // Si c'est une bille basse, le joueur j marque
          j.getPoche().marquer(this);
        } else if (this instanceof Haute) { // Si c'est une haute, le joueur j1 marque
          j1.getPoche().marquer(this);
        }
	
        // Dans GameStart.java les joueurs vont changer leur tour après la frappe, ici on le re-change, cela signifie que le joueur peut continuer à frapper si il a marqué
        j.changerTour();
        j1.changerTour();
        
      }
    }
  }

  /** être frapper par la bande */
  public void etreFrappee(int v, double d) {
    vitesse = v;
    direction = d;
  }

  /** déterminer si la bille est mobile */
  public boolean estMobile() {
    if (vitesse == 0) {
      return false;
    } else {
      return true;
    }
  }

  /** Afficher le couleur pour debug */
  public abstract void afficherColor();

}
