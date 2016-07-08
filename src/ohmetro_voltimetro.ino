//Valores del main:
const int buttonPin = 7;     
const int ledPinOhm = 2; 
const int ledPinVolt = 8;
unsigned long t_inicio = 0;
const float tiempo_accion = 2000;

//Variables de estado:
int buttonState = 0;     
int ohmState = 1;
int voltState = 0;
float tiempo = 0;

//Valores ohmetro:
int analogPinOhm = 0;
int raw = 0;
int Vin = 5;
float Vout = 0;
float R1 = 1000;
float R2 = 0;
float buffer = 0;

//Valores voltimetro:
int analogPinVolt = 5;
float vPow = 4.7;
float r1 = 100000;
float r2 = 10000;
float v_ant = 0;

//--------------------->Ohmetro<--------------------//
void ohmetro(float t){
  tiempo += t;
  raw = analogRead(analogPinOhm);
  if (raw){
    buffer = raw * Vin;
    Vout= (buffer)/1024.0;
    buffer= (Vin/Vout) -1;
    R2 = R1 * buffer;
    if (tiempo > tiempo_accion){
      //Serial.print("Vout: ");
      //Serial.println(Vout);
      Serial.print("R2: ");
      Serial.println(R2);
      //Serial.flush();
      tiempo -= tiempo_accion;
    }
  }
}

//-------------------->Voltimetro<---------------//

 void voltimetro(float t) {
   tiempo += t;
   float v = (analogRead(analogPinVolt) * vPow) / 1024.0;
   float v2 = v / (r2 / (r1 + r2));
   
   if (tiempo > tiempo_accion){
     Serial.print("V: ");
     Serial.print(v2);
     Serial.println(" v");
     tiempo -= tiempo_accion;
   }
 }

//------------------->Main<----------------------//
void setup() {
  Serial.begin(4800);
  
  pinMode(ledPinOhm, OUTPUT);
  pinMode(ledPinVolt, OUTPUT);
  pinMode(buttonPin, INPUT);
  
  digitalWrite(ledPinOhm, ohmState);
  digitalWrite(ledPinVolt, voltState);
}  

void loop() {
  buttonState = digitalRead(buttonPin);
  if (buttonState == LOW) {
    if (ohmState == LOW){
      ohmState = HIGH;
      voltState = LOW;
    }
    else{
      ohmState = LOW;
      voltState = HIGH;
    }
    digitalWrite(ledPinOhm, ohmState);
    digitalWrite(ledPinVolt, voltState);
  }
  
  unsigned long t_fin = millis();
  if (ohmState == HIGH){
    ohmetro(t_fin - t_inicio);
  }else{
    voltimetro(t_fin - t_inicio);
  }
  t_inicio = t_fin;
}
