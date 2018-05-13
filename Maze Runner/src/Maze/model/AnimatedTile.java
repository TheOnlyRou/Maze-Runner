/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.model;

import Maze.view.GFX.Screen;

/**
 *
 * @author user
 */
public class AnimatedTile extends StoneTile {
    
    private int[][]animationTileCoords;
    private int animationIndex;
    private long lastIterationTime;
    private int delay;
    
    
    public AnimatedTile(int id, int[][]animationCoords, int tileColour, int levelColour, int delay) {
        super(id, animationCoords[0][0], animationCoords[0][1], tileColour, levelColour);
        this.animationTileCoords = animationCoords;
        this.animationIndex=0;
        this.lastIterationTime=System.currentTimeMillis();
        this.delay=delay;
        this.doesDamage=true;
        this.damage=10;
    }
    
    @Override
    public void tick()
    {
        if((System.currentTimeMillis() - lastIterationTime)>=delay)
        {
            lastIterationTime = System.currentTimeMillis();
            animationIndex=(animationIndex+1)%animationTileCoords.length;
            tileID = (animationTileCoords[animationIndex][0] + animationTileCoords[animationIndex][1]*32);
        }
    }
}
