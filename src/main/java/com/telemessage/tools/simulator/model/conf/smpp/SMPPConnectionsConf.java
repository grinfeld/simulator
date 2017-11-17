package com.telemessage.tools.simulator.model.conf.smpp;

import com.telemessage.tools.simulator.model.conf.AbstractConnectionsConf;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Grinfeld Mikhail
 * @since 11/16/2017.
 */
@XmlRootElement (name = "connections")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SMPPConnectionsConf extends AbstractConnectionsConf<SMPPConnectionConf> {

    @XmlElement(name = "connection", type = SMPPConnectionConf.class)
    @Override
    public List<SMPPConnectionConf> getConnections() {
        return super.getConnections();
    }

    @Override
    public void setConnections(List<SMPPConnectionConf> connections) {
        super.setConnections(connections);
    }
}
