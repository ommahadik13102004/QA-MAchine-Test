package utils;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads a CSV file (header row + data rows) and returns it as a
 * String[][] so it can be fed straight into a TestNG @DataProvider.
 * Keeping test data in CSV files is what "parametrization" refers to
 * in the task brief for Login and Add Customer.
 */
public class CsvDataReader {

    public static Object[][] readCsv(String filePath) {
        List<String[]> rows = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            rows = reader.readAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to read CSV file: " + filePath, e);
        }

        // First row is the header - skip it
        Object[][] data = new Object[rows.size() - 1][];
        for (int i = 1; i < rows.size(); i++) {
            data[i - 1] = rows.get(i);
        }
        return data;
    }
}
