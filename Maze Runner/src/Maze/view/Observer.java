/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.view;

/**
 *
 * @author user
 */
public abstract class Observer {
    protected GameWindow game = GameWindow.getInstance();
    public abstract void update();
}
