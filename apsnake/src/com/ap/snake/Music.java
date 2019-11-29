package com.ap.snake;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Music extends Frame {
    public Music() {
        URL cb = Music.class.getResource("/audio/bg01.wav");
        AudioClip aau = Applet.newAudioClip(cb);
        aau.play();//循环播放 aau.play() 单曲 aau.stop()停止播放
    }

}