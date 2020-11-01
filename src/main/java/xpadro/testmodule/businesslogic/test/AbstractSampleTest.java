package xpadro.testmodule.businesslogic.test;

public class AbstractSampleTest implements SampleTest {
    private final String name;

    public AbstractSampleTest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
