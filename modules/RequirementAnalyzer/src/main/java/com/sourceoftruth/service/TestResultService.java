package com.sourceoftruth.service;

import com.sourceoftruth.model.Testcase;
import com.sourceoftruth.model.Testsuite;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

@Service
public class TestResultService {

    JAXBContext jaxbContext;
    Unmarshaller unmarshaller;
    Path path;

    public TestResultService(
            @Value("${sot.path}")
            Path path
    ) throws JAXBException {
        this.path = path;
        jaxbContext = JAXBContext.newInstance(Testsuite.class);
        unmarshaller = jaxbContext.createUnmarshaller();
    }

    public List<Testcase> getTestCases() throws IOException {
        List<Path> reportFiles = findReports(path);

        return reportFiles.stream()
                .map(TestResultService::getInputStream)
                .map(this::parseFileToTestSuite)
                .map(Testsuite::testcases)
                .flatMap(Arrays::stream)
                .toList();
    }

    private static InputStream getInputStream(Path p) {
        try {
            return Files.newInputStream(p, StandardOpenOption.READ);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Testsuite parseFileToTestSuite(InputStream file) {
        try {
            return (Testsuite) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Path> findReports(Path path) throws IOException {
        System.out.println(path);
        try(var test = Files.find(path, Integer.MAX_VALUE, surefireFiles().and(isReport()), FileVisitOption.FOLLOW_LINKS)){
            return test.toList();
        }
    }

    private BiPredicate<Path, BasicFileAttributes> isReport() {
        return (path, attributes) -> path.toString().contains("surefire-reports") && attributes.isRegularFile();
    }

    private static BiPredicate<Path, BasicFileAttributes> surefireFiles() {
        return (path, attributes) -> path.toString().endsWith(".xml");
    }
}
