package principal;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class main {
	private JFrame Janela;
	private Menu menu = new Menu();
	private JButton linkBo;
	private String chavePro = "";
	private String chaveDia = "";
	private Search pesquisa = new Search();
	private BufferedReader html = null;
	private InputStreamReader rin;
	private InputStream inurl;
	private URL url;
	
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
		Janela = new JFrame("Catch! Programacoes STV2"); //cria uma instancia para o objeto
		Janela = StartWindows(Janela); //inicia janela
		Janela = menu.getBoard(Janela); //põe componentes na tela
		linkBo = menu.getButtonSearch();
		
		try {
			//abre conexao com o site
			url = new URL("https://meuguia.tv/programacao/canal/SP2");
			
		} catch (MalformedURLException e) {
			System.out.println("sem cnexao");
			System.exit(404);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//açao do botao buscar
		linkBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//não consegui fazer com reset
				try {
					inurl = url.openStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rin = new InputStreamReader(inurl);
				html = new BufferedReader(rin);
				
				chaveDia = menu.getDay();
				chavePro = menu.getKey();
				
				System.out.println(pesquisa.doSearch(chavePro, chaveDia, html));
				pesquisa.FlashBlock();
			}
		});

		//ler linha a linha
		
		//System.out.println(bloco);
	
	
	}
	//cria a tela principal
	private JFrame StartWindows(JFrame Janela) {
		Janela.setBounds(0, 0, 400, 400);
		Janela.setLocationRelativeTo(null); //alinha a janela no centro da tela
		Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Janela.setIconImage(this.logo.getImage()); //define o icone da aplicação
//		Janela.getContentPane().setBackground(Color.BLACK);
		Janela.getContentPane().setLayout(null);
		return Janela;
	}
}
