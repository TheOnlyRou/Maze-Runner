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
public class ArmorObserver extends Observer{

    private int change;
    
    public ArmorObserver(int change)
    {
        this.change=change;
        update();
    }
    
    @Override
    public void update() {
        MainWindow.getInstance().AddArmor(change);
    }
    
}
