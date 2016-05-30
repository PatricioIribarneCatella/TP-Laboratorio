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
	
	public String getVoltaje() {
		return this.voltimetro.getVoltaje();
	}

	public String getAmperaje() {
		return this.amperimetro.getAmperaje();
	}

	public String getResistencia() {
		return this.ohmetro.getResistencia();
	}
}
