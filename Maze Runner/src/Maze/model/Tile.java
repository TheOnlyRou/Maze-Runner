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
    public static final Tile EXIT = new ExitTile(6,5,0,Colours.get(000,000,000,000), 0xFF27D5DF);
    public static final Tile STONE = new StoneTile(1,1,0,Colours.get(100,333,-1,-1), 0xFF555555);
    public static final Tile SAND = new BasicTile(2,2,0,Colours.get(440,441,541,440), 0xFFD4C62C);
    public static final Tile FIRE = new AnimatedTile(3,new int[][]{{0,2},{1,2},{0,2}},Colours.get(440,510,521,555), 0xFFF24726,1000);
    public static final Tile WOOD = new WoodTile(4,2,0,Colours.get(110,210,120,100), 0xFF664D1E);    
    public static final Tile BOMB = new AnimatedTile(5,new int[][]{{0,3},{1,3},{2,3},{0,3}},Colours.get(440,510,521,555), 0xFFCE2CD4,1000);
    public static final Tile HP = new PickupTile(7,4,0,Colours.get(440,441,500,440), 0xFF00ff00);
    public static final Tile ARMOR = new PickupTile(8,3,0,Colours.get(440,441,000,440), 0xFF717171);

    public int getHeal() {
        return heal;
    }
    private int levelColour;
    protected byte id;
    protected boolean solid;
    protected boolean destructible;
    protected boolean isPickup;

    public boolean isIsPickup() {
        return isPickup;
    }
    protected boolean hasWon;
    protected int damage;
    protected int heal;

    public int getDamage() {
        return damage;
    }
    public boolean isHasWon() {
        return hasWon;
    }
    public boolean doesDamage() {
        return doesDamage;
    }
    protected boolean doesDamage;
    
    public Tile(int id, boolean solid, boolean destructible, boolean doesDamage, int levelColour)
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
    
    public abstract void tick();
    
    public abstract void render(Screen screen, customLevel level, int x, int y);
}
