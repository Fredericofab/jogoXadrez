package xadrez.pecas;

import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;

public class Rei extends PecaDeXadrez {
	
	private PartidaDeXadrez partidaDeXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
	}
	
	@Override
	public String toString() {
		return "R";
	}

	private boolean podeMover(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();		
	}
	
	private boolean testaRoqueTorre(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
		return (p != null) && (p instanceof Torre) &&
			   (p.getCor() == getCor()) && (p.getQtdeDeMovimentos() == 0);
	} 
				
		
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);
		
		//acima
		p.setValores(posicao.getLinha()-1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//abaixo
		p.setValores(posicao.getLinha()+1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna()-1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//direita
		p.setValores(posicao.getLinha(), posicao.getColuna()+1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//noroeste
		p.setValores(posicao.getLinha()-1, posicao.getColuna()-1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//nordeste
		p.setValores(posicao.getLinha()-1, posicao.getColuna()+1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		//suldoeste
		p.setValores(posicao.getLinha()+1, posicao.getColuna()-1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//suldeste
		p.setValores(posicao.getLinha()+1, posicao.getColuna()+1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//movimento especial para Roque
		if (getQtdeDeMovimentos()==0 && partidaDeXadrez.getCheck()==false) {
			// rei nunca se moveu e não esta em check
			
			// testar se pode fazer o "roque pequeno" ou "Roque do lado do rei"
			//condicao 1 - torre esta no lugar apta para roque
			//condicao 2 - posicao do bispo vazia
			//condicao 3 - posicao do cavalo vazia
			Posicao posT1 = new Posicao(posicao.getLinha(),posicao.getColuna()+3); 
			Posicao posB1 = new Posicao(posicao.getLinha(),posicao.getColuna()+1); 
			Posicao posC1 = new Posicao(posicao.getLinha(),posicao.getColuna()+2); 
			if ((testaRoqueTorre(posT1)==true) && 
				(getTabuleiro().peca(posB1) == null) &&
				(getTabuleiro().peca(posC1) == null)){
				matriz[posicao.getLinha()][posicao.getColuna()+2] = true;
			}
			
			// testar se pode fazer o "roque grande" ou "Roque do lado da rainha"
			//condicao 1 - torre esta no lugar apta para roque
			//condicao 2 - posicao da rainha vazia
			//condicao 3 - posicao do bispo vazia
			//Condicao 4 - posicao do cavalo vazia
			Posicao posT2 = new Posicao(posicao.getLinha(),posicao.getColuna()-4); 
			Posicao posQ  = new Posicao(posicao.getLinha(),posicao.getColuna()-1); 
			Posicao posB2 = new Posicao(posicao.getLinha(),posicao.getColuna()-2); 
			Posicao posC2 = new Posicao(posicao.getLinha(),posicao.getColuna()-3); 
			if ((testaRoqueTorre(posT2)==true) && 
				(getTabuleiro().peca(posQ) == null) &&
				(getTabuleiro().peca(posB2) == null) &&
				(getTabuleiro().peca(posC2) == null)){
				matriz[posicao.getLinha()][posicao.getColuna()-2] = true;
			}
		}

		return matriz;
	}

}
