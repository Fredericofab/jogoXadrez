package xadrez;

import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;
		
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		setupInicial();
	}

	public PecaDeXadrez[][] getPecas(){
		PecaDeXadrez[][] matriz = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return matriz;
	}
	
	private void setupInicial() {
		tabuleiro.colocaPeca(new Torre(tabuleiro, Cor.BRANCA), new Posicao(2, 1));
		tabuleiro.colocaPeca(new Rei(tabuleiro, Cor.PRETA), new Posicao(0,4));
		tabuleiro.colocaPeca(new Rei(tabuleiro, Cor.BRANCA), new Posicao(7,4));
	}
}
