#include <Arduino.h>
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

WiFiClient client; //Lo usaremos para gestionar la conexión a la red wifi

String SSID = "PUERTOSURFIBRA869A";
String PASS = "A523869A";

int hum;

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

  pinMode(D1,OUTPUT);
  pinMode(A0,INPUT);

  digitalWrite(D1,HIGH);

  delay(200);
}

void loop() {

Serial.println(analogRead(A0));
delay(200);

hum = analogRead(A0);

digitalWrite(D1,LOW);
if(hum > 500){

  digitalWrite(D1,HIGH);

}

}