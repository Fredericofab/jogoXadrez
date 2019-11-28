package xadrez;

import jogoDeTabuleiro.Posicao;

public class PosicaoXadrez {

	private char coluna;
	private int linha;

	public PosicaoXadrez(char coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new XadrezException("Posicao " + coluna + linha + "invalida. "
									+ "Informe de a1 ate h8");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}
	public int getLinha() {
		return linha;
	}
	
	protected Posicao converteParaPosicao() {
		int linhaMatriz = 8 - this.linha;
		int colunaMatriz = this.coluna - 'a';
		return new Posicao(linhaMatriz, colunaMatriz);
	}

	protected static PosicaoXadrez converteDaPosicao(Posicao posicao) {
		char colunaXadrez = (char) ('a' - posicao.getColuna()); 
		int linhaXadrez = 8 - posicao.getLinha();
		return new PosicaoXadrez(colunaXadrez, linhaXadrez);
	}
	
	@Override
	public String toString() {
		return "" + this.coluna + this.linha;
	}
	
}
