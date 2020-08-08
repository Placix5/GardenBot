#include <Arduino.h>
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <BlynkSimpleEsp8266.h>


//CONEXIÓN WIFI
WiFiClient client; //Lo usaremos para gestionar la conexión a la red wifi

String SSID = "PUERTOSURFIBRA869A";
String PASS = "A523869A";

//CONEXIÓN A BLYNK
char auth[] = "IepxU-2PjuOPqU4H9Dehh9JqG9f4Ta9L";
char ssid[] = "PUERTOSURFIBRA869A";
char pass[] = "A523869A";

int hum;
int manual = 0;
int riega = 0;
int para = 0;

BLYNK_WRITE(V1)
{
  manual = param.asInt(); 
}

BLYNK_WRITE(V2)
{
  riega = param.asInt(); 
}

BLYNK_WRITE(V3)
{
  para = param.asInt(); 
}

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

  Blynk.begin(auth,ssid,pass);

  pinMode(D1,OUTPUT);
  pinMode(A0,INPUT);

  digitalWrite(D1,HIGH);

  delay(200);
}

void loop() {

  Blynk.run();
  hum = analogRead(A0);

  Serial.print("Humedad: ");
  Serial.println(hum);

  if(manual){
    
    if(riega){
      digitalWrite(D1,HIGH);
    }
    if(para){
      digitalWrite(D1,LOW);
    }

    riega = 0;
    para = 0;

  }else{

    digitalWrite(D1,LOW);
    if(hum > 500){

    digitalWrite(D1,HIGH);

    }

  }

}