package pw.proj.letsmeet.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
class EmbeddedTomcatCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

	private final EnableDeleteBodyTomcatCustomizer enableDeleteBodyTomcatCustomizer;

	public EmbeddedTomcatCustomizer(final EnableDeleteBodyTomcatCustomizer enableDeleteBodyTomcatCustomizer) {
		this.enableDeleteBodyTomcatCustomizer = enableDeleteBodyTomcatCustomizer;
	}

	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		factory.addConnectorCustomizers(enableDeleteBodyTomcatCustomizer);
	}
}
