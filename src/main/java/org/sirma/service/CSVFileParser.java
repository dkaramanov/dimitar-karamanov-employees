package org.sirma.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.sirma.data.PairData;
import org.sirma.exception.ParseDateException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CSVFileParser {

    private final PairDataFactory pairDataFactory;

    public CSVFileParser(PairDataFactory pairDataFactory) {
        this.pairDataFactory = pairDataFactory;
    }

    public List<PairData> parseCSVFile(MultipartFile file) throws IOException, CsvException, ParseDateException {
        if (file != null) {
            Reader reader = new InputStreamReader(file.getInputStream());

            CSVReader csvReader = new CSVReaderBuilder(reader).build();
            List<String[]> rows = csvReader.readAll();

            final var list = new ArrayList<PairData>();

            for (String[] row : rows) {
                final var id1 = row.length > 0 ? row[0].trim() : null;
                final var id2 = row.length > 1 ? row[1] .trim(): null;
                final var bDate = row.length > 2 ? row[2].trim() : null;
                final var eDate = row.length > 3 ? row[3].trim() : null;

                if (!Objects.equals(id1, "EmpID")) {
                    PairData data = pairDataFactory.parse(id1, id2, bDate, eDate);
                    list.add(data);
                }
            }
            return list;
        }
        throw new RuntimeException("Could not parse CSV file");
    }
}
