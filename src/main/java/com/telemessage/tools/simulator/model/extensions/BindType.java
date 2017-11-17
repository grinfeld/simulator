package com.telemessage.tools.simulator.model.extensions;

/**
 * @author Grinfeld Mikhail
 * @since 11/16/2017.
 */
public enum BindType {
    ESME, SMSC;

    public static BindType get(String value) {
        try {
            return BindType.valueOf(value.trim().toUpperCase());
        } catch (Exception ignore) {}
        return null;
    }
}
