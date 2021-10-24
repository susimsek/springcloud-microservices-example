package io.github.susimsek.services.organization.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import static io.github.susimsek.services.organization.util.WsUtil.*;
import static io.github.susimsek.services.organization.util.OrganizationUtil.*;

@Configuration(proxyBeanMethods = false)
@EnableWs
public class WSConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, String.format("%s/*", BASE_PATH));
	}

	@Bean(name = "organizations")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema articlesSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("OrganizationPort");
		wsdl11Definition.setLocationUri(LOCATION_URI);
		wsdl11Definition.setTargetNamespace(NAMESPACE_URI);
		wsdl11Definition.setSchema(articlesSchema);
		return wsdl11Definition;
	}
	@Bean
	public XsdSchema organizationsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/organization.xsd"));
	}
} 