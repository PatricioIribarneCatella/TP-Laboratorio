package laboratorio.multimetroDigital.aplicacion;

import javafx.application.Application;
import javafx.stage.Stage;
import laboratorio.multimetroDigital.vista.Vista;
import laboratorio.multimetroDigital.vista.VistaInicio;

public class AplicacionMultimetroDigital extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		Vista vista = new VistaInicio(stage);
		vista.mostrar();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
