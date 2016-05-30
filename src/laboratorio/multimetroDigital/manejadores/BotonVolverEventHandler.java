package laboratorio.multimetroDigital.manejadores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import laboratorio.multimetroDigital.vista.Vista;

public class BotonVolverEventHandler implements EventHandler<ActionEvent> {

	@SuppressWarnings("unused")
	private Vista vistaActual;
	private Vista vistaNueva;

	public BotonVolverEventHandler(Vista vistaActual, Vista vistaNueva) {
		
		this.vistaActual = vistaActual;
		this.vistaNueva = vistaNueva;
	}

	@Override
	public void handle(ActionEvent event) {
		
		this.vistaNueva.mostrar();
	}
}
