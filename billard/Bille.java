import javax.swing.*;
import java.awt.*;
public abstract class Bille extends Cercle implements Mobile {
  /** 运动速度 */
  protected int vitesse;
  /** 加速度 */
  protected double vAcceleree = 10;
  /** 运动方向 */
  protected double direction;
  protected boolean surTable;

  public Bille(int x, int y, int r, Color color, int v, double d) {
    super(x, y, r, color);
    this.vitesse = v;
    this.direction = d;
    this.vAcceleree = 10;
    this.surTable = true;
  }

  public void setPos(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /** 按照方向和速度移动 */
  public void deplacer() {

    x += vitesse * Math.cos(direction);
    y += vitesse * Math.sin(direction);
    centreX = getX() + getR();
    centreY = getY() + getR();


    if (vitesse > 0) { // 每deplacer十次 球的速度减少1
      vAcceleree -= 1;
      if (vAcceleree == 0) {
        vitesse -= 1;
        vAcceleree = 10;
      }
    }

    /*if (vitesse < 1) {
      vitesse = 0;
    }*/
  }

  /** 碰撞桌子边缘反弹 pb1号*/
  public void collisionBordure() {
    int suivantX = x + (int)(vitesse * Math.cos(direction));
    int suivantY = y + (int)(vitesse * Math.sin(direction));

    if (suivantX <= Global.tapOffset || suivantX >= Global.tapOffset + Global.tapWidth - width) {
      direction = Global.pi - direction;
    }
    if (suivantY <= Global.tapOffset || suivantY >= Global.tapOffset + Global.tapHeight - height) {
      direction = -direction;
    }

    if (surTable) {
      if (x < Global.tapOffset) x = Global.tapOffset;
      if (x > Global.tapOffset + Global.tapWidth - width) x = Global.tapOffset + Global.tapWidth - width;
      if (y < Global.tapOffset) y = Global.tapOffset;
      if (y > Global.tapOffset + Global.tapHeight - height) y = Global.tapOffset + Global.tapHeight - height;
    }
  }

  /** 碰撞另一个球反弹 pb2 TT*/
  public void collisionBille(Bille b) {
    if (this != b) {

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

        int tmpV;
        double tmpD;

        tmpV = vitesse;
        tmpD = direction;
        vitesse = b.vitesse;
        direction = b.direction;
        b.vitesse = tmpV;
        b.direction = tmpD;

        deplacer();
        b.deplacer();
      }

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

  /** 球被球竿击打 */
  public void etreFrappee(int v, double d) {
    vitesse = v;
    direction = d;
  }

  /** 判断球是否在移动 */
  public boolean estMobile() {
    if (vitesse == 0) { // 因为vitesse是double 所以条件不是 == 0
      return false;
    } else {
      return true;
    }
  }

  public abstract void afficherColor();

}
