import javax.swing.*;
import java.awt.*;
public class Bille extends Cercle implements Mobile {
  private int vitesse;
  private double direction;
	
  public Bille(int x, int y, int r, Color color) {
    super(x, y, r, color);
    this.vitesse = 2; //test
    this.direction = Global.pi / 3; // test data
  }

  public void deplacer() {
    x += vitesse * Math.cos(direction);
    y += vitesse * Math.sin(direction);
  }

  public void rebondirBordure() {
    if (x < Global.tapOffset || x > Global.tapOffset + Global.tapWidth - width) {
      direction = Global.pi - direction;
    }
    if (y < Global.tapOffset || y > Global.tapOffset + Global.tapHeight - height) {
      direction = -direction;
    }
  }

  public void rebondirBille(Bille b) {}
}
