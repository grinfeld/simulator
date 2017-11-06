package com.telemessage.tools.simulator;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author Grinfeld Mikhail
 * @since 11/6/2017.
 */
@SpringBootApplication
public class SimulatorApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SimulatorApp.class).run(args);
    }

}
