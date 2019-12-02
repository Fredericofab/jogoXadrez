package xadrez.pecas;

import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Peao extends PecaDeXadrez{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "P";
	}


	@Override
	public boolean[][] movimentosPossiveis() {
		//brancos andam para cima, pretos para baixo
		//na primeira vez pode andar uma ou duas casas
		//pode capturar na diagonal
		
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0,0);

		if (getCor() == Cor.BRANCA) {
			p.setValores(posicao.getLinha() -1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() -2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() -1, posicao.getColuna());
			if (   getTabuleiro().posicaoExiste(p)  && !getTabuleiro().haUmaPeca(p)
				&& getTabuleiro().posicaoExiste(p2) && !getTabuleiro().haUmaPeca(p2)
				&& getQtdeDeMovimentos()==0) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() -1, posicao.getColuna() -1);
			if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() -1, posicao.getColuna() +1);
			if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		}
		else {
			p.setValores(posicao.getLinha() +1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().haUmaPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() +2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() +1, posicao.getColuna());
			if (   getTabuleiro().posicaoExiste(p)  && !getTabuleiro().haUmaPeca(p)
				&& getTabuleiro().posicaoExiste(p2) && !getTabuleiro().haUmaPeca(p2)
				&& getQtdeDeMovimentos()==0) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() +1, posicao.getColuna() -1);
			if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() +1, posicao.getColuna() +1);
			if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		}
		return matriz;
	}

}
