import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

public class Global {
  public static final double pi = 3.14;

  /** 球的半径 */
  public static final int billeR = 10;

  /** 球洞的半径 */
  public static final int trouR = 20;

  /** 桌子的尺寸 */
  public static final int tabWidth = 850;
  public static final int tabHeight = 500;

  /** 桌布的尺寸和位置 */
  public static final int tapWidth = 750;
  public static final int tapHeight = 400;
  public static final int tapOffset = 50;

  public static final double epsilon = 0.1;

  /** 桌子下面那个白色区域的尺寸 */
  public static final int plancheHeight = 100;

  /** 球带的尺寸 就是白色区域里左右两边那几个圆 */
  public static final int pocheWidth = 240; // (billeR * 2 + 10) * 8
  public static final int pocheHeight = 20; // billeR * 2

  /** 判断桌上的所有球是不是都是静止的 */
  public static boolean billesSontImmobiles(ArrayList<Bille> billes) {
    for (int i = 0 ; i < billes.size() ; i++) {
      if (billes.get(i).estMobile()) return false;
    }
    return true;
  }
}
