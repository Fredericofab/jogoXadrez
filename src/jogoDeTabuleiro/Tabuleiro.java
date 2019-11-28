package jogoDeTabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) { 
			throw new TabuleiroException("erro na criacao do tabuleiro: "
									   + "necessario pelo menos 1 linha e 1 coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}
	public int getColunas() {
		return colunas;
	}
	
	public Peca peca(int linha, int coluna) {
		if (! posicaoExiste(linha, coluna)) {
			throw new TabuleiroException("Posicao " + linha + "," + coluna
					  +" nao existe no tabuleiro " + this.linhas + "x" + this.colunas);
		}
		return pecas[linha][coluna];
	}
	
	public Peca peca(Posicao posicao) {
		if (! posicaoExiste(posicao)) {
			throw new TabuleiroException("Posicao " + posicao.getLinha() + "," + posicao.getColuna()
					  +" nao existe no tabuleiro " + this.linhas + "x" + this.colunas);
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

	public void colocaPeca(Peca peca, Posicao posicao) {
		if (haUmaPeca(posicao)) {
			throw new TabuleiroException("Ja existe uma peca na posicao " + posicao.toString());
		}
		this.pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Peca removePeca(Posicao posicao) {
		if (! posicaoExiste(posicao)) {
			throw new TabuleiroException("Posicao " + posicao.toString()
									   + " nao existe no tabuleiro ");
		}
		if (peca(posicao) == null) {
			return null;
		}
		Peca aux = peca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}
	
	public Boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(),posicao.getColuna());
	}
	//metodo auxiliar para o método acima e outros
	private Boolean posicaoExiste(int linha, int coluna) {
		return (linha >=0 && linha <this.linhas) && (coluna >=0 && coluna < this.colunas);
	}
	
	
	public Boolean haUmaPeca(Posicao posicao) {
		if (! posicaoExiste(posicao)) {
			throw new TabuleiroException("Posicao " + posicao.toString()
					  +" nao existe no tabuleiro " + this.linhas + "x" + this.colunas);
		}
		return (peca(posicao) != null);
	}
}

 