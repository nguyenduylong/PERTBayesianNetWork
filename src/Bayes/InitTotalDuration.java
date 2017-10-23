/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bayes;

import java.util.ArrayList;

/**
 *
 * @author Truong
 */
public class InitTotalDuration {

    String taskName;
    Node duration;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Node getDuration() {
        return duration;
    }

    public void setDuration(Node duration) {
        this.duration = duration;
    }
    public InitTotalDuration() {
        duration = new Node("Duration");
    }

    public InitTotalDuration(Node totalduration, String taskName) {
        this.duration = totalduration;
        this.taskName = taskName;
    }

    public void initDuration(double trungbinh, double xichma, double giatri) {
        duration.setValue(getValueList(giatri));
        duration.setProbability(probability(getValueList(giatri),trungbinh,xichma));

    }


    public void innitTotalDuration(double valu1, double valu2, double valu3, double valu4) {
        double trungbinh = (valu1+ 4*valu2 + valu3)/6;
        double xichma = (valu3 - valu1)/6;
        initDuration(trungbinh,xichma,valu4);
    }

    public static void main(String[] args) {
        InitTotalDuration a = new InitTotalDuration();
    }

    public double[] getValueList(double a) {
        double[] giaTri = new double[5];
        giaTri[0] = a*1;
        giaTri[1] = MathForDummies.round(a * 1.1,2);
        giaTri[2] = MathForDummies.round(a * 1.2,2);
        giaTri[3] = MathForDummies.round(a * 1.3,2);
        giaTri[4] = MathForDummies.round(a * 1.4,2);
        return giaTri;

    }

    public ArrayList<FieldProbability[]> probability(double[] layGiaTri, double a, double b) {
        ArrayList<FieldProbability[]> arrP = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            double giaTri = layGiaTri[i];
            FieldProbability[] probability = new FieldProbability[2];
            double value = NormalDistribution.cdf(giaTri,a,b);
            FieldProbability p1 = new FieldProbability(value, "D1");
            FieldProbability p2 = new FieldProbability(1-value,"D2");
            probability[0] = p1;
            probability[1] = p2;
            arrP.add(probability);
        }
        return arrP;
    }
//    public void innitRisk(double probability) {
//        ArrayList<FieldProbability[]> arrP = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            FieldProbability[] list = new FieldProbability[2];
//            FieldProbability pro1 = new FieldProbability(probability, riskEvent.getName() + "1");
//            FieldProbability pro2 = new FieldProbability(1-probability, riskEvent.getName() + "2");
//            list[0] = pro1;
//            list[1] = pro2;
//            arrP.add(list);
//        }
//
//        riskEvent.setProbability(arrP);
//    }
//
//    public void initAdjustmentFactor(double probability) {
//        FieldProbability probabilityLow1 = new FieldProbability(probability, adjustmentFactor.getName() + "1");
//        FieldProbability probabilityLow2 = new FieldProbability(1 - probability, adjustmentFactor.getName() + "2");
//        FieldProbability[] probabilityLow = new FieldProbability[2];
//        probabilityLow[0] = probabilityLow1;
//        probabilityLow[1] = probabilityLow2;
//        adjustmentFactor.getProbability().add(probabilityLow);
//        FieldProbability probabilityMedium1 = new FieldProbability(probability, adjustmentFactor.getName() + "1");
//        FieldProbability probabilityMedium2 = new FieldProbability(1 - probability, adjustmentFactor.getName() + "2");
//        FieldProbability[] probabilityMedium = new FieldProbability[2];
//        probabilityMedium[0] = probabilityMedium1;
//        probabilityMedium[1] = probabilityMedium2;
//        adjustmentFactor.getProbability().add(probabilityMedium);
//        FieldProbability probabilityHigh1 = new FieldProbability(probability, adjustmentFactor.getName() + "1");
//        FieldProbability probabilityHigh2 = new FieldProbability(1 - probability, adjustmentFactor.getName() + "2");
//        FieldProbability[] probabilityHigh = new FieldProbability[2];
//        probabilityHigh[0] = probabilityHigh1;
//        probabilityHigh[1] = probabilityHigh2;
//        adjustmentFactor.getProbability().add(probabilityHigh);
//    }
//
//    public void initAdjustedDuration1(double valu1, double valu2, double valu3, float valu4, double valu5) {
//        initDuration1(valu1, valu2, valu3, valu4, valu5);
//        initAdjustmentFactor(0.2);
//        adjustedDuration.parents.add(adjustmentFactor);
//        adjustedDuration.parents.add(duration);
////        adjustmentFactor.getValue()[0] = value1;
////        adjustmentFactor.getValue()[1] = value2;
////        adjustmentFactor.getValue()[2] = value3;
//        double DT1 = 0.01 * adjustmentFactor.probability.get(0)[0].getValue()
//                + duration.probability.get(0)[0].getValue() * (1 - adjustmentFactor.probability.get(0)[0].getValue());
//        double DT2 = 0.01 * adjustmentFactor.probability.get(1)[0].getValue()
//                + duration.probability.get(1)[0].getValue() * (1 - adjustmentFactor.probability.get(1)[0].getValue());
//        double DT3 = 0.01 * adjustmentFactor.probability.get(2)[0].getValue()
//                + duration.probability.get(2)[0].getValue() * (1 - adjustmentFactor.probability.get(2)[0].getValue());
//        FieldProbability probabilityLow1 = new FieldProbability(DT1, adjustedDuration.getName() + "1");
//        FieldProbability probabilityLow2 = new FieldProbability(1 - DT1, adjustedDuration.getName() + "2");
//        FieldProbability[] probabilityLow = new FieldProbability[2];
//        probabilityLow[0] = probabilityLow1;
//        probabilityLow[1] = probabilityLow2;
//       // adjustedDuration.getProbability().add(probabilityLow);
//        FieldProbability probabilityMedium1 = new FieldProbability(DT2, adjustedDuration.getName() + "1");
//        FieldProbability probabilityMedium2 = new FieldProbability(1 - DT2, adjustedDuration.getName() + "2");
//        FieldProbability[] probabilityMedium = new FieldProbability[2];
//        probabilityMedium[0] = probabilityMedium1;
//        probabilityMedium[1] = probabilityMedium2;
//        
//        FieldProbability probabilityHigh1 = new FieldProbability(DT3, adjustedDuration.getName() + "1");
//        FieldProbability probabilityHigh2 = new FieldProbability(1 - DT3, adjustedDuration.getName() + "2");
//        FieldProbability[] probabilityHigh = new FieldProbability[2];
//        probabilityHigh[0] = probabilityHigh1;
//        probabilityHigh[1] = probabilityHigh2;
//        adjustedDuration.getValue()[0] = 1.1f * duration.getValue()[0];
//        adjustedDuration.getValue()[1] = 1f * duration.getValue()[1];
//        adjustedDuration.getValue()[2] = 0.9f * duration.getValue()[2];
//        adjustedDuration.getProbability().add(probabilityLow);
//        adjustedDuration.getProbability().add(probabilityMedium);
//        adjustedDuration.getProbability().add(probabilityHigh);
//
//    }
//
//    public void initAdjustedDuration2(double valu1, double valu2, double valu3, float valu4, double valu5) {
//        initDuration2(valu1, valu2, valu3, valu4, valu5);
//        initAdjustmentFactor(0.2);
//        adjustedDuration.parents.add(adjustmentFactor);
//        adjustedDuration.parents.add(duration);
////        adjustmentFactor.getValue()[0] = value1;
////        adjustmentFactor.getValue()[1] = value2;
////        adjustmentFactor.getValue()[2] = value3;
//        double DT1 = 0.01 * adjustmentFactor.probability.get(0)[0].getValue()
//                + duration.probability.get(0)[0].getValue() * (1 - adjustmentFactor.probability.get(0)[0].getValue());
//        double DT2 = 0.01 * adjustmentFactor.probability.get(1)[0].getValue()
//                + duration.probability.get(1)[0].getValue() * (1 - adjustmentFactor.probability.get(1)[0].getValue());
//        double DT3 = 0.01 * adjustmentFactor.probability.get(2)[0].getValue()
//                + duration.probability.get(2)[0].getValue() * (1 - adjustmentFactor.probability.get(2)[0].getValue());
//        FieldProbability probabilityLow1 = new FieldProbability(DT1, adjustedDuration.getName() + "1");
//        FieldProbability probabilityLow2 = new FieldProbability(1 - DT1, adjustedDuration.getName() + "2");
//        FieldProbability[] probabilityLow = new FieldProbability[2];
//        probabilityLow[0] = probabilityLow1;
//        probabilityLow[1] = probabilityLow2;
//        
//        FieldProbability probabilityMedium1 = new FieldProbability(DT2, adjustedDuration.getName() + "1");
//        FieldProbability probabilityMedium2 = new FieldProbability(1 - DT2, adjustedDuration.getName() + "2");
//        FieldProbability[] probabilityMedium = new FieldProbability[2];
//        probabilityMedium[0] = probabilityMedium1;
//        probabilityMedium[1] = probabilityMedium2;
//       
//        FieldProbability probabilityHigh1 = new FieldProbability(DT3, adjustedDuration.getName() + "1");
//        FieldProbability probabilityHigh2 = new FieldProbability(1 - DT3, adjustedDuration.getName() + "2");
//        FieldProbability[] probabilityHigh = new FieldProbability[2];
//        probabilityHigh[0] = probabilityHigh1;
//        probabilityHigh[1] = probabilityHigh2;
//       // System.out.println("DT3 ="+ DT3);
//        adjustedDuration.getValue()[0] = 1.1f * duration.getValue()[0];
//        adjustedDuration.getValue()[1] = 1f * duration.getValue()[1];
//        adjustedDuration.getValue()[2] = 0.9f * duration.getValue()[2];
//        adjustedDuration.getProbability().add(probabilityLow);
//        adjustedDuration.getProbability().add(probabilityMedium);
//        adjustedDuration.getProbability().add(probabilityHigh);
//
//    }
//
////    public void initDelay() {
////        riskEvent.parents.add(delay);
////        preMitigationDelay.parents.add(impact);
////        preMitigationDelay.parents.add(riskEvent);
////        delay.parents.add(preMitigationDelay);
////        delay.parents.add(reponse);
////    }
//    public void initRisk(double probability) {
//        FieldProbability probabilityHigh1 = new FieldProbability(probability, riskEvent.getName() + "1");
//        FieldProbability probabilityHigh2 = new FieldProbability(1 - probability, riskEvent.getName() + "2");
//        FieldProbability[] probabilityHigh = new FieldProbability[2];
//        probabilityHigh[0] = probabilityHigh1;
//        probabilityHigh[1] = probabilityHigh2;
//        riskEvent.getProbability().add(probabilityHigh);
//        FieldProbability probabilityMedium1 = new FieldProbability(probability, riskEvent.getName() + "1");
//        FieldProbability probabilityMedium2 = new FieldProbability(1 - probability, riskEvent.getName() + "2");
//        FieldProbability[] probabilityMedium = new FieldProbability[2];
//        probabilityMedium[0] = probabilityMedium1;
//        probabilityMedium[1] = probabilityMedium2;
//        riskEvent.getProbability().add(probabilityMedium);
//        FieldProbability probabilityLow1 = new FieldProbability(probability, riskEvent.getName() + "1");
//        FieldProbability probabilityLow2 = new FieldProbability(1 - probability, riskEvent.getName() + "2");
//        FieldProbability[] probabilityLow = new FieldProbability[2];
//        probabilityLow[0] = probabilityLow1;
//        probabilityLow[1] = probabilityLow2;
//        riskEvent.getProbability().add(probabilityLow);
//    }
//
//    public void initTotalDuration1(double valu1, double valu2, double valu3, float valu4, double valu5) {
//        initAdjustedDuration1(valu1, valu2, valu3, valu4, valu5);
//        totalduration.parents.add(riskEvent);
//        totalduration.parents.add(adjustedDuration);
//        double DT1 = 0.01 * riskEvent.probability.get(0)[0].getValue()
//                + adjustedDuration.probability.get(0)[0].getValue() * (1 - riskEvent.probability.get(0)[0].getValue());
//        double DT2 = 0.01 * riskEvent.probability.get(1)[0].getValue()
//                + adjustedDuration.probability.get(1)[0].getValue() * (1 - riskEvent.probability.get(1)[0].getValue());
//        double DT3 = 0.01 * riskEvent.probability.get(2)[0].getValue()
//                + adjustedDuration.probability.get(2)[0].getValue() * (1 - riskEvent.probability.get(2)[0].getValue());
//        FieldProbability probabilityLow1 = new FieldProbability(DT1, totalduration.getName() + "1");
//        FieldProbability probabilityLow2 = new FieldProbability(1 - DT1, totalduration.getName() + "2");
//        FieldProbability[] probabilityLow = new FieldProbability[2];
//        probabilityLow[0] = probabilityLow1;
//        probabilityLow[1] = probabilityLow2;
//        totalduration.getProbability().add(probabilityLow);
//        FieldProbability probabilityMedium1 = new FieldProbability(DT2, totalduration.getName() + "1");
//        FieldProbability probabilityMedium2 = new FieldProbability(1 - DT2, totalduration.getName() + "2");
//        FieldProbability[] probabilityMedium = new FieldProbability[2];
//        probabilityMedium[0] = probabilityMedium1;
//        probabilityMedium[1] = probabilityMedium2;
//        totalduration.getProbability().add(probabilityMedium);
//        FieldProbability probabilityHigh1 = new FieldProbability(DT3, totalduration.getName() + "1");
//        FieldProbability probabilityHigh2 = new FieldProbability(1 - DT3, totalduration.getName() + "2");
//        FieldProbability[] probabilityHigh = new FieldProbability[2];
//        probabilityHigh[0] = probabilityHigh1;
//        probabilityHigh[1] = probabilityHigh2;
//        totalduration.getProbability().add(probabilityHigh);
//        totalduration.setValue(adjustedDuration.getValue());
//
//    }
//
//    public void initTotalDuration2(double valu1, double valu2, double valu3, float valu4, double valu5) {
//        initAdjustedDuration2(valu1, valu2, valu3, valu4, valu5);
//        totalduration.parents.add(riskEvent);
//        totalduration.parents.add(adjustedDuration);
//        double DT1 = 0.01 * riskEvent.probability.get(0)[0].getValue()
//                + adjustedDuration.probability.get(0)[0].getValue() * (1 - riskEvent.probability.get(0)[0].getValue());
//        double DT2 = 0.01 * riskEvent.probability.get(1)[0].getValue()
//                + adjustedDuration.probability.get(1)[0].getValue() * (1 - riskEvent.probability.get(1)[0].getValue());
//        double DT3 = 0.01 * riskEvent.probability.get(2)[0].getValue()
//                + adjustedDuration.probability.get(2)[0].getValue() * (1 - riskEvent.probability.get(2)[0].getValue());
//        FieldProbability probabilityLow1 = new FieldProbability(DT1, totalduration.getName() + "1");
//        FieldProbability probabilityLow2 = new FieldProbability(1 - DT1, totalduration.getName() + "2");
//        FieldProbability[] probabilityLow = new FieldProbability[2];
//        probabilityLow[0] = probabilityLow1;
//        probabilityLow[1] = probabilityLow2;
//        //System.out.println("DT32="+adjustedDuration.probability.get(2)[0].getValue());
//        totalduration.getProbability().add(probabilityLow);
//        FieldProbability probabilityMedium1 = new FieldProbability(DT2, totalduration.getName() + "1");
//        FieldProbability probabilityMedium2 = new FieldProbability(1 - DT2, totalduration.getName() + "2");
//        FieldProbability[] probabilityMedium = new FieldProbability[2];
//        probabilityMedium[0] = probabilityMedium1;
//        probabilityMedium[1] = probabilityMedium2;
//        totalduration.getProbability().add(probabilityMedium);
//        FieldProbability probabilityHigh1 = new FieldProbability(DT3, totalduration.getName() + "1");
//        FieldProbability probabilityHigh2 = new FieldProbability(1 - DT3, totalduration.getName() + "2");
//        FieldProbability[] probabilityHigh = new FieldProbability[2];
//        probabilityHigh[0] = probabilityHigh1;
//        probabilityHigh[1] = probabilityHigh2;
//        totalduration.getProbability().add(probabilityHigh);
//        totalduration.setValue(adjustedDuration.getValue());
//
//    }
//
}
