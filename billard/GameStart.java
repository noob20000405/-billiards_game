import javax.swing.*;
import java.awt.*;

public class GameStart extends JFrame {
  Bille bille1 = new Bille("imgs/blanche.jpg", 50, 80);
  Bille bille2 = new Bille("imgs/blanche.jpg", 50, 100);
  Table table = new Table("imgs/table.jpg");  

  public void paint(Graphics g) {
    super.paint(g);
    //System.out.print("fuck");
    g.drawImage(table.getImage(), 0, 0, null);
    g.drawImage(bille1.getImage(), bille1.getX(), bille1.getY(), null);
    g.drawImage(bille2.getImage(), bille2.getX(), bille2.getY(), null);

    bille1.deplacer();
    bille2.deplacer();
    bille1.rebondirBordure();
    bille2.rebondirBordure();
  }

  void launchFrame() {
    setSize(850, 490);
    setLocation(50, 50);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

    while (true) {
      repaint();
      try {
        Thread.sleep(10);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] argv) {
    GameStart game = new GameStart();
    game.launchFrame();
  }
}
