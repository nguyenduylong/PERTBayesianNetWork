/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bayes;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Truong
 */
public class BayesInActivity {

    public void initParents(Task task) {
        //khoi tao cac node cha me 
        ArrayList<Node> parentsEF = new ArrayList<Node>();
        parentsEF.add(task.earlyStart);
        parentsEF.add(task.duration);
        task.earlyFinish.setParents(parentsEF);
        ArrayList<Node> parentsLS = new ArrayList<Node>();
        parentsLS.add(task.latestFinish);
        parentsLS.add(task.duration);
        task.latestStart.setParents(parentsLS);       
        task.earlyStart.setParents(findParentsES(task));       
        task.latestFinish.setParents(findParentsLF(task));

    }

    public void initDependencies(Task task) {
        for (int i = 0; i < task.earlyStart.parents.size(); i++) {
            if (task.earlyStart.parents.get(i) != null) {

                //System.out.println("la?" + task.earlyStart.parents.size() + "?" + task.earlyStart.parents.get(0).getName());
                task.earlyStart.parents.get(i).addDep(task.earlyStart);
               // System.out.println("ở đây1 : "+task.earlyStart.getName()+":"+task.earlyStart.parents.get(i).getName()+":"+task.earlyStart.parents.get(i).dependencies.size());
            }
        }
        for (int i = 0; i < task.earlyFinish.parents.size(); i++) {
            //System.out.println("co vao k");
            if (task.earlyFinish.parents.get(i) != null) {
                task.earlyFinish.parents.get(i).dependencies.add(task.earlyFinish);
                //System.out.println("ở đây2 : "+task.earlyFinish.getName());
            }
        }
        for (int i = 0; i < task.latestStart.parents.size(); i++) {
            if (task.latestStart.parents.get(i) != null) {
                task.latestStart.parents.get(i).dependencies.add(task.latestStart);
            }
        }
        for (int i = 0; i < task.latestFinish.parents.size(); i++) {
            if (task.latestFinish.parents.get(i) != null) {
                task.latestFinish.parents.get(i).dependencies.add(task.latestFinish);
            }
        }
    }

    public ArrayList<Node> findParentsES(Task task) {
        // System.out.println("?là sao");
        ArrayList<Node> list = new ArrayList<>();
      
        if (task.parents.size() != 0) {
            Task[] ret = task.parents.toArray(new Task[0]);
            double value = ret[0].earlyFinish.getValue()[0];
           
            //System.out.println("value -" + value);
            for (int i = 0; i < ret.length; i++) {
                if (ret[i].earlyFinish.getValue()[0] > value) {
                    value = ret[i].earlyFinish.getValue()[0];
                    
                }
                list.add(ret[i].earlyFinish);
            }
            
        }
        return list;
    }

    public ArrayList<Node> findParentsLF(Task task) {
        ArrayList<Node> list = new ArrayList<>();
        if (task.dependencies.size() != 0) {
            Task[] ret = task.dependencies.toArray(new Task[0]);
            double value = ret[0].latestStart.getValue()[0];
           
            for (int i = 0; i < ret.length; i++) {
                if (ret[i].latestStart.getValue()[0] < value) {
                    value = ret[i].latestStart.getValue()[0];
                    
                }
                list.add(ret[0].earlyFinish);
            }          
        }
        return list;
    }

    public void initAllOfActivity(Task[] ret) {
        for (int i = 0; i < ret.length; i++) {
            initParents(ret[i]);
            ret[i].earlyStart.initProbability();
            ret[i].earlyFinish.initProbability();
            ret[i].latestFinish.initProbability();
            ret[i].latestFinish.initProbability();
        }
        for (int i = 0; i < ret.length; i++) {
            initDependencies(ret[i]);
        }

    }
}
