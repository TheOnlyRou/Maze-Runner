/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.view;

import Maze.controller.InputHandler;
import Maze.view.GFX.Colours;
import Maze.view.GFX.Screen;
import Maze.view.GFX.SpriteSheet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class GameWindow extends Canvas implements Runnable{
    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH/12*9;
    public static final int SCALE = 5;
    public boolean running=false;
    public int tickCount=0;
    
    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private int[] pixels= ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    /* creates an array of colors of six shades for every base color (R, G, B) */
    private int[] colors = new int[6*6*6];
    
    private Screen screen;
    public InputHandler input;
    
    public GameWindow() throws InterruptedException
    {
        setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        
    }
    
    public void init()
    {
        int index= 0;
        for(int r = 0; r<6 ;r++)
        {
            for(int g = 0; g<6 ;g++)
            {
                for(int b =0; b<6 ; b++)
                {
                    int rr = (r*225/5);
                    int gg = (g*225/5);
                    int bb = (b*225/5);
                    
                    //We're populating a 6*6*6 integer, so we shift R to be first, G to be second and B isn't shifted
                    colors[index++]= rr<<16 | gg<<8 | bb;
                }
            }
        }
        screen = new Screen(WIDTH,HEIGHT,new SpriteSheet("\\SpriteSheet2.png"));
        input = new InputHandler(this);
        
    }    
    
    public synchronized void start()
    {
        running=true;
        new Thread(this).start();
    }
    
    public synchronized void stop()
    {
        running=false;
    }
    
    
    @Override
    public synchronized void run()
    {
        long last=System.nanoTime();
        double nsPerTick= 1000000000D/60D;
        
        long lastTimer=System.currentTimeMillis();
        double diff=0;

        /* Permission to render, made so as to decrease render operations */
        boolean permRender = false;
        
        int frames=0;
        int ticks = 0;
        
        init();
        while(running)
        {
            long now = System.nanoTime();   
            diff+=(now-last)/nsPerTick;
            last=now;
            
            while(diff>=1)
            {
                ticks++;
                tick();
                diff--;
                permRender=true;
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(permRender)
            {   
                frames++;
                render();
            }
            if(System.currentTimeMillis() - lastTimer >= 1000)
            {
                lastTimer+=1000;
                System.out.println("frames= " + frames + ", Ticks= " + ticks);
                frames=0;
                ticks=0;
            }
        }
    }
    
    public void tick()
    {
	tickCount++;
        if(input.up.isPressed())
        {
            screen.yOffset--;
            System.out.println("up");
        }
        if(input.down.isPressed())
        {
            screen.yOffset++;
            System.out.println("down");
        }
        if(input.left.isPressed())
        {
            screen.xOffset--;
            System.out.println("left");
        }
        if(input.right.isPressed())
        {
            screen.xOffset++;
            System.out.println("right");
        }       
        if(input.space.isPressed())
        {
            /* PEW PEW PEW */
            System.out.println("space");
        }        
    }
    
    public void render()
    {
        /* BufferStrategy to prevent Flickering of the screen*/
        BufferStrategy bs = getBufferStrategy();
	if (bs == null){
            createBufferStrategy(3);			
            return;
	}
        
        for(int y=0; y<32;y++)
        {
            for(int x =0; x<32; x++)
            {
                screen.render(x<<3, y<<3, 0, Colours.get(555,500,050,005));
            }
        }
		
           for(int y=0; y<screen.height;y++)
        {
            for(int x =0; x<screen.width; x++)
            {
                int colourCode = screen.pixels[x+y*screen.width];
                if(colourCode<255)
                {
                    pixels[x+y*WIDTH]=colors[colourCode];
                }
            }
        }
        Graphics g = bs.getDrawGraphics();
		
	g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
		
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
	g.dispose();
	bs.show();
    }
}

