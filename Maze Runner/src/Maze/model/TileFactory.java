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
public class TileFactory {
    
    public Tile getTile(String tile)
    {
      if( tile== null){
         return null;
      }
      if(tile.equalsIgnoreCase("VOID")){
         return new StoneTile(0,0,0,Colours.get(000,-1,-1,-1), 0xFF000000);
         
      } else if(tile.equalsIgnoreCase("EXIT")){
         return new ExitTile(6,5,0,Colours.get(000,000,000,000), 0xFF27D5DF);
         
      } else if(tile.equalsIgnoreCase("STONE")){
         return new StoneTile(1,1,0,Colours.get(100,333,-1,-1), 0xFF555555);
      }
      else if(tile.equalsIgnoreCase("SAND")){
         return new BasicTile(2,2,0,Colours.get(440,441,541,440), 0xFFD4C62C);
      }
      else if(tile.equalsIgnoreCase("FIRE")){
         return new AnimatedTile(3,new int[][]{{0,2},{1,2},{0,2}},Colours.get(440,510,521,555), 0xFFF24726,1000);
      }
      else if(tile.equalsIgnoreCase("WOOD")){
         return new WoodTile(4,4,0,Colours.get(110,110,110,100), 0xFF664D1E); 
      }
      else if(tile.equalsIgnoreCase("BOMB")){
         return new AnimatedTile(5,new int[][]{{0,3},{1,3},{2,3},{0,3}},Colours.get(440,510,521,555), 0xFFCE2CD4,1000);
      }
      
      
      return null;
    }
}
