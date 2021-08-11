import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class MyBubble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyBubble extends Actor
{
    NeuroneSystem ns=new NeuroneSystem();
    int d=15;  
    GreenfootImage sprite;
    
    //GreenfootImage radius=new GreenfootImage(200,200);
    
    public MyBubble(ArrayList<ArrayList<Double>> ns1, int inp, int outp){
      paint();
      //radius.drawOval(0,0,200,200);
      if(ns1==null){
          ns.createNN(6, 2);
      }
      else{
          ns.createNN(ns1, inp, outp);
        }
    }
    
    public void paint(){
      sprite = new GreenfootImage(d, d);
        sprite.setColor(Color.RED);
        sprite.fillOval(0,0,d,d);
        setImage(sprite);  
    }
    int start;
    MyWorld mw;
    public void act() 
    {
        if(start==0){
            //getWorld().addObject(myRad,getX(),getY());
            start=1;
        }
        keyboard();
        // условия игры
        if(isTouching(Bubble.class)){
            //получить круг, которого коснулся
            Actor bubble = getOneIntersectingObject(Bubble.class);
            //сравнить диаметр круга со своим диаметром
            if(bubble.getImage().getWidth()<d){
             removeTouching(Bubble.class);
             d++;//увеличить свой диаметр
             paint();//перерисовать себя
             if(d==50){
             //getWorld().showText("ура, я самый большой!!!...", 200, 200);   
             //Greenfoot.stop();
             //if(getObjectsInRange(getWorld().getWidth(),MyBubble.class).size()==0){
                 MyWorld mw=(MyWorld)getWorld();
                 mw.sp.bestBubble=this;
             //}
                }             
            }
            else{
             //getWorld().showText("вас съели...", 200, 200); 
             mw=(MyWorld)getWorld();
             if(mw.sp.bestBubble!=null && mw.sp.bestBubble.d<=d){
                mw.sp.bestBubble=this;
             }
             else if(mw.sp.bestBubble==null){
                mw.sp.bestBubble=this;
             }
               /*for(int i1=0;i1<5;i1++){
                    if(mw.sp.bestBubble1.get(i1)!=null && d>mw.sp.bestBubble1.get(i1).d){
                        mw.sp.bestBubble1.set(i1,this);
                        break;
                    }
                    else if(mw.sp.bestBubble1.get(i1)==null){
                        mw.sp.bestBubble1.set(i1,this);
                        break;
                    }
               }*/
             getWorld().removeObject(this);
             //Greenfoot.stop();   
            }
        }
    }   
    
    public void think(Bubble b){
        if(b!=null){
            turnTowards(b.getX(), b.getY());
            for(int i=0;i<ns.inputs;i++){
                ns.setInputVal(i+1,0);
            }
            ns.setInputVal(1,(double)getRotation()/360);
            ns.setInputVal(2,(double)d1);
            ns.setInputVal(3,(double)(d-b.d)/50);
            //ns.setInputVal(5,(double)getX()/getWorld().getWidth());
            //ns.setInputVal(6,(double)getY()/getWorld().getHeight());
            ns.setInputVal(4,(double)b.getRotation()/360);
            ns.setInputVal(5,(double)getX()/getWorld().getWidth());
            ns.setInputVal(6,(double)getY()/getWorld().getHeight());
            /*if(getRotation()>315 || getRotation()<=45){
                ns.setInputVal(1,(double)getRotation()/360);
                ns.setInputVal(2,(double)d1);
                ns.setInputVal(3,(double)d-b.d/50);
            }
            else if(getRotation()>45 && getRotation()<=135){
                ns.setInputVal(4,(double)getRotation()/360);
                ns.setInputVal(5,(double)d1);
                ns.setInputVal(6,(double)d-b.d/50);
            }
            else if(getRotation()>135 && getRotation()<=225){
                ns.setInputVal(7,(double)getRotation()/360);
                ns.setInputVal(8,(double)d1);
                ns.setInputVal(9,(double)d-b.d/50);
            }
            else{
                ns.setInputVal(10,(double)getRotation()/360);
                ns.setInputVal(11,(double)d1);
                ns.setInputVal(12,(double)d-b.d/50);
            }*/
            setRotation(0);
            /*ns.setInputVal(1,(double)getX()-b.getX()/getWorld().getWidth());
            ns.setInputVal(2,(double)getY()-b.getY()/getWorld().getHeight());
            ns.setInputVal(3,(double)d-b.d/50);
            ns.setInputVal(4,Math.sqrt(Math.pow(getY()-b.getY(),2)+Math.pow(getX()-b.getX(),2))/500);
            ns.setInputVal(5,(double)b.getRotation()/360);*/
            ns.think();
        }
        else{
            ns.setInputVal(1,(double)0);
            ns.setInputVal(2,(double)0);
            ns.setInputVal(3,(double)0);
            ns.setInputVal(4,(double)0);
            ns.setInputVal(5,(double)getX()/getWorld().getWidth());
            ns.setInputVal(6,(double)getY()/getWorld().getHeight());
            ns.think();
        }
    }
    
    int movex;
    int movey;
    
    double d1;
    Bubble b2;
    Bubble b3;
    
    int r;
    
    double value1;
    double value;
    int index;
    //Radius myRad=new Radius();;
    //управление машиной с клавиатуры
    int speed;
    int rot;
    public void keyboard(){ 
        d1=(double)getWorld().getWidth();
        b3=null;
        for(int i=0;i<getObjectsInRange(100, Bubble.class).size();i++){
            b2=getObjectsInRange(100, Bubble.class).get(i);
            if(Math.sqrt(Math.pow(b2.getX()-getX(),2)+Math.pow(b2.getY()-getY(),2))-b2.d<d1){
                b3=b2;
                d1=Math.sqrt(Math.pow(b2.getX()-getX(),2)+Math.pow(b2.getY()-getY(),2));
            }
            //think(getObjectsInRange(getWorld().getWidth(), Bubble.class).get(i));
        }
        d1/=100;
        think(b3);
        /*myRad.setLocation(getX(),getY());
        radius.setColor(new Color(0,0,0,0));
        radius.fill();
        radius.setColor(Color.BLACK);
        radius.drawOval(getX()-100,getY()-100,200,200);
        if(b3!=null){
            radius.drawLine(100,100,b3.getX()-getX()+100,b3.getY()-getY()+100);
        }
        myRad.setImage(radius);*/
        movey=0;
        movex=0;
        /*value1=0;
        index=5;
        //r=(int)(ns.getResults(1)*360);
        for(int i=0;i<5;i++){
            value1=ns.getResults(i+1);
            if(value1>=value){
                value=value1;
                index=i+1;
            }
        }
        if(index==1){
            movex++;
        }
        if(index==2){
            movey++;
        }
        if(index==3){
            movex--;
        }
        if(index==4){
            movey++;
        }
        if(index==5){
            movex=0;
            movey=0;
        }*/
        /*if(ns.getResults(5)){
            movex=0;
            movey=0;
        }*/
        rot=(int)(ns.getResults(1)*360);
        speed=0;
        if(ns.getBoolResults(2)){
            speed=1;
        }
        if(rot>315 || rot<=45){
            movex=speed;
        }
        else if(rot>45 && rot<=135){
            movey=speed;
        }
        else if(rot>135 && rot<=225){
            movex=-speed;
        }
        else{
            movey=-speed;
        }
            
         if (movey>0)
        {
        setLocation(getX(), getY()+2);
        }
        if (movey<0)
        {
        setLocation(getX(), getY()-2);
        }
        if (movex>0)
        {
        setLocation(getX()+2, getY());
        }
        if (movex<0)
        {
        setLocation(getX()-2, getY());
        }
        if(getX()==0){
            setLocation(getWorld().getWidth()-2, getY());
        }
        if(getX()==getWorld().getWidth()-1){
            setLocation(1, getY());
        }
        if(getY()==0){
            setLocation(getX(), getWorld().getHeight()-2);
        }
        if(getY()==getWorld().getHeight()-1){
            setLocation(getX(), 1);
        }
    }
}
