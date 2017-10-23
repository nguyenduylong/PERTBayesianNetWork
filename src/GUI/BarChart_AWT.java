/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Bayes.InitTotalDuration;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Truong
 */
public class BarChart_AWT extends JFrame {

    public BarChart_AWT(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Category",
                "Score",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {
        final String fiat = "FIAT";
        final String audi = "AUDI";
        final String ford = "FORD";
        final String speed = "Speed";
        final String millage = "Millage";
        final String userrating = "User Rating";
        final String safety = "safety";
        final DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();

        dataset.addValue(1.0, fiat, speed);
        dataset.addValue(3.0, fiat, userrating);
        dataset.addValue(5.0, fiat, millage);
        dataset.addValue(5.0, fiat, safety);

        dataset.addValue(5.0, audi, speed);
        dataset.addValue(6.0, audi, userrating);
        dataset.addValue(10.0, audi, millage);
        dataset.addValue(4.0, audi, safety);

        dataset.addValue(4.0, ford, speed);
        dataset.addValue(2.0, ford, userrating);
        dataset.addValue(3.0, ford, millage);
        dataset.addValue(6.0, ford, safety);
        XYSeries a = new XYSeries("AAA");

        return dataset;
    }

    public static void main(String[] args) {
        InitTotalDuration totl = new InitTotalDuration();
        totl.innitTotalDuration(8, 11,12, 10);
        XYSeries a = new XYSeries("AAA");
        a.add(1, 1);
        a.add(1, 3);
         XYSeries a1 = new XYSeries("b");
        a1.add(2, 2);
        a1.add(2, 4);
        XYSeriesCollection B = new XYSeriesCollection();
        B.addSeries(a);
        B.addSeries(a1);
        JFreeChart barChart = ChartFactory.createXYLineChart("SSSS", "Duratuon ", "Probability", B, PlotOrientation.VERTICAL, true, true, true);
        XYPlot plot = barChart.getXYPlot();
        XYLineAndShapeRenderer re = new XYLineAndShapeRenderer();
        re.setSeriesPaint(0, Color.RED);
        re.setSeriesStroke(0, new BasicStroke(4.0f));
        re.setSeriesPaint(1, Color.BLUE);
        re.setSeriesStroke(1, new BasicStroke(4.0f));
        plot.setRenderer(re);
        ChartPanel panel = new ChartPanel(barChart);
        JFrame fame = new JFrame();
        fame.add(panel);
        fame.setSize(500, 600);
        fame.setVisible(true);

    }
}
