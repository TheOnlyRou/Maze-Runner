/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.model.entities;

import Maze.controller.InputHandler;
import Maze.model.customLevel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.audio.*;

/**
 *
 * @author user
 */
public class SoundDecorator extends Decorator{

    public SoundDecorator(customLevel level, int x, int y, InputHandler input) {
        super(level, x, y, input);
    }
    @Override
    public void move(int xa, int ya)
    {
        super.move(xa, ya);
        File walk = new File("walk.wave");
        playSound(walk);
    }
    public void playSound(File sound)
    {
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            
            Thread.sleep(clip.getMicrosecondLength()/1000);
        }
        catch(Exception e)
        {
            
        }
        
    }
}
