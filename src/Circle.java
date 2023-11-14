import java.awt.*;

public class Circle extends TFigure {
    protected Color color; //цвет фигуры

    public Circle (int x, int y, int r) {
        super(x,y);
        if (x <= 10 || y <= 10){
            super.setPointXY(x + 100,y + 100);
            r1 = r2 = 20; x += 100; y +=100;
        } else if (y - r <= 0 || x - r <= 0 || x + r >= interfaceWidth || y + r >= interfaceHeight ){
            r1 = r2 = 10;
        } else {
            r1 = r2 = r;
        }
        x1 = x; y1 = y;
        this.color = Color.BLUE;
        System.out.println("Создана окружность: x = " + x + ", y = " + y + ", r = " + r1);
        this.tagFigure = 1;
    }

    public Circle (int x, int y, int r1, int r2) {
        super(x,y);
        if(x <= 20 || y <=10){
            super.setPointXY(x + 100,y + 100);
            r1 = 10; r2 = 5;
        } else if (x + r1 <= 0 || x - r1 <= 0 || x + r1 >= interfaceWidth || x - r1 >= interfaceWidth ||
                y + r2 <= 0 || y - r2 <= 0 || y + r2 >= interfaceWidth || y - r2 >= interfaceWidth ){
            this.r1 = 10;
            this.r2 = 5;
        }else{
            this.r1 = r1;
            this.r2 = r2;
        }
        this.color = Color.BLACK;
        this.tagFigure = 1;
        System.out.println("Создан овал: x = " + x + ", y = " + y + ", радиусы: r1 = " + r1 + ", r2 = " + r2);
    }

    public Circle (int x, int y, int r, Color color) {
        super(x,y);
        if (x <= 10 || y <= 10){
            super.setPointXY(100,100);
            r1 = r2 = 20; x = y = 100;
        } else if (y - r <= 0 || x - r <= 0 || x + r >= interfaceWidth || y + r >= interfaceHeight ){
            r1 = r2 = 10;
        } else {
            r1 = r2 = r;
        }
        this.color = color;
        this.tagFigure = 1;
        System.out.println("Создана окружность: x = " + x + ", y = " + y + ", r = " + r1);
    }

    public Circle (int x, int y, int r1, int r2, Color color) {
        super(x,y);
        if(x <= 20 || y <=10){
            super.setPointXY(x + 100,y + 100);
            r1 = 10; r2 = 5;
        } else if (x + r1 <= 0 || x - r1 <= 0 || x + r1 >= interfaceWidth || x - r1 >= interfaceWidth ||
                y + r2 <= 0 || y - r2 <= 0 || y + r2 >= interfaceWidth || y - r2 >= interfaceWidth ){
            this.r1 = 10;
            this.r2 = 5;
        }else{
            this.r1 = r1;
            this.r2 = r2;
        }
        this.color = color;
        this.tagFigure = 1;
        System.out.println("Создан овал: x = " + x + ", y = " + y + ", радиусы: r1 = " + r1 + ", r2 = " + r2);
    }

    //изменение диаметра ТОЛЬКО для окружности
    protected void chengeR(int R1, int R2, boolean isCircle) {
        if (super.getPointX() - R1 >= 0 && super.getPointX() + R1 <= interfaceWidth && super.getPointY() - R2 >= 0 && super.getPointY() + R2 <= interfaceHeight){
            if (isCircle){ r1 = r2 = R1; }
            else{ r1 = R1; r2 = R2; }
        } else {
            if (isCircle){ r1 = r2 = 5; }
            else{ r1 = 5; r2 = 7; }
        }
        repaint();
        System.out.println("Изменен радиус окружности: r = " + r1);
    }

    public void Show(boolean VISION) {
        this.VISION = VISION;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (VISION) {
            g.setColor(color);
            g.drawOval(super.getPointX()-r1, super.getPointY() - r2, r1*2, r2*2);
        }
    }
}