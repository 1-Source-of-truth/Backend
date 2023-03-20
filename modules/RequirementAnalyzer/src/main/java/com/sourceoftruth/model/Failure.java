package com.sourceoftruth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

public class Failure {
    @XmlAttribute(name = "message")
    @JsonIgnore
    private String message;

    @XmlAttribute(name = "type")
    private String type;

    @XmlValue
    @JsonIgnore
    private String stackTrace;
}
