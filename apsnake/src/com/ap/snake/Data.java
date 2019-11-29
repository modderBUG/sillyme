package com.ap.snake;

import javax.swing.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.net.URL;

public class Data extends Frame {

    public String respath = "/res/emoji";
    public String Title = "贪吃的滑稽哥";

    public void selectMode() {
        Object[] options = {"阿潘", "滑稽", "其他"};
        String s = (String) JOptionPane.showInputDialog(null, "来选择为主题:\n", "选择游戏主题", JOptionPane.PLAIN_MESSAGE, new ImageIcon("xx.png"), options, "xx");
        System.out.println(s);
        if (s == null) {
            s = "滑稽";
        }

        switch (s) {
            case "阿潘": {
                respath = "/res/apwonder";
                Title = "贪吃的阿潘同学";
            }
            break;
            case "滑稽": {
                respath = "/res/emoji";
                Title = "贪吃的滑稽哥";
            }
            break;
            default:
                respath += "emoji";
        }
    }


    public URL headerURL = Data.class.getResource(respath + "/icon.png");
    public ImageIcon header = new ImageIcon(headerURL);

    public URL upURL = Data.class.getResource(respath + "/headerup.png");
    public URL downURL = Data.class.getResource(respath + "/headerdown.png");
    public URL leftURL = Data.class.getResource(respath + "/headerleft.png");
    public URL rightURL = Data.class.getResource(respath + "/headerright.png");
    public ImageIcon up = new ImageIcon(upURL);
    public ImageIcon down = new ImageIcon(downURL);
    public ImageIcon left = new ImageIcon(leftURL);
    public ImageIcon right = new ImageIcon(rightURL);

    public URL bodyURL = Data.class.getResource(respath + "/body.png");
    public ImageIcon body = new ImageIcon(bodyURL);

    public URL foodURL = Data.class.getResource(respath + "/foods.png");
    public ImageIcon food = new ImageIcon(foodURL);
    public URL giftURL = Data.class.getResource(respath + "/gift01.png");
    public ImageIcon gift = new ImageIcon(giftURL);
    public URL lifeURL = Data.class.getResource(respath + "/headerup.png");
    public ImageIcon life = new ImageIcon(lifeURL);

    public URL bgURL = Data.class.getResource(respath + "/bg.jpg");
    public ImageIcon bg = new ImageIcon(bgURL);
    public URL bg02URL = Data.class.getResource(respath + "/bg02.jpg");
    public ImageIcon bg02 = new ImageIcon(bg02URL);
    public URL bg03URL = Data.class.getResource(respath + "/bg03.jpg");
    public ImageIcon bg03 = new ImageIcon(bg03URL);


    public URL audio_bg01 = Music.class.getResource("/audio/bg01.wav");
    public URL audio_addlife = Music.class.getResource("/audio/addlife.wav");
    public URL audio_die = Music.class.getResource("/audio/die.wav");
    public URL audio_end = Music.class.getResource("/audio/end.wav");
    public URL audio_die_wo = Music.class.getResource("/audio/die_woman.wav");
    public URL audio_biggift = Music.class.getResource("/audio/biggift.wav");
    public URL audio_smallgift = Music.class.getResource("/audio/smallgift.wav");
    public URL audio_bigcoin = Music.class.getResource("/audio/bigCoin.wav");
    public URL audio_smallcoin = Music.class.getResource("/audio/smallCoin.wav");

    //public static URL audio_bg = Music.class.getResource("/audio/bg01.wav");


    public void playbg() {
        //Music(audio_bg01);
        AudioClip aau = Applet.newAudioClip(audio_bg01);
        aau.loop();//循环播放 aau.play() 单曲 aau.stop()停止播放
    }

    public void playAddlife() {
        Music(audio_addlife);
    }

    public void playDie() {
        Music(audio_die);
    }

    public void playEnd() {
        Music(audio_end);
    }

    public void playDieW() {
        Music(audio_die_wo);
    }

    public void playGift01() {
        Music(audio_biggift);
    }

    public void playGift02() {
        Music(audio_smallgift);
    }

    public void playGift03() {
        Music(audio_bigcoin);
    }

    public void playGift04() {
        Music(audio_smallcoin);
    }


    public void Music(URL cb) {
        AudioClip aau = Applet.newAudioClip(cb);
        aau.play();//循环播放 aau.play() 单曲 aau.stop()停止播放
    }

}
