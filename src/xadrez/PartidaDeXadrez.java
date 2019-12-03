package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<Peca>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
		
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCA;
		check = false; // só para efeito de documentar, pois ja seria inicializada com falso
		setupInicial();
	}

	public int getTurno() {
		return turno;
	}
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	public boolean getCheck() {
		return check;
	}
	public boolean getCheckMate() {
		return checkMate;
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
		
		if (testaCheck(jogadorAtual)) {
			desfazMovimento(origem, destino, pecaCapturada);
			throw new XadrezException("Voce nao pode se  colocar em check");
		}
		
		check =  (testaCheck(oponente(jogadorAtual))) ? true : false;

		if (testaCheckMate(oponente(jogadorAtual)) == true ) {
			checkMate = true;
		}
		else {
			proximoTurno();
		}
		return (PecaDeXadrez) pecaCapturada;
	}
	
	private Peca realizaMovimento(Posicao origem, Posicao destino) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removePeca(origem);
		p.incrementaQtdeDeMovimentos();
		Peca pecaCapturada = tabuleiro.removePeca(destino);
		tabuleiro.colocaPeca(p, destino);
		
		if ( pecaCapturada != null ) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		//movimento especial - roque pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna()+2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna()+3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna()+1);
			PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.removePeca(origemT);
			tabuleiro.colocaPeca(torre, destinoT);
			torre.incrementaQtdeDeMovimentos();
		}
		
		//movimento especial - roque grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna()-2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna()-4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna()-1);
			PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.removePeca(origemT);
			tabuleiro.colocaPeca(torre, destinoT);
			torre.incrementaQtdeDeMovimentos();
		}
		return pecaCapturada;
	}
	
	private void desfazMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removePeca(destino);
		p.decrementaQtdeDeMovimentos();
		tabuleiro.colocaPeca(p, origem);
		
		if (pecaCapturada != null) {
			tabuleiro.colocaPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
		
		//movimento especial - roque pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna()+2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna()+3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna()+1);
			PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.removePeca(destinoT);
			tabuleiro.colocaPeca(torre, origemT);
			torre.decrementaQtdeDeMovimentos();
		}
		
		//movimento especial - roque grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna()-2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna()-4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna()-1);
			PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.removePeca(destinoT);
			tabuleiro.colocaPeca(torre, origemT);
			torre.decrementaQtdeDeMovimentos();;
		}
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
		jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private PecaDeXadrez rei(Cor cor) {
		//padrao para filtrar listas
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for ( Peca p : lista) {
			if (p instanceof Rei) {
				return (PecaDeXadrez) p;
			}
		}
		// isso nunca vai acontecer, mas o compilador exige um return fora do FOR
		throw new IllegalStateException("Na existe rei " + cor + "no tabuleiro");
		// nem vai ser tratada no programa principal. se acontecer vai estourar o sistema aqui
	}
	
	private boolean testaCheck(Cor cor) {
		//verifica se tem alguma peca adversaria que pode se mover para a posicao do rei
		Posicao posicaoDoRei = rei(cor).getPosicaoXadrez().converteParaPosicao();
		List<Peca> pecasDoOponente =  pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
        for (Peca p : pecasDoOponente) {
        	boolean[][] mat = p.movimentosPossiveis();
        	if (mat[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()] == true) {
        		return true;
        	}
        }
        return false;
	}
	
	private boolean testaCheckMate(Cor cor) {
		if (testaCheck(cor) == false) {
			return false;
		}
		//estando o rei em check, verifica se tem alguma peca da sua cor que tira ele do check
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : lista) {
			//para cada peca verificar cada um dos seus movimentos possiveis
			boolean[][] matriz = p.movimentosPossiveis();
			for (int i=0; i<tabuleiro.getLinhas();i++) {
				for (int j=0; j<tabuleiro.getColunas();j++) {
					if (matriz[i][j] == true) {
						//verificar se esse movimento possivel tira o rei do  check. Para tanto:
						//1 mover a peca "p" para essa posicao
						Posicao origem = ((PecaDeXadrez)p).getPosicaoXadrez().converteParaPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = realizaMovimento(origem, destino);
						//2 verifica se o rei ainda esta em check
						boolean testaCheck = testaCheck(cor);
						//3 desfaz o movimento para nao bagunçar o tabuleiro
						desfazMovimento(origem, destino, pecaCapturada);
						//4 testa se estava em check
						if (testaCheck == false) {
							//tirou o rei d check
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	
	private void colocaNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocaPeca(peca, new PosicaoXadrez(coluna, linha).converteParaPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	private void setupInicial() {
		colocaNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA,this));
		colocaNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCA));
		
		colocaNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETA));
		colocaNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETA));
		colocaNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETA));
		colocaNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETA));
		colocaNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETA,this));		
		colocaNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETA));
		colocaNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETA));
		colocaNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETA));
		colocaNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETA));
		colocaNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETA));
		colocaNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETA));
		colocaNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETA));
		colocaNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETA));
		colocaNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETA));
		colocaNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETA));
		colocaNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETA));
	}
}
