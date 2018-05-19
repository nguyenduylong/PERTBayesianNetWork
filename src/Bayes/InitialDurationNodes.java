/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bayes;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Long
 */
public class InitialDurationNodes {

    String taskName;
    Node estimatedDuration;
    Node totalDuration;
    Node riskEvent;
    
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Node getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Node duration) {
        this.estimatedDuration = duration;
    }
    
    public Node getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Node totalDuration) {
        this.totalDuration = totalDuration;
    }
    
        public Node getRiskEvent() {
        return riskEvent;
    }

    public void setRiskEvent(Node riskEvent) {
        this.riskEvent = riskEvent;
    }
    
    public InitialDurationNodes() {
        
        estimatedDuration = new Node("Duration");
        totalDuration = new Node("D");
        riskEvent = new Node("Risk Event");
    }

    public void initDuration(double trungbinh, double xichma, double giatri) {
        estimatedDuration.setValue(getValueList(giatri));
        estimatedDuration.setProbability(probability(getValueList(giatri),trungbinh,xichma));

    }
    
    public void initRisk(String riskFile){
        Double[] riskProbability = new Double[23];
		
        try {
            // Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
            FileInputStream fis = new FileInputStream(riskFile);
            DataInputStream dis = new DataInputStream(fis);

            // Bước 2: Đọc dữ liệu				
            for (int i = 0; i < 23; i++) {				
		riskProbability[i] = dis.readDouble();				
            }
            System.out.println("total risk : " + riskProbability[22]);
            ArrayList<FieldProbability[]> arrP = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                FieldProbability[] list = new FieldProbability[2];
                FieldProbability pro1 = new FieldProbability(riskProbability[22], riskEvent.getName() + "1");
                FieldProbability pro2 = new FieldProbability(1-riskProbability[22], riskEvent.getName() + "2");
                list[0] = pro1;
                list[1] = pro2;
                arrP.add(list);
            }

            riskEvent.setProbability(arrP);
			// Bước 3: Đóng luồng
            fis.close();
            dis.close();
            
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void innitTotalDuration(double valu1, double valu2, double valu3, String riskFile) {
        double trungbinh = (valu1+ 4*valu2 + valu3)/6;
        double xichma = (valu3 - valu1)/6;
        
        initDuration(trungbinh,xichma,valu2);
        System.out.println(riskFile);
        initRisk(riskFile);
        
        totalDuration.getParents().add(estimatedDuration);
        totalDuration.getParents().add(riskEvent);
        
        ConditionalProbability con1 = new ConditionalProbability(1, "D1", estimatedDuration.getName() + "1", riskEvent.getName() + "2");
        ConditionalProbability con2 = new ConditionalProbability(0, "D2", estimatedDuration.getName() + "1", riskEvent.getName() + "2");
        ConditionalProbability con3 = new ConditionalProbability(0.7, "D1", estimatedDuration.getName() + "1", riskEvent.getName() + "1");
        ConditionalProbability con4 = new ConditionalProbability(0.3, "D2", estimatedDuration.getName() + "1", riskEvent.getName() + "1");
        ConditionalProbability con5 = new ConditionalProbability(0.7, "D1", estimatedDuration.getName() + "2", riskEvent.getName() + "2");
        ConditionalProbability con6 = new ConditionalProbability(0.3, "D2", estimatedDuration.getName() + "2", riskEvent.getName() + "2");
        ConditionalProbability con7 = new ConditionalProbability(0, "D1", estimatedDuration.getName() + "2", riskEvent.getName() + "1");
        ConditionalProbability con8 = new ConditionalProbability(1, "D2", estimatedDuration.getName() + "2", riskEvent.getName() + "1");
        totalDuration.getListConditional().add(con1);
        totalDuration.getListConditional().add(con2);
        totalDuration.getListConditional().add(con3);
        totalDuration.getListConditional().add(con4);
        totalDuration.getListConditional().add(con5);
        totalDuration.getListConditional().add(con6);
        totalDuration.getListConditional().add(con7);
        totalDuration.getListConditional().add(con8);
        totalDuration.initProbability();
        BayesCalculation bay = new BayesCalculation();
        bay.calculateBayes(totalDuration);
        totalDuration.setValue(estimatedDuration.getValue());
        for (int i = 0; i < 5; i++) {
            System.out.println("totalduration " + i + ":" + totalDuration.probability.get(i)[0].getValue());
        }
        
    }

    public double[] getValueList(double a) {
        double[] giaTri = new double[5];
        giaTri[0] = a-2;
        giaTri[1] = a-1;
        giaTri[2] = a;
        giaTri[3] = a+1;
        giaTri[4] = a+2;
        return giaTri;

    }

    public ArrayList<FieldProbability[]> probability(double[] layGiaTri, double a, double b) {
        ArrayList<FieldProbability[]> arrP = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            double giaTri = layGiaTri[i];
            FieldProbability[] probability = new FieldProbability[2];
            double value = NormalDistribution.cdf(giaTri,a,b);
            FieldProbability p1 = new FieldProbability(value,estimatedDuration.getName()+"1");
            FieldProbability p2 = new FieldProbability(1-value,estimatedDuration.getName()+"2");
            probability[0] = p1;
            probability[1] = p2;
            arrP.add(probability);
        }
        return arrP;
    }

}
