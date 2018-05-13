/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.controller;

import Maze.view.MainWindow;

/**
 *
 * @author user
 */
public class GameStart implements State {

    @Override
    public void execute() {
        MainWindow.getInstance().setVisible(true);
    }
    
}
