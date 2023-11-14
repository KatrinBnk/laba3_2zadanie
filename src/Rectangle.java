import java.awt.*;

public class Rectangle extends Square {

    public Rectangle (int x, int y, int w, int h, Color color) {
        super(x,y, w, h, color);
    }

    public Rectangle (int x, int y, int w, int h) {
        super(x,y, w,h, Color.BLUE);
    }

    //ТОЛЬКО для прямоугольника
    public void chSize(int dw, int dh) {
        int testw = w, testh = h;
        testw += dw;
        testh += dh;
        if (testw > 1 && testh > 1 && testw < interfaceWidth && testh < interfaceHeight){
            this.w += dw;
            this.h += dh;
        } else {
            do {
                this.w = (int) (Math.random() * 400 + 1);
                this.h = (int) (Math.random() * 400 + 1);
            } while (x1 + w >= interfaceWidth || y1 + h >= interfaceHeight);
        }
        super.sets(x1, y1, x1+w, y1, x1+w, y1+h, x1, y1+h, false);
        System.out.println("Изменение размера объекта. (x1; x2) = (" + x1 + "; " + y1+"), (w; h) = (" + w + "; " + h + ")");
    }
}