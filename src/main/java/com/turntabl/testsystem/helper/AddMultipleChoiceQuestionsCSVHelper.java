package com.turntabl.testsystem.helper;

import com.turntabl.testsystem.message.QuestionRequest;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AddMultipleChoiceQuestionsCSVHelper {
    public static List<QuestionRequest> csvToQuestions(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<QuestionRequest> questions = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                QuestionRequest question = new QuestionRequest();
                List<String> options = new ArrayList<>();
                question.setQuestion(csvRecord.get(0));
                question.setMark_allocated(Double.parseDouble(csvRecord.get(1)));
                question.setValidAnswer(csvRecord.get(2));
                options.add(csvRecord.get(3));
                options.add(csvRecord.get(4));
                options.add(csvRecord.get(5));
                options.add(csvRecord.get(6));
                question.setOption(options);
                questions.add(question);
            }
            return questions;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
