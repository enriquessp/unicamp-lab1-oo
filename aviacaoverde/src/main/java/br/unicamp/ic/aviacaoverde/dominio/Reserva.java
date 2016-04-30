package br.unicamp.ic.aviacaoverde.dominio;

public class Reserva {

	private Passageiro passageiro;
	private boolean confirmada;

	public Passageiro getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	public boolean isConfirmada() {
		return confirmada;
	}

	public void setConfirmada(boolean confirmada) {
		this.confirmada = confirmada;
	}

}
