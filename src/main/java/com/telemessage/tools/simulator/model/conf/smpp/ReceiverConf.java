package com.telemessage.tools.simulator.model.conf.smpp;

import com.telemessage.tools.simulator.model.extensions.BindOption;
import com.telemessage.tools.simulator.model.extensions.BindType;
import com.telemessage.tools.simulator.model.extensions.ConcatenationType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Grinfeld Mikhail
 * @since 11/16/2017.
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ReceiverConf extends SMPPConnectionTypeConf {

    public ReceiverConf() {}

    @lombok.Builder(builderClassName = "Builder", toBuilder = true)
    protected ReceiverConf(BindType bindType, String host, int port, long timeout, byte dataCoding, String systemId, String password, String systemType, String encoding, BindOption bindOption, ConcatenationType concatenation, int threads, byte srcTON, byte srcNPI, byte dstTON, byte dstNPI, byte clbTON, byte clbNPI) {
        super(bindType, host, port, timeout, dataCoding, systemId, password, systemType, encoding, bindOption, concatenation, threads, srcTON, srcNPI, dstTON, dstNPI, clbTON, clbNPI);
    }

    @XmlElement
    @Override
    public BindType getBindType() {
        return super.getBindType();
    }
}
