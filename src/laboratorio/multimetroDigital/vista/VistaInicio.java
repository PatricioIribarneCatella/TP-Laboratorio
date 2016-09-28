package laboratorio.multimetroDigital.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class VistaInicio implements Vista {

	private Stage stage;
	private Scene escena;
	private VBox contenedor;
	
	public VistaInicio(Stage stage) {
		
		this.stage = stage;
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
		this.contenedor.setSpacing(20);
		this.contenedor.setPadding(new Insets(25));
	}

	private VBox setCaracateristicasAlContendorBotonMedir() {
		
		VBox contenedor = new VBox();
		
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setSpacing(20);
		contenedor.setPadding(new Insets(10, 10, 80, 10));
		
		return contenedor;
	}
	
	private void setCaracteristicasAlContenedorPrincipal() {
		
		Button botonMedir = new Button("Medir");
		botonMedir.setTextFill(Color.WHITE);
		
		BackgroundFill fondoDeColor = new BackgroundFill(Color.RED, new CornerRadii(5), new Insets(0.0,0.0,0.0,0.0));
		botonMedir.setBackground(new Background(fondoDeColor));
		
		botonMedir.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		botonMedir.setAlignment(Pos.TOP_CENTER);
		botonMedir.setMaxSize(100, 30);
		
		botonMedir.setOnMouseEntered(e -> {
			
			botonMedir.setScaleX(1.2);
			botonMedir.setScaleY(1.2);
		});
		
		botonMedir.setOnMouseExited(e -> {
			
			botonMedir.setScaleX(1);
			botonMedir.setScaleY(1);
		});
		
		botonMedir.setOnAction(e -> {
			
			Vista nuevaVista = new VistaMultimetro(this);
			nuevaVista.mostrar();
		});
		
		Button botonAcerca = new Button("Acerca");
		botonAcerca.setTextFill(Color.WHITE);
		
		BackgroundFill fondoDeColorAcerca = new BackgroundFill(Color.BLUE, new CornerRadii(5), new Insets(0.0,0.0,0.0,0.0));
		botonAcerca.setBackground(new Background(fondoDeColorAcerca));
		
		botonAcerca.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
		botonAcerca.setAlignment(Pos.TOP_CENTER);
		
		botonAcerca.setOnMouseEntered(e -> {
			
			botonAcerca.setScaleX(1.3);
			botonAcerca.setScaleY(1.3);
		});
		
		botonAcerca.setOnMouseExited(e -> {
			
			botonAcerca.setScaleX(1);
			botonAcerca.setScaleY(1);
		});
		
		botonAcerca.setOnAction(e -> {
			
			VistaCreditos nuevaVista = new VistaCreditos(this);
			nuevaVista.mostrar();
		});
		
		VBox contenedorBotonMedir = this.setCaracateristicasAlContendorBotonMedir();
		contenedorBotonMedir.getChildren().addAll(botonMedir, botonAcerca);
		
		this.contenedor.getChildren().add(contenedorBotonMedir);
	}

	@Override
	public void mostrar() {
	
		this.stage.setTitle("Multimetro Digital");
		this.stage.setScene(this.escena);
		this.stage.show();
	}

	@Override
	public Stage getStage() {
		return this.stage;
	}
}
