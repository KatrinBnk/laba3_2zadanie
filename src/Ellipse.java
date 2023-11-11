import javax.swing.*;
import java.awt.*;

public class Ellipse extends Circle {

    public  Ellipse(int x, int y, int r1, int r2, Color color) {
        super(x, y, r1, r2, color); //вызов родительского конструктора
        System.out.println("Объект Ellipse создан");
    }

    public  Ellipse(int x, int y, int r1, int r2) {
        super(x, y, r1, r2);
        System.out.println("Объект Ellipse создан");
    }

    public void Turn() {
        int a = r1, b = r2;
        if(a + super.getPointX() < interfaceWidth && b + super.getPointY() < interfaceHeight &&
                a - super.getPointX() > 0 && b - super.getPointY() > 0){
            r1 = b; r2 = b;
            System.out.println("Изменены радиусы овала: r1 = " + r1 + ", r2 = " + r2);
            System.out.println("Осуществлен поворот овала");
        }
        else{
            JOptionPane.showMessageDialog(null, "Невозможно повернуть. Выход за границы.");
            System.out.println("Невозможно повернуть. Выход за границы.");
        }
        this.repaint();
    }

    protected void chengeD(int dr1, int dr2) {
        int x = super.getPointX(); int y = super.getPointY();
        if (!(x - dr1 >= 0 && x + dr1 <= interfaceWidth && y - dr2 >= 0 && y + dr2 <= interfaceHeight)){
           dr1 = 10; dr2 = 5;
        }
        r1 = dr1; r2 = dr2;
        repaint();
        System.out.println("Изменен радиус овала: r = " + r1 + ", r = " + r2);
    }
}