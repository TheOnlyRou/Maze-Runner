/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.model;

/**
 *
 * @author user
 */
public class ExitTile extends BasicTile {
    
    public ExitTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, x, y, tileColour, levelColour);
        this.hasWon=true;
    }  
}
