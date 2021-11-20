package xpadro.testmanager.domain.test.impl;

public class BiochemistryTest extends AbstractSampleTest {
    private final float hbA1cLevel;
    private final float hbA1cLevelInMoPeriod;
    private final float hbA1cLevelInPeacePeriod;

    public BiochemistryTest(String name, float hbA1cLevel, float hbA1cLevelInMoPeriod, float hbA1cLevelInPeacePeriod) {
        super(name);
        this.hbA1cLevel = hbA1cLevel;
        this.hbA1cLevelInMoPeriod = hbA1cLevelInMoPeriod;
        this.hbA1cLevelInPeacePeriod = hbA1cLevelInPeacePeriod;
    }

    public float getHbA1cLevel() {
        return hbA1cLevel;
    }

    public float getHbA1cLevelInMoPeriod() {
        return hbA1cLevelInMoPeriod;
    }

    public float getHbA1cLevelInPeacePeriod() {
        return hbA1cLevelInPeacePeriod;
    }
}
