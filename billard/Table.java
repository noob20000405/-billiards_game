import javax.swing.*;
import java.awt.*;
public class Table extends Materiel {
  private Tapis tapis;
  private Trou[] trous;

  public Table(int x, int y, int w, int h, Color color, Tapis tapis, Trou[] trous) {
    super(x, y, w, h, color);
    this.tapis = tapis;
    this.trous = trous;
  }

  public Tapis getTapis() {
    return tapis;
  }

  public Trou[] getTrous() {
    return trous;
  }
}
