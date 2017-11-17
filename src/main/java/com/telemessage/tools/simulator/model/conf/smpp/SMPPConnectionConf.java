package com.telemessage.tools.simulator.model.conf.smpp;

import com.telemessage.tools.simulator.model.conf.AbstractConnectionConf;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.*;

/**
 * @author Grinfeld Mikhail
 * @since 11/16/2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement
public class SMPPConnectionConf extends AbstractConnectionConf {

    private TransmitterConf transmitter;
    private ReceiverConf receiver;
    private int transmitterRef;

    @Override
    @XmlAttribute
    public int getId() {
        return super.getId();
    }

    @XmlAttribute(name = "transmitter")
    public int getTransmitterRef() {
        return transmitterRef;
    }
}
