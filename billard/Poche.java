

public class Poche extends Materiel {
  // 球带中球们和球的个数
  private Bille[] billes;
  private int nbBilles;

  public Poche(int x, int y) {
    super(x, y, Global.pocheWidth, Global.pocheHeight, null);
    billes = new Bille[8];
    nbBilles = 0;
  }

  // 球进得分
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
