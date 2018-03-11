/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bayes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Long
 */
public class IOConditionalProbability {

    Task[] result;
    Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public IOConditionalProbability(Task[] result) {
        this.result = result;
    }

    public IOConditionalProbability() {
    }

    public void inputTask(Task task) {
        ConditionalProbability con1 = new ConditionalProbability(0.9, task.name + ".EF1", "D1", task.name + ".ES1");
        ConditionalProbability con2 = new ConditionalProbability(0.5, task.name + ".EF1", "D1", task.name + ".ES2");
        ConditionalProbability con3 = new ConditionalProbability(0.5, task.name + ".EF1", "D2", task.name + ".ES1");
        ConditionalProbability con4 = new ConditionalProbability(0.1, task.name + ".EF1", "D2", task.name + ".ES2");
        ConditionalProbability con5 = new ConditionalProbability(0.1, task.name + ".EF2", "D1", task.name + ".ES1");
        ConditionalProbability con6 = new ConditionalProbability(0.5, task.name + ".EF2", "D1", task.name + ".ES2");
        ConditionalProbability con7 = new ConditionalProbability(0.5, task.name + ".EF2", "D2", task.name + ".ES1");
        ConditionalProbability con8 = new ConditionalProbability(0.9, task.name + ".EF2", "D2", task.name + ".ES2");
        task.earlyFinish.getListConditional().add(con1);
        task.earlyFinish.getListConditional().add(con2);
        task.earlyFinish.getListConditional().add(con3);
        task.earlyFinish.getListConditional().add(con4);
        task.earlyFinish.getListConditional().add(con5);
        task.earlyFinish.getListConditional().add(con7);
        task.earlyFinish.getListConditional().add(con6);
        task.earlyFinish.getListConditional().add(con8);
        // P(A.EF1/A.D1, A.ES1) = ....
        if (task.earlyStart.parents.size() > 0) {
            ArrayList<String> result = new ArrayList<>();
            ArrayList<Double> luuXacSuat = new ArrayList<>();
            int n = task.earlyStart.parents.size()+1;
            TRY("", n, result);
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
                int[] indexs = new int[n];
                for (int j = 0; j < n; j++) {
                    indexs[j] = result.get(i).charAt(j) - 48;
                }

                String str = "P(" + task.name + ".ES" + indexs[n - 1] + "/";
                String[] cha = new String[n - 1];
                for (int j = 0; j < n - 2; j++) {
                    str += task.earlyStart.parents.get(j).getName() + indexs[j] + ",";
                    cha[j] = task.earlyStart.parents.get(j).getName() + indexs[j];
                }
                str += task.earlyStart.parents.get(n - 2).getName() + indexs[n - 2] + ")=";
                System.out.println(str);
                 double giatri=0;
                if(i ==0){
                    giatri = 0.9;
                    luuXacSuat.add(i, giatri);
                } else
                if (i % 2 == 0 && i >0) {
                     giatri = 0.1;
                     luuXacSuat.add(i, giatri);
                } else if(i%2 ==1){
                    giatri = 1-luuXacSuat.get(i-1);
                    luuXacSuat.add(i, giatri);
                }
              
               // double giatri = input.nextDouble();
                cha[n - 2] = task.earlyStart.parents.get(n - 2).getName() + indexs[n - 2];
                ConditionalProbability pro = new ConditionalProbability(giatri, task.name + ".ES" + indexs[n - 1], cha);
                task.earlyStart.getListConditional().add(pro);
            }
             
        }
    }

    public void TRY(String str, int len, ArrayList<String> result) {
        if (str.length() < len) {
            for (int i = 1; i < 3; i++) {
                TRY(str + String.valueOf(i), len, result);
            }
        } else {
            result.add(new String(str));
        }
    }

    public static void main(String[] args) {
        ArrayList<String> result = new ArrayList<>();
        IOConditionalProbability demo = new IOConditionalProbability();
        int n = 3;
        demo.TRY("", n, result);
        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
            int[] indexs = new int[n];
            for (int j = 0; j < n; j++) {
                indexs[j] = result.get(i).charAt(j) - 48;
            }
            String[] parent = {"A", "B", "C"};
            String str = "P(D" + indexs[n - 1] + "/";
            for (int j = 0; j < n - 2; j++) {
                str += parent[j] + indexs[j] + ",";
            }
            str += parent[n - 2] + indexs[n - 2] + ")=";
            System.out.println(str);
        }

    }

    public void inputListTask() {
        for (int i = 0; i < result.length; i++) {
            inputTask(result[i]);
        }
    }
    //

}
