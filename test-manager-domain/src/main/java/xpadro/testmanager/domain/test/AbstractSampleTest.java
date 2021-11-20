package xpadro.testmanager.domain.test;

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
