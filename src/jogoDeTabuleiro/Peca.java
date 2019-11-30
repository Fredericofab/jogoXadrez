package jogoDeTabuleiro;

public abstract class Peca {
	
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		this.posicao = null; 	//a peca quando criada ainda não tem posicao
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public abstract boolean[][] movimentosPossiveis();
		
	public boolean possivelMover(Posicao posicao) {
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean existeMovimentoPossivel() {
		boolean[][] matriz = movimentosPossiveis();
		for (int i=0; i<matriz.length; i++) {
			for (int j=0; j<matriz.length; j++) {
				if (matriz[i][j] == true) {
					return true;
				}
			}
		}
		return false;
	}
		
	
}
