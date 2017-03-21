package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import data.Product;
import datamanager.DataManager;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.Color;

public class BuscaProduto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblPesquisarAqui;
	private TextArea txtrTextarea;

	public BuscaProduto(DataManager manager) {
		setTitle("Informações do Produto");
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
		txtrTextarea.setEditable(false);
		txtrTextarea.setBackground(Color.WHITE);
		txtrTextarea.setText("");
		txtrTextarea.setColumns(15);
		contentPane.add(txtrTextarea, BorderLayout.CENTER);

		btnMeuBotao.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnMeuBotao.setEnabled(false);
				String valorDigitadoPeloUsuario = textField.getText();
				String text = "";
				for (Product prod : manager.getProducts()) {
					if (prod.getProductId().contains(valorDigitadoPeloUsuario)|| prod.getTitle().contains(valorDigitadoPeloUsuario)) {
						text += ("Id do produto: " + prod.getProductId());
						text += ("\nNome: " + prod.getTitle());
						text += ("\nPreço: U$ " + prod.getPrice());
						text += ("\n" + prod.getReviews()+ "\n" + "\n");
					}
				}
				
				txtrTextarea.setText("".equals(text) ? "Não encontrado" : text);
				btnMeuBotao.setEnabled(true);
			}
		});
	}

}
