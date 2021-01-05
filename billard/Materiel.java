import java.awt.*;

public abstract class Materiel {
  /** Coordonnées, taille, couleur du matériel */
  protected int x;
  protected int y;
  protected int width;
  protected int height;
  protected Color color;

  public Materiel(int x, int y, int w, int h, Color color) {
    this.x = x;
    this.y = y;
    height = h;
    width = w;
    this.color = color;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public Color getColor() {
    return color;
  }
}
