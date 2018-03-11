/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bayes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

/**
 *
 * @author Long
 */
public class Task {
    // the duration the task
    public Node duration;
    //the total duration of the task
    public Node totalDuration;
    // the earliest start
    public Node earlyStart;
    // the earliest finish
    public Node earlyFinish;
    // the latest start
    public Node latestStart;
    // the latest finish
    public Node latestFinish;
    
    public double maxCost;
    
    public String name;
    // the cost of the task along the critical path
    public Node criticalCost;
    //risk file name 
    public String riskFile; 
    // a name for the task for printing
    public HashSet<Task> dependencies = new HashSet<Task>();
    public HashSet<Task> parents = new HashSet<>();

    public Task(String name,String riskFile, Node duration, Node totalDuration, Task... dependencies) {
        this.name = name;
        this.riskFile = riskFile;
        criticalCost = new Node(name + ".BP");
        earlyFinish = new Node(name + ".EF");
        earlyStart = new Node(name + ".ES");
        latestStart = new Node(name + ".LS");
        latestFinish = new Node(name + ".LF");

        this.duration = duration;
        this.totalDuration = totalDuration;
        this.maxCost = maxCost;
        for (Task t : dependencies) {
            this.dependencies.add(t);
        }
      
        for (int i = 0; i < 5; i++) {
            this.earlyFinish.getValue()[i] = -1;
        }
    }
    public Task(String name, Task... dependencies) {
        this.name = name;
        criticalCost = new Node(name + ".BP");
        earlyFinish = new Node(name + ".EF");
        earlyStart = new Node(name + ".ES");
        latestStart = new Node(name + ".LS");
        latestFinish = new Node(name + ".LF");

        this.duration = duration;
        this.maxCost = maxCost;
        for (Task t : dependencies) {
            this.dependencies.add(t);
        }
      
        for (int i = 0; i < 5; i++) {
            this.earlyFinish.getValue()[i] = -1;
        }
    }

    public double getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(double maxCost) {
        this.maxCost = maxCost;
    }

    public void setLatest(double[] maxCost) {
        
        for (int i = 0; i < 5; i++) {
            double t = criticalCost.getValue()[i];
            latestStart.getValue()[i] = maxCost[i] - t;
            latestFinish.getValue()[i] = (latestStart.getValue()[i] + duration.getValue()[i]);
            
        }
    }

    public ArrayList< String[]> toStringArray() {
        ArrayList<String[]> list = new ArrayList<>();
       
        for (int i = 0; i < 5; i++) {
            double soSanh = latestStart.getValue()[i] - earlyStart.getValue()[i];
            if (soSanh < 0.0001) {
                soSanh = 0;
            }
            String criticalCond = soSanh == 0 ? "Yes" : "No";
            String[] toString = {name, earlyStart.getValue()[i] + "", earlyFinish.getValue()[i] + "", latestStart.getValue()[i] + "", latestFinish.getValue()[i] + "",
                soSanh + "", criticalCond};
            list.add(toString);
        }
        return list;
    }

    public HashSet<Task> getDependencies() {
        return dependencies;
    }

    public void setDependencies(HashSet<Task> dependencies) {
        this.dependencies = dependencies;
    }
    

    public boolean isDependent(Task t) {
        // is t a direct dependency?
        if (dependencies.contains(t)) {
            return true;
        }
        // is t an indirect dependency
        for (Task dep : dependencies) {
            if (dep.isDependent(t)) {
                return true;
            }
        }
        return false;
    }
    public Vector taskToVector(){
        Vector v = new Vector();
        v.add(this.name);
         Task[] ret = getDependencies().toArray(new Task[0]);
         String str ="";
       for(int i =0; i < ret.length; i++){
           str +=ret[i].name+",";
       }
       v.add(str);
       return v;
    }
}
