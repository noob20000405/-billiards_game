import java.util.ArrayList;

public class Poche extends Materiel {
  private Bille[] billes;
  private int nbBilles;

  public Poche(int x, int y) {
    super(x, y, Global.pocheWidth, Global.pocheHeight, null);
    billes = new Bille[6];
    nbBilles = 0;
  }

  public void marquer(Bille b) { 
      b.x = this.x + nbBilles * (2 * Global.billeR + 10);
      b.y = this.y;
      billes[nbBilles] = b;
      nbBilles++;
  }
}