package com.telemessage.tools.simulator.config;

import com.telemessage.tools.simulator.model.conf.AbstractConnectionsConf;
import com.telemessage.tools.simulator.model.conf.http.HttpConnectionsConf;
import com.telemessage.tools.simulator.model.conf.smpp.SMPPConnectionsConf;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * @author Grinfeld Mikhail
 * @since 11/6/2017.
 */
@Configuration
public class AppConfig {

    @Configuration
    public static class ConnectionConfiguration {
        @Value("${com.telemessage.tools.simulator.conf.smpp}")
        protected String smpps;
        @Value("${com.telemessage.tools.simulator.conf.http}")
        protected String https;

        @Bean
        public HttpConnectionsConf httpConf() throws Exception {
            return loadConf(HttpConnectionsConf.class, https);
        }

        @Bean
        public SMPPConnectionsConf smtpConf() throws Exception {
            return loadConf(SMPPConnectionsConf.class, smpps);
        }

        private static <T extends AbstractConnectionsConf> T loadConf(Class<T> clazzT, String path) throws Exception {
            JAXBContext context = JAXBContext.newInstance(clazzT);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            return  (T) unmarshaller.unmarshal(ClassLoader.getSystemResourceAsStream(path));
        }
    }

}
