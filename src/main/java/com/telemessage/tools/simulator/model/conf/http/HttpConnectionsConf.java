package com.telemessage.tools.simulator.model.conf.http;

import com.telemessage.tools.simulator.model.conf.AbstractConnectionsConf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Grinfeld Mikhail
 * @since 11/16/2017.
 */
@XmlRootElement (name = "connections")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class HttpConnectionsConf extends AbstractConnectionsConf<HttpConnectionConf> {

    @Override
    @XmlElement(name = "connection", type = HttpConnectionConf.class)
    public List<HttpConnectionConf> getConnections() {
        return super.getConnections();
    }

    @Override
    public void setConnections(List<HttpConnectionConf> connections) {
        super.setConnections(connections);
    }
}
