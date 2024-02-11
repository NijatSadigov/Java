import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    public List<Employee> Employees;


    public  Manager(String name, double salary){
        super(name, salary);
        Employees=new ArrayList<>(); 
    } 
    
    
    public String GetName(){return this.name;}


    public double GetSalary(){
        double sum=0;
        for (int i=0;i<Employees.size();i++){
            sum+=Employees.get(i).GetSalary();

        }
        return this.salary+sum*0.05;}
    
    ////////////////////////////////////
    public void AddEmployee( Employee e){
        Employees.add(e);

    }
    public void removeEmployee( Employee e){
        Employees.remove(e);

    }
}
