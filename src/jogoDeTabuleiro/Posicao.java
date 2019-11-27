package jogoDeTabuleiro;

public class Posicao {
	
	// primeira versao
	// eu exclui os setLinha e setColuna e ja criei o setPosicao

	private int linha;
	private int coluna;
	
	public Posicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	public int getLinha() {
		return linha;
	}
	public Integer getColuna() {
		return coluna;
	}
	public void setPosicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	@Override
	public String toString() {
		return linha + "," + coluna;
	}


}
