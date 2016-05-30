package laboratorio.multimetroDigital.modelo;

public class Ohmetro {

	private float resistencia;
	
	public Ohmetro() {
		
		this.resistencia = 0;
	}
	
	public String getResistencia() {
		return Float.toString(this.resistencia);
	}
}
