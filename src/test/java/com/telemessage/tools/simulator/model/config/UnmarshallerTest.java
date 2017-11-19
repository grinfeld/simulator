package com.telemessage.tools.simulator.model.config;

import com.telemessage.tools.simulator.config.AppConfig;
import com.telemessage.tools.simulator.model.conf.http.HttpConnectionConf;
import com.telemessage.tools.simulator.model.conf.http.HttpConnectionsConf;
import com.telemessage.tools.simulator.model.conf.smpp.SMPPConnectionConf;
import com.telemessage.tools.simulator.model.conf.smpp.SMPPConnectionsConf;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
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
    public void loadConf_whenSmpp_ExpectedLoaded5Confs() throws Exception {
        SMPPConnectionsConf c = Whitebox.invokeMethod(AppConfig.ConnectionConfiguration.class, "loadConf", SMPPConnectionsConf.class, "smpps.xml");

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
    public void loadConf_whenHttp_ExpectedLoaded6Confs() throws Exception {
        HttpConnectionsConf c = Whitebox.invokeMethod(AppConfig.ConnectionConfiguration.class, "loadConf", HttpConnectionsConf.class, "https.xml");

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
