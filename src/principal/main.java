package principal;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class main {
	private JFrame Janela;
	private Menu menu = new Menu();
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.Janela.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public main() throws IOException {
		initialize();
	}
	
	private void initialize() throws IOException {
		Janela = new JFrame("Catch! Programações ST2"); //cria uma instancia para o objeto
		Janela = StartWindows(Janela); //inicia janela
		Janela = menu.getBoard(Janela); //põe componentes na tela
		
		
		
		String linha = "";
		String auxLin = "";
		String bloco = "";
		
		String chavePro = "Superliga";
		String chaveDia = "Sexta";
		
		URL url;
		BufferedReader html = null;
		
		try {
			//abre conexao com o site
			url = new URL("https://meuguia.tv/programacao/canal/SP2");
			
			//abre a entrada de dados do site
			InputStream inurl = url.openStream();
			
			//convertemos o inputstream para poder usar no buffered reader
			//e ler linha a linha
			InputStreamReader rin = new InputStreamReader(inurl);
			
			//associamos o inputstreamreader ao bufferedreader
			html = new BufferedReader(rin);
			
		} catch (MalformedURLException e) {
			System.out.println("sem cnexao");
			System.exit(404);
		} catch (UnknownHostException e) {
			System.out.println("sem conexao");
			System.exit(404);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CatchPage cat = new CatchPage();
		
		//ler linha a linha
		int i = 0;
		try {
			while(auxLin != null && i != 1) {
				auxLin = html.readLine();
				
				//AUI
				//vvrifica se é o dia selecionado
				if(auxLin.contains("<li class=\"subheader devicepadding\">" + chaveDia)) {
					//set para data dos programas
					cat.setData(auxLin, "<li class=\"subheader devicepadding\">" + chaveDia + ", ");
					//roda até terminar jogos daquele dia
					while(true) {
						auxLin = html.readLine();
						//System.out.println(auxLin);
						//verifica se a linha é item de uma lista 
						if(auxLin != null && auxLin.contains("<li>")) {
							auxLin = html.readLine();
							
							if(auxLin.contains(chavePro)) {
								while(true) {
									auxLin = html.readLine();
									if(auxLin != null && !auxLin.contains("\n")) {
										//System.out.println(auxLin);
										linha = auxLin;
										cat.saveLine(linha); //manda linha para o objeto tratar
									}
									
									//verifica se acabou de ler aquele bloco de programa
									if(auxLin.contains("</a>			</li>") && auxLin != null) {
										bloco += cat.finalBlock();
										cat.flashBlock();
										break;
									}
								}
								
							}
						}
						//para quando acha dia diferente
						if(auxLin != null && auxLin.contains("<!-- <li class=\"subheader devicepadding\" style=\"color: #0099EE\">")) {
							i = 1;
							cat.flashData();
							break;
						}
						//quando der null e o i = 1 para ele não pegar
						//o dia da proxima semana
						if(auxLin == null) {
							cat.flashData();
							break;
						}
					}
				}
				//IU
				
			}
		}catch(IOException e) {
			
		}
		System.out.println(bloco);
	
	
	}
	private JFrame StartWindows(JFrame Janela) {
		Janela.setBounds(0, 0, 400, 200);
		Janela.setLocationRelativeTo(null); //alinha a janela no centro da tela
		Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Janela.setIconImage(this.logo.getImage()); //define o icone da aplicação
//		Janela.getContentPane().setBackground(Color.BLACK);
		Janela.getContentPane().setLayout(null);
		return Janela;
	}
}
