package br.unicamp.ic.aviacaoverde.dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Voo {

	private String numero;
	private Aeronave aeronave;
	private Rota rota;
	private List<Reserva> reservas;
	private Stack<Reserva> listaDeEspera;
	private Map<Integer, Integer> idsReservados;
	private Map<Integer, Integer> idsListaEspera;

	public Voo() {
		super();
		reservas = new LinkedList<>();
		listaDeEspera = new Stack<>();
		idsReservados = new HashMap<>();
		idsListaEspera = new HashMap<>();
	}

	public void adicionarParaReservas(Reserva reserva) {
		idsReservados.put(reserva.getPassageiro().getId(), reservas.size());
		reservas.add(reserva);
	}

	public void adicionarParaListaEspera(Reserva reserva) {
		idsReservados.put(reserva.getPassageiro().getId(), listaDeEspera.size());
		listaDeEspera.push(reserva);
	}

	public Reserva buscarReserva(Integer idPassageiro) {
		Reserva reserva = null;

		if (idsReservados.containsKey(idPassageiro)) {
			int index = idsReservados.get(idPassageiro);
			reserva = reservas.get(index);
		} else {
			int index = idsListaEspera.get(idPassageiro);
			reserva = listaDeEspera.get(index);
		}

		return reserva;
	}

	public boolean existeReserva(Integer idPassageiro) {
		return idsReservados.containsKey(idPassageiro) || idsListaEspera.containsKey(idPassageiro);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Aeronave getAeronave() {
		return aeronave;
	}

	public void setAeronave(Aeronave aeronave) {
		this.aeronave = aeronave;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Stack<Reserva> getListaDeEspera() {
		return listaDeEspera;
	}

	public void setListaDeEspera(Stack<Reserva> listaDeEspera) {
		this.listaDeEspera = listaDeEspera;
	}

}
