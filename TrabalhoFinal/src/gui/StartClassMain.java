package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.ui.RefineryUtilities;

import datamanager.DataManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Scrollbar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;

public class StartClassMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static DataManager manager;
	private JButton btnBuscaProduto;

	public static void main(String[] args) {
		manager = new DataManager();
		try {
			manager.load();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartClassMain frame = new StartClassMain();
					frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public StartClassMain() {
		setTitle("Pesquisa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JButton btnRankUsuarios = new JButton("Ranking de usu\u00E1rios com avalia\u00E7\u00F5es mais \u00FAteis");
		btnRankUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				RankUsuarios tela = new RankUsuarios(manager);
				tela.setVisible(true);
			}
		});
		
		JButton btnRankProdutos = new JButton("Ranking dos produtos mais bem avaliados");
		btnRankProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnRankProdutos.setEnabled(false);
				RankProdutos tela = new RankProdutos(manager);
				btnRankProdutos.setEnabled(true);
				tela.setVisible(true);
			}
		});
		
				JButton btnUsuario = new JButton("Buscar Usuario");
				btnUsuario.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						try {
							BuscaUsuario frame = new BuscaUsuario(manager);
							frame.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
		
				btnBuscaProduto = new JButton("Buscar Produto");
				btnBuscaProduto.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						btnBuscaProduto.setEnabled(false);
						try {
							BuscaProduto frame = new BuscaProduto(manager);
							frame.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						btnBuscaProduto.setEnabled(true);
					}
				});
		
		JButton btnBuscarReview = new JButton("Buscar Avalia\u00E7\u00E3o");
		btnBuscarReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBuscarReview.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BuscaReview gui = new BuscaReview(manager);
				gui.setVisible(true);
			}
		});
		
		JButton btnNewButton = new JButton("Histograma");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DataManager manager = new DataManager();
				try {
					manager.load();
				} catch (FileNotFoundException e2) {
					
					e2.printStackTrace();
				}
			    Histograma histograma = new Histograma  ("",manager); 
			    histograma.setVisible(true);
			    }
			}
		);
		
		JButton btnGrficoDeReviews = new JButton("Gr\u00E1fico de Reviews");
		btnGrficoDeReviews.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					BuscaGrafico frame = new BuscaGrafico();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}							
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnRankUsuarios, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnBuscarReview, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnBuscaProduto, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
										.addComponent(btnUsuario, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnGrficoDeReviews))))
							.addContainerGap(51, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnRankProdutos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(51))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUsuario)
						.addComponent(btnGrficoDeReviews))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBuscaProduto)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuscarReview)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRankUsuarios)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRankProdutos)
					.addContainerGap(137, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.SOUTH);
	}
}

