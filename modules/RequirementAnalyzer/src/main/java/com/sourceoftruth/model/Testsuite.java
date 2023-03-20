package com.sourceoftruth.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "testsuite")
public class Testsuite {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "tests")
    private int tests;
    @XmlAttribute(name = "failures")
    private int failures;
    @XmlAttribute(name = "errors")
    private int errors;
    @XmlAttribute(name = "skipped")
    private int skipped;
    @XmlElement(name = "testcase")
    private Testcase[] testcases;

    Testsuite(){}

    public Testsuite(
            String name,
            int tests,
            int failures,
            int errors,
            int skipped,
            Testcase[] testcases
    ) {
        this.name = name;
        this.tests = tests;
        this.failures = failures;
        this.errors = errors;
        this.skipped = skipped;
        this.testcases = testcases;
    }

    public String name() {
        return name;
    }
    public int testCount() {
        return tests;
    }
    public int failureCount() {
        return failures;
    }
    public int errorCount() {
        return errors;
    }
    public int skipCount() {
        return skipped;
    }
    public Testcase[] testcases() {
        return testcases;
    }
}