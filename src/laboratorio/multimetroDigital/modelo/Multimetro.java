package laboratorio.multimetroDigital.modelo;

public class Multimetro {

	private Voltimetro voltimetro;
	private Ohmetro ohmetro;
	
	public Multimetro() {
		
		this.voltimetro = new Voltimetro();
		this.ohmetro = new Ohmetro();
	}
	
	public String getTension() {
		return this.voltimetro.getTension();
	}

	public String getResistencia() {
		return this.ohmetro.getResistencia();
	}
}
