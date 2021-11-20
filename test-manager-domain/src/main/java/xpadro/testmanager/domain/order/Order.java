package xpadro.testmanager.domain.order;


import xpadro.testmanager.domain.test.SampleTest;

import java.util.List;

public class Order {
    private final List<TestRequest> tests;

    public Order(List<TestRequest> tests) {
        this.tests = tests;
    }

    public List<TestRequest> getTestRequests() {
        return tests;
    }
}
