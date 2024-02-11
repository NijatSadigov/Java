public class Subcontractor implements SalariedEntity {
    long taxID;

    @Override
    public double GetSalary() {
        throw new UnsupportedOperationException("Unimplemented method 'GetSalary'");
    }
}
