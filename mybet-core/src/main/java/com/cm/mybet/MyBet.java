package com.cm.mybet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cm.mybet.core.engine.MyBetEngine;

/**
 * Main boot app.
 *
 * @author mcristian
 */
@SpringBootApplication
public class MyBet implements CommandLineRunner {

    private final MyBetEngine importer;

    @Autowired
    public MyBet(MyBetEngine importer) {
        this.importer = importer;
    }

    public static void main(String[] args) {
        SpringApplication.run(MyBet.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        importer.start();
    }
}