package jogoDeTabuleiro;

public class Peca {
	
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		this.posicao = null; 	//a peca quando criada ainda n�o tem posicao
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

}
