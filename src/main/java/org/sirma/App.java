package org.sirma;

import com.opencsv.exceptions.CsvException;
import org.sirma.data.PairData;
import org.sirma.exception.ParseDateException;
import org.sirma.service.CSVFileParser;
import org.sirma.service.PairService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}