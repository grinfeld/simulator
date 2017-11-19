package com.telemessage.tools.simulator.model.conf.smpp.smsc;

import com.cloudhopper.smpp.SmppServerConfiguration;
import com.cloudhopper.smpp.SmppServerHandler;
import com.cloudhopper.smpp.SmppServerSession;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.cloudhopper.smpp.pdu.BaseBind;
import com.cloudhopper.smpp.pdu.BaseBindResp;
import com.cloudhopper.smpp.ssl.SslConfiguration;
import com.cloudhopper.smpp.type.SmppProcessingException;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.cloudhopper.smpp.SmppConstants.*;

/**
 * @author Grinfeld Mikhail
 * @since 11/19/2017.
 */
public class SpecificSmppServerConfiguration extends SmppServerConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(SpecificSmppServerConfiguration.class);

    @Getter @Setter private String password;
    @Getter @Setter private int id;

    @lombok.Builder(builderClassName = "Builder")
    protected SpecificSmppServerConfiguration(int id, String name, String host, int port, boolean useSsl, long bindTimeout,
                                              long connTimeout, SslConfiguration sslConfiguration, String systemId, String password,
                                              boolean autoNegotiateInterfaceVersion, byte interfaceVersion, int maxConnectionSize,
                                              Boolean nonBlockingSocketsEnabled, Boolean reuseAddress, boolean jmxEnabled, String jmxDomain,
                                              int defaultWindowSize, long defaultWindowWaitTimeout, long defaultRequestExpiryTimeout,
                                              long defaultWindowMonitorInterval, boolean defaultSessionCountersEnabled) {

        validateNotEmptyString(host, "host");
        validateNotEmptyString(systemId, "systemId");
        validateNotEmptyString(password, "password");
        validatePort(port);
        this.id = id;
        this.setName(name);
        this.setHost(host);
        this.setPort(port);
        this.setUseSsl(useSsl);
        this.setSslConfiguration(sslConfiguration);
        this.setBindTimeout(defaultLong(bindTimeout, "bindTimeout", DEFAULT_BIND_TIMEOUT));
        this.setConnectTimeout(defaultLong(connTimeout, "connTimeout", DEFAULT_BIND_TIMEOUT));
        this.setSystemId(systemId);
        this.password = password;
        this.setAutoNegotiateInterfaceVersion(autoNegotiateInterfaceVersion);
        this.setInterfaceVersion(defaultByte(interfaceVersion, "interfaceVersion", VERSION_3_4));
        this.setMaxConnectionSize(defaultInt(maxConnectionSize, "maxConnectionSize", DEFAULT_SERVER_MAX_CONNECTION_SIZE));
        this.setNonBlockingSocketsEnabled(Optional.ofNullable(nonBlockingSocketsEnabled).orElse(DEFAULT_SERVER_NON_BLOCKING_SOCKETS_ENABLED));
        this.setReuseAddress(Optional.ofNullable(reuseAddress).orElse(DEFAULT_SERVER_REUSE_ADDRESS));
        this.setJmxDomain(Optional.ofNullable(jmxDomain).orElse("com.cloudhopper.smpp"));
        this.setJmxEnabled(jmxEnabled);
        this.setDefaultWindowSize(defaultInt(defaultWindowSize, "defaultWindowSize", DEFAULT_WINDOW_SIZE));
        this.setDefaultWindowWaitTimeout(defaultLong(defaultWindowWaitTimeout, "defaultWindowWaitTimeout", DEFAULT_WINDOW_WAIT_TIMEOUT));
        this.setDefaultRequestExpiryTimeout(defaultLong(defaultRequestExpiryTimeout, "defaultRequestExpiryTimeout", DEFAULT_REQUEST_EXPIRY_TIMEOUT));
        this.setDefaultWindowMonitorInterval(defaultLong(defaultWindowMonitorInterval, "defaultWindowMonitorInterval", DEFAULT_WINDOW_MONITOR_INTERVAL));
        this.setDefaultSessionCountersEnabled(defaultSessionCountersEnabled);
    }

    private void validateNotEmptyString(String value, String field) {
        if (value == null || value.trim().length() <= 0)
            throw new IllegalArgumentException("Empty " + field + " - " + String.valueOf(value));
    }

    private byte defaultByte(byte value, String field, byte def) {
        if (value <= 0) {
            LOGGER.debug("Using default " + def + " for " + field + " instead of " + value);
            return def;
        }
        return value;
    }

    private int defaultInt(int value, String field, int def) {
        if (value <= 0) {
            LOGGER.debug("Using default " + def + " for " + field + " instead of " + value);
            return def;
        }
        return value;
    }

    private long defaultLong(long value, String field, long def) {
        if (value <= 0) {
            LOGGER.debug("Using default " + def + " for " + field + " instead of " + value);
            return def;
        }
        return value;
    }

    private void validatePort(int port) {
        if (port <= 0)
            throw new IllegalArgumentException("Port should be positive number, but was " + port);
    }

    public SmppServerHandler handler() {
        return InnerSmppServerHandler.builder().id(this.id).name(this.getName()).systemId(this.getSystemId()).password(this.getPassword()).build();
    }

    // TODO: add listener to Handler or maybe 3 or 2 lambda expressions (or again get it outside of this class)
    @Data
    @Value
    @lombok.Builder(builderClassName = "Builder")
    private static class InnerSmppServerHandler implements SmppServerHandler {

        public static final int ESME_RBINDFAIL = 0x0000000D;

        private String systemId;
        private String password;
        private int id;
        private String name;

        @Override
        public void sessionBindRequested(Long sessionId, SmppSessionConfiguration sessionConfiguration, BaseBind bindRequest) throws SmppProcessingException {
            if ( !(systemId.equals(sessionConfiguration.getSystemId()) && password.equals(sessionConfiguration.getPassword())) ) {
                throw new SmppProcessingException(ESME_RBINDFAIL, "Failed to bind connection No. " + id + ", " + name + " with " + sessionConfiguration.getSystemId() + " and " + sessionConfiguration.getPassword());
            }
        }

        @Override
        public void sessionCreated(Long sessionId, SmppServerSession session, BaseBindResp preparedBindResponse) throws SmppProcessingException {

        }

        @Override
        public void sessionDestroyed(Long sessionId, SmppServerSession session) {

        }
    }
}
