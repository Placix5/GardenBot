package server;

import org.schors.vertx.telegram.bot.LongPollingReceiver;
import org.schors.vertx.telegram.bot.TelegramBot;
import org.schors.vertx.telegram.bot.TelegramOptions;
import org.schors.vertx.telegram.bot.api.methods.SendMessage;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.mqtt.MqttClient;
import io.vertx.mqtt.MqttClientOptions;
import io.vertx.mqtt.impl.MqttClientImpl;

public class TelegramVerticle extends AbstractVerticle {

	
	private TelegramBot bot;
	
	private String classInstanceId = this.hashCode() + "";
	
	private MqttClientOptions MQTToptions = new MqttClientOptions();
	
	MqttClient mqttClient;
	
	
	@Override
	public void start(Promise<Void> future) {
		
		//Creaci�n del cliente MQTT para usarlo posteriormente
		MQTToptions.setAutoKeepAlive(true);
		MQTToptions.setAutoGeneratedClientId(false);
		MQTToptions.setClientId(classInstanceId);
		MQTToptions.setConnectTimeout(5000);
		MQTToptions.setCleanSession(true);
		MQTToptions.setKeepAliveTimeSeconds(10000);
		MQTToptions.setReconnectAttempts(10);
		MQTToptions.setReconnectInterval(5000);
		MQTToptions.setUsername("mqttbroker");
		MQTToptions.setPassword("mqttbrokerpass");
		mqttClient = new MqttClientImpl(vertx, MQTToptions);
		
		//BOT DE TELEGRAM
		TelegramOptions options = new TelegramOptions().setBotName("GardenBot").setBotToken("token");
		
		bot = TelegramBot.create(vertx, options).receiver(new LongPollingReceiver().onUpdate(handler -> {
			
			//-------------------------------------------------COMANDO HOLA----------------------------------------------------
			if(handler.getMessage().getText().toLowerCase().contains("hola")) {
				System.out.println("Mensaje recibido con exito :D");
				bot.sendMessage(new SendMessage().setText("Hola " + handler.getMessage().getFrom().getFirstName() + " bienvenido, soy el bot que ha creado @Placix5 :D\n\n"
						+ "Se me ha encomendado la tarea de informar sobre el estado del proyecto GardenBot hasta que se encuentre funcional, "
						+ "cuando eso ocurra podr� ofrecer informaci�n sobre sus plantas y el estado en el que se encuentran :D\n\n"
						+ "De momento, puedes preguntar sobre el estado de las siguientes cosas:\n\n"
						+ " - GitHub del proyecto\n"
						+ " - Prototipo hardware\n"
						+ " - MQTT").setChatId(handler.getMessage().getChatId()));
			}
			
			//-------------------------------------------------COMANDO GITHUB----------------------------------------------------	
				
			if (handler.getMessage().getText().toLowerCase().contains("github")){			
				
				bot.sendMessage(new SendMessage().setText("El enlace a GitHub de nuestro proyecto es el siguiente: \n\n"
						+ "https://github.com/Placix5/GardenBot\n\n"
						+ "Esperamos que disfrutes viendo el c�digo :D").setChatId(handler.getMessage().getChatId()));
				
			}
			
			//-------------------------------------------------COMANDO PROTOTIPO----------------------------------------------------
			
			if (handler.getMessage().getText().toLowerCase().contains("prototipo")){			
				
				bot.sendMessage(new SendMessage().setText("Nuestras piezas para comenzar el desarrollo del prototipo hardware a�n est�n en camino :(\n"
						+ "Pero pronto podremos comenzar a crear cosas nuevas :D\n\n"
						+ "Puedes preguntarme sobre el prototipo cuantas veces quieras y podr� informar de los avances del mismo").setChatId(handler.getMessage().getChatId()));
				
			}
			
			//-------------------------------------------------COMANDO MQTT----------------------------------------------------
			
			if (handler.getMessage().getText().toLowerCase().contains("mqtt")){			
				
				bot.sendMessage(new SendMessage().setText("Nuestro servidor MQTT a�n se encuentra en desarrollo, pero pronto ser� totalmente operativo :D\n\n"
						+ "Cuando esto ocurra, podr�s usar este mismo bot para poder realizar peticiones MQTT").setChatId(handler.getMessage().getChatId()));
				
			}
			
			//-------------------------------------------------COMANDO ADIOS----------------------------------------------------
			
			if (handler.getMessage().getText().toLowerCase().contains("adios")){			
				
				bot.sendMessage(new SendMessage().setText("Te echar� de menos :(\nPero siempre estar� aqu� disponible para ti :D").setChatId(handler.getMessage().getChatId()));
				
			}
			
		}));
		
		bot.start();
	}
	
}
