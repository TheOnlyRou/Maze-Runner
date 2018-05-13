/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.model;

import Maze.view.GFX.Colours;

/**
 *
 * @author user
 */
public class WoodTile extends StoneTile {
    
    int x,y,id;
    public WoodTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, x, y, tileColour, levelColour);
        this.x=x;
        this.y=y;
        this.id=id;
        this.destructible = true;
    }
    
}
