package xadrez;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;
		
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		setupInicial();
	}

	public PecaDeXadrez[][] getPecas(){
		PecaDeXadrez[][] matriz = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return matriz;
	}
	
	public PecaDeXadrez executaMoveXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.converteParaPosicao();
		Posicao destino = posicaoDestino.converteParaPosicao();
		validaPosicaoOrigem(origem);
		Peca pecaCapturada = realizaMovimento(origem, destino);
		return (PecaDeXadrez) pecaCapturada;
	}
	
	private Peca realizaMovimento(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem);
		Peca pecaCapturada = tabuleiro.removePeca(destino);
		tabuleiro.colocaPeca(p, destino);
		return pecaCapturada;
	}
	
	private void validaPosicaoOrigem(Posicao posicao) {
		if ( ! tabuleiro.haUmaPeca(posicao)) {
			throw new XadrezException("nao existe peca na posicao de origem");
		}
		if ( ! tabuleiro.peca(posicao).existeMovimentoPossivel()) {
			throw new XadrezException("nao existe movimentos possiveis para a peca escolhida");
		}
	}
	
	private void colocaNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocaPeca(peca, new PosicaoXadrez(coluna, linha).converteParaPosicao());
	}
	
	private void setupInicial() {
		colocaNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCA));

		colocaNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETA));
		colocaNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETA));
		colocaNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETA));
		colocaNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETA));
		colocaNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETA));
		colocaNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETA));		
	}
}
