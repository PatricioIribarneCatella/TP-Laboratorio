package laboratorio.multimetroDigital.modelo;

public class Amperimetro {

	private float amperaje;
	
	public Amperimetro() {
		
		this.amperaje = 0;
	}
	
	public String getAmperaje() {
		return Float.toString(this.amperaje);
	}
}
