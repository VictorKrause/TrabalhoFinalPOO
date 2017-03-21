package gui;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import data.Review;
import datamanager.DataManager;
import java.awt.Color;

public class BuscaReview extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblPesquisarAqui;
	private TextArea txtrTextarea;

	public BuscaReview(DataManager manager) {
		setTitle("Avaliações que contenham o texto...");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JButton btnMeuBotao = new JButton("Buscar");

		lblPesquisarAqui = new JLabel("Pesquisar aqui");
		panel.add(lblPesquisarAqui);
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		panel.add(btnMeuBotao);
		
		txtrTextarea = new TextArea();
		txtrTextarea.setBackground(Color.WHITE);
		txtrTextarea.setEditable(false);
		contentPane.add(txtrTextarea, BorderLayout.CENTER);

		btnMeuBotao.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnMeuBotao.setEnabled(false);
				String valorDigitadoPeloUsuario = textField.getText();
				String text = "";
				for (Review review : manager.getReviews()) {
					if (review.getSummary().contains(valorDigitadoPeloUsuario) || review.getText().contains(valorDigitadoPeloUsuario)) {
						text += (review.getProduct());
						text += ("\nScore: " + review.getScore());
						text += ("\nSummary: " + review.getSummary());
						text += ("\nText: " + review.getText());
						text += "\n" + "\n";
					}
				}
				
				txtrTextarea.setText("".equals(text) ? "NÃ£o encontrado" : text);
//				System.out.println("Numero de usuÃ¡rios cadastrados no sistema: " + manager.getUsers().size());
				btnMeuBotao.setEnabled(true);
			}
		});
	}

}
