import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class GameStart extends Frame {
  
  private static Table table = Table.getSingletonTable();

  /** Les deux joueurs */
  private static Joueur j = new Joueur(true);
  private static Joueur j1 = new Joueur(false);

  /** La force de la frappe */
  private static int force;

  /** Les billes */
  private static Bille billes[] = new Bille[16];
  private static Blanche blanche = Blanche.getSingletonBlanche();
  private static Noir noir = new Noir(540, 250);
  private static Basse basse = new Basse(0, 0);
  private static Haute haute = new Haute(0, 0);


  public GameStart() {

    // Créer toutes les billes et les positionner par une boucle
    billes[0] = blanche;
    billes[1] = noir;
    for (int i = 2 ; i < 9 ; i++) {
      billes[i] = basse.clone();
    }
    for (int i = 9 ; i < 16 ; i++) {
      billes[i] = haute.clone();
    }
  
    billes[2].setPos(580, 273);
    billes[3].setPos(580, 227);
    billes[4].setPos(660, 273);
    billes[5].setPos(660, 227);
    billes[6].setPos(660, 319);
    billes[7].setPos(660, 181);
    billes[8].setPos(700, 250);
    billes[9].setPos(700, 296);
    billes[10].setPos(700, 204);
    billes[11].setPos(700, 158);
    billes[12].setPos(700, 342);
    billes[13].setPos(620, 204);
    billes[14].setPos(620, 296);
    billes[15].setPos(620, 250);

    // Créer la fenêtre
    setSize(Global.tabWidth, Global.tabHeight + Global.plancheHeight);
    setLocation(50, 50);
    setVisible(true);
    
    // Ajouter MouseLisener
    addMouseListener(new MouseAdapter() {
      long startTime;
      long endTime;
      long totalTime;

      // On commence à chronométrer dès qu’on appuie sur le bouton gauche de la souris 
      @Override
      public void mousePressed(MouseEvent e1) {
        startTime = System.currentTimeMillis();
        force = 0;
      }
      // Des qu’on relâche le bouton gauche de la souris, le chronomètre s’arrête. Ensuite on détermine que d’autant le temps d’appuie de la souris est long, d’autant la force qu’on effectue sur la balle est grande. 
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

        // Frappe
        if (j.estEnTour()) {
          j.getBande().frapper(force);
        } else if (j1.estEnTour()) {
          j1.getBande().frapper(force);
        }
	      // Changer leur tour
        j.changerTour();
        j1.changerTour();
        
      }
    });

    // Ajouter WindowListener
    addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){ // Si on clique le bouton fermé, le jeu s'arrête
        System.exit(0);
    }});

  }


  private Image offScreenImage = null;
  // Renouveler et tracer le background image qui est statique
  public void update(Graphics g) {
    if (offScreenImage == null) offScreenImage = this.createImage(table.getWidth(), table.getHeight() + Global.plancheHeight);
    Graphics gOffScreen = offScreenImage.getGraphics();
    // 背景底色涂白 实在不会写了
    gOffScreen.setColor(new Color(255, 255, 255));
    gOffScreen.fillRect(0, 0, table.getWidth(), table.getHeight() + Global.plancheHeight);
    // Dessiner la table
    gOffScreen.setColor(table.getColor());
    gOffScreen.fillRect(table.getX(), table.getY(), table.getWidth(), table.getHeight());
    // Dessiner le tapis
    gOffScreen.setColor(table.getTapis().getColor());
    gOffScreen.fillRect(table.getTapis().getX(), table.getTapis().getY(), table.getTapis().getWidth(), table.getTapis().getHeight());
    // Dessiner les trous
    gOffScreen.setColor(table.getTrous()[0].getColor());
    for (int i = 0 ; i < 6 ; i++) {
      gOffScreen.fillOval(table.getTrous()[i].getX(), table.getTrous()[i].getY(), table.getTrous()[i].getWidth(), table.getTrous()[i].getHeight());
    }


    // Dessiner les cercles dans la poche 就是左右下角的那几个球的形状
    gOffScreen.setColor(new Color(211, 211, 211));
    for (int i = 0 ; i < 8 ; i++) {
      gOffScreen.fillOval(j.getPoche().getX() + i * (2 * Global.billeR + 10), j.getPoche().getY(), Global.billeR * 2, Global.billeR * 2);
      gOffScreen.fillOval(j1.getPoche().getX() + i * (2 * Global.billeR + 10), j1.getPoche().getY(), Global.billeR * 2, Global.billeR * 2);
    }
    
    // Dessiner time control 我不知道这个应该叫什么 就是桌子下面那三个长方形的条条
      gOffScreen.setColor(new Color(211, 211, 211));
      gOffScreen.fillRect(425 - 25 - 10 - 50, 520, 50, 10);
      gOffScreen.fillRect(425 - 25, 520, 50, 10);
      gOffScreen.fillRect(425 + 25 + 10, 520, 50, 10);

    paint(gOffScreen);
    g.drawImage(offScreenImage, table.getX(), table.getY(), null);
  }

  // Renouveler et tracer les objets sur table qui sont dynamique
  public void paint(Graphics g) {
    super.paint(g);

    // Dessiner les billes
    for (int i = 0 ; i < billes.length ; i++) {
      g.setColor(billes[i].getColor());
      g.fillOval(billes[i].getX(), billes[i].getY(), billes[i].getWidth(), billes[i].getHeight());
      //  Dessiner les rayures des billes hautes
      if (billes[i] instanceof Haute) {
        g.setColor(((Haute)billes[i]).getColorRayure());
        g.fillOval(((Haute)billes[i]).getX() + ((Haute)billes[i]).getWidthRayure() / 2, ((Haute)billes[i]).getY() + ((Haute)billes[i]).getWidthRayure() / 2, ((Haute)billes[i]).getWidthRayure(), ((Haute)billes[i]).getWidthRayure());
      }

      // Vérifier la collision avec les boredure
      billes[i].collisionBordure();

      // Vérifier la collision avec les trous
      billes[i].collisionTrou(table.getTrous(), j, j1);

      // Vérifier la collision entre les billes
      for (int n = 0 ; n + i < billes.length ; n++) {
        billes[i].collisionBille(billes[i + n]);
      }
      
      // Déplacer
      billes[i].deplacer();
    }

    // Renouveler time control 更新这个东西加上色 ->（我不知道这个应该叫什么 就是桌子下面那三个长方形的条条）根据按鼠标的时间画成红色的
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


    // Vérifier les billes, si toutes les billes sont immobiles, on mettre la bande visible, sinon on le cacher
    if (Global.billesSontImmobiles(billes)) {
      if (j.estEnTour()) {
        j.getBande().show();
      } else if (j1.estEnTour()) {
        j1.getBande().show();
      }
      // Repaint time control 再把这个东西重新画成灰色的
      g.setColor(new Color(211, 211, 211));
      g.fillRect(425 - 25 - 10 - 50, 520, 50, 10);
      g.fillRect(425 - 25, 520, 50, 10);
      g.fillRect(425 + 25 + 10, 520, 50, 10);
    } else {
      j.getBande().hide();
      j1.getBande().hide();
    }

    // Dessiner les bandes
    if (j.getBande().estVisible()) {
      g.setColor(j.getBande().getColor());
      g.drawLine(j.getBande().getX(), j.getBande().getY(), j.getBande().getSourisX(), j.getBande().getSourisY());
      j.getBande().viser();
    }
    if (j1.getBande().estVisible()) {
      g.setColor(j1.getBande().getColor());
      g.drawLine(j1.getBande().getX(), j1.getBande().getY(), j1.getBande().getSourisX(), j1.getBande().getSourisY());
      j1.getBande().viser();
    }

    // Déterminer si l'un des deux joueurs est gagné
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

    if (j.estGagneur() || j1.estGagneur()) System.exit(0);

    repaint();

    // 每次重新画前停顿10毫秒 
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
