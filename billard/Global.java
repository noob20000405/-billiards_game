import java.awt.*;

import java.util.ArrayList;

public class Global {
  public static final double pi = 3.14;
  public static final double epsilon = 0.1;

  /** Rayon de la bille */
  public static final int billeR = 10;

  /** Rayon du trou */
  public static final int trouR = 20;

  /** Taille de la table */
  public static final int tabWidth = 850;
  public static final int tabHeight = 500;

  /** Taille et position du tapis */
  public static final int tapWidth = 750;
  public static final int tapHeight = 400;
  public static final int tapOffset = 50;

  /** Taille de la zone blanche en-dessous de la table */
  public static final int plancheHeight = 100;

  /** Taille de la poche */
  public static final int pocheWidth = 240; // (billeR * 2 + 10) * 8
  public static final int pocheHeight = 20; // billeR * 2

  /** Constantes concernantes la vitesse */
  public static final int uniteVitesse = 5;
  public static final int perteVitesseCollision = 1;

  /** Déterminer si toutes les billes sur table sont immobiles */
  public static boolean billesSontImmobiles(Bille[] billes) {
    for (int i = 0 ; i < billes.length ; i++) {
      if (billes[i].estMobile()) return false;
    }
    return true;
  }

  /** Vérifier les paramètres du programme */
  public static void verifierParams(int nb) throws ErreurParamsException {
    if (nb != 1) throw new ErreurParamsException("Nombre du parametre incorrect");
    else return ;
  }
}
