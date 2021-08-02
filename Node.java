import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.io.*;
/**
 * Write a description of class Node here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Node extends Actor implements Serializable
{
    /**
     * Act - do whatever the Node wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int layer;
    double outputVal;
    double bias;
    ArrayList<Integer> connections=new ArrayList<Integer>();
    ArrayList<Double> weight=new ArrayList<Double>();
    public void act() 
    {
        // Add your action code here.
    }  
    
    public void createNode(ArrayList<Integer> connections1, ArrayList<Double> weight1, double bias1){
        connections=(ArrayList<Integer>)connections1.clone();
        weight=(ArrayList<Double>)weight1.clone();
        bias=bias1;
    }
    
    public void summ(ArrayList<Node> nodes1){
        outputVal=0;
        for(int i=0;i<connections.size();i++){
            outputVal+=nodes1.get(connections.get(i)).outputVal*weight.get(i);
        }
        outputVal+=bias;
        if(layer!=0){
            outputVal=sigmoid(outputVal);
        }
    }
    
    public void summ1(ArrayList<Node> nodes1){
        for(int i=0;i<connections.size();i++){
            outputVal+=nodes1.get(connections.get(i)).outputVal*weight.get(i);
        }
    }
    
    public double sigmoid(double x){
        double y=1/(1+Math.pow(Math.E,-x));
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
}
