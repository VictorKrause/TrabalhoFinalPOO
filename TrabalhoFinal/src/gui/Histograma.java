package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.ui.RefineryUtilities;

import data.Product;
import datamanager.DataManager;
import data.User;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.io.FileNotFoundException;
import java.util.Arrays;


public final class Histograma extends JFrame {

    private static final long serialVersionUID = 515079878767052562L;

    public Histograma(String s, DataManager manager) {
	super(s);
	JPanel jpanel = criarHistograma(manager);
	jpanel.setPreferredSize(new Dimension(500,270));
	setContentPane(jpanel);
	pack();
	setVisible(true);
	RefineryUtilities.centerFrameOnScreen(this);
    }

    public JPanel criarHistograma(DataManager manager) {
    int contador = 0;
	HistogramDataset histogramaDataset = new HistogramDataset();
    double [] dados = new double [manager.getUsers().size()];
	
    for (User u : manager.getUsers()){
	dados[contador] = u.getReviews().size();
	contador++;
	}
    //System.out.print(Arrays.toString(dados));Verificar a sequÃªncia de dados. 
    
	histogramaDataset.addSeries("Usuarios Cadastrados", dados, contador);
	JFreeChart jfreechart = ChartFactory.createHistogram("Histograma ", 
		"Avaliações", "Usuarios",
		histogramaDataset, PlotOrientation.VERTICAL, true, true, false);
	return new ChartPanel(jfreechart);
    }
    

    
 }
