/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.model.entities;

import Maze.controller.InputHandler;
import Maze.model.customLevel;
import Maze.view.GameWindow;

/**
 *
 * @author user
 */
public class Decorator extends Player{
    
    GameWindow game = GameWindow.getInstance();
    Player player = game.player;
    
    public Decorator(customLevel level, int x, int y, InputHandler input) {
        super(level, x, y, input);
    }
    
}
