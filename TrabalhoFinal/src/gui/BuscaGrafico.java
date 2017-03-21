package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.ui.RefineryUtilities;

import datamanager.DataManager;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

public class BuscaGrafico extends JFrame {

	private JPanel contentPane;
	private JTextField txtDInicial;
	private JTextField txtDFinal;

	public BuscaGrafico() {
		setTitle("Evolução da quantidade de avaliações por mês");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 435, 103);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtDInicial = new JTextField();
		txtDInicial.setText("(Ex: 2000-01)");
		txtDInicial.setToolTipText("Insira a data inicial do gráfico");
		txtDInicial.setColumns(10);
		
		JLabel lblDataInicial = new JLabel("Data Inicial");
		
		JButton btnGerarGrfico = new JButton("Gerar Gráfico");
		btnGerarGrfico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DataManager manager = new DataManager();
				
				try{
					try {
						manager.load();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
					
					GraficoEvolucao evolucao = new GraficoEvolucao("Grafico de evolução das avaliações por mês", manager,txtDInicial.getText() , txtDFinal.getText());
			        evolucao.pack();
			        RefineryUtilities.centerFrameOnScreen(evolucao);
			        evolucao.setVisible(true);
				}catch(DateTimeParseException dtpe){
					JOptionPane.showMessageDialog(null,"Formato errado!"
							+ "\n Digite ANO-MES"
							+ "\n Exemplo: 2016-06");
				}
			}
			
		});
		
		txtDFinal = new JTextField();
		txtDFinal.setText("(Ex 2016-06)");
		txtDFinal.setToolTipText("Insira a data Final do Gr\u00E1fico");
		txtDFinal.setColumns(10);
		
		JLabel lblDataFinal = new JLabel("Data Final");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addComponent(txtDInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(txtDFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addComponent(btnGerarGrfico)
					.addGap(36))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addComponent(lblDataInicial)
					.addGap(71)
					.addComponent(lblDataFinal)
					.addContainerGap(207, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataInicial)
						.addComponent(lblDataFinal))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGerarGrfico))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
