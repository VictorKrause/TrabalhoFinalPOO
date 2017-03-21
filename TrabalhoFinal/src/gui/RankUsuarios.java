package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datamanager.DataManager;

import javax.swing.JTextArea;
import java.awt.TextArea;
import java.awt.Color;

public class RankUsuarios extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public RankUsuarios(DataManager manager) {
		setTitle("Ranking dos 20 usuários com aavaliações mais úteis");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		TextArea textArea = new TextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		textArea.setText(manager.getRankUsuarios().toString());
		contentPane.add(textArea, BorderLayout.CENTER);
	}

}
