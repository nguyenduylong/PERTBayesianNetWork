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
public class BayesCalculation {

    private Task[] result;

    public BayesCalculation(Task[] result) {
        this.result = result;
    }

    public BayesCalculation() {

    }

    public void calculateBayes(Node node) {
        int chay = 0;
        while (chay < 5) {
//
            // FieldProbability[] fieProbability = new FieldProbability[2];
                if (node.parents.size() == 0) {
                    node.getProbability().get(chay)[0].setValue(0.95);
                    node.getProbability().get(chay)[1].setValue(0.05);
                    //System.out.println(" nhan lĂ  :"+node.getName());
                }else {
                    for (int i = 0; i < 2; i++) {
                        double value = 0;
                        FieldProbability fie = node.getProbability().get(chay)[i];
                        for (int j = 0; j < node.getListConditional().size(); j++) {
                            if (fie.getFieldName().equals(node.getListConditional().get(j).getFieldName())) {
                                ConditionalProbability con = node.getListConditional().get(j);
                                double value1 = con.getValue();
                                //System.out.println(node.getListConditional().get(j).print() )
                                for (int k = 0; k < con.getParentsField().size(); k++) {
                                    for (int h = 0; h < node.parents.size(); h++) {
                                        // System.out.println("sao lại vậy : "+ h+" :" +node.parents.get(h).getProbability().size());
                                        for (int l = 0; l < 2; l++) {

                                            if (con.getParentsField().get(k).equals(node.parents.get(h).getProbability().get(chay)[l].getFieldName())) {
                                                value1 = value1 * node.parents.get(h).getProbability().get(chay)[l].getValue();
                                                //System.out.println("ở đây :"+ node.parents.get(h).getProbability().get(chay)[l].print()+":   "+node.parents.get(h).getProbability().get(chay)[l].getValue());
                                            }
                                        }
                                    }
                                }
                                value += value1;
                            }
                        }
                        //FieldProbability
                        node.probability.get(chay)[i].setValue(value);

                    }

                }
                chay++;
        }

    }

    public void calculateTask() {
        for (int i = 0; i < result.length; i++) {
            calculateBayes(result[i].earlyStart);
            calculateBayes(result[i].earlyFinish);

        }
    }

//    public Node calculateDuration(Task t) {
//        Node totalDuration = new Node(t.name + ".D");
//        InitTotalDuration init = new InitTotalDuration(totalDuration, t.name);
//        ArrayList<Node> listNode = new ArrayList<>();
//       
//    }
//    public void calculateTask1(Task t) {
//
//        calculateBayes(t.earlyStart);
//        //calculate(t.earlyFinish);
//
//    }
}
