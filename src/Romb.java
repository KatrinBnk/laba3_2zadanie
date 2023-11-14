import java.awt.*;

public class Romb extends Square {
    public int x0, y0;

    public Romb (int x, int y, int w, int h, Color color) {
        super( x, y-h/2, x + w/2,y, x, y+h/2, x-w/2, y, color, true );
        this.color=color;
        this.x0 = x1; this.y0 = y4; this.w = (x2 - x1)*2; this.h = (y3 - y4)*2;
        System.out.println("Создан ромб: центральная точка (" + x0 + ", " + y0 + "), ширина/высота (" + w + ", " + h + ").");
    }

    public Romb(int x, int y, int w, int h) {
        super(x, y-h/2, x + w/2,y, x, y+h/2, x-w/2, y, Color.CYAN, true);
        this.x0 = x1; this.y0 = y4; this.w = (x2 - x1)*2; this.h = (y3 - y4)*2;
        System.out.println("Создан ромб: центральная точка (" + x0 + ", " + y0 + "), ширина/высота (" + w + ", " + h + ").");
    }

    public void chSize(int dw, int dh) {
        this.x0 = x1; this.y0 = y4; this.w = (x2 - x1)*2; this.h = (y3 - y4)*2;
        int testw = w, testh = h;
        testw += dw;
        testh += dh;
        if (!(x0 + testw/2 >= interfaceWidth || y0 + testh/2 >= interfaceHeight ||
                x0 - testw/2 <= 0 || y0 - testh/2 <= 0) ){
            this.w += dw;
            this.h += dh;
        } else {
            do {
                this.w = (int) (Math.random() * 400 + 10);
                this.h = (int) (Math.random() * 400 + 10);
            } while ( x0 + w/2 >= interfaceWidth || y0 + h/2 >= interfaceHeight ||
                    x0 - w/2 <= 0 || y0 - h/2 <= 0);
        }

        sets(x0, y0-h/2, x0 + w/2,y0, x0, y0+h/2, x0-w/2, y0, true);
        System.out.println("Изменение размера объекта. (x0;y0) = (" + x0 + "; " + y0+"), (w; h) = (" + w + "; " + h + ")");
    }
}