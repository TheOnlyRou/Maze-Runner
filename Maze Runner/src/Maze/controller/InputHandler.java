/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.controller;

import Maze.view.GameWindow;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class InputHandler implements KeyListener {
    
    public class Key{
        public boolean pressed = false;
        private int numTimesPressed = 0;
        
        public boolean isPressed()
        {
            return pressed;
        }
        
        public void toggle(boolean isPressed)
        {
            pressed = isPressed;
            if(isPressed)
            {
                numTimesPressed++;
            }
        }
        
        public int getNumTimesPressed()
        {
            return numTimesPressed;
        }
    }
    
    public InputHandler(GameWindow game)
    {
        game.addKeyListener(this);
    }
    
    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right  = new Key();
    public Key space  = new Key();

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        toggleKey(ke.getKeyCode(),true);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        toggleKey(ke.getKeyCode(),false);
    }
    
    
    /*Method to map actual buttons to our game action buttons */
    private void toggleKey(int keyCode, boolean isPressed) {
        if(keyCode == KeyEvent.VK_SPACE)
        {
            space.toggle(isPressed);
        }
        if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W)
        {
            up.toggle(isPressed);
        }        
        if(keyCode == KeyEvent.VK_DOWN  || keyCode == KeyEvent.VK_S)
        {
            down.toggle(isPressed);
        }        
        if(keyCode == KeyEvent.VK_LEFT  || keyCode == KeyEvent.VK_A)
        {
            left.toggle(isPressed);
        }        
        if(keyCode == KeyEvent.VK_RIGHT  || keyCode == KeyEvent.VK_D)
        {
            right.toggle(isPressed);
        }                
    }
    
}
