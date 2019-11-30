package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Programa {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while (true) {
			try {
				InterfaceDoUsuario.limpaTela();
				InterfaceDoUsuario.imprimeTabuleiro(partidaDeXadrez.getPecas());
				
				System.out.println();
				System.out.print("Origem ");
				PosicaoXadrez origem = InterfaceDoUsuario.lerPosicaoXadrez(sc);
				
				boolean[][] possiveisMovimentos = partidaDeXadrez.possiveisMovimentos(origem);
				InterfaceDoUsuario.limpaTela();
				InterfaceDoUsuario.imprimeTabuleiro(partidaDeXadrez.getPecas(), possiveisMovimentos);
							
				System.out.println();
				System.out.print("Destino ");
				PosicaoXadrez destino = InterfaceDoUsuario.lerPosicaoXadrez(sc);
				
				PecaDeXadrez pecaCapturada = partidaDeXadrez.executaMoveXadrez(origem, destino);
			}
			catch (XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}

}
