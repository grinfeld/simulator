package com.telemessage.tools.simulator.config;

import com.google.common.collect.Lists;
import com.telemessage.tools.simulator.model.conf.AbstractConnectionsConf;
import com.telemessage.tools.simulator.model.conf.http.HttpConnectionConf;
import com.telemessage.tools.simulator.model.conf.http.HttpConnectionsConf;
import com.telemessage.tools.simulator.model.conf.smpp.SMPPConnectionConf;
import com.telemessage.tools.simulator.model.conf.smpp.SMPPConnectionsConf;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        public Map<Integer, HttpConnectionConf> httpConf() throws Exception {
            return Optional.ofNullable(loadConf(HttpConnectionsConf.class, https)).map(HttpConnectionsConf::getConnections)
                .orElseGet(Lists::newArrayList).stream().collect(Collectors.toMap(HttpConnectionConf::getId, Function.identity()));
        }

        @Bean
        public Map<Integer, SMPPConnectionConf> smtpConf() throws Exception {
            return Optional.ofNullable(loadConf(SMPPConnectionsConf.class, smpps)).map(SMPPConnectionsConf::getConnections)
                .orElseGet(Lists::newArrayList).stream().collect(Collectors.toMap(SMPPConnectionConf::getId, Function.identity()));
        }

        private static <T extends AbstractConnectionsConf> T loadConf(Class<T> clazzT, String path) throws Exception {
            JAXBContext context = JAXBContext.newInstance(clazzT);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            String fileName = ClassLoader.getSystemResource(path).getFile();
            if (fileName == null || !(new File(fileName).exists()))
                return null;
            return  (T) unmarshaller.unmarshal(new File(fileName));
        }
    }

}
