import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Jpanel extends JPanel{
int x=40,y=40;
Jpanel(){
JFrame frame = new JFrame();
frame.setSize( 800, 600);
frame.setLayout(null);
this.setBounds(0, 0, 800, 700);
frame.setLocationRelativeTo(null);
frame.setVisible(true);
this.setBackground(Color.GREEN);
frame.add(this);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public static void main(String[] args) {
new Jpanel();
}
public void paint(Graphics g){
super.paint(g);
g.setColor(Color.red);
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