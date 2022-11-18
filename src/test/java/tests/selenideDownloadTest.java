package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.pdftest.PDF;
import com.codeborne.pdftest.matchers.ContainsExactText;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class selenideDownloadTest {

    ClassLoader cl = selenideDownloadTest.class.getClassLoader();

    static {
        Configuration.browser = "firefox";
    }

    @Test
    void downloadTest() throws Exception {
        Selenide.open("https://github.com/junit-team/junit5/blob/main/README.md");
        File textFile = $("#raw-url").download();
        InputStream is = new FileInputStream(textFile);
        String strContent = null;
        try {
            byte[] fileContent = is.readAllBytes();
            strContent = new String(fileContent, StandardCharsets.UTF_8);
        } finally {
            org.assertj.core.api.Assertions.assertThat(strContent).contains("JUnit 5");
            is.close();
        }
    }

    @Test
    void pdfParsingTest() throws Exception {
        InputStream stream = cl.getResourceAsStream("pdf/junit-user-guide-5.8.2.pdf");
        PDF pdf = new PDF(stream);
        Assertions.assertEquals(166, pdf.numberOfPages);
      //  assertThat (pdf, new ContainsExactText("123"));
    }

    @Test
    void xlsParsingTest() throws Exception {
        InputStream stream = cl.getResourceAsStream("xls/sample-xlsx-file.xlsx");
        XLS xls = new XLS(stream);
        String stringCellValue = xls.excel.getSheetAt(0).getRow(3).getCell(1).getStringCellValue();
        org.assertj.core.api.Assertions.assertThat(stringCellValue).contains("Philip");
    }

    @Test
    void csvParsingTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("csv/teachers.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {

            List<String[]> content = reader.readAll();
            org.assertj.core.api.Assertions.assertThat(content).contains(
                    new String[]{"Name", "Surname"},
                    new String[]{"Darya", "Raicheva"},
                    new String[]{"Artem", "Eroshenko"}
            );
        }
    }

    @Test
    void zipParsingTest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/zip/sample-zip-file.zip"));
        ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("zip/sample-zip-file.zip"));
        ZipEntry entry;
        while((entry = is.getNextEntry()) != null) {
            org.assertj.core.api.Assertions.assertThat(entry.getName()).isEqualTo("sample.txt");
            try (InputStream inputStream = zf.getInputStream(entry)) {
                // checks
            }
        }
    }
}
