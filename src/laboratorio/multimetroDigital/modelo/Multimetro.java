package laboratorio.multimetroDigital.modelo;

public class Multimetro {

	private Voltimetro voltimetro;
	private Amperimetro amperimetro;
	private Ohmetro ohmetro;
	
	public Multimetro() {
		
		this.voltimetro = new Voltimetro();
		this.amperimetro = new Amperimetro();
		this.ohmetro = new Ohmetro();
	}
	
	public String getTension() {
		return this.voltimetro.getTension();
	}

	public String getCorriente() {
		return this.amperimetro.getCorriente();
	}

	public String getResistencia() {
		return this.ohmetro.getResistencia();
	}
}
