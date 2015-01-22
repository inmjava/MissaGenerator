package br.com.inmjava.geradormissa;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
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

	private static void gerarAPartirDeArquivoAux() throws IOException {
		GeneratePptByTxtAux.main(null);
	}

	private static void gerarTudo() throws IOException {
		GenerateDocPptByFile.main(null);
	}

	private static void printOptions() {
		System.out.println("op1: Gerar Tudo");
		System.out.println("op2: Gerar a partir de arquivo aux");
		System.out.println("Adicione uma das opções: (op1, op2)");
	}

}
