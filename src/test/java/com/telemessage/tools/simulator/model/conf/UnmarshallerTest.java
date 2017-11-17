package com.telemessage.tools.simulator.model.conf;

import com.telemessage.tools.simulator.model.conf.http.HttpConnectionConf;
import com.telemessage.tools.simulator.model.conf.http.HttpConnectionsConf;
import com.telemessage.tools.simulator.model.conf.smpp.SMPPConnectionConf;
import com.telemessage.tools.simulator.model.conf.smpp.SMPPConnectionsConf;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author Grinfeld Mikhail
 * @since 11/16/2017.
 */
public class UnmarshallerTest {

    @Test
    public void unmarshalSMPPConfTest() throws Exception {
        JAXBContext context = JAXBContext.newInstance(SMPPConnectionsConf.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        SMPPConnectionsConf c = (SMPPConnectionsConf) unmarshaller.unmarshal(ClassLoader.getSystemResourceAsStream("smpps.xml"));

        assertThat(c).isNotNull();
        List<SMPPConnectionConf> connections = c.getConnections();
        assertThat(connections).hasSize(5);
        Map<Integer, SMPPConnectionConf> map = connections.stream().collect(Collectors.toMap(SMPPConnectionConf::getId, Function.identity()));

        assertThat(map.get(5)).isNotNull();
        assertThat(map.get(7)).isNotNull();
        assertThat(map.get(8)).isNotNull();
        assertThat(map.get(9)).isNotNull();
        assertThat(map.get(387)).isNotNull();
    }

    @Test
    public void unmarshalHttpConfTest() throws Exception {
        JAXBContext context = JAXBContext.newInstance(HttpConnectionsConf.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        HttpConnectionsConf c = (HttpConnectionsConf) unmarshaller.unmarshal(ClassLoader.getSystemResourceAsStream("https.xml"));

        assertThat(c).isNotNull();
        List<HttpConnectionConf> connections = c.getConnections();
        assertThat(connections).hasSize(6);
        Map<Integer, HttpConnectionConf> map = connections.stream().collect(Collectors.toMap(HttpConnectionConf::getId, Function.identity()));

        assertThat(map.get(1)).isNotNull();
        assertThat(map.get(6)).isNotNull();
        assertThat(map.get(14)).isNotNull();
        assertThat(map.get(74)).isNotNull();
        assertThat(map.get(1500)).isNotNull();
        assertThat(map.get(1000)).isNotNull();
    }

}
