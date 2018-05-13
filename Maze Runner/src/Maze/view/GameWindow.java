/*
 * 
 */
package Maze.view;

import Maze.controller.InputHandler;
import Maze.view.GFX.Colours;
import Maze.view.GFX.Font;
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
import Maze.model.customLevel;
import Maze.model.entities.Player;

/**
 *
 * This is the game canvas class that contains nearly all the visuals and where the main movement lies.
 */
public class GameWindow extends Canvas implements Runnable{
    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    public static final int SCALE = 3;
    public boolean running=false;
    public int tickCount=0;
    
    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private int[] pixels= ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    /* creates an array of colors of six shades for every base color (R, G, B) */
    private int[] colors = new int[6*6*6];
    
    private Screen screen;
    public InputHandler input;
    
    public Player player;
    
    public customLevel level;
    
    public GameWindow()
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
        screen = new Screen(WIDTH,HEIGHT,new SpriteSheet("\\SpriteSheetV2.png"));
        input = new InputHandler(this);
        level = new customLevel("\\levels\\Level2.png");
        player = new Player(level,0,0,input);
        level.addEntity(player);
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
        MainWindow window = MainWindow.getInstance();
	tickCount++;
        if(input.p.isPressed())
        {
            MainWindow.getInstance().pause();
            System.out.println("pause");
        }        

        level.tick(); 
    }
    
    
    /* Note as to the Colouring algorithm:
        
        The Colors produced from the Colour Class are used to paint the grayscale components rendered from the spritesheet by
        this method and other similar methods in other classes. What it does is detect the shade of black; black/dark gray/ light gray/ white
        and maps the colors given according to it.
    
        For instance, if the screen is all black and the sent color to the Colour class is (000,......), the rendered object will be black,
        whereas(100,....) will give the black component a slight red shade. increasing the first number up to 5 gives a higher shade.
        Same apply to the dark gray (...,xxx,.....), light gray (...,....,xxx,...) and white (...,....,...,xxx)
    
        Thus, if the spritesheet component being rendered is white, the black, dark gray, light gray colour components are useless since they aren't
        actually there in the spritesheet (we render the background colour with -1 to remove it)
    
    */
    public void render()
    {
        /* BufferStrategy to prevent Flickering of the screen*/
        BufferStrategy bs = getBufferStrategy();
	if (bs == null){
            createBufferStrategy(3);			
            return;
	}
        
        int xOffset = player.x-(screen.width/2);
        int yOffset = player.y-(screen.height/2);
        level.renderTiles(screen, xOffset,yOffset);
        
        level.renderEntities(screen);
        
        /*   For Testing Purposes
        
        for(int y=0; y<32;y++)
        {
            for(int x =0; x<32; x++)
            {
                boolean flipX = x%2 == 1;
                boolean flipY = y%2 == 1;
                screen.render(x<<3, y<<3, 0, Colours.get(555,500,050,005),flipX,flipY);
            }
        }
	
        String msg = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String msg1 = "abcdefghijklmnopqrstuvwxyz";
        String msg2= "!@#$%^%^&*()-+= / ., ><";
        Font.render(msg, screen, 30, 30, Colours.get(-1, -1, -1, 555));
        Font.render(msg1, screen, 30, 60, Colours.get(-1, -1, -1, 555));
        Font.render(msg2, screen, 30, 90, Colours.get(-1, -1, -1, 555));
        */
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

