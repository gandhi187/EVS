package sharma.university.evs.service;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class StatementListener implements StatementAwareUpdateListener {

    private final Logger log = LoggerFactory.getLogger(StatementListener.class);


    @Inject
    private MqttClient mqttClient;

    private ObjectMapper mapper = new ObjectMapper();

    public void update(EventBean[] newEvents, EventBean[] oldEvents, EPStatement statement, EPServiceProvider provider) {
        try {
            MqttMessage message = new MqttMessage(mapper.writeValueAsBytes("aaaaaa"));

			mqttClient.publish("/test/",message);
		} catch (MqttPersistenceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MqttException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        EventBean event = newEvents[0];
        log.info("Update statement: {}", statement.getName());
        try {
            MqttMessage message = new MqttMessage(mapper.writeValueAsBytes(event.getUnderlying()));
            event.getEventType();
            mqttClient.publish("/test/" + statement.getName(), message);
        } catch (JsonProcessingException e) {
            log.error("Can not deserialize event value to JSON! cause: {}", e.getCause());
        } catch (MqttException e) {
            log.error("Can not publish statement to Mqtt broker! cause: {}", e.getCause());
        } catch (Exception e) {
            log.error("Unpredicted exception!", e);
        }

    }
}
