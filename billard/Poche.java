public class Poche extends Materiel {
  /** Les billes dans la poche */
  private Bille[] billes;
  /** Nombre des billes */
  private int nbBilles;

  public Poche(int x, int y) {
    super(x, y, Global.pocheWidth, Global.pocheHeight, null);
    billes = new Bille[8];
    nbBilles = 0;
  }

  /** Qqn frappe une bille dans les trous */
  public void marquer(Bille b) { 
      b.x = this.x + nbBilles * (2 * Global.billeR + 10);
      b.y = this.y;
      b.surTable = false;
      billes[nbBilles] = b;
      nbBilles++;
  }

  public int getNbBilles() {
    return nbBilles;
  }
}
