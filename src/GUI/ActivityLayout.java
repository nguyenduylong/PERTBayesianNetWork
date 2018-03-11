/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Bayes.CriticalPath;
import Bayes.RiskBayesNet;
import Bayes.Task;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Long
 */
public class ActivityLayout extends JPanel {

    String tit;

    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton button7;
    ArrayList<Task> listTask ;

    public ActivityLayout(String tit,ArrayList<Task> listTask) {
        this.listTask = listTask;
        this.tit = tit;
        button1 = new JButton("LS");
        Point point1 = new Point(0, 00);
        button1.setSize(50, 50);
        button1.setLocation(point1);
        button2 = new JButton("TD");
        Point point2 = new Point(75,75);
        button2.setLocation(point2);
        button2.setSize(50, 50);
        button6 = new JButton("D");
        Point point6 = new Point(75,0);
        button6.setLocation(point6);
        button6.setSize(50, 50);
        button7 = new JButton("R");
        Point point7 = new Point(75,150);
        button7.setLocation(point7);
        button7.setSize(50, 50);
        button3 = new JButton("LF");
        Point point3 = new Point(0, 150);
        button3.setLocation(point3);
        button3.setSize(50, 50);
        button4 = new JButton("ES");
        Point point4 = new Point(150, 0);
        button4.setLocation(point4);
        button4.setSize(50, 50);
        button5 = new JButton("EF");
        Point point5 = new Point(150, 150);
        button5.setLocation(point5);
        button5.setSize(50, 50);
        this.setLayout(null);
        this.add(button3);
        this.add(button2);
        this.add(button4);
        this.add(button5);
        this.add(button6);
        this.add(button7);
        JLabel layBel = new JLabel(tit);
        Point point8 = new Point(30, 50);
        layBel.setLocation(point8);
        this.add(layBel);
        layBel.setForeground(Color.RED);
       
        layBel.setSize(100, 100);
        this.add(button1);
        this.setBackground(Color.GRAY);
        System.out.println("size:"+ listTask.size());
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               dothiTotalDuration();
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               doThiES();
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               doThiEF();
            }
        });
        
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doThiDuration();
            }
        });
        
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                riskNet();
            }
        });
        
    }
        
    public void doThiDuration(){
        CriticalPath cri = new CriticalPath();
        ArrayList<ArrayList<Double>> duLieu = new ArrayList<>();       
        duLieu = cri.duLieuDuration(listTask.get(0));
        JFrame fame = new JFrame();
        DoThi doThi = new DoThi(duLieu," Duration : " + tit,0);
        fame.add(doThi);
        fame.setSize(500, 500);
        fame.setVisible(true);
    }
    
    public void  dothiTotalDuration(){
        CriticalPath cri = new CriticalPath();
        ArrayList<ArrayList<Double>> duLieu = new ArrayList<>();       
        duLieu = cri.duLieuTotalDuration(listTask.get(0));
        JFrame fame = new JFrame();
        DoThi doThi = new DoThi(duLieu," Total Duration : " + tit,0);
        fame.add(doThi);
        fame.setSize(500, 500);
        fame.setVisible(true);
    }
     public void doThiEF(){
        CriticalPath cri = new CriticalPath();
        ArrayList<ArrayList<Double>> duLieu = new ArrayList<>();       
        duLieu = cri.duLieuEF(listTask.get(0));
        JFrame fame = new JFrame();
        DoThi doThi = new DoThi(duLieu," EF : " + tit,0);
        fame.add(doThi);
        fame.setSize(500, 500);
        fame.setVisible(true);
    }
      public void doThiES(){
        CriticalPath cri = new CriticalPath();
        ArrayList<ArrayList<Double>> duLieu = new ArrayList<>();       
        duLieu = cri.duLieuES(listTask.get(0));
        JFrame fame = new JFrame();
        DoThi doThi = new DoThi(duLieu," ES : " + tit,0);
        fame.add(doThi);
        fame.setSize(500, 500);
        fame.setVisible(true);
    }
    
    public void riskNet(){
        RiskBayesNet frame = new RiskBayesNet(listTask.get(0).name, listTask.get(0).riskFile);
	frame.setSize(400, 320);
	frame.setVisible(true);
    }  
      
    public ArrayList<Task> getListTask() {
        return listTask;
    }

    public void setListTask(ArrayList<Task> listTask) {
        this.listTask = listTask;
    }
//

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.drawLine(button1.getX() + 25, button1.getY() + 50, button3.getX() + 25, button3.getY());
        g.drawLine(button1.getX() + 25, button1.getY() + 50, button1.getX() + 22, button1.getY() + 55);
        g.drawLine(button1.getX() + 25, button1.getY() + 50, button1.getX() + 27, button1.getY() + 55);

        g.drawLine(button2.getX(), button2.getY(), button1.getX() + 50, button1.getY() + 25);
        g.drawLine(button1.getX() + 50, button1.getY() + 25, button1.getX() + 55, button1.getY() + 27);
        g.drawLine(button1.getX() + 50, button1.getY() + 25, button1.getX() + 53, button1.getY() + 30);

        g.drawLine(button4.getX() + 25, button4.getY() + 50, button5.getX() + 25, button5.getY());
        g.drawLine(button5.getX() + 25, button5.getY(), button5.getX() + 22, button5.getY() - 5);
        g.drawLine(button5.getX() + 25, button5.getY(), button5.getX() + 27, button5.getY() - 5);

        g.drawLine(button2.getX() + 50, button2.getY() + 50, button5.getX(), button5.getY() + 25);
        g.drawLine(button5.getX(), button5.getY() + 25, button5.getX() - 1, button5.getY() + 17);
        g.drawLine(button5.getX(), button5.getY() + 25, button5.getX() - 7, button5.getY() + 25);
        
        g.drawLine(button6.getX() + 25, button6.getY() + 50, button2.getX()+25, button2.getY());
        g.drawLine(button2.getX()+25, button2.getY(), button2.getX() + 20, button2.getY() - 5);
        g.drawLine(button2.getX()+25, button2.getY(), button2.getX() +30 , button2.getY() - 5);
        
        g.drawLine(button7.getX() + 25, button7.getY(), button2.getX()+25, button2.getY() + 50);
        g.drawLine(button2.getX()+25, button2.getY() + 50, button2.getX() + 20, button2.getY() +55);
        g.drawLine(button2.getX()+25, button2.getY() + 50, button2.getX() + 30, button2.getY() + 55);
        
        
        
    }
    int mouseLocationX, mouseLocationY;

    public void setMousePoint(int mouseLocationX, int mouseLocationY) {
        this.mouseLocationX = mouseLocationX;
        this.mouseLocationY = mouseLocationY;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public JButton getButton1() {
        return button1;
    }

    public void setButton1(JButton button1) {
        this.button1 = button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public void setButton2(JButton button2) {
        this.button2 = button2;
    }

    public JButton getButton3() {
        return button3;
    }

    public void setButton3(JButton button3) {
        this.button3 = button3;
    }

    public JButton getButton4() {
        return button4;
    }

    public void setButton4(JButton button4) {
        this.button4 = button4;
    }

    public JButton getButton5() {
        return button5;
    }

    public void setButton5(JButton button5) {
        this.button5 = button5;
    }

    public int getMouseLocationX() {
        return mouseLocationX;
    }

    public void setMouseLocationX(int mouseLocationX) {
        this.mouseLocationX = mouseLocationX;
    }

    public int getMouseLocationY() {
        return mouseLocationY;
    }

    public void setMouseLocationY(int mouseLocationY) {
        this.mouseLocationY = mouseLocationY;
    }


}
