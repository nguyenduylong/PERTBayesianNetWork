/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Truong
 */
public class DoThi extends JPanel {

    String tit;
    int i = 0;

    public DoThi( ArrayList<ArrayList<Double>> duLieu2, String tit, int xacdinh) {
        this.tit = tit;
        setLayout(null);
        XYSeries a2 = new XYSeries("Cao");
        for (int i = 0; i < duLieu2.size(); i++) {
            a2.add(duLieu2.get(i).get(0), duLieu2.get(i).get(1));
            // System.out.println("Trung bình"+duLieu1.get(1).get(0));
        }
        XYSeriesCollection B = new XYSeriesCollection();
        B.addSeries(a2);
        JFreeChart barChart = ChartFactory.createXYLineChart("Đồ thị thời gian và xác suất của " + tit, "Duratuon ", "Probability", B, PlotOrientation.VERTICAL, false, false, false);
        XYPlot plot = barChart.getXYPlot();
        XYLineAndShapeRenderer re = new XYLineAndShapeRenderer();
        re.setSeriesPaint(0, Color.RED);
        re.setSeriesStroke(0, new BasicStroke(4.0f));
        re.setSeriesPaint(1, Color.BLUE);
        re.setSeriesStroke(1, new BasicStroke(4.0f));
        re.setSeriesPaint(2, Color.BLACK);
        re.setSeriesStroke(2, new BasicStroke(4.0f));
        plot.setRenderer(re);
        ChartPanel panel = new ChartPanel(barChart);
        //JFrame fame = new JFrame();
        if (xacdinh <= 3) {
            panel.setSize(400, 400);
            panel.setLocation(0, 0);
        } else {
            panel.setSize(300, 300);
            panel.setLocation(0, 0);
        }
        this.add(panel);
        //this.setSize(500, 600);
//        this.setVisible(true);
    }

    public DoThi(ArrayList<ArrayList<Double>> duLieu) {
        XYSeries a = new XYSeries("Thấp");
        for (int i = 0; i < duLieu.size(); i++) {
            a.add(duLieu.get(i).get(0), duLieu.get(i).get(1));
        }

        XYSeriesCollection B = new XYSeriesCollection();
        B.addSeries(a);

        JFreeChart barChart = ChartFactory.createXYLineChart("Đồ thị thể hiện sự phụ thuộc thời gian và xác suất", "Duratuon ", "Probability", B, PlotOrientation.VERTICAL, true, true, true);
        XYPlot plot = barChart.getXYPlot();
        XYLineAndShapeRenderer re = new XYLineAndShapeRenderer();
        re.setSeriesPaint(0, Color.RED);
        re.setSeriesStroke(0, new BasicStroke(4.0f));

        plot.setRenderer(re);
        ChartPanel panel = new ChartPanel(barChart);
        //JFrame fame = new JFrame();
        this.add(panel);
        this.setSize(500, 600);
        this.setVisible(true);
    }
}
