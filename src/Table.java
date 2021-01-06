import java.awt.*;

public class Table extends Materiel {
  /** Créer le tapis, les trous sur la table */
  private static Tapis tapis = Tapis.getSingletonTapis();
  private static Trou[] trous = {
    new Trou(Global.tapOffset - Global.trouR, Global.tapOffset - Global.trouR, Global.trouR), 
    new Trou(Global.tapOffset - Global.trouR, Global.tapOffset + Global.tapHeight - Global.trouR, Global.trouR), 
    new Trou(Global.tabWidth / 2 - Global.trouR, Global.tapOffset - Global.trouR, Global.trouR), 
    new Trou(Global.tabWidth / 2 - Global.trouR, Global.tapOffset + Global.tapHeight - Global.trouR, Global.trouR), 
    new Trou(Global.tapOffset + Global.tapWidth - Global.trouR, Global.tapOffset - Global.trouR, Global.trouR), 
    new Trou(Global.tapOffset + Global.tapWidth - Global.trouR, Global.tapOffset + Global.tapHeight - Global.trouR, Global.trouR)
  };
  /** Créer le singleton */
  private static Table singletonTable = new Table(0, 0, Global.tabWidth, Global.tabHeight, new Color(139,69,19));


  private Table(int x, int y, int w, int h, Color color) {
    super(x, y, w, h, color);
  }

  public Tapis getTapis() {
    return tapis;
  }

  public Trou[] getTrous() {
    return trous;
  }

  public static Table getSingletonTable() {
    return singletonTable;
  }

  public void afficher() {
    System.out.println("Table");
  }
}
