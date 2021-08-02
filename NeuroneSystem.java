import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class NeyroneSystem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NeuroneSystem extends Actor
{
    /**
     * Act - do whatever the NeyroneSystem wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    ArrayList<ArrayList<Double>> ns=new ArrayList<ArrayList<Double>>();
    int inputs;
    int outputs;
    
    int type;
    
    ArrayList<Integer> addneu=new ArrayList<Integer>();
    
    public void act() 
    {
        // Add your action code here.
    }    
    
    public void setType(int type1){
        type=type1;
    }
    
    public void createNN(int inputs1, int outputs1){
        inputs=inputs1;
        outputs=outputs1;
        for(int i=0;i<inputs+outputs;i++){
            ArrayList<Double> l=new ArrayList<Double>();
            l.add((double)0);
            if(i<inputs){
                l.add(0.0);
            }
            else{
                l.add((Math.random()*2)-1);
            }
            ns.add(l);
        }
        mutate();
    }
    
    public void createNN(ArrayList<ArrayList<Double>> ns1, int inputs1, int outputs1){
        inputs=inputs1;
        outputs=outputs1;
        for(int i=0;i<ns1.size();i++){
            ns.add((ArrayList<Double>)ns1.get(i).clone());
        }
        mutate();
    }
    
    public void mutate(){
        double rand=Math.random();
        if(rand<0.8){
            for(int i=inputs;i<ns.size();i++){
                for(int i1=1;i1<ns.get(i).size();i1+=2){
                    if(i1==1 && i<inputs){
                        if(ns.get(i).size()>3){
                            i1=3;
                        }
                        else{
                            break;
                        }
                    }
                    mutateWeight(i,i1);
                }
            }
        }
        
        if(type==0){
            double rand2=Math.random();
            if(rand2<0.08){
                addConnection();
            }
            
            double rand3=Math.random();
            if(rand2<0.01){
                addNode();
            }
        }
    }
    
    public void mutateWeight(int i, int i1){
        double rand1=Math.random();
        if(rand1>0.1){
            ns.get(i).set(i1,ns.get(i).get(i1)+(((Math.random()*2)-1)*0.1));
            if(ns.get(i).get(i1)>1){
                ns.get(i).set(i1,1.0);
            }
            else if(ns.get(i).get(i1)<-1){
                ns.get(i).set(i1,-1.0);
            }
        }
        else{
            ns.get(i).set(i1,(Math.random()*2)-1);
        }
    }
    
    double numofway;
    public void addConnection(){
        int i=inputs+Greenfoot.getRandomNumber(ns.size()-inputs);
        numofway=(double)Greenfoot.getRandomNumber(i-1);
        while(numofway>=ns.size()-outputs){
            numofway=(double)Greenfoot.getRandomNumber(i-1);
        }
        
        boolean addway=true;
        for(int i2=2;i2<ns.get(i).size();i2+=2){
            if(ns.get(i).get(i2)==numofway){
                addway=false;
            }
        }
        
        if(addway==true){
            ns.get(i).add(numofway);
            ns.get(i).add((Math.random()*2)-1);
        }
    }
    
    public void addNode(){
        int n1=Greenfoot.getRandomNumber(ns.size()-inputs)+inputs;
        if(n1<ns.size() && ns.get(n1).size()-2>0){
            int num=(Greenfoot.getRandomNumber(((ns.get(n1).size()-2)/2))*2)+2;
            int num1=ns.get(n1).get(num).intValue();
            ns.get(n1).remove(num);
            ns.get(n1).remove(num);
            
            ArrayList<Double> al1=new ArrayList<Double>();
            if(n1<ns.size()-outputs){
                al1.add((double)0);
                al1.add((Math.random()*2)-1);
                al1.add((double)num1);
                al1.add((Math.random()*2)-1);
                ns.add(n1,al1);
                addneu.add(n1);
                
                for(int i=inputs;i<ns.size();i++){
                    for(int i1=2;i1<ns.get(i).size();i1+=2){
                        for(int i2=0;i2<addneu.size();i2++){
                            if(ns.get(i).get(i1).intValue()>=addneu.get(i2)){
                                ns.get(i).set(i1, ns.get(i).get(i1)+1);
                            }
                        }
                    }
                }
                ns.get(n1+1).add((double)(n1));
                ns.get(n1+1).add((Math.random()*2)-1);
            }
            else{
                al1.add((double)0);
                al1.add((Math.random()*2)-1);
                al1.add((double)num1);
                al1.add((Math.random()*2)-1);
                addneu.add(ns.size()-outputs);
                ns.add(ns.size()-outputs,al1);
                
                for(int i=inputs;i<ns.size();i++){
                    for(int i1=2;i1<ns.get(i).size();i1+=2){
                        for(int i2=0;i2<addneu.size();i2++){
                            if(ns.get(i).get(i1).intValue()>=addneu.get(i2)){
                                ns.get(i).set(i1, ns.get(i).get(i1)+1);
                            }
                        }
                    }
                }
                ns.get(ns.size()-outputs).add((double)(ns.size()-outputs-1));
                ns.get(ns.size()-outputs).add((Math.random()*2)-1);
            }
        }
    }
    
    public void addSimpleConnecdtion(int index1, int index2){
        int i=index1;
        numofway=index2;
        boolean goodWay=true;
        
        if(numofway>=ns.size()-outputs){
            goodWay=false;
        }
        
        for(int i1=2;i1<ns.get(i).size();i1++){
            if(ns.get(i).get(i1)==numofway){
                goodWay=false;
            }
        }
        
        if(goodWay){
            ns.get(i).add(numofway);
            ns.get(i).add((Math.random()*2)-1);
        }
    }
    
    public void addSimpleNode(int index, ArrayList<Double> node){
        ArrayList<Double> n=new ArrayList<Double>();
        n=(ArrayList<Double>)node.clone();
        ns.add(index, n);
        
        addneu.add(index);
        for(int i=inputs;i<ns.size();i++){
            for(int i1=2;i1<ns.get(i).size();i1++){
                for(int i2=0;i2<addneu.size();i2++){
                    if(ns.get(i).get(i1)>=addneu.get(i2)){
                        ns.get(i).set(i1, ns.get(i).get(i1)+1);
                    }
                }
            }
        }
    }
    
    
    int maxlayer;
    ArrayList<Integer> layers=new ArrayList<Integer>();
    public void setLayers(){
        layers.clear();
        for(int i=inputs;i<ns.size()-outputs;i++){
            int mylayer=1;
            for(int i1=2;i1<ns.get(i).size();i1+=2){
                if(ns.get(i).get(i1).intValue()>inputs-1 && layers.size()>0 && layers.get(ns.get(i).get(i1).intValue()-inputs)>=mylayer){
                    mylayer=layers.get(ns.get(i).get(i1).intValue()-inputs)+1;
                }
            }
            layers.add(mylayer);
        }
        maxlayer=0;
        for(int i=0;i<layers.size();i++){
            if(layers.get(i)>maxlayer){
                maxlayer=layers.get(i);
            }
        }
        maxlayer++;
        for(int i=0;i<outputs;i++){
            layers.add(maxlayer);
        }
    }
    
    ArrayList<Integer> nodesinLayer=new ArrayList<Integer>();
    ArrayList<Integer> numinlayers=new ArrayList<Integer>();
    int maxnodesinlayer;
    int numinlayer=0;
    public GreenfootImage drawNN(){
        setLayers();
        
        nodesinLayer.clear();
        nodesinLayer.add(inputs);
        for(int i=0;i<maxlayer;i++){
            nodesinLayer.add(0);
        }
        
        maxnodesinlayer=0;
        for(int i=0;i<layers.size();i++){
            nodesinLayer.set(layers.get(i), nodesinLayer.get(layers.get(i))+1);
            if(nodesinLayer.get(layers.get(i))>maxnodesinlayer){
                maxnodesinlayer=nodesinLayer.get(layers.get(i));
            }
        }
        
        if(maxnodesinlayer<inputs){
            maxnodesinlayer=inputs;
        }
        
        numinlayers=(ArrayList<Integer>)layers.clone();
        for(int i1=0;i1<nodesinLayer.size();i1++){
            numinlayer=0;
            for(int i=0;i<layers.size();i++){
                if(layers.get(i)==i1){
                    numinlayers.set(i,numinlayer);
                    numinlayer++;
                }
            }
        }
        
        draw();
        return nsimage;
    }
    
    GreenfootImage nsimage;
    int neunum;
    public void draw(){
        nsimage=new GreenfootImage((maxlayer+1)*50,maxnodesinlayer*50);
        neunum=0;
        for(int i=0;i<nodesinLayer.size();i++){
            GreenfootImage layer=new GreenfootImage(50,nodesinLayer.get(i)*50);
            for(int i1=0;i1<nodesinLayer.get(i);i1++){
                if(ns.get(neunum).get(1)<0){
                    layer.setColor(new Color((int)(ns.get(neunum).get(1)*(-255)),0,0,255));
                }
                else{
                    layer.setColor(new Color(0,(int)(ns.get(neunum).get(1)*255),0,255));
                }
                layer.fillOval(20,20+(50*i1),9,9);
                neunum++;
            }
            nsimage.drawImage(layer,i*50,(nsimage.getHeight()/2)-(layer.getHeight()/2));
        }
        
        for(int i=inputs;i<ns.size();i++){
            for(int i1=2;i1<ns.get(i).size();i1+=2){
                if(ns.get(i).get(i1+1)<0){
                    nsimage.setColor(new Color((int)(ns.get(i).get(i1+1)*(-255)),0,0,255));
                }
                else{
                    nsimage.setColor(new Color(0,(int)(ns.get(i).get(i1+1)*255),0,255));
                }
                if(ns.get(i).get(i1).intValue()<inputs){
                    nsimage.drawLine((layers.get(i-inputs)*50)+25
                    ,(nsimage.getHeight()/2)-((nodesinLayer.get(layers.get(i-inputs))*50)/2)+(numinlayers.get(i-inputs)*50)+25
                    ,25
                    ,(nsimage.getHeight()/2)-((nodesinLayer.get(0)*50)/2)+(ns.get(i).get(i1).intValue()*50)+25);
                    
                    if(ns.get(i).get(1)<0){
                        nsimage.setColor(new Color((int)(ns.get(i).get(1)*(-255)),0,0,255));
                    }
                    else{
                        nsimage.setColor(new Color(0,(int)(ns.get(i).get(1)*255),0,255));
                    }
                    nsimage.fillOval((layers.get(i-inputs)*50)+20, (nsimage.getHeight()/2)-((nodesinLayer.get(layers.get(i-inputs))*50)/2)+(numinlayers.get(i-inputs)*50)+20,9,9);
                    
                    if(ns.get(ns.get(i).get(i1).intValue()).get(1)<0){
                        nsimage.setColor(new Color((int)(ns.get(ns.get(i).get(i1).intValue()).get(1)*(-255)),0,0,255));
                    }
                    else{
                        nsimage.setColor(new Color(0,(int)(ns.get(ns.get(i).get(i1).intValue()).get(1)*255),0,255));
                    }
                    nsimage.fillOval(20,(nsimage.getHeight()/2)-((nodesinLayer.get(0)*50)/2)+(ns.get(i).get(i1).intValue()*50)+20,9,9);
                }
                else{
                    nsimage.drawLine((layers.get(i-inputs)*50)+25
                    ,(nsimage.getHeight()/2)-((nodesinLayer.get(layers.get(i-inputs))*50)/2)+(numinlayers.get(i-inputs)*50)+25
                    ,(layers.get(ns.get(i).get(i1).intValue()-inputs)*50)+25
                    ,(nsimage.getHeight()/2)-((nodesinLayer.get(layers.get(ns.get(i).get(i1).intValue()-inputs))*50)/2)+(numinlayers.get(ns.get(i).get(i1).intValue()-inputs)*50)+25);
                    
                    if(ns.get(i).get(1)<0){
                        nsimage.setColor(new Color((int)(ns.get(i).get(1)*(-255)),0,0,255));
                    }
                    else{
                        nsimage.setColor(new Color(0,(int)(ns.get(i).get(1)*255),0,255));
                    }
                    nsimage.fillOval((layers.get(i-inputs)*50)+20,(nsimage.getHeight()/2)-((nodesinLayer.get(layers.get(i-inputs))*50)/2)+(numinlayers.get(i-inputs)*50)+20,9,9);
                    
                    if(ns.get(ns.get(i).get(i1).intValue()).get(1)<0){
                        nsimage.setColor(new Color((int)(ns.get(ns.get(i).get(i1).intValue()).get(1)*(-255)),0,0,255));
                    }
                    else{
                        nsimage.setColor(new Color(0,(int)(ns.get(ns.get(i).get(i1).intValue()).get(1)*255),0,255));
                    }
                    nsimage.fillOval((layers.get(ns.get(i).get(i1).intValue()-inputs)*50)+20
                    ,(nsimage.getHeight()/2)-((nodesinLayer.get(layers.get(ns.get(i).get(i1).intValue()-inputs))*50)/2)+(numinlayers.get(ns.get(i).get(i1).intValue()-inputs)*50)+20
                    ,9,9);
                }
            }
        }
    }
    
    double nz;
    public void think(){
        for(int i=inputs;i<ns.size();i++){
            nz=0;
            for(int i1=2;i1<ns.get(i).size();i1+=2){
                nz+=ns.get(ns.get(i).get(i1).intValue()).get(0)*ns.get(i).get(i1+1);
            }
            if(i<ns.size()-outputs){
                nz+=ns.get(i).get(1);
                ns.get(i).set(0,sigmoid(nz));
            }
            else{
                ns.get(i).set(0,ns.get(i).get(0)+nz);
            }
        }
    }
    
    public double sigmoid(double x){
        double y=1/(1+Math.pow(Math.E,-x*5));
        return y;
    }
    
    public double tanh(double x){
        double y=(2/(1+Math.pow(Math.E,-2*x)))-1;
        return y;
    }
    
    public double ReLu(double x){
        double y=Math.max(0,x);
        return y;
    }
    
    public double stepFun(double x, double step){
        double y=0;
        if(x>=step){
            y=1;
        }
        return y;
    }
    
    public void setInputVal(int index, double Val){
        if(index-1<=inputs){
            ns.get(index-1).set(0,Val);
        }
    }
    
    public boolean getBoolResults(int num){
        boolean dothis=false;
        if(num<=outputs){
            ns.get(ns.size()-outputs+(num-1)).set(0,sigmoid(ns.get(ns.size()-outputs+(num-1)).get(0)));
            if(ns.get(ns.size()-outputs+(num-1)).get(0)>0.5){
                dothis=true;
            }
            ns.get(ns.size()-outputs+(num-1)).set(0,0.0);
        }
        return dothis;
    }
    
    double outputValue;
    public double getResults(int num){
        if(num<=outputs){
            ns.get(ns.size()-outputs+(num-1)).set(0,sigmoid(ns.get(ns.size()-outputs+(num-1)).get(0)));
            outputValue=ns.get(ns.size()-outputs+(num-1)).get(0);
            ns.get(ns.size()-outputs+(num-1)).set(0,0.0);
        }
        return outputValue;
    }
}
