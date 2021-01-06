import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class GameStart extends Frame {

  private static Table table = Table.getSingletonTable();

  /** Les deux joueurs */
  private static Joueur j1 = new Joueur(true);
  private static Joueur j2 = new Joueur(false);

  /** La force de la frappe */
  private static int force;

  /** Les billes */
  private static Bille billes[] = new Bille[16];

  public GameStart() {

    // Créer toutes les billes par une boucle et les positionner 
    billes[0] = Blanche.getSingletonBlanche(); // Bille blanche

    billes[1] = new Bille(0, 0, Global.billeR, new Color(255, 0, 0), 0, 0); // Billes pleines
    for (int i = 2 ; i <= 7 ; i++) {
      billes[i] = billes[1].clone();
    }

    billes[8] = new Noire(620, 250); // Bille noire

    billes[9] = new Bille(0, 0, Global.billeR, new Color(0, 0, 255), 0, 0); // Billes rayées
    for (int i = 10 ; i <= 15 ; i++) {
      billes[i] = billes[9].clone();
    }

    billes[1].setPos(700, 250);
    billes[2].setPos(580, 273);
    billes[3].setPos(580, 227);
    billes[4].setPos(660, 273);
    billes[5].setPos(660, 227);
    billes[6].setPos(660, 319);
    billes[7].setPos(660, 181);
    billes[9].setPos(700, 296);
    billes[10].setPos(700, 204);
    billes[11].setPos(700, 158);
    billes[12].setPos(700, 342);
    billes[13].setPos(620, 204);
    billes[14].setPos(540, 250);
    billes[15].setPos(620, 296);

    // Créer la fenêtre
    setSize(Global.tabWidth, Global.tabHeight + Global.plancheHeight);
    setLocation(50, 50);
    setVisible(true);

    // Ajouter MouseListener
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
        if (j1.estEnTour()) {
          j1.getQueue().frapper(force);
        } else if (j2.estEnTour()) {
          j2.getQueue().frapper(force);
        }
	      // Changer leur tour
        j1.changerTour();
        j2.changerTour();

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
  @Override
  public void update(Graphics g) {
    if (offScreenImage == null) offScreenImage = this.createImage(table.getWidth(), table.getHeight() + Global.plancheHeight);
    Graphics gOffScreen = offScreenImage.getGraphics();
    // On coloris le fond en blanc
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


    // Dessiner les cercles des poches qui seront les emplacements des billes apres avoir rentrees dans les trous
    gOffScreen.setColor(new Color(211, 211, 211));
    for (int i = 0 ; i < 8 ; i++) {
      gOffScreen.fillOval(j1.getPoche().getX() + i * (2 * Global.billeR + 10), j1.getPoche().getY(), Global.billeR * 2, Global.billeR * 2);
      gOffScreen.fillOval(j2.getPoche().getX() + i * (2 * Global.billeR + 10), j2.getPoche().getY(), Global.billeR * 2, Global.billeR * 2);
    }

    // Dessiner time control. Nous représentons la force (donc temps) qu'on soumet pour frapper une bille par les trois tirets en bas de la table
    gOffScreen.setColor(new Color(211, 211, 211));
    gOffScreen.fillRect(425 - 25 - 10 - 50, 520, 50, 10);
    gOffScreen.fillRect(425 - 25, 520, 50, 10);
    gOffScreen.fillRect(425 + 25 + 10, 520, 50, 10);

    paint(gOffScreen);
    g.drawImage(offScreenImage, table.getX(), table.getY(), null);
  }

  // Renouveler et tracer les objets sur table qui sont dynamiques
  @Override
  public void paint(Graphics g) {
    super.paint(g);

    // Dessiner les billes
    for (int i = 0 ; i < billes.length ; i++) {
      g.setColor(billes[i].getColor());
      g.fillOval(billes[i].getX(), billes[i].getY(), billes[i].getWidth(), billes[i].getHeight());
      //  Dessiner les rayures des billes rayees
      if (billes[i].estRayee()) {
        g.setColor(new Color(255, 255, 255));
        g.fillOval(billes[i].getX() + Global.billeR / 2, billes[i].getY() + Global.billeR / 2, Global.billeR, Global.billeR);
      }

      // Vérifier la collision avec les boredures
      billes[i].collisionBordure();

      // Vérifier la collision avec les trous
      billes[i].collisionTrou(table.getTrous(), j1, j2);

      // Vérifier la collision entre les billes
      for (int n = 0 ; n + i < billes.length ; n++) {
        billes[i].collisionBille(billes[i + n]);
      }

      // Déplacer
      billes[i].deplacer();
    }

    // Renouveler time control. Nous colorions les trois tirets selon la quantité de temps qu'on a restés appuyés sur le bouton de la souris, et représente donc aussi la quantité de force appliqué à la bille
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


    // Vérifier l'état de mouvement des billes, si toutes les billes sont immobiles, on mettre la queue visible, sinon on le cache
    if (Global.billesSontImmobiles(billes)) {
      if (j1.estEnTour()) {
        j1.getQueue().show();
      } else if (j2.estEnTour()) {
        j2.getQueue().show();
      }
      // Repaint time control. On ré-initialise les trois tirets en bas en couleur grise.
      g.setColor(new Color(211, 211, 211));
      g.fillRect(425 - 25 - 10 - 50, 520, 50, 10);
      g.fillRect(425 - 25, 520, 50, 10);
      g.fillRect(425 + 25 + 10, 520, 50, 10);
    } else {
      j1.getQueue().hide();
      j2.getQueue().hide();
    }

    // Dessiner les queues
    if (j1.getQueue().estVisible()) {
      g.setColor(j1.getQueue().getColor());
      g.drawLine(j1.getQueue().getX(), j1.getQueue().getY(), j1.getQueue().getSourisX(), j1.getQueue().getSourisY());
      j1.getQueue().viser();
    }
    if (j2.getQueue().estVisible()) {
      g.setColor(j2.getQueue().getColor());
      g.drawLine(j2.getQueue().getX(), j2.getQueue().getY(), j2.getQueue().getSourisX(), j2.getQueue().getSourisY());
      j2.getQueue().viser();
    }

    // Déterminer si l'un des deux joueurs a gagné
    if (j1.getPoche().getNbBilles() == 8) {
      j1.gagner();
      setVisible(false);
      System.out.println("Joueur " + j1.getId() + " gagne !");
    }
    if (j2.getPoche().getNbBilles() == 8) {
      j2.gagner();
      setVisible(false);
      System.out.println("Joueur " + j2.getId() + " gagne !");
    }

    if (j1.estGagneur() || j2.estGagneur()) System.exit(0);

    repaint();

    // Dans le but d'afficher et que l'image courant soit visible, faire une pause de 10ms
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
