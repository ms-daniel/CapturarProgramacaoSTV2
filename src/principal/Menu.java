package principal;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu {
	private JButton bBuscar;
	private JButton bLimpar;
	private JLabel dia;
	private Date data = new Date(); // pegar dia atual
	
	Object[] items = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"};
	
//	private JFileChooser escolher;
	
	private Insets margem = new Insets(0,0,0,0);
	
	private JComboBox diasBox;
	
	public Menu() {
		bBuscar = new JButton("Filtrar e Salvar");
		bBuscar.setBounds(80, 120, 120, 30);
		bBuscar.setMargin(margem);
		bBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bBuscar.setFont(new Font("Arial", Font.BOLD, 12));
		bBuscar.setFocusable(false);
		bBuscar.setEnabled(false);
		
		dia = new JLabel();
		dia.setText("Dia de jogo");
		dia.setBounds(10, 5, 120, 15);
		dia.setFont(new Font("Arial", Font.BOLD, 12));
		
		items = OrgDay();
		diasBox = new JComboBox(items);
		diasBox.setBounds(10, 25, 150, 30);
		
		
//		escolher = new JFileChooser();
//		escolher.setCurrentDirectory(new File("."));
//		escolher.setBounds(0, 0, 400, 200);
		
	}
	
	public JFrame getBoard(JFrame Jan) {
		Jan.getContentPane().add(bBuscar);
		Jan.getContentPane().add(diasBox);
		Jan.getContentPane().add(dia);
		
		
		return Jan;
	}
	//organiza os dias na ordem do atual dia 
	private Object[] OrgDay() {
		Object[] newI = {"", "", "", "", "", "", ""};
		String[] strD = data.toString().split(" ");
		
		for(int i = 0, count = whatDay(strD[0]); i < 7; i++, count++) {
			newI[i] = items[count];
			if(count == 6)
				count = -1;
			
		}
		
		return newI;
	}
	//esse metodo é usado para saber qual dia é
	//auxilio para fazer a organização a partir do dia atual
	private int whatDay(String dayEN) {
		int dia;
		
		if(dayEN.equals("Sun")) {
			dia = 0;
		}else if(dayEN.equals("Mon")) {
			dia = 1;
		}else if(dayEN.equals("Tue")) {
			dia = 2;
		}else if(dayEN.equals("Wed")) {
			dia = 3;
		}else if(dayEN.equals("Thu")) {
			dia = 4;
		}else if(dayEN.equals("Fri")) {
			dia = 5;
		}else if(dayEN.equals("Sat")) {
			dia = 6;
		}else {
			dia = 0;
		}
		return dia;
	}
	
}
