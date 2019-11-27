package jogoDeTabuleiro;

public class Posicao {
	
	// primeira versao
	// eu usei Integer ele int
	// eu exclui os setLinha e setColuna e ja criei o setPosicao
	// eu criei tambem um construtor vazio
	private Integer linha;
	private Integer coluna;
	
	public Posicao() {
	}
	public Posicao(Integer linha, Integer coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	public Integer getLinha() {
		return linha;
	}
	public Integer getColuna() {
		return coluna;
	}
	public void setPosicao(Integer linha, Integer coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	@Override
	public String toString() {
		return linha + "," + coluna;
	}
	
	

}
