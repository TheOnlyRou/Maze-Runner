/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.model;

import Maze.view.GFX.Colours;
import Maze.view.GFX.Screen;

/**
 *
 * @author user
 */
public abstract class Tile {
    public static final Tile[] tiles = new Tile[256];
    
    //level colours are added + alpha channel, which gets removed later
    public static final Tile VOID = new StoneTile(0,0,0,Colours.get(000,-1,-1,-1), 0xFF000000);
    public static final Tile STONE = new StoneTile(1,1,0,Colours.get(100,333,-1,-1), 0xFF555555);
    public static final Tile SAND = new BasicTile(2,2,0,Colours.get(440,441,541,440), 0xFFD4C62C);
    public static final Tile BOMB = null;
    public static final Tile WOOD = null;
    private int levelColour;
    protected byte id;
    protected boolean solid;
    protected boolean destructible;
    
    public Tile(int id, boolean solid, boolean destructible, int levelColour)
    {
        this.id=(byte)id;
        if(tiles[id]!=null)
            throw new RuntimeException("Duplicate Tile ID on " + id);
        this.solid=solid;
        this.destructible=destructible;
        this.levelColour=levelColour;
        tiles[id]=this;
    }

    public int getLevelColour() {
        return levelColour;
    }

    public byte getID() {
        return id;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isDestructible() {
        return destructible;
    }

    public abstract void render(Screen screen, customLevel level, int x, int y);
}
