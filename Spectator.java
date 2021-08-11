import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.ArrayList;
/**
 * Write a description of class Spectator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spectator extends Actor
{
    /**
     * Act - do whatever the Spectator wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    ArrayList<MyBubble> bestBubble1=new ArrayList<MyBubble>();
    MyBubble bestBubble;
    MyBubble thisBubble;
    
    Ser ser;
    public Spectator(){
        GreenfootImage im=new GreenfootImage(1,1);
        setImage(im);
        for(int i1=0;i1<5;i1++){
            bestBubble1.add(null);
        }
    }
    public void act() 
    {
        /*if(getObjectsInRange(getWorld().getWidth(),MyBubble.class).size()>0){
            bestBubble=null;
            for(int i=0;i<getObjectsInRange(getWorld().getWidth(),MyBubble.class).size();i++){
                thisBubble=getObjectsInRange(getWorld().getWidth(),MyBubble.class).get(i);
                if(bestBubble!=null && thisBubble.d>bestBubble.d && bestBubble.d<50){
                    bestBubble=thisBubble;
                }
                else if(bestBubble==null){
                    bestBubble=thisBubble;
                }
                for(int i1=0;i1<5;i1++){
                    bestBubble1.add(null);
                }
                for(int i1=0;i1<5;i1++){
                    if(bestBubble1.get(i1)!=null && thisBubble.d>bestBubble1.get(i1).d){
                        bestBubble1.set(i1,thisBubble);
                        break;
                    }
                    else if(bestBubble1.get(i1)==null){
                        bestBubble1.set(i1,thisBubble);
                        break;
                    }
                }
            }
        }*/
        //Greenfoot.setSpeed(50+getObjectsInRange(getWorld().getWidth(),MyBubble.class).size()*3);
        if(getObjectsInRange(getWorld().getWidth(),MyBubble.class).size()==0 || bestBubble!=null && bestBubble.d==50){
            MyWorld mw=(MyWorld)getWorld();
            mw.setBBubble(bestBubble);
            save();
            for(int i=0;i<getObjectsInRange(mw.getWidth(), Bubble.class).size();i++){
                mw.removeObject(getObjectsInRange(mw.getWidth(), Bubble.class).get(i));
            }
            mw.prepare();
            bestBubble=null;
        }// Add your action code here.
    }    
    
    public void save(){
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("save.ser"));
            
            ser=new Ser();
            ser.save(bestBubble.ns);
            os.writeObject(ser);
            
            os.close();
        }catch(Exception e){System.out.println(e);}
    }
}
