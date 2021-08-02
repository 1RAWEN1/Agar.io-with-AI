import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.io.*;
/**
 * Write a description of class Ser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ser extends Actor implements Serializable
{
    /**
     * Act - do whatever the Ser wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    ArrayList<ArrayList<Double>> nodes=new ArrayList<ArrayList<Double>>();
    int inputs;
    int outputs;
    public void act() 
    {
        // Add your action code here.
    }   
    
    public void save(NeuroneSystem ns){
        nodes=(ArrayList<ArrayList<Double>>)ns.ns.clone();
        inputs=ns.inputs;
        outputs=ns.outputs;
    }
}
