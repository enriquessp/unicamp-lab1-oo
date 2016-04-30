package br.unicamp.ic.aviacaoverde.controle;

import br.unicamp.ic.aviacaoverde.dominio.Aeronave;
import br.unicamp.ic.aviacaoverde.dominio.Aeroporto;
import br.unicamp.ic.aviacaoverde.dominio.Passageiro;
import br.unicamp.ic.aviacaoverde.dominio.Reserva;
import br.unicamp.ic.aviacaoverde.dominio.Rota;
import br.unicamp.ic.aviacaoverde.dominio.Voo;

public class ReservaControle {

	private Voo voo;

	public void reservar(Passageiro passageiro) {
		Reserva reserva = new Reserva();
		reserva.setPassageiro(passageiro);
		int capacidadeTotal = voo.getAeronave().getCapacidadeTotal();

		if (voo.getReservas().size() < capacidadeTotal) {
			reserva.setConfirmada(true);
			voo.adicionarParaReservas(reserva);
			System.out.println("Reserva efetuada com sucesso!");
		} else {
			voo.adicionarParaListaEspera(reserva);
			System.out.println("Este voo está lotado, a reserva foi adicionada à lista de espera.");
		}
	}

	public boolean validarDados(Integer idPassageiro, String numeroVoo, boolean passageiroExistente) {
		if (!numeroVoo.equals("AD4032")) {
			System.err.println("Número de voo inválido");
			return false;
		}

		if (passageiroExistente) {
			if (!voo.existeReserva(idPassageiro)) {
				System.err.println("Não existe uma reserva para esse passageiro neste voo");
				return false;
			}
		} else {
			if (voo.existeReserva(idPassageiro)) {
				System.err.println("Já existe uma reserva para esse passageiro neste voo");
				return false;
			}
		}

		return true;
	}

	public void cosultarReserva(Integer idPassageiro, String numeroVoo) {
		if (!validarDados(idPassageiro, numeroVoo, true)) {
			return;
		}

		Reserva reserva = voo.buscarReserva(idPassageiro);
		Rota rota = voo.getRota();

		System.out.println("Dados da reserva:");
		System.out.println("Voo: " + voo.getNumero());
		System.out.println("Origem: " + rota.getOrigem().getNomeCidade());
		System.out.println("Destino: " + rota.getDestino().getNomeCidade());

		String escalas = rota.getEscalas().size() > 0 ? Integer.toString(rota.getEscalas().size()) : "Não";
		System.out.println("Escalas: " + escalas);

		Passageiro passageiro = reserva.getPassageiro();
		System.out.println("Id do Passageiro: " + passageiro.getId());
		System.out.println("Nome do Passageiro: " + passageiro.getNome());

		String statusReserva = reserva.isConfirmada() ? "Confirmada" : "Na Lista de Espera";
		System.out.println("Status da Reserva: " + statusReserva);
		System.out.println();
	}

	public void imprimir() {
		System.out.println("Passageiros do voo " + voo.getNumero() + ":");

		for (Reserva reserva : voo.getReservas()) {
			System.out.println("Passageiro: " + reserva.getPassageiro().getNome());
			System.out.println("Status da Reserva: Confirmada");
			System.out.println();
		}

		for (Reserva reserva : voo.getListaDeEspera()) {
			System.out.println("Passageiro: " + reserva.getPassageiro().getNome());
			System.out.println("Status da Reserva: Na Lista de Espera");
			System.out.println();
		}
	}

	public void encerrar() {
		System.out.println("Pedidos encerrados.");
		System.out.println("O voo partiu com " + voo.getReservas().size() + " passageiros");

		boolean lotado = voo.getReservas().size() == voo.getAeronave().getCapacidadeTotal();
		System.out.println("A aeronave estava " + (lotado ? "loatada" : "com assentos desocupados"));
	}

	public void criarVoo() {
		voo = new Voo();
		voo.setNumero("AD4032");
		voo.setAeronave(criarAeronave());
		voo.setRota(criarRota());
	}

	private Aeronave criarAeronave() {
		Aeronave aeronave = new Aeronave();
		aeronave.setCapacidadeTotal(30);
		return aeronave;
	}

	private Rota criarRota() {
		Rota rota = new Rota();
		rota.setOrigem(criarAeroporto("Campinas"));
		rota.setDestino(criarAeroporto("Campo Grande"));
		return rota;
	}

	private Aeroporto criarAeroporto(String nomeCidade) {
		Aeroporto aeroporto = new Aeroporto();
		aeroporto.setNomeCidade(nomeCidade);
		return aeroporto;
	}

	public Passageiro criarPassageiro(Integer idPassageiro, String nome) {
		System.out.println("Informe o nome do passageiro");

		Passageiro passageiro = new Passageiro();
		passageiro.setId(idPassageiro);
		passageiro.setNome(nome);

		return passageiro;
	}

}