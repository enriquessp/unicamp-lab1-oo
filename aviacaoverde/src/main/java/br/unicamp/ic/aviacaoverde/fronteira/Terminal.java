package br.unicamp.ic.aviacaoverde.fronteira;

import java.util.Scanner;

import br.unicamp.ic.aviacaoverde.controle.ReservaControle;
import br.unicamp.ic.aviacaoverde.dominio.Passageiro;

public class Terminal {

	private final Scanner scanner;

	private final ReservaControle reservaControle = new ReservaControle();
	
	private int opcao;

	public Terminal() {
		super();
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		Terminal principal = new Terminal();
		principal.iniciarSistema();
	}

	private void iniciarSistema() {
		reservaControle.criarVoo();

		while (opcao != 4) {
			mostrarMenu();
			opcao = scanner.nextInt();
			executaOperacao();
		}
	}

	private void mostrarMenu() {
		System.out.println("Aviação Verde");
		System.out.println();
		System.out.println("Escolha uma opção");
		System.out.println("1. Reserva");
		System.out.println("2. Consulta de Reservas");
		System.out.println("3. Imprimir");
		System.out.println("4. Encerrar");
	}

	private void executaOperacao() {
		switch (opcao) {
		case 1:
			reservar();
			break;
		case 2:
			consultarReserva();
			break;
		case 3:
			imprimir();
			break;
		case 4:
			encerrar();
			break;
		default:
			System.err.println("Operação Inválida!");
			break;
		}
	}

	private void reservar() {
		Integer idPassageiro = solicitarIdPassageiro();
		String numeroVoo = solicitarNumeroVoo();

		if (!reservaControle.validarDados(idPassageiro, numeroVoo, false)) {
			return;
		}

		System.out.println("Informe o nome do passageiro");
		
		String nome = scanner.next();
		Passageiro passageiro = reservaControle.criarPassageiro(idPassageiro, nome);

		reservaControle.reservar(passageiro);
	}
	private void consultarReserva() {
		Integer idPassageiro = solicitarIdPassageiro();
		String numeroVoo = solicitarNumeroVoo();

		reservaControle.cosultarReserva(idPassageiro, numeroVoo);

	}

	private void imprimir() {
		reservaControle.imprimir();
	}

	private void encerrar() {
		reservaControle.encerrar();
	}

	private Integer solicitarIdPassageiro() {
		System.out.println("Informe o número de identificação do passageiro");
		return scanner.nextInt();
	}

	private String solicitarNumeroVoo() {
		System.out.println("Informe o número do voo");
		return scanner.next();
	}

}