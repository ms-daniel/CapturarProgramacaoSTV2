package principal;

import java.io.BufferedReader;
import java.io.IOException;

public class Search {
	private String bloco = "";
	private String auxLin = "", linha = "";
	private CatchPage cat = new CatchPage();
	
	public String doSearch(String chavePro, String chaveDay, BufferedReader html) {
		int i = 0;
		
		try {
			while(auxLin != null && i != 1) {
				auxLin = html.readLine();
				
				//AUI
				//vvrifica se é o dia selecionado
				if(auxLin.contains("<li class=\"subheader devicepadding\">" + chaveDay)) {
					//set para data dos programas
					cat.setData(auxLin, "<li class=\"subheader devicepadding\">" + chaveDay + ", ");
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
		
		return this.bloco;
	}
	
	//limpa a string que contem os horarios e dados restantes
	public void FlashBlock() {
		bloco = "";
	}
}
