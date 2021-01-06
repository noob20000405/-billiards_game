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

  public Bille[] getBilles() {
    return billes;
  }

  public void nbBillesAugmente() {
    nbBilles++;
  }

  public int getNbBilles() {
    return nbBilles;
  }

  public void afficher() {
    System.out.println("Poche");
  }
}
