package com.ap.snake;

import com.sun.org.apache.bcel.internal.generic.SIPUSH;
import com.sun.org.apache.bcel.internal.generic.SWITCH;

import javax.swing.*;

public class startGames {
    public static void main(String[] args) {

        Data mydata=new Data();

        mydata.selectMode();

        JFrame frame=new JFrame(mydata.Title);
        frame.setBounds(10,10,config.SCR_WIDTH,config.SCR_HEIGHT); //设置界面大小
        frame.setResizable(false);                                        //窗口大小不可变
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);              //设置关闭事件

        frame.setIconImage(mydata.header.getImage());
        frame.add(new GamePanel(mydata));

        frame.setVisible(true);
    }
}
