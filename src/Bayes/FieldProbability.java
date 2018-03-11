/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bayes;


/**
 *
 * @author Long
 */

// class xác suất để lưu tên và giá trị xác suất
public class FieldProbability {
    private double value;
    private String fieldName;

    public FieldProbability(double value, String fieldName) {
        this.value = value;
        this.fieldName = fieldName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    public String print(){
        return "P("+fieldName+")="+value;
    }
    
}
