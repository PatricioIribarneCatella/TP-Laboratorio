package laboratorio.multimetroDigital.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import laboratorio.multimetroDigital.manejadores.BotonVolverEventHandler;

public class VistaCreditos implements Vista {

	private Vista vistaAnterior;
	private Stage stage;
	private VBox contenedor;
	private Scene escena;

	public VistaCreditos(Vista vistaAnterior) {
		
		this.vistaAnterior = vistaAnterior;
		this.stage = vistaAnterior.getStage();
		this.initialize();
	}

	private void initialize() {

		this.contenedor = new VBox();
		
		this.setContenedorPrincipal();
		
		this.setImagenDeFondo();
		
		this.setCaracteristicasAlContenedorPrincipal();
		
		this.escena = new Scene(this.contenedor, 350, 600);
	}

	private void setImagenDeFondo() {
		// Si le queremos poner una imagen copada de fondo
	}

	private void setContenedorPrincipal() {
		
		this.contenedor.setAlignment(Pos.CENTER);
		this.contenedor.setSpacing(16);
		this.contenedor.setPadding(new Insets(25));
	}

	private void setCaracteristicasAlContenedorPrincipal() {
		
		Label etiquetaNombreVersion = new Label("Multimetro Digital - v1.0");
		etiquetaNombreVersion.setFont(Font.font("Tahoma", FontWeight.NORMAL, 22));
		etiquetaNombreVersion.setTextFill(Color.BLACK);
		
		Label etiquetaAutores = new Label("Autores");
		etiquetaAutores.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		etiquetaAutores.setTextFill(Color.WHITE);
		
		BackgroundFill fondoDeColorAutores = new BackgroundFill(Color.RED, new CornerRadii(3), new Insets(0.0,0.0,0.0,0.0));
		etiquetaAutores.setBackground(new Background(fondoDeColorAutores));
		
		
		Label etiquetaAna = new Label("Ana Czarnitzki");
		etiquetaAna.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		etiquetaAna.setTextFill(Color.BLACK);
		
		Label etiquetaAriel = new Label("Ariel Vergara");
		etiquetaAriel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		etiquetaAriel.setTextFill(Color.BLACK);
		
		Label etiquetaPatricio = new Label("Patricio Iribarne Catella");
		etiquetaPatricio.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		etiquetaPatricio.setTextFill(Color.BLACK);
		
		Button botonVolver = new Button("Volver");
		botonVolver.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
		botonVolver.setTextFill(Color.BLACK);
		
		BackgroundFill fondoDeColorVolver = new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5), new Insets(0.0,0.0,0.0,0.0));
		botonVolver.setBackground(new Background(fondoDeColorVolver));
		
		botonVolver.setOnMouseEntered(e -> {
			
			botonVolver.setScaleX(1.2);
			botonVolver.setScaleY(1.2);
		});
		
		botonVolver.setOnMouseExited(e -> {
			
			botonVolver.setScaleX(1);
			botonVolver.setScaleY(1);
		});
		
		botonVolver.setOnAction(new BotonVolverEventHandler(this, this.vistaAnterior));
		
		this.contenedor.getChildren().addAll(etiquetaNombreVersion, etiquetaAutores, etiquetaAna, etiquetaAriel, etiquetaPatricio, botonVolver);
	}
	
	@Override
	public void mostrar() {
		
		this.stage.setTitle("Multímetro Digital");
		this.stage.setScene(this.escena);
		this.stage.show();
	}

	@Override
	public Stage getStage() {
		return this.stage;
	}
}
