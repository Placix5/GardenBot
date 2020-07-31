#include <Arduino.h>
#include <ESP32Servo.h>
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

WiFiClient client; //Lo usaremos para gestionar la conexión a la red wifi

String SSID = "PUERTOSURFIBRA869A";
String PASS = "A52869A";

Servo servo;

void setup() {
  
  Serial.begin(9600);
  WiFi.begin(SSID,PASS);

  //Conexión a la red wifi
  Serial.print("Conectando...");
  while(WiFi.status() != WL_CONNECTED){
    delay(500);
    Serial.print(".");
  }
  Serial.println("Conectado, IP: ");
  Serial.print(WiFi.localIP());


  //Inicialización de los servos
  //servo.setPeriodHertz(50);// Standard 50hz servo
  servo.attach(9, 500, 2400);
  servo.write(0);

}

void loop() {
  
  servo.write(90);
  delay(200);
  servo.write(0);
  delay(200);

}