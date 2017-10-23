/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bayes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Truong
 */
public class CriticalPath {

    public   double[] maxCost = new double[5];
    public  String format = "%1$-10s %2$-5s %3$-5s %4$-5s %5$-5s %6$-5s %7$-10s\n";
    public Set<Task> tasks;
    BayesCalculation bayesCalculation;

    public CriticalPath(Set<Task> tasks) {
        this.tasks = tasks;

    }

    public CriticalPath() {
    }

    public static void main(String[] args) {
        CriticalPath c = new CriticalPath();
        c.run1();
        //c.run2();
       

    }

    public void run1() {
        System.out.println("**************Thay đổi thới gian*************");
        HashSet<Task> allTasks = new HashSet<Task>();
        
        InitTotalDuration DA = new InitTotalDuration();
        DA.innitTotalDuration(5,6,7,5.5);
        Node costA = DA.getDuration();
        InitTotalDuration DB = new InitTotalDuration();
        DB.innitTotalDuration(5, 7, 8.5, 7);
        Node costB = DB.getDuration();
        InitTotalDuration DC = new InitTotalDuration();
        DC.innitTotalDuration(6, 8, 8.5, 8);
        Node costC = DC.getDuration();
        InitTotalDuration DD = new InitTotalDuration();
        DD.innitTotalDuration(2,4,6,4);
        Node costD = DD.getDuration();
        Task D = new Task("D", costD);
        Task C = new Task("C",costC ,D);
        Task B = new Task("B", costB, D);
        Task A = new Task("A", costA, B);
        allTasks.add(A);
        allTasks.add(B);
        allTasks.add(C);
        allTasks.add(D);
        System.out.println("Cost :" +costA.getProbability().get(0)[0].getValue());
        CriticalPath cri = new CriticalPath(allTasks);
        cri.findParents(cri.tasks);        
        Task[] result = cri.criticalPath(cri.tasks);
        cri.print(result);
        BayesInActivity bay = new BayesInActivity();
        //tao bayesian trong tung hoat dong
        bay.initAllOfActivity(result);
        IOConditionalProbability IO = new IOConditionalProbability(result);
        IO.inputListTask();
        BayesCalculation bay1 = new BayesCalculation(result);
        bay1.calculateTask();
        cri.print(result);
//        for(int i = 0 ; i < 5 ; i++){
//            System.out.println("D:"+ D.earlyFinish.getProbability().get(i)[0].getValue());
//
//        }
    }
    public void run(HashSet<Task> allTasks){
        //CriticalPath cri = new CriticalPath(allTasks);
        findParents(allTasks);        
        Task[] result = criticalPath(allTasks);
        //print(result);
        BayesInActivity bay = new BayesInActivity();
        bay.initAllOfActivity(result);
        IOConditionalProbability IO = new IOConditionalProbability(result);
        IO.inputListTask();
        BayesCalculation bay1 = new BayesCalculation(result);
        bay1.calculateTask();
        
    }
    public ArrayList<Task> timTaskCuoi(HashSet<Task> allTasks){
        ArrayList<Task> timTaskCuoi = new ArrayList<>();
         Task[] result = allTasks.toArray(new Task[0]);
        for(int i =0; i < result.length; i++){
            if(result[i].getDependencies().size() ==0){
                timTaskCuoi.add(result[i]);
            }
        }
        return timTaskCuoi;
    }
    public ArrayList<ArrayList<Double>> duLieu(Task task){
        ArrayList<ArrayList<Double>> duLieu = new ArrayList<>();
        for(int i =0; i <5; i++){
            ArrayList<Double> arr = new ArrayList<>();
            arr.add(task.earlyFinish.getValue()[i]);
            arr.add(task.earlyFinish.getProbability().get(i)[0].getValue()*100);
            duLieu.add(arr);
        }
        return duLieu;
    }
     public ArrayList<ArrayList<Double>> duLieuCost(Task task){
        ArrayList<ArrayList<Double>> duLieu = new ArrayList<>();
        for(int i =0; i <5; i++){
            ArrayList<Double> arr = new ArrayList<>();
            arr.add(task.duration.getValue()[i]);
            arr.add(task.duration.getProbability().get(i)[0].getValue());
            duLieu.add(arr);
        }
        return duLieu;
    }
     public ArrayList<ArrayList<Double>> duLieuEF(Task task){
        ArrayList<ArrayList<Double>> duLieu = new ArrayList<>();
        for(int i =0; i <5; i++){
            ArrayList<Double> arr = new ArrayList<>();
            arr.add(task.earlyFinish.getValue()[i]);
            arr.add(task.earlyFinish.getProbability().get(i)[0].getValue());
            duLieu.add(arr);
        }
        return duLieu;
    }
      public ArrayList<ArrayList<Double>> duLieuES(Task task){
        ArrayList<ArrayList<Double>> duLieu = new ArrayList<>();
        for(int i =0; i <5; i++){
            ArrayList<Double> arr = new ArrayList<>();
            arr.add(task.earlyStart.getValue()[i]);
            arr.add(task.earlyStart.getProbability().get(i)[0].getValue());
            duLieu.add(arr);
        }
        return duLieu;
    }

    public Task[] criticalPath(Set<Task> tasks) {
        // tasks whose critical cost has been calculated
        HashSet<Task> completed = new HashSet<Task>();
        // tasks whose critical cost needs to be calculated
        HashSet<Task> remaining = new HashSet<Task>(tasks);
        while (!remaining.isEmpty()) {
            boolean progress = false;
            for (Iterator<Task> it = remaining.iterator(); it.hasNext();) {
                Task task = it.next();
                if (completed.containsAll(task.dependencies)) {                   
                    for (int i = 0; i < 5; i++) {
                        double critical = 0;
                        for (Task t : task.dependencies) {

                            if (t.criticalCost.getValue()[i] > critical) {
                                critical = new MathForDummies().round(t.criticalCost.getValue()[i],3);
                            }
                        }
                        task.criticalCost.getValue()[i] = (critical + new MathForDummies().round(task.duration.getValue()[i],3));
                    }
                    // set task as calculated an remove
                    completed.add(task);
                    it.remove();
                    // note we are making progress
                    progress = true;
                }
            }
            // If we haven't made any progress then a cycle must exist in
            // the graph and we wont be able to calculate the critical path
            if (!progress) {
                throw new RuntimeException("Cyclic dependency, algorithm stopped!");
            }
        }
        // get the cost
        maxCost(tasks);
        HashSet<Task> initialNodes = initials(tasks);
        calculateEarly(initialNodes);

        // get the tasks
        Task[] ret = completed.toArray(new Task[0]);
        // create a priority list
        Arrays.sort(ret, new Comparator<Task>() {

            @Override
            public int compare(Task o1, Task o2) {
                return o1.name.compareTo(o2.name);
            }
        });

        return ret;
    }

    public void calculateEarly(HashSet<Task> initials) {
        
        for (int i = 0; i < 5; i++) {
            for (Task initial : initials) {
                initial.earlyStart.getValue()[i] = 0;
                initial.earlyFinish.getValue()[i] = new MathForDummies().round(initial.duration.getValue()[i],3);
                setEarly(initial);
            }
        }
    }

    public void setEarly(Task initial) {
       
        for (int i = 0; i < 5; i++) {
            double completionTime = initial.earlyFinish.getValue()[i];
            for (Task t : initial.dependencies) {
                if (completionTime >= t.earlyStart.getValue()[i]) {
                    t.earlyStart.getValue()[i] = new MathForDummies().round(completionTime,3);
                    t.earlyFinish.getValue()[i] = new MathForDummies().round(completionTime + t.duration.getValue()[i],3);
                }
                setEarly(t);
            }
        }
    }

    public ArrayList<Task> findParents(Set<Task> tasks) {
        ArrayList<Task> listTask = new ArrayList<>();
        for (Task t : tasks) {
            for (Task t1 : t.dependencies) {
                t1.parents.add(t);
            }
            listTask.add(t);
        }
        return listTask;
    }

    public HashSet<Task> initials(Set<Task> tasks) {
        HashSet<Task> remaining = new HashSet<Task>(tasks);
        for (Task t : tasks) {
            for (Task td : t.dependencies) {
                remaining.remove(td);
            }
        }

        System.out.print("Initial nodes: ");
        for (Task t : remaining) {
            System.out.print(t.name + " ");
        }
        return remaining;
    }

    public void maxCost(Set<Task> tasks) {
       
        for (int i = 0; i < 5; i++) {
            double max = -1;
            for (Task t : tasks) {
                if (t.criticalCost.getValue()[i] > max) {
                    max = new MathForDummies().round(t.criticalCost.getValue()[i],3);
                }
            }
            maxCost[i]= max;

            for (Task t : tasks) {
                t.setLatest(maxCost);
            }
        }

    }
    int i = 0;

    public void print(Task[] tasks) {

        for (int i = 0; i < 5; i++) {
            System.out.format(format, "Task", "ES", "EF", "LS", "LF", "Slack", "Critical?");
            for (Task t : tasks) {
                System.out.format(format, (Object[]) t.toStringArray().get(i));
            }
        }
    }
}
// A wrapper class to hold the tasks during the calculation

