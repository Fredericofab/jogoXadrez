package aplicacao;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while (true) {
			InterfaceDoUsuario.imprimeTabuleiro(partidaDeXadrez.getPecas());
			
			System.out.println();
			System.out.print("Origem ");
			PosicaoXadrez origem = InterfaceDoUsuario.lerPosicaoXadrez(sc);
		
			System.out.println();
			System.out.print("Destino ");
			PosicaoXadrez destino = InterfaceDoUsuario.lerPosicaoXadrez(sc);
			
			PecaDeXadrez pecaCapturada = partidaDeXadrez.executaMoveXadrez(origem, destino);
		}
	}

}
