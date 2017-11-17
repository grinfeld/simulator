package com.telemessage.tools.simulator.model.conf;

import lombok.Data;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * @author Grinfeld Mikhail
 * @since 11/16/2017.
 *
 * Since jaxb not good to work with generics, classes which override this class
 * should override getter and setter with appropriate annotations above the methods
 *
 */
@Data
@XmlTransient
public abstract class AbstractConnectionsConf<T extends AbstractConnectionConf> {
    private List<T> connections;
}
