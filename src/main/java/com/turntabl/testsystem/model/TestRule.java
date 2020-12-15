package com.turntabl.testsystem.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="testRules")
public class TestRule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_rule_id")
    private long test_rule_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
    @Column(name = "test_rule")
    private String test_rule;
    public TestRule() {
    }
    public TestRule(long test_rule_id, Test test, String test_rule) {
        this.test_rule_id = test_rule_id;
        this.test = test;
        this.test_rule = test_rule;
    }
    public long getTest_rule_id() {
        return test_rule_id;
    }
    public void setTest_rule_id(long test_rule_id) {
        this.test_rule_id = test_rule_id;
    }
    public Test getTest() {
        return test;
    }
    public void setTest(Test test) {
        this.test = test;
    }
    public String getTest_rule() {
        return test_rule;
    }
    public void setTest_rule(String test_rule) {
        this.test_rule = test_rule;
    }
}
