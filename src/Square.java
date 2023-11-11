import java.awt.*;

public class Square extends TFigure {
    protected int[] coordinates;
    protected int w,h;
    protected Color color;
    private boolean VISION = true;
    protected int interfaceWidth = 1000, interfaceHeight = 500;

    public Square(int x, int y, int w, int h, Color color){
        super(x,y);

        System.out.println("Координаты центра верхней левой точки: x=" + x + ", y=" + y);
        if ((x+w) >= 1000) { w = 1000 - x;}
        if ((y+h) >= 500) { h = 500 - y;}
        System.out.println("Ширина/высота: " + w + "; " + h);
        this.w = w;
        this.h = h;
        sets(x, y, x+w, y, x+w, y+h, x, y+h);
        this.color = color;

        this.tagFigure = 4;
        System.out.println("Создан прямоугольник");
    }

    public Square(int x, int y, int w){
        super(x,y); h = w;

        System.out.println("Координаты центра: x=" + x + ", y=" + y);
        if ((x+w) >= 1000) { w = 1000 - x; h =w;}
        if ((y+h) >= 500) { h = 500 - y; w = h;}
        System.out.println("Ширина/высота: " + w);
        this.w = w;
        this.h = h;
        sets(x, y, x+w, y, x+w, y+h, x, y+h);
        color = Color.RED;

        this.tagFigure = 4;
        System.out.println("Создан квадрат");
    }

    public Square(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, Color color, boolean tag){
        super(x1,y1);
        sets(x1, y1, x2, y2, x3, y3, x4, y4);
        if (tag){
            if (!check(0,0)){
                x1 = 40; y1 = 100;
                x4 = 10; y4 = 60;
                x3 = 40; y3 = 20;
                x2 = 70; y2 = 60;
                setPointXY(x1, y1);
            }
            this.h = y4-y1;
            this.w = x1-x2;
        } else{
            if(!check(0,0)){
                x1 = 100; y1 = 50;
                x2 = 150; y2 = 50;
                x3 = 200; y3 = 100;
                x4 = 100; y4 = 100;
                setPointXY(x1, y1);
            }

        }
        sets(x1, y1, x2, y2, x3, y3, x4, y4);
        this.color=color;
        this.tagFigure = 4;
        System.out.println("Объект Quadrangle создан с координатами точек: x = {" + x1 + "; " + x2 + "; " + x3 +"; " + x4 +"}, y = {" +y1 + "; " + y2 + "; " + y3 + ";"+ y4 +"}.");
    }


    protected void sets(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
        this.coordinates = new int[] {x1, y1, x2, y2, x3, y3, x4, y4};
    }

    private boolean check(int d1, int d2) {
        int[] tests = new int[8];
        boolean f = true;
        for (int i = 0; i < 8; i++) {
            if (i % 2 != 0) {
                tests[i] = coordinates[i] + d1;
                if (f & (tests[i] <= 0 || tests[i] >= interfaceWidth || tests[i] >= interfaceHeight)) {
                    f = false;
                    return false;
                }
            } else {
                tests[i] = coordinates[i] + d2;
                if (f & (tests[i] <= 0 || tests[i] >= interfaceHeight)) {
                    f = false;
                    return f;
                }
            }
        }
        return f;
    }
    @Override
    public void Show(boolean VISION) {
        this.VISION= VISION;
        this.repaint();
    }

    //ТОЛЬКО для квадрата
    public void chSize(int dw) {
        int testw = coordinates[2] - coordinates[0], testh;
        int x = coordinates[0], y = coordinates[1];
        testw += dw; testh = testw;
        if (testw > 10 && testw < interfaceWidth && testh < interfaceHeight){
            this.w += dw;
            this.h = this.w;
        } else {
            do {
                this.w = (int) (Math.random() * 400 + 10);
                this.h = w;
            } while (x + w >= interfaceWidth || y + h >= interfaceHeight);
        }
        sets(x, y, x+w, y, x+w, y+h, x, y+h);
        System.out.println("Изменение размера объекта. (x1; x2) = (" + x1 + "; " + y1+"), (w; h) = (" + w + "; " + h + ")");
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (VISION) {
            g.setColor(color);
            g.drawPolygon(new int[] {x1, x2, x3, x4}, new int[] {y1, y2, y3, y4}, 4);
        }

    }

    public int getXi(int i){
        return coordinates[i];
    }
    public int getYi(int i){
        return coordinates[i];
    }


}