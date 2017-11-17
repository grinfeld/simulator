package com.telemessage.tools.simulator.model.extensions;

/**
 * @author Grinfeld Mikhail
 * @since 11/16/2017.
 */
public enum ConcatenationType {
    NA, PAYLOAD, UDHI, SAR;

    public static ConcatenationType get(String value) {
        try {
            return ConcatenationType.valueOf(value.trim().toUpperCase());
        } catch (Exception ignore) {}
        return null;
    }
}
