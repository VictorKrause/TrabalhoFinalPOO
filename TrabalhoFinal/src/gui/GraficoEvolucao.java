package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import datamanager.DataManager;


public class GraficoEvolucao extends JFrame {    

	//Construtor
	public GraficoEvolucao(String title, DataManager manager, String dInicio, String dFinal) {
        super(title);
        final CategoryDataset dataset = createDataset(manager, dInicio, dFinal);
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 1500));
        setContentPane(chartPanel);  
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
    }

    //Cria o DataSet
    private CategoryDataset createDataset(DataManager manager, String dInicio, String dFinal) {
        
        
        final String linha = "Avaliações";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<YearMonth, Integer> dados = manager.selecionarDataDigitada(dInicio, dFinal);

        Set<YearMonth> keys = dados.keySet();
		ArrayList<YearMonth> list = new ArrayList<YearMonth>(keys);
		Collections.sort(list);
		for(YearMonth key: list){
			dataset.addValue(dados.get(key), linha, key);
		}

        return dataset;
                
    }
    
    //Cria o gráfico usando o dataset como parametro   
    private JFreeChart createChart(final CategoryDataset dataset) {
    	
    	JFreeChart chart = ChartFactory.createLineChart(           
    		"Grafico de evolução das avaliações por mês",    // Titulo do Gráfico
            "Ano - Mês",                   		 			 // X
            "Numero de Avaliações",       				     // Y
            dataset,                   					     // dados
            PlotOrientation.VERTICAL, 					     // Orientação
            true,                     			             // Legenda
            true,                     			      	     // tooltips
            false                    					     // urls
        );

        //Customizações estéticas do gráfico
        
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

        renderer.setSeriesStroke(
            0, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {10.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            1, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {6.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            2, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {2.0f, 6.0f}, 0.0f
            )
        );        
        
        return chart;
    }
}

