package com.elearningweb.library.util;

import com.elearningweb.library.model.Question;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelReadingUtil {


    public List<Question> getQuestionListFromExcel(String excelFile) throws IOException {
        try {
            FileInputStream inputStream = new FileInputStream(excelFile);
            List<Question> questionList = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();
            Iterator<Row> iterator = sheet.iterator();
            Row firstRow = iterator.next();
            Cell firstCell = firstRow.getCell(0);
            System.out.println(firstCell.getStringCellValue());
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Question question = new Question();

                question.setQuestionId(Long.parseLong(fmt.formatCellValue(currentRow.getCell(0))));
                question.setSection(fmt.formatCellValue(currentRow.getCell(1)));
                question.setParagraph(fmt.formatCellValue(currentRow.getCell(2)));
                question.setQuestion(fmt.formatCellValue(currentRow.getCell(3)));
                question.setCorrectAnswer(fmt.formatCellValue(currentRow.getCell(4)));

                questionList.add(question);
            }
//            for (Row currentRow : sheet) {
//                for (Cell currentCell : currentRow) {
//                    int columnIndex = currentCell.getColumnIndex();
//                    switch (columnIndex) {
//                        case 0 -> {
//
//                        }
//                        case 1 -> {
//                            question = new Question();
//                            question.setCorrectAnswer(fmt.formatCellValue(currentCell));
//                            questionList.add(question);
//                        }
//                    }
//                }
//            }
            workbook.close();
            return questionList;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
