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
public class HPObserver extends Observer{

    private int change;
    private boolean isHeal;
    
    public HPObserver(int change, boolean isHeal)
    {
        this.change=change;
        this.isHeal=isHeal;
        update();
    }
    @Override
    public void update() {
        
        if(isHeal)
        {
            MainWindow.getInstance().Heal(change);
        }
        else
        {
            System.out.println("inflicting damage" + change);
            MainWindow.getInstance().inflictDamage(change);
        }
    }
    
}
