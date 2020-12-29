import javax.swing.*;
import java.awt.*;

public class GameStart extends Frame {
  // vitesse : 5, 10, 15, 20
  Bille bille1 = new Bille(500, 250, Global.billeR, new Color(255, 255, 255), 20, Global.pi/2);
  Bille bille2 = new Bille(500, 150, Global.billeR, new Color(255, 100, 255), 0, Global.pi/2);
  Bille bille3 = new Bille(500, 50, Global.billeR, new Color(255, 0, 255), 0, Global.pi/2);
  Tapis tapis = new Tapis(Global.tapOffset, Global.tapOffset, Global.tapWidth, Global.tapHeight, new Color(0, 128, 0));
  Trou[] trous = {
                  new Trou(Global.tapOffset - Global.trouR, Global.tapOffset - Global.trouR, Global.trouR), 
                  new Trou(Global.tapOffset - Global.trouR, Global.tapOffset + Global.tapHeight - Global.trouR, Global.trouR), 
                  new Trou(Global.tabWidth / 2 - Global.trouR, Global.tapOffset - Global.trouR, Global.trouR), 
                  new Trou(Global.tabWidth / 2 - Global.trouR, Global.tapOffset + Global.tapHeight - Global.trouR, Global.trouR), 
                  new Trou(Global.tapOffset + Global.tapWidth - Global.trouR, Global.tapOffset - Global.trouR, Global.trouR), 
                  new Trou(Global.tapOffset + Global.tapWidth - Global.trouR, Global.tapOffset + Global.tapHeight - Global.trouR, Global.trouR)
                 };
  Table table = new Table(0, 0, Global.tabWidth, Global.tabHeight, new Color(139,69,19), tapis, trous);

  public GameStart() {
    setSize(Global.tabWidth, Global.tabHeight);
    setLocation(50, 50);
    //JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  Image offScreenImage = null;
  public void update(Graphics g) {
    if (offScreenImage == null) offScreenImage = this.createImage(table.getWidth(), table.getHeight());
    Graphics gOffScreen = offScreenImage.getGraphics();
    // Dessiner la table
    gOffScreen.setColor(table.getColor());
    gOffScreen.fillRect(table.getX(), table.getY(), table.getWidth(), table.getHeight());
    // Dessiner le tapis
    gOffScreen.setColor(tapis.getColor());
    gOffScreen.fillRect(tapis.getX(), tapis.getY(), tapis.getWidth(), tapis.getHeight());
    // Dessiner les trous
    gOffScreen.setColor(trous[0].getColor());
    for (int i = 0 ; i < 6 ; i++) {
      gOffScreen.fillOval(trous[i].getX(), trous[i].getY(), trous[i].getWidth(), trous[i].getHeight());
    }
    
    paint(gOffScreen);
    g.drawImage(offScreenImage, table.getX(), table.getY(), null);
  }

  public void paint(Graphics g) {
    super.paint(g);

    //get mouse posotion
    //Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
    //System.out.println("Location:x=" + point.x + ", y=" + point.y);

    // Dessiner les billes
    g.setColor(bille1.getColor());
    g.fillOval(bille1.getX(), bille1.getY(), bille1.getWidth(), bille1.getHeight());
    g.setColor(bille2.getColor());
    g.fillOval(bille2.getX(), bille2.getY(), bille2.getWidth(), bille2.getHeight());
    g.setColor(bille3.getColor());
    g.fillOval(bille3.getX(), bille3.getY(), bille3.getWidth(), bille3.getHeight());




    bille1.rebondirBille(bille2);
    bille2.rebondirBille(bille3);
    bille1.rebondirBille(bille3);

    bille1.rebondirBordure();
    bille2.rebondirBordure();
    bille3.rebondirBordure();

    bille1.deplacer();
    bille2.deplacer();
    bille3.deplacer();

    repaint();

    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] argv) {
    GameStart game = new GameStart();
  }
}
