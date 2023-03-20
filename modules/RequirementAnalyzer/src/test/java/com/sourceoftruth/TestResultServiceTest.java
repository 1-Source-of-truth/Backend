package com.sourceoftruth;

import com.sourceoftruth.model.Testcase;
import com.sourceoftruth.service.TestResultService;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TestResultServiceTest {

    TestResultService testResultService;

    TestResultServiceTest() throws JAXBException {
        testResultService = new TestResultService(Path.of(getClass().getResource("/surefire-reports").getPath()));
    }

    @Test
    @DisplayName("Test results can be found in a subdirectory")
    void getTestCases() throws IOException {
        List<Testcase> testCases = testResultService.getTestCases();

        assertThat(testCases)
                .isNotEmpty();
    }

    @Test
    @DisplayName("DisplayName is used as name for test results")
    void getTestCases_test() throws IOException {
        List<Testcase> testCases = testResultService.getTestCases();

        assertThat(testCases)
                .extracting(Testcase::name)
                .containsExactlyInAnyOrder(
                        "DisplayName is used as name for test results",
                        "Test results can be found in a subdirectory"
                );
    }
}