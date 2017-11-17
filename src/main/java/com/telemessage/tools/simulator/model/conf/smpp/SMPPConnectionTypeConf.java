package com.telemessage.tools.simulator.model.conf.smpp;

import com.telemessage.tools.simulator.model.extensions.BindOption;
import com.telemessage.tools.simulator.model.extensions.BindType;
import com.telemessage.tools.simulator.model.extensions.ConcatenationType;
import com.telemessage.tools.simulator.model.extensions.Constants;
import lombok.*;

import javax.xml.bind.annotation.*;

import static com.telemessage.tools.simulator.model.extensions.smpp.SMPPConstants.*;
import static com.telemessage.tools.simulator.model.extensions.smpp.SMPPConstants.DFLT_GSM_NPI;
import static com.telemessage.tools.simulator.model.extensions.smpp.SMPPConstants.DFLT_GSM_TON;

/**
 * @author Grinfeld Mikhail
 * @since 11/16/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlTransient
public abstract class SMPPConnectionTypeConf {
    protected BindType bindType;
    protected String host;
    protected int port;
    protected long timeout = DEF_TIMEOUT;
    protected byte dataCoding = DFLT_DATA_CODING;
    protected String systemId;
    protected String password;
    protected String systemType; // The system ID for authentication to SMSC: EXT_SME
    protected String encoding = Constants.ENC_ISO_8859_1;
    protected BindOption bindOption;
    protected ConcatenationType concatenation = ConcatenationType.NA;
    protected int threads = DEF_THREAD_NUM;

    protected byte srcTON = DFLT_GSM_TON;
    protected byte srcNPI = DFLT_GSM_NPI;
    protected byte dstTON = DFLT_GSM_TON;
    protected byte dstNPI = DFLT_GSM_NPI;
    protected byte clbTON = DFLT_GSM_TON;
    protected byte clbNPI = DFLT_GSM_NPI;
}
