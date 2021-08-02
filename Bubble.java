import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bubble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bubble extends Actor
{
    int d;  
    GreenfootImage sprite;
    
    public Bubble(int d1){
        d = Greenfoot.getRandomNumber(50)+1;
        setRotation(Greenfoot.getRandomNumber(180));
        //рисование круга
        sprite = new GreenfootImage(d, d);
        sprite.setColor(Color.GREEN);
        sprite.drawOval(0,0,d,d);
        setImage(sprite);
    }
    
    public void act() 
    {
       move(1);
       if(isAtEdge()){//отталкивается от стены и поворачивает
          turn( Greenfoot.getRandomNumber(180));
        }
    }    
}
