import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import java.awt.event.MouseAdapter;

import java.util.ArrayList;


public class GameStart extends Frame {
  // vitesse : 5, 10, 15, 20
  Bille bille1 = new Blanche(500, 250);
  Bille bille2 = new Basse(500, 150, new Color(255, 100, 255));
  Bille bille3 = new Haute(500, 50, new Color(255, 0, 255));
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
  Bande bande = new Bande(bille1);

  ArrayList<Bille> billes = new ArrayList<Bille>();

  Poche poche = new Poche(50, 580);

  int time;


  private GameStart() {

    

    billes.add(bille1);
    billes.add(bille2);
    billes.add(bille3);

    setSize(Global.tabWidth, Global.tabHeight + Global.plancheHeight);
    setLocation(50, 50);
    //JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    
		addMouseListener(new MouseAdapter() {
      long startTime;
      long endTime;
      long totalTime;
			@Override
			public void mousePressed(MouseEvent e1) {
        startTime = System.currentTimeMillis();
        //System.out.println(startTime);
        time = 0;
        //bande.frapper();
      }
      @Override
      public void mouseReleased(MouseEvent e2) {
        endTime  = System.currentTimeMillis();
        totalTime = endTime - startTime; 
        //System.out.println(startTime);
        if (totalTime > 0) {
          time = 1;
          /*gOffScreen.setColor(new Color(255, 99, 71));
          gOffScreen.fillRect(425 - 25 - 10 - 50, 520, 50, 10);*/
        }
        if (totalTime > 1000) {
          time = 2;
          /*gOffScreen.setColor(new Color(255,0,0));
          gOffScreen.fillRect(425 - 25, 520, 50, 10);*/
        }
        if (totalTime > 2000) {
          time = 3;
          /*gOffScreen.setColor(new Color(220,20,60));
          gOffScreen.fillRect(425 + 25 + 10, 520, 50, 10);*/
        }
        bande.frapper(time);
      }
		});

  }


  
  Image offScreenImage = null;
  public void update(Graphics g) {
    if (offScreenImage == null) offScreenImage = this.createImage(table.getWidth(), table.getHeight() + Global.plancheHeight);
    Graphics gOffScreen = offScreenImage.getGraphics();
    // Dessiner bkg
    gOffScreen.setColor(new Color(255, 255, 255));
    gOffScreen.fillRect(0, 0, table.getWidth(), table.getHeight() + Global.plancheHeight);
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

    // Dessiner time control
    gOffScreen.setColor(new Color(211, 211, 211));
    gOffScreen.fillRect(425 - 25 - 10 - 50, 520, 50, 10);
    gOffScreen.fillRect(425 - 25, 520, 50, 10);
    gOffScreen.fillRect(425 + 25 + 10, 520, 50, 10);


    // Dessiner les cercles dans la poche
    gOffScreen.setColor(new Color(211, 211, 211));
    for (int i = 0 ; i < 6 ; i++) {
      gOffScreen.fillOval(poche.getX() + i * (2 * Global.billeR + 10), poche.getY(), Global.billeR * 2, Global.billeR * 2);
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
    /* g.setColor(bille1.getColor());
    g.fillOval(bille1.getX(), bille1.getY(), bille1.getWidth(), bille1.getHeight());
    g.setColor(bille2.getColor());
    g.fillOval(bille2.getX(), bille2.getY(), bille2.getWidth(), bille2.getHeight());
    g.setColor(bille3.getColor());
    g.fillOval(bille3.getX(), bille3.getY(), bille3.getWidth(), bille3.getHeight());*/
    for (int i = 0 ; i < billes.size() ; i++) {
      g.setColor(billes.get(i).getColor());
      g.fillOval(billes.get(i).getX(), billes.get(i).getY(), billes.get(i).getWidth(), billes.get(i).getHeight());
      if (billes.get(i) instanceof Haute) {
        g.setColor(((Haute)billes.get(i)).getColorRayure());
        g.fillOval(((Haute)billes.get(i)).getX() + ((Haute)billes.get(i)).getWidthRayure() / 2, ((Haute)billes.get(i)).getY() + ((Haute)billes.get(i)).getWidthRayure() / 2, ((Haute)billes.get(i)).getWidthRayure(), ((Haute)billes.get(i)).getWidthRayure());
      }

      billes.get(i).rebondirBordure();
      billes.get(i).rebondirTrou(trous, poche);
      for (int j = 0 ; j < billes.size() ; j++) {
        billes.get(i).rebondirBille(billes.get(j));
      }

      billes.get(i).deplacer();
    }

    // Dessiner time control
    if (time >= 1) {
      g.setColor(new Color(255, 99, 71));
      g.fillRect(425 - 25 - 10 - 50, 520, 50, 10);
    }
    if (time >= 2) {
      g.setColor(new Color(255,0,0));
      g.fillRect(425 - 25, 520, 50, 10);
    }
    if (time >= 3) {
      g.setColor(new Color(220,20,60));
      g.fillRect(425 + 25 + 10, 520, 50, 10);
    }

    /*bille1.rebondirBille(bille2);
    bille2.rebondirBille(bille3);
    bille1.rebondirBille(bille3);

    bille1.rebondirBordure();
    bille2.rebondirBordure();
    bille3.rebondirBordure();

    bille1.deplacer();
    bille2.deplacer();
    bille3.deplacer();*/

    // Dessiner la bande
    if (Global.billesSontImmobiles(billes)) {
      bande.show();
      // Repaint time control
      g.setColor(new Color(211, 211, 211));
      g.fillRect(425 - 25 - 10 - 50, 520, 50, 10);
      g.fillRect(425 - 25, 520, 50, 10);
      g.fillRect(425 + 25 + 10, 520, 50, 10);
    } else {
      bande.hide();
    }

    if (bande.estVisible()) {
      g.setColor(bande.getColor());
      g.drawLine(bande.getX(), bande.getY(), bande.getSourisX(), bande.getSourisY());
      bande.viser();
    }

    repaint();

    try {
      Thread.sleep(20);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] argv) {
    GameStart game = new GameStart();
  }
}
