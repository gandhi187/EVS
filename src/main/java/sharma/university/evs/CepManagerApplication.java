package sharma.university.evs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import sharma.university.evs.event.SimpleEvent;
import sharma.university.evs.service.StatementListener;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class CepManagerApplication {

    public static void main(String[] args) {


        SpringApplication.run(CepManagerApplication.class, args);
//
        
//        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
//        epService.getEPAdministrator().getConfiguration().addEventType("ficken", SimpleEvent.class);
//        String expression = "select avg(value) from org.esperiot.event.SimpleEvent.win:time(30 sec)";
//        EPStatement statement = epService.getEPAdministrator().createEPL(expression);
////		epService.getEPAdministrator().cre
//        StatementListener listener = new StatementListener();
//        statement.addListener(listener);
//        Configuration config = new Configuration();
//        SimpleEvent event = new SimpleEvent("shirt", 74.50);
//        epService.getEPRuntime().sendEvent(event);
    }
}
