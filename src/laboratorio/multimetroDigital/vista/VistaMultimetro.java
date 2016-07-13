package laboratorio.multimetroDigital.vista;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import gnu.io.SerialPortEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import laboratorio.multimetroDigital.manejadores.BotonVolverEventHandler;
import laboratorio.multimetroDigital.modelo.Multimetro;

public class VistaMultimetro implements Vista, Observer {

	private Vista vistaAnterior;
	private Stage stage;
	private Multimetro modelo;
	private Scene escena;
	private BorderPane contenedor;
	private Label medicionVoltimetro;
	private Label medicionOhmetro; 
	
	/*Variables de conexión*/
	private SerialPort serialPort;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 4800;
	private final String PUERTO = "COM3";
	private InputStream input;
	
	public VistaMultimetro(Vista vistaAnterior) {
		
		this.modelo = new Multimetro();
		this.vistaAnterior = vistaAnterior;
		this.stage = vistaAnterior.getStage();
		this.modelo.addObserver(this);
		this.initialize();
	}

	private void initialize() {
		
		this.contenedor = new BorderPane();
		
		this.setContenedorPrincipal();
		
		this.setImagenDeFondo();
		
		this.setCaracteristicasAlContenedorPrincipal();
		
		this.escena = new Scene(this.contenedor, 350, 600);
		
		this.setConexion();
	}

	private void setConexion() {
		
		CommPortIdentifier iDPuerto = null;
		boolean encontrado = false;
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> puertos = CommPortIdentifier.getPortIdentifiers();
		
		while (puertos.hasMoreElements() && !encontrado) {
			
			CommPortIdentifier iDPuertoActual = puertos.nextElement();
			if (PUERTO.equals(iDPuertoActual.getName())) {
				iDPuerto = iDPuertoActual;
				encontrado = true;
			}
		}
		
		if (!encontrado) {
			System.exit(1);
		}
	
		try {
			
			this.serialPort = (SerialPort)iDPuerto.open(this.getClass().getName(), TIME_OUT);
			this.serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			
			this.input = this.serialPort.getInputStream();
			
			this.serialPort.addEventListener(e->{
				if (e.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
					String s = "";
					try {
						int dato = this.input.read();
						while (this.input.available() > 0 && (char)dato != '\n') {
							dato = this.input.read();
							s = s + (char)dato;
						}
						this.modelo.guardarValor(s);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setImagenDeFondo() {
		// Si le queremos poner una imagen copada de fondo
	}

	private void setContenedorPrincipal() {
		
		this.contenedor.setPadding(new Insets(25));
	}
	
	private void setCaracteristicasAlContenedorCentro() {
		
		GridPane contenedorCentral = new GridPane();
		contenedorCentral.setHgap(15);
		contenedorCentral.setVgap(10);
		contenedorCentral.setPadding(new Insets(25));
		contenedorCentral.setAlignment(Pos.CENTER);
		
		/*Voltímetro*/
		
		Label etiquetaVoltimetro = new Label("Voltímetro (Volt)");
		etiquetaVoltimetro.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		etiquetaVoltimetro.setTextFill(Color.WHITE);
		
		BackgroundFill fondoDeColorVoltimetro = new BackgroundFill(Color.GREEN, new CornerRadii(3), new Insets(0.0,0.0,0.0,0.0));
		etiquetaVoltimetro.setBackground(new Background(fondoDeColorVoltimetro));
		
		this.medicionVoltimetro = new Label(this.modelo.getTension());
		this.medicionVoltimetro.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
		this.medicionVoltimetro.setTextFill(Color.BLACK);
		
		contenedorCentral.add(this.medicionVoltimetro, 1, 0);
		
		contenedorCentral.add(etiquetaVoltimetro, 0, 0);
		
		/*Ohmetro*/
		
		Label etiquetaOhmetro = new Label("Ohmetro (Ohm)");
		etiquetaOhmetro.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		etiquetaOhmetro.setTextFill(Color.WHITE);
		
		BackgroundFill fondoDeColorOhmetro = new BackgroundFill(Color.GREEN, new CornerRadii(3), new Insets(0.0,0.0,0.0,0.0));
		etiquetaOhmetro.setBackground(new Background(fondoDeColorOhmetro));
		
		this.medicionOhmetro = new Label(this.modelo.getResistencia());
		this.medicionOhmetro.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
		this.medicionOhmetro.setTextFill(Color.BLACK);
		
		contenedorCentral.add(this.medicionOhmetro, 1, 1);
		
		contenedorCentral.add(etiquetaOhmetro, 0, 1);
		
		this.contenedor.setCenter(contenedorCentral);
	}
	
	private void setCaracteristicasAlContenedorInferior() {
		
		Button botonVolver = new Button("Volver");
		botonVolver.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
		botonVolver.setTextFill(Color.WHITE);
		
		BackgroundFill fondoDeColorVolver = new BackgroundFill(Color.RED, new CornerRadii(5), new Insets(0.0,0.0,0.0,0.0));
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
		
		this.contenedor.setBottom(botonVolver);
	}
	
	private void setCaracteristicasAlContenedorPrincipal() {
		
		this.setCaracteristicasAlContenedorCentro();
		this.setCaracteristicasAlContenedorInferior();
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

	@Override
	public void update(Observable o, Object arg) {
		
		this.medicionVoltimetro.setText(this.modelo.getTension());
		this.medicionOhmetro.setText(this.modelo.getResistencia());
	}
}
