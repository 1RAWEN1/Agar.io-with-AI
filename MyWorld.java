import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.ArrayList;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        //prepare();
    }
    MyBubble bestBubble;
    ArrayList<MyBubble> bBubble;
    int start;
    int size=1;
    int bnum;
    public void act(){
        if(start==0){
            prepare();
            start=1;
        }
        if(Greenfoot.getRandomNumber(1000)<12){//частота появления кругов
            Bubble b = new Bubble(size);
            //круги появляются сверху
            addObject(b,Greenfoot.getRandomNumber(500)+50, 0);
            bnum++;
        }
        if(bnum==2){
            size++;
            bnum=0;
        }
        //background1=new GreenfootImage(background);
    }
    
    public void setBBubble(MyBubble b){
        bestBubble=b; 
    }
    
    public void setBBubble(ArrayList<MyBubble> bb1){
        bBubble=(ArrayList<MyBubble>)bb1.clone();
    }
    
    //GreenfootImage background=getBackground();
    GreenfootImage background1;
    GreenfootImage nsimage;
    Ser ser1;
    Spectator sp;
    private void prepare()
    {
        sp=new Spectator();
        addObject(sp,400,300);
        if(bestBubble==null){
            try{
                ObjectInputStream ois=new ObjectInputStream(new FileInputStream("save.ser"));
               
                ser1 = (Ser)ois.readObject();
               
                ois.close();
            }catch(Exception e){System.out.println(e);}
        }
        if(ser1!=null){
            for(int i=0;i<15;i++){
                MyBubble myBubble = new MyBubble(ser1.nodes, ser1.inputs,ser1.outputs);
                addObject(myBubble,397,324);
            }
        }
        else if(bBubble!=null){
            for(int i=0;i<15;i++){
                MyBubble myBubble = new MyBubble(bBubble.get(i/3).ns.ns, bBubble.get(i/3).ns.inputs,bBubble.get(i/3).ns.outputs);
                addObject(myBubble,397,324);
            }
        }
        else if(bestBubble!=null){
            for(int i=0;i<15;i++){
                MyBubble myBubble = new MyBubble(bestBubble.ns.ns, bestBubble.ns.inputs,bestBubble.ns.outputs);
                addObject(myBubble,397,324);
            }
        }
        else{
            for(int i=0;i<15;i++){
                MyBubble myBubble = new MyBubble(null, 0 ,0);
                addObject(myBubble,397,324);
            }
        }
        if(bestBubble!=null || bBubble!=null){
            if(bBubble!=null){
                bestBubble=bBubble.get(0);
            }
            getBackground().setColor(Color.WHITE);
            getBackground().fill();
            addObject(new Label("Функция приспособленности: "+(double)bestBubble.d/50,20), 150, 30);
            nsimage=bestBubble.ns.drawNN();
            getBackground().drawImage(nsimage,400-nsimage.getWidth()/2,300-nsimage.getHeight()/2);
            //setBackground(background);
        }
    }
}
