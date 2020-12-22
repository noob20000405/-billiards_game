import javax.swing.*;
import java.awt.*;

public abstract class Materiel {
  protected Image img;

  public Materiel(String path) {
    img = Toolkit.getDefaultToolkit().getImage(path);
  }

  public Image getImage() {
    return img;
  }
}
