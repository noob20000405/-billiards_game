import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import java.awt.event.MouseAdapter;

import java.util.ArrayList;


public class GameStart extends Frame {
  
  // 一坨instances
  // 球们
  Bille bille0 = new Blanche(250, 250);
  Bille bille1 = new Basse(500, 150, new Color(255, 0, 0));
  Bille bille2 = new Basse(500, 200, new Color(255, 0, 0));
  Bille bille3 = new Basse(500, 250, new Color(255, 0, 0));
  Bille bille4 = new Basse(500, 300, new Color(255, 0, 0));
  Bille bille5 = new Basse(550, 150, new Color(255, 0, 0));
  Bille bille6 = new Basse(550, 200, new Color(255, 0, 0));
  Bille bille7 = new Basse(550, 250, new Color(255, 0, 0));
  Bille bille8 = new Haute(550, 300, new Color(0, 0, 255));
  Bille bille9 = new Haute(600, 150, new Color(0, 0, 255));
  Bille bille10 = new Haute(600, 200, new Color(0, 0, 255));
  Bille bille11 = new Haute(600, 250, new Color(0, 0, 255));
  Bille bille12 = new Haute(600, 300, new Color(0, 0, 255));
  Bille bille13 = new Haute(650, 150, new Color(0, 0, 255));
  Bille bille14 = new Haute(650, 200, new Color(0, 0, 255));
  Bille bille15 = new Noir(650, 250);
  // 保存球的list
  ArrayList<Bille> billes = new ArrayList<Bille>();

  // 桌子 桌布 洞们
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

  // 球竿 球带 玩家各两个 玩家1（j）的球竿是白色的 目标球是basse球 玩家2（j1）黑色 haute球
  Bande bande = new Bande(bille0, new Color(255, 255, 255));
  Bande bande1 = new Bande(bille0, new Color(0, 0, 0));
  Poche poche = new Poche(50, 580);
  Poche poche1 = new Poche(Global.tabWidth - 50 - Global.pocheWidth, 580);
  Joueur j = new Joueur(bande, poche, true);
  Joueur j1 = new Joueur(bande1, poche1, false);

  // 击球的力度
  int force;


  private GameStart() {

    // 把球加到list里
    billes.add(bille0);
    billes.add(bille1);
    billes.add(bille2);
    billes.add(bille3);
    billes.add(bille4);
    billes.add(bille5);
    billes.add(bille6);
    billes.add(bille7);
    billes.add(bille8);
    billes.add(bille9);
    billes.add(bille10);
    billes.add(bille11);
    billes.add(bille12);
    billes.add(bille13);
    billes.add(bille14);
    billes.add(bille15);

    // 设置窗口属性
    setSize(Global.tabWidth, Global.tabHeight + Global.plancheHeight);
    setLocation(50, 50);
    //JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    
    // 添加鼠标操作
    addMouseListener(new MouseAdapter() {
      long startTime;
      long endTime;
      long totalTime;

      // 一旦按下鼠标 开始计时
      @Override
      public void mousePressed(MouseEvent e1) {
        startTime = System.currentTimeMillis();
        force = 0;
      }
      // 松开鼠标 计时结束 时间按得越久 击球力度越大
      @Override
      public void mouseReleased(MouseEvent e2) {
        endTime  = System.currentTimeMillis();
        totalTime = endTime - startTime; 
        if (totalTime > 0) {
          force = 1;
        }
        if (totalTime > 500) {
          force = 2;
        }
        if (totalTime > 1000) {
          force = 3;
        }

        // 击球 
        if (j.estEnTour()) {
          bande.frapper(force);
        } else if (j1.estEnTour()) {
          bande1.frapper(force);
        }
	// 击球后交换击球
        j.changerTour();
        j1.changerTour();
        
      }
    });

  }


  
  Image offScreenImage = null;
  // 更新窗口背景 这个函数会被自动调用
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


    // Dessiner les cercles dans la poche 就是左右下角的那几个球
    gOffScreen.setColor(new Color(211, 211, 211));
    for (int i = 0 ; i < 8 ; i++) {
      gOffScreen.fillOval(poche.getX() + i * (2 * Global.billeR + 10), poche.getY(), Global.billeR * 2, Global.billeR * 2);
      gOffScreen.fillOval(poche1.getX() + Global.pocheWidth - i * (2 * Global.billeR + 10), poche1.getY(), Global.billeR * 2, Global.billeR * 2);
    }
    
    // Dessiner time control 我不知道这个应该叫什么 就是桌子下面那三个长方形的条条
      gOffScreen.setColor(new Color(211, 211, 211));
      gOffScreen.fillRect(425 - 25 - 10 - 50, 520, 50, 10);
      gOffScreen.fillRect(425 - 25, 520, 50, 10);
      gOffScreen.fillRect(425 + 25 + 10, 520, 50, 10);

    paint(gOffScreen);
    g.drawImage(offScreenImage, table.getX(), table.getY(), null);
  }

  // 更新那些动态的物体 跟上面那个update一样也会被自动调用
  public void paint(Graphics g) {
    super.paint(g);

    // 画球
    for (int i = 0 ; i < billes.size() ; i++) {
      g.setColor(billes.get(i).getColor());
      g.fillOval(billes.get(i).getX(), billes.get(i).getY(), billes.get(i).getWidth(), billes.get(i).getHeight());
      //  如果是haute球再画花纹
      if (billes.get(i) instanceof Haute) {
        g.setColor(((Haute)billes.get(i)).getColorRayure());
        g.fillOval(((Haute)billes.get(i)).getX() + ((Haute)billes.get(i)).getWidthRayure() / 2, ((Haute)billes.get(i)).getY() + ((Haute)billes.get(i)).getWidthRayure() / 2, ((Haute)billes.get(i)).getWidthRayure(), ((Haute)billes.get(i)).getWidthRayure());
      }

      // 检测球和边框或者球洞的碰撞
      billes.get(i).collisionBordure();
      billes.get(i).collisionTrou(trous, j, j1);

      // 检测球之间的碰撞
      for (int j = 0 ; j < billes.size() ; j++) {
        billes.get(i).collisionBille(billes.get(j));
      }
      
      // 球移动 
      billes.get(i).deplacer();
    }

    // Dessiner time control 更新这个东西 ->（我不知道这个应该叫什么 就是桌子下面那三个长方形的条条）根据按鼠标的时间画成红色的
    if (force >= 1) {
      g.setColor(new Color(255, 99, 71));
      g.fillRect(425 - 25 - 10 - 50, 520, 50, 10);
    }
    if (force >= 2) {
      g.setColor(new Color(255,0,0));
      g.fillRect(425 - 25, 520, 50, 10);
    }
    if (force >= 3) {
      g.setColor(new Color(220,20,60));
      g.fillRect(425 + 25 + 10, 520, 50, 10);
    }


    // Dessiner la bande 如果所有的球都静止 显示球竿 否则隐藏球竿
    if (Global.billesSontImmobiles(billes)) {
      if (j.estEnTour()) {
        bande.show();
      } else if (j1.estEnTour()) {
        bande1.show();
      }
      
      // Repaint time control 再把这个东西重新画成灰色的
      g.setColor(new Color(211, 211, 211));
      g.fillRect(425 - 25 - 10 - 50, 520, 50, 10);
      g.fillRect(425 - 25, 520, 50, 10);
      g.fillRect(425 + 25 + 10, 520, 50, 10);
    } else {
      bande.hide();
      bande1.hide();
    }

    // 画出球竿
    if (bande.estVisible()) {
      g.setColor(bande.getColor());
      g.drawLine(bande.getX(), bande.getY(), bande.getSourisX(), bande.getSourisY());
      bande.viser();
    }
    if (bande1.estVisible()) {
      g.setColor(bande1.getColor());
      g.drawLine(bande1.getX(), bande1.getY(), bande1.getSourisX(), bande1.getSourisY());
      bande1.viser();
    }

    // 检测有无玩家获胜
    if (j.getPoche().getNbBilles() == 8) {
      j.gagner();
      setVisible(false);
      System.out.println("Joueur " + j.getId() + " gagne !");
    }
    if (j1.getPoche().getNbBilles() == 8) {
      j1.gagner();
      setVisible(false);
      System.out.println("Joueur " + j1.getId() + " gagne !");
    }

    repaint();

    // 每次重新画前停顿20毫秒 
    try {
      Thread.sleep(20);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  // main 他会循环自动调用update 和 paint函数重画 形成动画效果
  public static void main(String[] argv) {
    GameStart game = new GameStart();
  }
}
