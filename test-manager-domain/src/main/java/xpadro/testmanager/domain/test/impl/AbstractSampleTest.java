package xpadro.testmanager.domain.test.impl;

import xpadro.testmanager.domain.test.SampleTest;

public abstract class AbstractSampleTest implements SampleTest {
    private final String name;

    public AbstractSampleTest(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
