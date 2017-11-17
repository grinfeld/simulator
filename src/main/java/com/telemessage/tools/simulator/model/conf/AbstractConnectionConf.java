package com.telemessage.tools.simulator.model.conf;

import lombok.Data;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Grinfeld Mikhail
 * @since 11/16/2017.
 */
@Data
@XmlTransient
public abstract class AbstractConnectionConf {
    private String name;
    private String automatic_dr;
    private int id;

}
