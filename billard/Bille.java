import javax.swing.*;
import java.awt.*;
public class Bille extends Cercle implements Mobile {
  /** 运动速度 */
  protected double vitesse;
  /** 加速度 */
  protected double vAcceleree = 10;
  /** 运动方向 */
  protected double direction;

  public Bille(int x, int y, int r, Color color, double v, double d) {
    super(x, y, r, color);
    this.vitesse = v;
    this.direction = d;
    this.vAcceleree = 10;
  }

  /** 按照方向和速度移动 */
  public void deplacer() {
    x += vitesse * Math.cos(direction);
    y += vitesse * Math.sin(direction);
    centreX = getX() + getR();
    centreY = getY() + getR();

    if (vitesse > 0.1) { // 每deplacer十次 球的速度减少1
      vAcceleree -= 1;
      if (vAcceleree == 0) {
        vitesse -= 1;
        vAcceleree = 10;
      }
    }
  }

  /** 碰撞桌子边缘反弹 bug1号*/
  public void collisionBordure() {
    int suivantX = x + (int)(vitesse * Math.cos(direction));
    int suivantY = y + (int)(vitesse * Math.sin(direction));

    if (suivantX <= Global.tapOffset || suivantX >= Global.tapOffset + Global.tapWidth - width) {
      direction = Global.pi - direction;
    }
    if (suivantY <= Global.tapOffset || suivantY >= Global.tapOffset + Global.tapHeight - height) {
      direction = -direction;
    }
  }

  /** 碰撞另一个球反弹 bug2号 这个函数可以不用看 我感觉要大改 TT*/
  public void collisionBille(Bille b) {
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
      double tmpVx;
      double tmpVy;

      tmpVx = v1x;
      tmpVy = v1y;
      v1x = v2x;
      v1y = v2y;
      v2x = tmpVx;
      v2y = tmpVy;
      vitesse = Math.sqrt(v1x * v1x + v1y * v1y);
      b.vitesse = Math.sqrt(v2x * v2x + v2y * v2y);
      if (Math.abs(v1x) < 0.01) {
        if (v1y > 0) {
          direction = Global.pi / 2;
        } else {
          direction = -Global.pi / 2;
        }
      } else {
        if (v1x < 0) {
          direction = Math.atan(v1y / v1x) + Global.pi;
        } else {
          direction = Math.atan(v1y / v1x);
        }
        
      } 
      if (Math.abs(v2x) < 0.01) {
        if (v2y > 0) {
          b.direction = Global.pi / 2;
        } else {
          b.direction = -Global.pi / 2;
        }
      } else {
        if (v2x < 0) {
          b.direction = Math.atan(v2y / v2x) + Global.pi;
        } else {
          b.direction = Math.atan(v2y / v2x);
        }
      }

      deplacer();
      b.deplacer();
    }
    
  }

  /** 球被球竿击打 */
  public void etreFrappee(double v, double d) {
    vitesse = v;
    direction = d;
  }

  /** 判断球是否在移动 */
  public boolean estMobile() {
    if (Math.abs(vitesse) < 0.1) { // 因为vitesse是double 所以条件不是 == 0
      return false;
    } else {
      return true;
    }
  }

  /** 判断球是否碰到球洞 */
  public void collisionTrou(Trou[] trous, Joueur j, Joueur j1) {
    for (int i = 0 ; i < trous.length ; i++) {
      double dis = Math.sqrt((trous[i].centreX - this.centreX) * (trous[i].centreX - this.centreX) +  (trous[i].centreY - this.centreY) * (trous[i].centreY - this.centreY));

      if (dis < 30) {
        this.vitesse = 0;
        if (this instanceof Basse) { // 如果进洞的是basse球 j玩家得分
          j.getPoche().marquer(this);
        } else { // 如果是haute球 j1玩家得分
          j1.getPoche().marquer(this);
        }
	
	// 在gamestart里每次frapper之后会交换击球 这里因为得分了所以再换一次 就相当于换回来了（得分玩家继续击球）
        j.changerTour();
        j1.changerTour();
        
      }
    }
  }
}
