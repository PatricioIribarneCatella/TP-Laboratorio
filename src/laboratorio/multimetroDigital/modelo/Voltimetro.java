package laboratorio.multimetroDigital.modelo;

public class Voltimetro {

	private float voltaje;

	public Voltimetro() {
		
		this.voltaje = 0;
	}
	
	public String getTension() {
		return Float.toString(this.voltaje);
	}

	public void setValor(String valor) {
		this.voltaje = Float.parseFloat(valor);
	}
}
