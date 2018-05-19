/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Bayes.TimeCalculation;
import Bayes.Task;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Long
 */
public class Result extends JPanel {

    public Result( ArrayList<Task> timTaskCuoi1) {
        TimeCalculation tc = new TimeCalculation();
        Task task1 = null;
        Task task2 = null;
        Task task3 = null;
        this.setLayout(null);
        for (int i = 0; i < timTaskCuoi1.size(); i++) {
            task1 = timTaskCuoi1.get(i);
            if (task1 != null) {
                    DoThi doThi = new DoThi(tc.duLieu(task1),task1.name);
                    doThi.setSize(400, 400);
                    doThi.setLocation(i * 410, 0);
                    this.add(doThi);
            }
        }
    }
}
