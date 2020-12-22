public class Bille extends Materiel implements Mobile {
  private int x;
  private int y;
  private int vitesse;
  private double direction;
	
  public Bille(String path, int x, int y) {
    super(path);
    this.x = x;
    this.y = y;
    this.vitesse = 2;
    this.direction = Global.pi / 3;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void deplacer() {
    x += vitesse * Math.cos(direction);
    y += vitesse * Math.sin(direction);
  }

  public void rebondirBordure() {
    if (x < 50 || x > 800) {
      direction = Global.pi - direction;
    }
    if (y < 80 || y > 450) {
      direction = -direction;
    }
  }

  public void rebondirBille(Bille b) {}
}
