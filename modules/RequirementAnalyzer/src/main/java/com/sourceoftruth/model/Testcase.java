package com.sourceoftruth.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "testcase")
public class Testcase {
        @XmlAttribute(name = "name")
        private String name;
        @XmlAttribute(name = "classname")
        private String classname;
        @XmlAttribute(name = "time")
        private float time;
        @XmlElement(name = "failure")
        private Failure failure;

        Testcase(){}

        public Testcase(
                String name,
                String classname,
                float time,
                Failure failure
        ) {
                this.name = name;
                this.classname = classname;
                this.time = time;
                this.failure = failure;
        }

        public String name() {
                return name;
        }

        public String classname() {
                return classname;
        }

        public float time() {
                return time;
        }

        public Failure failure() {
                return failure;
        }
}
