/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.model;

import Maze.view.GFX.Screen;

public class BasicTile extends Tile{

    protected int tileID;
    protected int tileColour;

    public BasicTile(int id, boolean solid, boolean destructible, int levelColour) {
        super(id, solid, destructible,levelColour);
    }
    

    public BasicTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, false, false,levelColour);
        this.tileID= x+y; 
        //This is different from the tile's ID in the tiles array
        this.tileColour=tileColour;
    }    

    @Override
    public void render(Screen screen, customLevel level, int x, int y) {
        screen.render(x, y, tileID, tileColour);
    }
    
}
