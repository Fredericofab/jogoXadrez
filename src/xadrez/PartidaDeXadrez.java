package xadrez;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
		
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCA;
		setupInicial();
	}

	public int getTurno() {
		return turno;
	}
	public Cor getJogadorAtual() {
		return jogadorAtual;
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
	
	public boolean[][] possiveisMovimentos(PosicaoXadrez posicaoOrigem){
		Posicao posicao = posicaoOrigem.converteParaPosicao();
		validaPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();		
	}
	
	public PecaDeXadrez executaMoveXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.converteParaPosicao();
		Posicao destino = posicaoDestino.converteParaPosicao();
		validaPosicaoOrigem(origem);
		validaPosicaoDestino(origem, destino);
		Peca pecaCapturada = realizaMovimento(origem, destino);
		proximoTurno();
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
		if ( jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(posicao)).getCor()){
			throw new XadrezException("A peca escolhida nao e sua");
		}
		if ( ! tabuleiro.peca(posicao).existeMovimentoPossivel()) {
			throw new XadrezException("nao existe movimentos possiveis para a peca escolhida");
		}
	}
	
	private void validaPosicaoDestino(Posicao origem, Posicao destino) {
		if (! tabuleiro.peca(origem).possivelMover(destino)) {
			throw new XadrezException("A peca escolhida nao pode se mover para posicao de destino");
		}
	}
	
	private void proximoTurno() {
		turno = turno + 1;
		jogadorAtual = (jogadorAtual == Cor.BRANCA ? Cor.PRETA : Cor.BRANCA);
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
