package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datamanager.DataManager;

import java.awt.TextArea;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Color;

public class RankProdutos extends JFrame {

	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public RankProdutos(DataManager manager) {
		setTitle("Ranking dos 20 produtos mais bem avaliados");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		TextArea textArea = new TextArea();
		textArea.setText("[]");
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		contentPane.add(textArea, BorderLayout.CENTER);
	}

}
