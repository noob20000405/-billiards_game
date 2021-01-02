import javax.swing.*;
import java.awt.*;
public class Bille extends Cercle implements Mobile {
  protected double vitesse;
  protected static double vAcceleree = 10;
  protected double direction;

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

    if (vitesse > 0.1) {
      vAcceleree -= 1;
      if (vAcceleree == 0) {
        vitesse -= 1;
        vAcceleree = 10;
      }
    }
  }

  public void rebondirBordure() {
    if (x < Global.tapOffset || x > Global.tapOffset + Global.tapWidth - width) {
      direction = Global.pi - direction;
      deplacer();/////////////
      //System.out.println("change " + direction);//////////////
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
    double v1x = vitesse * Math.cos(direction);
    double v1y = vitesse * Math.sin(direction);
    double v2x = b.vitesse * Math.cos(b.direction);
    double v2y = b.vitesse * Math.sin(b.direction);


    if (Math.abs(d) < Global.epsilon + 2 * getR()) {
      System.out.println("v1x " + v1x);///////////////
      System.out.println("v1y " + v1y);
      System.out.println("v2x " + v2x);
      System.out.println("v2y " + v2y);
      //System.out.println("fk");///////////////////////////
      double tmpVx;
      double tmpVy;

      tmpVx = v1x;
      tmpVy = v1y;
      v1x = v2x;
      v1y = v2y;
      v2x = tmpVx;
      v2y = tmpVy;
      System.out.println("change");
      System.out.println("v1x " + v1x);///////////////
      System.out.println("v1y " + v1y);
      System.out.println("v2x " + v2x);
      System.out.println("v2y " + v2y);
      vitesse = Math.sqrt(v1x * v1x + v1y * v1y);
      b.vitesse = Math.sqrt(v2x * v2x + v2y * v2y);
      if (Math.abs(v1x) < 0.01) {
        System.out.println("<0.1");
        if (v1y > 0) {
          direction = Global.pi / 2;
        } else {
          direction = -Global.pi / 2;
        }
        //direction = -direction;
      } else {
        if (v1x < 0) {
          direction = Math.atan(v1y / v1x) + Global.pi;
        } else {
          direction = Math.atan(v1y / v1x);
        }
        
      } 
      if (Math.abs(v2x) < 0.01) {
        System.out.println("<0.1");
        if (v2y > 0) {
          b.direction = Global.pi / 2;
        } else {
          b.direction = -Global.pi / 2;
        }
        //b.direction = -b.direction;
      } else {
        if (v2x < 0) {
          b.direction = Math.atan(v2y / v2x) + Global.pi;
        } else {
          b.direction = Math.atan(v2y / v2x);
        }
      }
      System.out.println("direc " + direction);
      System.out.println("b.direc " + b.direction);
      System.out.println("=================");

      deplacer();
      b.deplacer();
    }
    
  }

  public void etreFrappee(double v, double d) {
    vitesse = v;
    direction = d;
  }

  public boolean estMobile() {
    if (Math.abs(vitesse) < 0.1) {
      return false;
    } else {
      return true;
    }
  }

  public void rebondirTrou(Trou[] trous, Poche poche) {
    for (int i = 0 ; i < trous.length ; i++) {
      double dis = Math.sqrt((trous[i].centreX - this.centreX) * (trous[i].centreX - this.centreX) +  (trous[i].centreY - this.centreY) * (trous[i].centreY - this.centreY));
      if (dis < 30) {
        this.vitesse = 0;
        poche.marquer(this);
      }
    }
  }
}
