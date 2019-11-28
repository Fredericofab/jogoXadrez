package jogoDeTabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) { 
			throw new tabuleiroException("erro na criacao do tabuleiro: "
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
			throw new tabuleiroException("Posicao " + linha + "," + coluna
					  +" nao existe no tabuleiro " + this.linhas + "x" + this.colunas);
		}
		return pecas[linha][coluna];
	}
	
	public Peca peca(Posicao posicao) {
		if (! posicaoExiste(posicao)) {
			throw new tabuleiroException("Posicao " + posicao.getLinha() + "," + posicao.getColuna()
					  +" nao existe no tabuleiro " + this.linhas + "x" + this.colunas);
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

	public void colocaPeca(Peca peca, Posicao posicao) {
		if (haUmaPeca(posicao)) {
			throw new tabuleiroException("Ja existe uma peca na posicao " + posicao.toString());
		}
		this.pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
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
			throw new tabuleiroException("Posicao " + posicao.toString()
					  +" nao existe no tabuleiro " + this.linhas + "x" + this.colunas);
		}

		return (peca(posicao) != null);
	}
}

 