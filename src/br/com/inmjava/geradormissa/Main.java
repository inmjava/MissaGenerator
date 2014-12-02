package br.com.inmjava.geradormissa;

public class Main {
	
	public static void main(String[] args) {
		
		if(args == null){
			printOptions();
			return ;
		}
		switch (args[0]) {
		case "op1":
			gerarTudo();
			break;
		case "op2":
			gerarAPartirDeArquivoAux();
			break;
		default:
			printOptions();
			break;
		}
	}

	private static void gerarAPartirDeArquivoAux() {
		
	}

	private static void gerarTudo() {
		
	}

	private static void printOptions() {
		System.out.println("op1: Gerar Tudo");
		System.out.println("op2: Gerar a partir de arquivo aux");
		System.out.println("Adicione uma das opções: (op1, op2)");
	}

}
