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

	public void guardarValor(String valor) {
		// Verifico si es mayor que 100 porque no se va a medir un voltaje tan alto
		if (Float.parseFloat(valor) > 100) {
			this.ohmetro.setValor(valor);
		} else {
			this.voltimetro.setValor(valor);
		}
	}
}
