package com.telemessage.tools.simulator.model.extensions;

/**
 * @author Grinfeld Mikhail
 * @since 11/16/2017.
 */
public enum BindOption {
    receiver, transceiver, transmitter;

    public static BindOption get(String value) {
        try {
            return BindOption.valueOf(value.trim().toLowerCase());
        } catch (Exception ignore) {}
        return null;
    }
}
