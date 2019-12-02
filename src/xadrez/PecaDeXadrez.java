package xadrez;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;

public abstract class PecaDeXadrez extends Peca {
	
	private Cor cor;
	private int qtdeDeMovimentos;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	public int getQtdeDeMovimentos() {
		return qtdeDeMovimentos;
	}
	
	public void incrementaQtdeDeMovimentos() {
		qtdeDeMovimentos = qtdeDeMovimentos + 1;
	}
	public void decrementaQtdeDeMovimentos() {
		qtdeDeMovimentos = qtdeDeMovimentos - 1;
	}
	
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.converteDaPosicao(posicao);
	}
	
	protected boolean existePecaOponente(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
		return p != null && p.cor != this.cor;
	}
}
