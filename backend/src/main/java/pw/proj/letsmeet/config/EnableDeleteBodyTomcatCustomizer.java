package pw.proj.letsmeet.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.stereotype.Component;

@Component
class EnableDeleteBodyTomcatCustomizer implements TomcatConnectorCustomizer {

	@Override
	public void customize(Connector connector) {
		connector.setParseBodyMethods("POST,PUT,DELETE");
	}
}
