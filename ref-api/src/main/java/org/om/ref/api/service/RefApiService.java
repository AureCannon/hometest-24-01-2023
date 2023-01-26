package org.om.ref.api.service;

import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.io.FileUtils;
import org.om.ref.api.model.AsxCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.LogManager;

@Service
public class RefApiService {

    @Value("${asx.path}")
    private String fileurl;

    @Value("${asx.csv}")
    private String csvfile;

    private static Logger logger = LoggerFactory.getLogger(RefApiService.class);

    private void downloadCsv() {
        try {
            FileUtils.copyURLToFile(
                    new URL(fileurl),
                    new File(csvfile),
                    60 * 1000,
                    60 * 1000);
        } catch (IOException e) {
            logger.debug("Failed to download csv file.", e);
        }
    }

    public Flux<AsxCompany> readCsv() {
        Flux<AsxCompany> beans = null;
        try {
            beans = Flux.fromIterable(new CsvToBeanBuilder(new FileReader(csvfile))
                    .withType(AsxCompany.class)
                    .build()
                    .parse());
        } catch (FileNotFoundException e) {
            logger.debug("Failed to read downloaded csv file.", e);
        }

        return beans;
    }
}
