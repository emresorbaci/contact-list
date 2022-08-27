package com.example.web.web.util;

import com.example.web.web.entity.Contact;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private static final int NAME_INDEX = 0;
    private static final int URL_INDEX = 1;

    public static List<Contact> readLineByLine() throws Exception {
        Path path = Paths.get(ClassLoader.getSystemResource("data/people.csv").toURI());
        List<Contact> contactList = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(path)) {

            CSVParser parser = new CSVParserBuilder()
                    //.withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreQuotations(true)
                    .build();

            com.opencsv.CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                if (line.length > 1) {
                    String name;
                    String url;
                    if (!line[2].equals("")) {
                        name = line[NAME_INDEX] + line[URL_INDEX];
                        url = line[2];
                    } else {
                        name = line[NAME_INDEX];
                        url = line[URL_INDEX];
                    }
                    contactList.add(new Contact(name, url));
                } else {
                    throw new RuntimeException("Not valid csv");
                }
            }

        }
        return contactList;
    }
}
