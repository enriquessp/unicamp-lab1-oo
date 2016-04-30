package br.unicamp.ic.aviacaoverde.dominio;

import java.util.ArrayList;
import java.util.List;

public class Rota {

	private Aeroporto origem;
	private Aeroporto destino;
	private List<Aeroporto> escalas;

	public Rota() {
		super();
		escalas = new ArrayList<>();
	}

	public Aeroporto getOrigem() {
		return origem;
	}

	public void setOrigem(Aeroporto origem) {
		this.origem = origem;
	}

	public Aeroporto getDestino() {
		return destino;
	}

	public void setDestino(Aeroporto destino) {
		this.destino = destino;
	}

	public List<Aeroporto> getEscalas() {
		return escalas;
	}

	public void setEscalas(List<Aeroporto> escalas) {
		this.escalas = escalas;
	}

}
