public class Subordinate extends Employee{
    public  Subordinate(String name, double salary){super(name, salary);} 
    
    @Override
    public String GetName(){return this.name;}
    @Override
    public double GetSalary(){return this.salary;}

    
}
