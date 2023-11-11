class Point {
    private int x,y;

    public Point () {
        System.out.println("Объект точка(Point) создан");
    }

    public Point (int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("Объект точка(Point) создан");
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }


}