package org.apromore.plugin.portal.CSVImporterPortal;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180ParserBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import org.apromore.service.csvimporter.InvalidCSVException;
import org.zkoss.util.media.Media;
import org.zkoss.zul.Messagebox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class CSVFileReader {

    CSVReader newCSVReader(Media media, String charset) {
        try{
            // Guess at ethe separator character
            Reader reader = media.isBinary() ? new InputStreamReader(media.getStreamData(), charset) : media.getReaderData();
            BufferedReader brReader = new BufferedReader(reader);
            String firstLine = brReader.readLine();
            if (firstLine == null || firstLine.isEmpty()) {
                throw new InvalidCSVException("Failed to read the log! header must have non-empty value!");
            }

            char separator = getMaxOccurringChar(firstLine);
            if (!(new String(Constants.supportedSeparators).contains(String.valueOf(separator)))) {
                throw new InvalidCSVException("Failed to read the log! Try different encoding");
            }

            // Create the CSV reader
            reader = media.isBinary() ? new InputStreamReader(media.getStreamData(), charset) : media.getReaderData();
            return (new CSVReaderBuilder(reader))
                    .withSkipLines(0)
                    .withCSVParser((new RFC4180ParserBuilder()).withSeparator(separator).build())
                    .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                    .build();
        }catch (InvalidCSVException e){
            Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
            return null;
        } catch (IOException e) {
            Messagebox.show("Unable to import file : " + e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
            return null;
        }
    }

    private char getMaxOccurringChar(String str) {
        char maxchar = ' ';
        int maxcnt = 0;
        int[] charcnt = new int[Character.MAX_VALUE + 1];
        for (int i = str.length() - 1; i >= 0; i--) {
            if (!Character.isLetter(str.charAt(i))) {
                for (char supportedSeparator : Constants.supportedSeparators) {
                    if (str.charAt(i) == supportedSeparator) {
                        char ch = str.charAt(i);
                        if (++charcnt[ch] >= maxcnt) {
                            maxcnt = charcnt[ch];
                            maxchar = ch;
                        }
                    }
                }
            }
        }
        return maxchar;
    }

}