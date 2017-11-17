package com.telemessage.tools.simulator.model.conf.http;

import com.telemessage.tools.simulator.model.conf.AbstractConnectionConf;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.*;

import static com.telemessage.tools.simulator.model.extensions.smpp.SMPPConstants.DEF_THREAD_NUM;

/**
 * @author Grinfeld Mikhail
 * @since 11/16/2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement
public class HttpConnectionConf extends AbstractConnectionConf {
    private String impl;
    private String httpMethod;

    private String drURL;
    private String drFromIP;
    private String inUrl;
    private int threads = DEF_THREAD_NUM;
    private int queue;

    @XmlAttribute
    @Override
    public int getId() {
        return super.getId();
    }

    @XmlAttribute (required = true)
    public String getImpl() {
        return impl;
    }

    @XmlAttribute (name = "method")
    public String getHttpMethod() {
        return httpMethod;
    }
}
