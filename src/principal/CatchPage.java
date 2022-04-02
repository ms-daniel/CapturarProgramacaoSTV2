package principal;

public class CatchPage {
	private String title = "";
	private String time = "";
	private String data = "";
	private boolean live = false;
	private boolean ar = false;
	private String bloco = "";
	
	public void saveLine(String line) {
		line = removeSpace(line);
		divideString(line);
	}
	
	public String finalBlock() {
		bloco += title + '\n';
		bloco += time + '\n';
		
		if(live)
			bloco += "Ao Vivo\n";
		if(ar)
			bloco += "NO AR\n";
		
		bloco += "\n";
		
		return bloco;
	}
	
	//limpa o bloco e retorna se foi limpo com sucesso
	public boolean flashBlock() {
		bloco = "";
		time = "";
		title = "";
		live = false;
		ar = false;
		
		if(bloco != null)
			return false;
		else
			return true;
	}
	
	public boolean flashData() {
		data = null;
		
		if(data == null)
			return true;
		else
			return false;
		
	}
	
	public void setData(String line, String flag) {
		String[] fs = line.split(flag);
		fs = fs[1].split("</li>");
		this.data = "| "+ fs[0];
	}
	
	//remove espaÃ§os da string
	private String removeSpace(String line) {
		line = line.replaceAll("\t", "");
		
		return line;
	}
	
	//remove tags da string
	private void divideString(String line) {
		//separa tag de time
		if(line.contains("<div class='lileft time'>")) {
			String[] fs = line.split("<div class='lileft time'>");
			fs = fs[1].split("</div>");
			
			//mudança de caractere estranho
			fs[0] = fs[0].replace("∶", ":");
			
			this.time = "Horario: " + fs[0] + " " + this.data;
		}
		
		//separa tag <h2> e se Ã© ao vivo
		else if(line.contains("<h2>")) {
			String[] fs = line.split("<h2>");
			fs = fs[1].split("</h2>");
			title = fs[0];
			title = title.replace("Vôlei", "Volei");
			if(title.contains(" - Ao Vivo")) {
				live = true;
				title = title.replace(" - Ao Vivo", "");
			}
		}
		else if(line.contains("<div class=\"liright\"><div "
				+ "class=\"noar\">NO AR</div></div>")) {
			ar = true;
		}
	}
}
