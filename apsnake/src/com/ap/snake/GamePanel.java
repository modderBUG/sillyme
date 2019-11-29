package com.ap.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    int length;                     //射的长度
    int[] snakeX = new int[600];      //舍得坐标X
    int[] snakeY = new int[500];       //射的坐标Y
    String direction = "R";
    Boolean isStart = false;
    Boolean isFail = false;
    int score = 0;


    int speedlevel = 100;
    int conrrentspeed = speedlevel;
    boolean speedflag = false;
    Timer timer = new Timer(conrrentspeed, this);        //定时器


    int foodint = 3;
    int[] foodx, foody;                //食物坐标
    int giftx=9999, gifty=9999,lifex=9999, lifey=9999;
    Random random = new Random();

    Data mydata;

    long startTime ;

    int[] TaskInfo=new int[3];


    public GamePanel(Data data) {
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
        this.mydata = data;

        mydata.playbg();


//        new Thread(() -> {
//        },"线程1").start();
    }

    public void initfood() {
        foodx = new int[foodint];
        foody = new int[foodint];
        for (int i = 0; i < foodint; i++) {
            foodx[i] = 25 + 25 * random.nextInt(34);
            foody[i] = 75 + 25 * random.nextInt(24);
        }
    }

    public void newfood(int i) {
        foodx[i] = 25 + 25 * random.nextInt(34);
        foody[i] = 75 + 25 * random.nextInt(24);
    }

    public void initgift() {
        giftx = 25 + 25 * random.nextInt(34);
        gifty = 75 + 25 * random.nextInt(24);

        double p=Math.random();
        if(p>0.8){
            lifex = 25 + 25 * random.nextInt(34);
            lifey = 75 + 25 * random.nextInt(24);
        }
    }

    public void init() {
        length = 3;
        snakeX[0] = 100;
        snakeY[0] = 100;        //头部
        snakeX[1] = 75;
        snakeY[1] = 100;         //身体
        snakeX[2] = 50;
        snakeY[2] = 100;         //身体
        direction = "R";
        //isStart=false;

        initfood();

        startTime= System.currentTimeMillis();


    }


    public void drawTask(int x,int y,int type){
        TaskInfo[0]=x;
        TaskInfo[1]=y;
        TaskInfo[2]=type;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);                            //清屏
        this.setBackground(Color.white);                    //设置背景
        g.drawImage(mydata.bg02.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        mydata.header.paintIcon(this, g, 25, 11);      //绘制头部计分板
        //g.fillRect(25, 75, config.PANEL_WIDTH, config.PANEL_HEIGHT);       //绘制游戏区域
        g.drawImage(mydata.bg03.getImage(), 25, 75, config.PANEL_WIDTH, config.PANEL_HEIGHT, this);

        //画一条蛇
        //Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        switch (direction) {
            case "R":
                mydata.right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "L":
                mydata.left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "U":
                mydata.up.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "D":
                mydata.down.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            default:
                System.out.println("不改变");
        }
        //画蛇身
        //Data.body.paintIcon(this, g, snakeX[1], snakeY[1]);
        //Data.body.paintIcon(this, g, snakeX[2], snakeY[2]);
        for (int i = 1; i < length; i++) {
            mydata.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        //画积分
        drawHeader(g);

        //画食物
        for (int i = 0; i < foodint; i++) {
            if(foodx[i]!=0 && foody[i]!=0)
            mydata.food.paintIcon(this, g, foodx[i], foody[i]);
        }

        if (giftx < 900)
            mydata.gift.paintIcon(this, g, giftx, gifty);
        if (lifex < 900)
            mydata.life.paintIcon(this, g, lifex, lifey);



       if( TaskInfo[0]!=0&&TaskInfo[1]!=0){
           switch (TaskInfo[2]){
               case 1: g.drawRoundRect(TaskInfo[0],TaskInfo[1],40,40,40,40);break;
               case 2:g.drawRoundRect(TaskInfo[0],TaskInfo[1],60,60,60,60);break;
               default:g.drawRoundRect(TaskInfo[0],TaskInfo[1],40,40,40,40);
           }

           for(int i=0;i<3;i++) TaskInfo[i]=0;
       }



        //检测状态
        if (isStart == false) {
            g.setColor(Color.white);
            g.setFont(new Font("宋体", Font.BOLD, 40));
            g.drawString("按下空格开始游戏", config.PANEL_HEIGHT / 2, config.PANEL_WIDTH / 2);
            drawInfotoScr(g);

            System.out.println("按下空格开始游戏");
        }
        if (isFail) {
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("失败, 按下空格重新开始", 200, 300);
            drawInfotoScr(g);

        }

    }

    public void drawInfotoScr(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("宋体", Font.BOLD, 20));
        g.drawString("长度：" + length, config.PANEL_HEIGHT / 2, config.PANEL_WIDTH / 2 - 200);
        g.drawString("分数:" + score, config.PANEL_HEIGHT / 2 + 200, config.PANEL_WIDTH / 2 - 200);


    }

    public void drawHeader(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("宋体", Font.BOLD, 18));
        g.drawString("长度：" + length, config.PANEL_WIDTH - 100, 35);
        g.drawString("分数:" + score, config.PANEL_WIDTH - 100, 60);

        g.drawString("游戏等级：" + ((100 - conrrentspeed + 5) / 5), config.PANEL_WIDTH - 300, 35);
        g.drawString("游戏速度:" + 100.0/timer.getDelay()*10, config.PANEL_WIDTH - 300, 60);

        long nowTime = System.currentTimeMillis()-startTime;
        g.drawString("游戏时间：" + nowTime/1000/60+":"+(nowTime/1000-(nowTime/1000/60)*60), config.PANEL_WIDTH - 500, 35);


    }



    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            mydata.playGift04();
            if (isFail) {
                isFail = false;
                init();
            } else {
                isStart = !isStart;
            }
            repaint();
            System.out.println(keyCode);
        }


        //判断方向
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                if (direction != "R")
                    direction = "L";
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != "L")
                    direction = "R";
                break;
            case KeyEvent.VK_UP:
                if (direction != "D")
                    direction = "U";
                break;
            case KeyEvent.VK_DOWN:
                if (direction != "U")
                    direction = "D";
                break;
            case KeyEvent.VK_A:
                if (direction != "R")
                    direction = "L";
                break;
            case KeyEvent.VK_D:
                if (direction != "L")
                    direction = "R";
                break;
            case KeyEvent.VK_W:
                if (direction != "D")
                    direction = "U";
                break;
            case KeyEvent.VK_S:
                if (direction != "U")
                    direction = "D";
                break;
            default:
                System.out.println(keyCode);
        }

        //判断是否加速
        if (keyCode == KeyEvent.VK_X) {
            speedflag = true;
            //timer.setDelay(speedup);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (isStart && isFail == false) {
            //移动
            for (int i = length - 1; i > 0; i--) {  //除了脑袋，身体都向前
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }

            //加速？
            if (speedflag == true) {
                timer.setDelay(conrrentspeed / 2);
            } else {
                timer.setDelay(conrrentspeed);
            }


            //边界条件
            //if (snakeX[0] > config.PANEL_WIDTH) {
            //snakeX[0] = 25;
            // }
            //snakeX[0] = snakeX[0] + 25;         //头部移动
            //小蛇头部移动
            switch (direction) {
                case "R": {
                    snakeX[0] = snakeX[0] + 25;
                    if (snakeX[0] > config.PANEL_WIDTH) {
                        snakeX[0] = 25;
                    }
                }

                break;
                case "L": {
                    snakeX[0] = snakeX[0] - 25;
                    if (snakeX[0] < 25) {
                        snakeX[0] = config.PANEL_WIDTH;
                    }
                }

                break;
                case "U": {
                    snakeY[0] = snakeY[0] - 25;
                    if (snakeY[0] < 75) {
                        snakeY[0] = config.PANEL_HEIGHT + 50;
                    }
                }

                break;
                case "D": {
                    snakeY[0] = snakeY[0] + 25;
                    if (snakeY[0] > config.PANEL_HEIGHT + 50) {
                        snakeY[0] = 75;
                    }
                }

                break;
                default:
                    System.out.println("不改变");
            }

            //吃到食物，极坐标重合
            for (int i = 0; i < foodint; i++) {
                if (snakeX[0] == foodx[i] && snakeY[0] == foody[i]) {
                    length++;
                    score += 10;
                    paramsReset(score);
                    newfood(i);
                    mydata.playGift01();

                    //初始化食物
                    //initfood();一下初始化3个，不可取
                }
            }
            if (snakeX[0] == giftx && snakeY[0] == gifty) {
                length++;
                score += 50;

                drawTask(giftx,gifty,1);
                paramsReset(score);
                giftx = 9999;
                gifty = 9999;
                mydata.playGift02();
            }

            if (snakeX[0] == lifex && snakeY[0] == lifey) {
                length+=5;
                score += 233;
                drawTask(lifex,lifey,2);
                paramsReset(score);
                lifex = 9999;
                lifey = 9999;
                mydata.playAddlife();
            }


            //吃到自己，失败！
            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = true;
                    mydata.playDie();
                    mydata.playEnd();
                    mydata.playDieW();
                }
            }


            //刷新界面
            repaint();
        }
        timer.start();
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //判断是否加速
        if (keyCode == KeyEvent.VK_X) {
            speedflag = false;
            //timer.setDelay(speedup);
        }
    }

    public void paramsReset(int score) {

        if (score / 10 % 5 == 0) {
            conrrentspeed -= 5;
            timer.setDelay(conrrentspeed);
            initgift();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

}
