import java.awt.*;

public class Square extends TFigure {

    protected int w,h;
    protected Color color;
    private boolean VISION = true;

    public Square(int x, int y, int w, int h, Color color){
        super(x,y);

        System.out.println("Координаты центра верхней левой точки: x=" + x + ", y=" + y);
        if ((x+w) >= 1000) { w = 1000 - x;}
        if ((y+h) >= 500) { h = 500 - y;}
        System.out.println("Ширина/высота: " + w + "; " + h);
        this.w = w;
        this.h = h;
        sets(x, y, x+w, y, x+w, y+h, x, y+h, false);
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
        sets(x, y, x+w, y, x+w, y+h, x, y+h, false);
        color = Color.RED;

        this.tagFigure = 4;
        System.out.println("Создан квадрат");
    }

    public Square(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, Color color, boolean tag){
        super(x1,y1);
        sets(x1, y1, x2, y2, x3, y3, x4, y4, tag);
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
        sets(x1, y1, x2, y2, x3, y3, x4, y4, tag);
        this.color=color;
        this.tagFigure = 4;
        System.out.println("Объект Quadrangle создан с координатами точек: x = {" + x1 + "; " + x2 + "; " + x3 +"; " + x4 +"}, y = {" +y1 + "; " + y2 + "; " + y3 + ";"+ y4 +"}.");
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
    public void chSize(int dw, int dh, int keyFigure) {
        if (keyFigure == 1) {

            if (w + dw + x1 > 1 && h + dw + y1> 1 && w + dw + x1 < interfaceWidth && h + dw + y1 < interfaceHeight){
                this.w += dw;
                this.h += dw;
            } else {
                do {
                    this.w = (int) (Math.random() * 400 + 1);
                    this.h = w;
                } while (x1 + w >= interfaceWidth || y1 + h >= interfaceHeight);
            }
            System.out.println("Изменение размера объекта - квадрат. (x1;y1) = (" + x1 + "; " + y1+"), (w; h) = (" + w + "; " + h + ")");
            sets(x1, y1, x1+w, y1, x1+w, y1+h, x1, y1+h, false);
        } else if(keyFigure == 2){
            int testw = w + x1, testh = h + y1;
            testw += dw;
            testh+= dh;
            if (testw > 0 && testh > 0 && testw < interfaceWidth && testh < interfaceHeight &&testw +w > 0 && testh + h > 0 && testw+w < interfaceWidth && testh+h < interfaceHeight ){
                this.w += dw;
                this.h += dh;
            } else {
                do {
                    this.w = (int) (Math.random() * 400 + 1);
                    this.h = (int) (Math.random() * 400 + 1);
                } while (x1 + w > interfaceWidth || y1 + h > interfaceHeight);
            }
            System.out.println("Изменение размера объекта - прямоугольник. (x1;y1) = (" + x1 + "; " + y1+"), (w; h) = (" + w + "; " + h + ")");


            sets(x1, y1, x1 + this.w, y1, x1 + this.w, y1 + this.h, x1, y1 + this.h, false);
        } else if ( keyFigure == 3) {
            int x0 = x1; int y0 = y4; this.w = (x2 - x1)*2; this.h = (y3 - y4)*2;
            int testw = w, testh = h;
            testw += dw;
            testh += dh;
            if (!(x0 + testw/2 >= interfaceWidth || y0 + testh/2 >= interfaceHeight ||
                    x0 - testw/2 <= 0 || y0 - testh/2 <= 0) ){
                this.w += dw;
                this.h += dh;
            } else {
                do {
                    this.w = (int) (Math.random() * 50 + 1);
                    this.h = (int) (Math.random() * 50 + 1);
                } while ( x0 + w/2 >= interfaceWidth || y0 + h/2 >= interfaceHeight ||
                        x0 - w/2 <= 0 || y0 - h/2 <= 0);
            }
            repaint();
            sets(x0, y0-h/2, x0 + w/2,y0, x0, y0+h/2, x0-w/2, y0, true);
            System.out.println("Изменение размера объекта - ромб. (x0;y0) = (" + x0 + "; " + y0+"), (w; h) = (" + w + "; " + h + ")");
        }
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