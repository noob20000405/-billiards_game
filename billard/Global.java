import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

public class Global {
  public static final double pi = 3.14;

  public static final int billeR = 10;

  public static final int trouR = 20;

  public static final int tabWidth = 850;
  public static final int tabHeight = 500;

  public static final int tapWidth = 750;
  public static final int tapHeight = 400;
  public static final int tapOffset = 50;

  public static final double epsilon = 0.1;

  // Planche
  public static final int plancheHeight = 100;
  public static final int pocheWidth = 180; // (billeR * 2 + 10) * 6
  public static final int pocheHeight = 20; // billeR * 2

  public static boolean billesSontImmobiles(ArrayList<Bille> billes) {
    for (int i = 0 ; i < billes.size() ; i++) {
      if (billes.get(i).estMobile()) return false;
    }
    return true;
  }
}