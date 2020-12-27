import javax.swing.*;
import java.awt.*;
public class Bille extends Cercle implements Mobile {
  private double vitesse;
  private double direction;

  public Bille(int x, int y, int r, Color color, double v, double d) {
    super(x, y, r, color);
    this.vitesse = v; //test
    this.direction = d; // test data
  }

  public void deplacer() {
    x += vitesse * Math.cos(direction);
    y += vitesse * Math.sin(direction);
    centreX = getX() + getR();
    centreY = getY() + getR();
  }

  public void rebondirBordure() {
    if (x < Global.tapOffset || x > Global.tapOffset + Global.tapWidth - width) {
      direction = Global.pi - direction;
      deplacer();/////////////
      System.out.println("change " + direction);//////////////
    }
    if (y < Global.tapOffset || y > Global.tapOffset + Global.tapHeight - height) {
      direction = -direction;
      deplacer();/////////////////
      //System.out.println("change");//////////////
    }

    /*while(x < Global.tapOffset || x > Global.tapOffset + Global.tapWidth - width || y < Global.tapOffset || y > Global.tapOffset + Global.tapHeight - height) {
      deplacer();
    }*/
  }

  public void rebondirBille(Bille b) {
    //System.out.println("begin");//////////////
    int x1 = super.centreX;
    int x2 = b.centreX;
    int y1 = super.centreY;
    int y2 = b.centreY;
    double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    //System.out.println(d);////////////////////

    if (Math.abs(d) < Global.epsilon + 2 * getR()) {
      //System.out.println("fk");///////////////////////////
      if (x1 != x2) {
        double angle = Math.atan((y1 - y2) / (x1 - x2));
        double vitesseB1X = Math.cos(b.direction - angle) * b.vitesse;
        double vitesseB2X = Math.cos(direction - angle) * vitesse;
        double vitesseB1Y = Math.sin(direction - angle) * vitesse;
        double vitesseB2Y = Math.sin(b.direction - angle) * b.vitesse;
        System.out.println("vB1X :" + vitesseB1X);
        System.out.println("vB1Y :" + vitesseB1Y);
        System.out.println("vB2X :" + vitesseB2X);
        System.out.println("vB2Y :" + vitesseB2Y);//////////

        if (Math.abs(vitesseB1X) < Global.epsilon) {
          direction = Global.pi / 2 + angle;
        } else {
          direction = Math.atan(vitesseB1Y / vitesseB1X) + angle + 3.14;////
        }
        if (Math.abs(vitesseB2X) < Global.epsilon) {
          b.direction = Global.pi / 2 + angle;
        } else {
          b.direction = Math.atan(vitesseB2Y / vitesseB2X) + angle;
        }
        System.out.println("d1 " + direction + ", vB1X " + vitesseB1X);/////////////
        System.out.println("d2 " + b.direction);

        vitesse = (Math.sqrt(vitesseB1X * vitesseB1X + vitesseB1Y * vitesseB1Y));
        b.vitesse = (Math.sqrt(vitesseB2X * vitesseB2X + vitesseB2Y * vitesseB2Y));
        System.out.println("v1 " + vitesse);/////////////
        System.out.println("v2 " + b.vitesse);
      } else {
        double tmpV = vitesse;
        double tmpD = direction;

        vitesse = b.vitesse;
        direction = b.direction;
        b.vitesse = tmpV;
        b.direction = tmpD;
      }
    }
  }
}
