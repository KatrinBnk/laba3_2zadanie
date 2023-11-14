import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private JFrame fNL = new JFrame("Лабораторная работа №5");
    private JPanel sP, cP = centrePanel();
    private JButton[] buttons;
    private int interfaceWight = 1100, interfaceHight = 600, buttonKey = 0;

    private JPanel circle = null; //buttonkey == 1
    private Circle[] circles = null; //buttonkey == 11
    private JPanel ellipse = null; //buttonkey == 2
    private Ellipse[] ellipses = null; //buttonkey == 12

    private JPanel square = null; //buttonkey == 4
    private Square[] squares = null; //buttonkey == 14
    private JPanel rectangle = null; //buttonkey == 5
    private Rectangle[] rectangles = null; //buttonkey == 15
    private JPanel romb = null; //buttonkey == 6
    private Romb[] rombs = null;

    private JPanel[] container = null;


    private boolean visionCircle, visionCircles, visionEllipse, visionEllipses;
    private boolean visionSquare, visionSquares, visionRectangle, visionRectangles, visionRomb, visionRombs;

    private int cntCircles = 10, cntEllipses = 10;
    private int cntSquares = 10, cntRectangles = 10;

    private int a, b, c, d, e, f;
    private int[] moveTags = new int[25];
    private int keyForMove, keyForPanelCheck = 0;
    private boolean tagCreateContainer = true;
    private int cntRoms = 10;
    private int keyForPanelCheckEllipse = 1, keyForPanelCheckSquare = 2;


    private Main() {
        buttons = new JButton[]{
                new JButton("Назад"), //0
                new JButton("Круг"), //1
                new JButton("Овал"), //2
                new JButton("Квадрат"), //3
                new JButton("Прямоугольник"), //4
                new JButton("Ромб"), //5
                new JButton("Создать"), //6
                new JButton("Переместить"), //7
                new JButton("Изменить радиус"), //8
                new JButton("Изменить размер"), //9
                new JButton("Сделать видимым/невидимым"), //10
                new JButton("Удалить"), //11
                new JButton("Массив"), //12
                new JButton("Контейнер"), //13
                new JButton("Создать"), //14
                new JButton("Окружности"), //15
                new JButton("Четырехугольники"), //16
                new JButton("Показать"),//17
                new JButton("Стереть"), //18
                new JButton("Уничтожить"), //19
                new JButton("Случайно"), //20
                new JButton("Подиерархии") //20

        };
        sP = southPanel();

        //создаем основное окно
        fNL.setLayout(new BorderLayout());
        fNL.setSize(1200,700);
        fNL.add(cP, BorderLayout.CENTER);
        fNL.add(sP, BorderLayout.SOUTH);
        fNL.setResizable(false);
        fNL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fNL.setVisible(true);

        visionCircle = visionCircles = visionEllipses = visionEllipse = true;
        visionSquare = visionSquares = visionRectangle = visionRectangles = true;

        fNL.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        MoveContainer(0,-10);
                        break;
                    case KeyEvent.VK_DOWN:
                        MoveContainer(0,10);
                        break;
                    case KeyEvent.VK_LEFT:
                        MoveContainer(-10,0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        MoveContainer(10,0);
                        break;
                }
            }
        });
        fNL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fNL.setVisible(true);
    }

    //холст
    private JPanel centrePanel() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setBackground(Color.WHITE);
        return p;
    }

    //стартовая панель с кнопками
    private JPanel southPanel() {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.setBackground(Color.LIGHT_GRAY);
        for (int i = 1; i <= 5; i++) {
            p.add(buttons[i]);
        }
        p.add(buttons[13]);

        //вернуться назад
        buttons[0].addActionListener(e -> back());

        //окружность
        buttons[1].addActionListener(e -> createPanel(1, new int[] {0,11,6,7,8,10,12}));
        //овал
        buttons[2].addActionListener(e -> createPanel(2, new int[] {0,11,6,7,8,10,12}));

        //квадрат
        buttons[3].addActionListener(e -> createPanel(3, new int[] {0,11,6,7,9,10,12}));
        //прямоугольник
        buttons[4].addActionListener(e -> createPanel(4, new int[] {0,11,6,7,9,10,12}));
        //ромб
        buttons[5].addActionListener(e -> createPanel(5, new int[] {0,11,6,7,9,10,12}));

        //создание геометрического примитива и рисование его на холсте
        buttons[6].addActionListener(e -> createObject());
        //перемещение примитива в пределах холста
        buttons[7].addActionListener(e -> moveToObject());
        //изменение радиуса: ОКРУЖНОСТЬ, ОВАЛ
        buttons[8].addActionListener(e -> changeRadius());
        //изменение размера: КВАДРАТ, ПРЯМОУГОЛЬНИК, РОМБ
        buttons[9].addActionListener(e -> changeSize());
        //изменение видимости: ДЛЯ ВСЕХ ФИГУР
        buttons[10].addActionListener(e -> changeVisiability());
        //удаление объекта/ов: ДЛЯ ВСЕХ ФИГУР
        buttons[11].addActionListener(e -> deleteObject());
        //работа с массивом: ДЛЯ ВСЕХ ОБЪЕКТОВ
        buttons[12].addActionListener(e -> arrayObjects());

        //работа с контейнером объектов
        buttons[13].addActionListener(e -> createPanel(6, new int[]{0,14,17,18,19, 21}));
        buttons[14].addActionListener(e -> createPanel(7, new int[]{0,20,15,16}));
        buttons[15].addActionListener(e -> panelORnext(keyForPanelCheckEllipse));
        buttons[16].addActionListener(e -> panelORnext(keyForPanelCheckSquare));

        buttons[17].addActionListener(e -> showAll());
        buttons[18].addActionListener(e -> eraseAll());
        buttons[19].addActionListener(e -> deleteAll());
        buttons[20].addActionListener(e -> createContainerResult(25,5));
        buttons[21].addActionListener(e -> hierarchy());


        return p;
    }

    private void panelORnext(int key) {
        if (key == 1) {
            createContainerResult(25,2);
        } else if (key == 2) {
            createContainerResult(25,3);
        } else if (key == 11) {
            keyForMove = 1;
            sP.removeAll();
            createPanel(8, new int[]{0,7,8});
            sP.revalidate(); sP.repaint();
        } else if (key == 12) {
            keyForMove = 2;
            sP.removeAll();
            createPanel(9, new int[]{0,7,9});
            sP.revalidate(); sP.repaint();
        } else {
            System.out.println("Передан некорректный кюч. panelORnext");
        }
    }

    private void hierarchy() {
        sP.removeAll();
        createPanel(10, new int[]{0, 15, 16, 7});
        sP.revalidate();
        sP.repaint();
        keyForPanelCheckEllipse = 11;
        keyForPanelCheckSquare = 12;
    }


    private void createContainerResult(int countFigure, int tagFigure) {
        int tagFigureRandom;
        if (container == null){
            container = new TFigure[countFigure];
            for(int i = 0; i<countFigure; i++){
                if (tagFigure == 3){
                    tagFigureRandom = 3  + (int) (Math.random()*tagFigure);
                } else {
                    tagFigureRandom = 1 + (int) (Math.random() * tagFigure);
                }
                moveTags[i] = tagFigureRandom;
                a = (int) (Math.random() * 300) + 1;
                b = (int) (Math.random() * 300) + 1;
                c = (int) (Math.random() * 200) + 1;
                d = (int) (Math.random() * 200) + 1;
                if (tagFigureRandom == 1){
                    container[i] = new Circle(a, b, c, Color.BLACK);
                    System.out.println("Фигура №" + (i+1) + " - Круг");
                } else if (tagFigureRandom == 2) {
                    container[i] = new Ellipse(a, b, c, d, Color.BLUE);
                    System.out.println("Фигура №" + (i+1) + " - Овал");
                } else if (tagFigureRandom == 3) {
                    container[i] = new Square(a, b, c);
                    System.out.println("Фигура №" + (i+1) + " - Квадрат");
                } else if (tagFigureRandom == 4) {
                    container[i] = new Rectangle(a, b, c, d, Color.GREEN);
                    System.out.println("Фигура №" + (i+1) + " - Прямоугольник");
                } else if (tagFigureRandom == 5) {
                    container[i] = new Romb(a,b,c,d,Color.CYAN);
                    System.out.println("Фигура №" + (i+1) + " - Ромб");
                } else {System.out.println("Сгенерирован некорректный ключ.");}
                cP.add(container[i],BorderLayout.CENTER);
                cP.validate();
                cP.revalidate();
                cP.repaint();
            }
        } else {System.out.println("Контейнер уже создан.");}

        fNL.setFocusable(true);
        fNL.requestFocus();

    }


    private void deleteAll() {
        if (container != null) {
            for (int i = 0; i < 25; i++) {
                cP.remove(container[i]);
            }
            cP.revalidate();
            cP.repaint();
            container = null;
            System.out.println("Фигуры стерты, а соответствующие объекты уничтожены");
        } else {
            JOptionPane.showMessageDialog(fNL, "Массив не создан");
        }
        fNL.setFocusable(true);
        fNL.requestFocus();
    }

    private void eraseAll() {
        if (container != null) {
            for (int i=0; i<25; i++) {
                cP.remove(container[i]);
            }
            cP.revalidate();
            cP.repaint();
            System.out.println("Фигуры стерты, но объекты не уничтожены");
        }
        else {
            JOptionPane.showMessageDialog(fNL, "Массив не создан");
        }
        fNL.setFocusable(true);
        fNL.requestFocus();
    }

    private void showAll() {
        if (container != null) {
            for (int i=0; i<25; i++) {
                ((TFigure) container[i]).Show(true);
                cP.add(container[i], BorderLayout.CENTER);

                cP.validate();
                cP.revalidate();
                cP.repaint();
            }
            JOptionPane.showMessageDialog(fNL,"Все фигуры показаны");
        }
        else {
            JOptionPane.showMessageDialog(fNL, "Массив не создан");
        }
        fNL.setFocusable(true);
        fNL.requestFocus();
    }

    //панель с кнопками при работе с массивом объектов
    private void arrayObjects() {
        sP.removeAll();
        JOptionPane.showMessageDialog(fNL, "Следующие действия будут выполнены для массива объектов");
        int[] numbers = new int[]{0, 6, 7, 10};
        for (int number: numbers){
            sP.add(buttons[number]);
        }
        if (buttonKey == 1 || buttonKey == 2){ sP.add(buttons[8]); buttonKey+=10;}
        else if (buttonKey == 4 || buttonKey == 3 || buttonKey == 5) { sP.add(buttons[9]); buttonKey +=10;}
        else{System.out.println("Поступил некорректный ключ кнопки.");}
        sP.add(buttons[11]);
        sP.revalidate();
        sP.repaint();
    }
    private void back() {

        if (buttonKey >= 1 && buttonKey <= 6) {
            sP.removeAll();
            for (int i = 1; i <= 5; i++) { sP.add(buttons[i]); }
            sP.add(buttons[13]);
        } else if(buttonKey == 7){
            sP.removeAll();
            sP.add(buttons[0]);
            for (int i = 14;i<= 19;i++){sP.add(buttons[i]);}
            sP.remove(buttons[15]);
            sP.remove(buttons[16]);
            sP.add(buttons[21]);
            buttonKey = 6;
        } else if (buttonKey == 8 || buttonKey == 9) {
            keyForMove = 0;
            createPanel(10, new int[]{0, 15, 16, 7});
        } else if (buttonKey == 10) {
            keyForPanelCheckEllipse = 1; keyForPanelCheckSquare = 2;
            createPanel(6, new int[]{0,14,17,18,19, 21});
        } else  if (buttonKey >= 11 && buttonKey <= 17) {
            buttonKey -= 10;
            sP.add(buttons[12]);
        }
        sP.revalidate();
        sP.repaint();
    }

    private void deleteObject() {
        if (buttonKey == 1){
            if (circle != null){
                cP.remove(circle);
                circle = null;
            }
        } else if(buttonKey == 11){
            if (circles != null){
                for (int i=0; i<cntCircles; i++) {
                    cP.remove(circles[i]);
                }
                circles = null;
            }
        } else if(buttonKey == 2){
            if(ellipse != null){
                cP.remove(ellipse);
                ellipse = null;
            }
        } else if (buttonKey == 12){
            if (ellipses != null){
                for (int i=0; i<cntEllipses; i++) {
                    cP.remove(ellipses[i]);
                }
                ellipses = null;
            }
        } else if(buttonKey == 4){
            if(square != null){
                cP.remove(square);
                square = null;
            }
        } else if (buttonKey == 14){
            if (squares != null){
                for (int i=0; i<cntSquares; i++) {
                    cP.remove(squares[i]);
                }
                squares = null;
            }
        } else if(buttonKey == 5){
            if(rectangle != null){
                cP.remove(rectangle);
                rectangle = null;
            }
        } else if (buttonKey == 15){
            if (rectangles != null){
                for (int i=0; i<cntRectangles; i++) {
                    cP.remove(rectangles[i]);
                }
                rectangles = null;
            }
        } else if(buttonKey == 6){
            if(romb != null){
                cP.remove(romb);
                romb = null;
            }
        } else if (buttonKey == 16){
            if (rombs != null){
                for (int i=0; i<cntRoms; i++) {
                    cP.remove(rombs[i]);
                }
                rombs = null;
            }
        }

        cP.revalidate();
        cP.repaint();
    }

    private void changeVisiability() {
        if (buttonKey == 1){
            if (circle != null){
                visionCircle = !visionCircle;
                ((Circle) circle).Show(visionCircle);
                cP.revalidate(); cP.repaint();
            } else { System.out.println("Фигур данного типа нет на холсте."); }
        } else if(buttonKey == 11){
            if (circles != null){
                visionCircles = !visionCircles;
                for (int i=0; i<cntCircles; i++) {
                    circles[i].Show(visionCircles);
                    cP.repaint();
                }
                cP.revalidate();
            } else { System.out.println("Фигур данного типа нет на холсте.");}
        } else if(buttonKey == 2){
            if(ellipse != null){
                visionEllipse = !visionEllipse;
                ((Ellipse) ellipse).Show(visionEllipse);
                cP.revalidate(); cP.repaint();
            } else { System.out.println("Фигур данного типа нет на холсте."); }
        } else if (buttonKey == 12){
            if (ellipses != null){
                visionEllipses = !visionEllipses;
                for (int i=0; i<cntEllipses; i++) {
                    ellipses[i].Show(visionEllipses);
                    cP.repaint();
                }
                cP.revalidate();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if(buttonKey == 4){
            if(square != null){
                visionSquare = !visionSquare;
                ((Square) square).Show(visionSquare);
                cP.revalidate(); cP.repaint();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if (buttonKey == 14){
            if (squares != null){
                visionSquares = !visionSquares;
                for (int i=0; i<cntSquares; i++) {
                    squares[i].Show(visionSquares);
                    cP.repaint();
                }
                cP.revalidate();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if(buttonKey == 5){
            if(rectangle != null){
                visionRectangle = !visionRectangle;
                ((Rectangle) rectangle).Show(visionRectangle);
                cP.revalidate(); cP.repaint();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if (buttonKey == 15){
            if (rectangles != null){
                visionRectangles = !visionRectangles;
                for (int i=0; i<cntRectangles; i++) {
                    rectangles[i].Show(visionRectangles);
                    cP.repaint();
                }
                cP.revalidate();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if(buttonKey == 6){
            if(romb != null){
                visionRomb = !visionRomb;
                ((Romb) romb).Show(visionRomb);
                cP.revalidate(); cP.repaint();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if (buttonKey == 16){
            if (rombs != null){
                visionRombs = !visionRombs;
                for (int i=0; i<cntRoms; i++) {
                    rombs[i].Show(visionRomb);
                    cP.repaint();
                }
                cP.revalidate();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        }
    }

    private void changeSize() {
        int x = (int) (Math.random() * 50) - 10;
        int y = (int) (Math.random() * 50) - 10;
        if (buttonKey == 5) {
            if (rectangle != null) { ((Rectangle) rectangle).chSize(x, y, 2); }
            else { JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден"); }
        }
        else if (buttonKey == 15) {
            if (rectangles != null) {
                for (int i = 0; i < cntRectangles; i++) { rectangles[i].chSize(x, y, 2); }
            } else { JOptionPane.showMessageDialog(fNL, "Массив прямоугольников не создан"); }
        }
        else if (buttonKey == 4) {
            if (square != null) { ((Square) square).chSize(x, x, 1); }
            else { JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден"); }
        }
        else if (buttonKey == 14) {
            if (squares != null) {
                for (int i = 0; i < cntSquares; i++) { squares[i].chSize(x, x, 1); }
            } else { JOptionPane.showMessageDialog(fNL, "Массив прямоугольников не создан"); }
        }
        if (buttonKey == 6) {
            if (romb != null) { ((Romb) romb).chSize(x, y, 3); }
            else { JOptionPane.showMessageDialog(fNL, "Ромб не найден"); }
        }
        else if (buttonKey == 16) {
            if (rombs != null) {
                for (int i = 0; i < cntRoms; i++) { rombs[i].chSize(x, y, 3); }
            } else { JOptionPane.showMessageDialog(fNL, "Массив ромбов не создан"); }
        } else if (buttonKey == 9) {
            if (container != null){
                for (int i = 0; i < 25; i ++){
                    if (container[i] instanceof Square){
                        ((Square) container[i]).chSize(x, y, moveTags[i] - 2);
                    }
                }
            }
            fNL.setFocusable(true);
            fNL.requestFocus();
        }
        cP.revalidate();
        cP.repaint();
    }

    private void changeRadius()  {
        int x = 10 + (int) (Math.random() * 150);
        int y = 10+ (int) (Math.random()*150);
        if (buttonKey == 1) {
            if (circle != null) { ((Circle) circle).chengeR(x, x, true); }
            else { JOptionPane.showMessageDialog(fNL, "Окружность не найдена"); }
        }
        else if (buttonKey == 11) {
            if (circles != null) {
                for (int i = 0; i < cntCircles; i++) {
                    circles[i].chengeR(x, x, true);
                    x = 10 + (int) (Math.random() * 150);
                }
            } else { JOptionPane.showMessageDialog(fNL, "Массив окружностей не найден"); }
        } if (buttonKey == 2) {
            if (ellipse != null) { ((Ellipse) ellipse).chengeR(x, y, false); }
            else { JOptionPane.showMessageDialog(fNL, "Овал не найден"); }
        }
        else if (buttonKey == 12) {
            if (ellipses != null) {
                for (int i = 0; i < cntEllipses; i++) {
                    ellipses[i].chengeR(x, y, false);
                    x = 10 + (int) (Math.random() * 150);
                    y = 10 + (int) (Math.random() * 150);

                }
            } else { JOptionPane.showMessageDialog(fNL, "Массив овалов не найден"); }
        } else if (buttonKey == 8) {
            if (container != null){
                for (int i = 0; i < 25; i ++){
                    if (container[i] instanceof Circle){
                        if (moveTags[i] == 1){((Circle) container[i]).chengeR(x, x, true);}
                        else if (moveTags[i] == 2){((Circle) container[i]).chengeR(x, y, false);}
                        else {System.out.println("Передан некорректный ключ. changeR for container");}
                    }
                }
            }
            fNL.setFocusable(true);
            fNL.requestFocus();
        }
        cP.revalidate();
        cP.repaint();
    }


    private void moveToObject()  {

        int x = (int) (Math.random() * 600) - 300;
        int y = (int) (Math.random() * 600) - 300;

        if (buttonKey == 1){
            if (circle != null) { ((Circle) circle).MoveTo(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Окружность не найдена"); }
        } else if (buttonKey == 11) {
            if (circles != null) {
                for (int i=0; i<cntCircles; i++) { circles[i].MoveTo(x, y); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив окружностей не найден"); }
        } else if(buttonKey == 2){
            if (ellipse != null) { ((Ellipse) ellipse).MoveTo(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Овал не найден"); }
        } else if (buttonKey == 12) {
            if (ellipses != null) {
                for (int i=0; i<cntEllipses; i++) { ellipses[i].MoveTo(x, y); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив овал не найден"); }
        } else if(buttonKey == 3){
            if (square != null) { ((Square) square).MoveTo(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Квадрат не найден"); }
        } else if (buttonKey == 13) {
            if (squares != null) {
                for (int i=0; i<cntSquares; i++) { squares[i].MoveTo(x, y); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив квадратов не найден"); }
        } else if (buttonKey == 4) {
            if (rectangle != null) { ((Rectangle) rectangle).MoveTo(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден"); }
        } else if (buttonKey == 14) {
            if (rectangles != null) {
                for (int i=0; i<cntRectangles; i++) { rectangles[i].MoveTo(x, y); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив прямоугольников не найден"); }
        } else if (buttonKey == 5) {
            if (romb != null) { ((Romb) romb).MoveTo(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Ромб не найден"); }
        } else if (buttonKey == 15) {
            if (rombs != null) {
                for (int i=0; i<cntRoms; i++) { rombs[i].MoveTo(x, y); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив ромбов не найден"); }
        } else if (buttonKey >= 6 && buttonKey<= 10 ){
            x = (int) (Math.random() * 200) - 100;
            y = (int) (Math.random() * 200) - 100;
            MoveContainer(x,y);
        }
        cP.repaint();
        cP.revalidate();
    }

    private void createPanel(int key, int[] numbers) {
        sP.removeAll();
        for (int number: numbers){ sP.add(buttons[number]); }
        sP.revalidate();
        sP.repaint();
        buttonKey = key;
    }

    private void createObject(){
        int tag;
        if (buttonKey >= 1 && buttonKey <=9){
            try{
                tag = Integer.parseInt(JOptionPane.showInputDialog("Хотите ввести параметры вручную? (0-нет, 1-да)"));
                if (tag != 0 && tag != 1) {
                    JOptionPane.showMessageDialog(null, "Ошибка: Введите 0 или 1.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (tag == 1){ create(); }
                    else { createRandom(); }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка: некорректный ввод.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else{ createRandom(); }
    }

    /*
    Для создания фигуры возможен ручной и компьютерный ввод
    Пример компьютерного ввода абсолютно для всех можно посмотреть в ЛР 1.1 и ЛР 2.1
    В следующих лабораторных работах для экономии памяти будет оставлен ручной ввод
    ТОЛЬКО ДЛЯ КРУГА и массива кругов, остающиеся фигуры будут создаваться при помощи случайных чисел
    */

    private void create() {
        if (buttonKey == 1){
            if (circle == null){
                int x  = Integer.parseInt(JOptionPane.showInputDialog(""));
                if (check(x, 1)){
                    int y = Integer.parseInt(JOptionPane.showInputDialog(""));
                    if (check(y, 2)){
                        int r = Integer.parseInt(JOptionPane.showInputDialog(""));
                        if (check(y+r, 2) && check(x+r, 1)){
                            circle = new Circle(x,y,r, Color.BLACK);
                            cP.add(circle, BorderLayout.CENTER);
                            cP.revalidate();
                        }
                        else { JOptionPane.showMessageDialog(fNL, "Некорректный ввод: выход за пределы холста при создании фигуры."); }
                    }
                    else { JOptionPane.showMessageDialog(fNL, "Некорректный ввод: выход за пределы холста при создании фигуры."); }
                }
                else { JOptionPane.showMessageDialog(fNL, "Некорректный ввод: выход за пределы холста при создании фигуры."); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Некорректный ввод: выход за пределы холста при создании фигуры."); }
        } else if(buttonKey == 11) {
            JOptionPane.showMessageDialog(fNL, "Фигуры будут созданы по сгенерированным значениям.");
            cntCircles = Integer.parseInt(JOptionPane.showInputDialog("Сколько фигур необходимо создать?"));
            if (cntCircles <= 0){
                JOptionPane.showMessageDialog(fNL, "Некорректный ввод. Будет создано 10 фигур.");
                cntCircles = 10;
            }
            createRandom();
        } else {
            JOptionPane.showMessageDialog(fNL, "!!!Создание будет по сгенерированным числам!!!");
            createRandom();
        }
    }

    private boolean check(int a, int key) {
        if (key == 1) {
            if (a <= 0 || a >= 1100) {
                return false;
            }
        } else if (key == 2) {
            if (a <= 0 || a >= 600) {
                return false;
            }
        } else {
            System.out.println("Передан некорректный ключ символа. Проверка не была произведена.");
            return false;
        }
        return true;
    }

    private void createRandom() {
        a = (int) (Math.random() * 300) + 1;
        b = (int) (Math.random() * 300) + 1;
        c = (int) (Math.random() * 300) + 1;
        d = (int) (Math.random() * 300) + 1;
        e = (int) (Math.random() * 300) + 1;
        f = (int) (Math.random() * 300) + 1;
        if (buttonKey == 1){
            if(circle == null){
                circle = new Circle(a, b, c, Color.BLACK);
                cP.add(circle,BorderLayout.CENTER);
            }
            else{ JOptionPane.showMessageDialog(fNL, "Окружность уже нарисована"); }
        } else if(buttonKey == 11){
            if(circles == null){
                circles = new Circle[cntCircles];
                for (int i = 0; i < cntCircles; i++){
                    circles[i] = new Circle(a, b, c, Color.BLUE);
                    cP.add(circles[i], BorderLayout.CENTER);
                    a = (int) (Math.random() * 300) + 1;
                    b = (int) (Math.random() * 300) + 1;
                    c = (int) (Math.random() * 300) + 1;
                    cP.validate();
                    cP.repaint();
                }
            }
            else{ JOptionPane.showMessageDialog(fNL, "Массив окружностей уже создан"); }
        } else if (buttonKey == 2){
            if(ellipse == null){
                ellipse = new Ellipse(a, b, c, d, Color.GRAY);
                cP.add(ellipse,BorderLayout.CENTER);
            }
            else{ JOptionPane.showMessageDialog(fNL, "Овал уже нарисована"); }
        } else if(buttonKey == 12){
            if(ellipses == null){
                ellipses = new Ellipse[cntEllipses];
                for (int i = 0; i < cntEllipses; i++){
                    ellipses[i] = new Ellipse(a, b, c, d, Color.BLUE);
                    cP.add(ellipses[i], BorderLayout.CENTER);
                    a = (int) (Math.random() * 300) + 1;
                    b = (int) (Math.random() * 300) + 1;
                    c = (int) (Math.random() * 300) + 1;
                    d = (int) (Math.random() * 300) + 1;
                    cP.validate();
                    cP.repaint();
                }
            }
            else{ JOptionPane.showMessageDialog(fNL, "Массив овалов уже создан"); }
        } else if (buttonKey == 4){
            if(square == null){
                square = new Square(a, b, c);
                cP.add(square, BorderLayout.CENTER);
            }
            else{ JOptionPane.showMessageDialog(fNL, "Квадрат уже нарисована"); }
        } else if(buttonKey == 14){
            if(squares == null){
                squares = new Square[cntSquares];
                for (int i = 0; i < cntSquares; i++){
                    squares[i] = new Square(a, b, c);
                    cP.add(squares[i], BorderLayout.CENTER);
                    a = (int) (Math.random() * 300) + 1;
                    b = (int) (Math.random() * 300) + 1;
                    c = (int) (Math.random() * 300) + 1;
                    cP.validate();
                    cP.repaint();
                }
            }
            else{ JOptionPane.showMessageDialog(fNL, "Массив квадратов уже создан"); }
        } else if (buttonKey == 5){
            if(rectangle == null){
                rectangle = new Rectangle(a, b, c, e);
                cP.add(rectangle, BorderLayout.CENTER);
            }
            else{ JOptionPane.showMessageDialog(fNL, "Прямоугольник уже нарисована"); }
        } else if(buttonKey == 15){
            if(rectangles == null){
                rectangles = new Rectangle[cntRectangles];
                for (int i = 0; i < cntRectangles; i++){
                    rectangles[i] = new Rectangle(a, b, c, e);
                    cP.add(rectangles[i], BorderLayout.CENTER);
                    a = (int) (Math.random() * 300) + 1;
                    b = (int) (Math.random() * 300) + 1;
                    c = (int) (Math.random() * 300) + 1;
                    e = (int) (Math.random() * 300) + 1;
                    cP.validate();
                    cP.repaint();
                }
            }
            else{ JOptionPane.showMessageDialog(fNL, "Массив прямоугольников уже создан"); }
        } else if (buttonKey == 6){
            if(romb == null){
                romb = new Romb(a, b, c, e);
                cP.add(romb, BorderLayout.CENTER);
            }
            else{ JOptionPane.showMessageDialog(fNL, "Ромб уже нарисован"); }
        } else if(buttonKey == 16){
            if(rombs == null){
                rombs = new Romb[cntRoms];
                for (int i = 0; i < cntRoms; i++){
                    rombs[i] = new Romb(a, b, c, e);
                    cP.add(rombs[i], BorderLayout.CENTER);
                    a = (int) (Math.random() * 300) + 1;
                    b = (int) (Math.random() * 300) + 1;
                    c = (int) (Math.random() * 300) + 1;
                    e = (int) (Math.random() * 300) + 1;
                    cP.validate();
                    cP.repaint();
                }
            }
            else{ JOptionPane.showMessageDialog(fNL, "Массив ромбов уже создан"); }
        }
        cP.validate();
        cP.repaint();
        cP.revalidate();
    }


    private void MoveContainer (int dx, int dy) {
        if (keyForMove == 0) {
            if (container != null) {
                for (int i = 0; i < 25; i++) {
                    ((TFigure) container[i]).MoveTo(dx, dy);
                    cP.add(container[i], BorderLayout.CENTER);
                }
                cP.revalidate();
                cP.repaint();
            } else {
                JOptionPane.showMessageDialog(fNL, "Контейнер не создан");
            }
        } else if (keyForMove == 1) {
            if (container != null) {
                for (int i = 0; i < 25; i++) {
                    if (container[i] instanceof Circle) {
                        ((TFigure) container[i]).MoveTo(dx, dy);
                        cP.add(container[i], BorderLayout.CENTER);
                    }
                }
                cP.revalidate();
                cP.repaint();
            } else {
                JOptionPane.showMessageDialog(fNL, "Контейнер не создан");
            }
        } else if (keyForMove == 2) {
            if (container != null) {
                for (int i = 0; i < 25; i++) {
                    if (container[i] instanceof Square) {
                        ((TFigure) container[i]).MoveTo(dx, dy);
                        cP.add(container[i], BorderLayout.CENTER);
                    }
                }
                cP.revalidate();
                cP.repaint();
            } else {
                JOptionPane.showMessageDialog(fNL, "Контейнер не создан");
            }
        }
        fNL.setFocusable(true);
        fNL.requestFocus();
    }

    public static void main (String[] argc) {
        SwingUtilities.invokeLater(Main::new);
    }


}