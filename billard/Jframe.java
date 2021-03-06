import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;


public class Jframe extends Frame{
  int x = 40,y=50;
  Jframe(){
    this.setSize(800,700);
    this.setLocationRelativeTo(null);
    this.setBackground(Color.GREEN);
    this.setVisible(true);
    // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public static void main(String[] args) {
    new Jframe();
  }
  Image offScreenImage = null;
  public void update(Graphics g) { //双缓冲
    if(offScreenImage == null) {
      offScreenImage = this.createImage(800, 600);
    }
    Graphics gOffScreen = offScreenImage.getGraphics();
    Color c = gOffScreen.getColor();
    gOffScreen.setColor(Color.GREEN);
    gOffScreen.fillRect(0, 0, 800, 600);
    gOffScreen.setColor(c);
    paint(gOffScreen);
    g.drawImage(offScreenImage, 0, 0, null);
  }
  public void paint(Graphics g){
    super.paint(g);
    g.setColor(Color.RED);
    g.fillOval(x, y, 20, 20);
    y++;
    repaint();
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
    // TODO 自动生成的 catch 块
    e.printStackTrace();
   }
  }
}