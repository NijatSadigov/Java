public abstract class Employee implements SalariedEntity {
    protected String name;
    protected double salary;
    public Employee(String name, double salary){

this.name=name;
this.salary=salary;

    }
public void PrintEmployee(){
    System.out.println(this.name+ " "+ this.salary  );

}
//getters
public abstract String GetName();

//Increase Salary
public void IncreaseSalarybyP(double p){
 this.salary= this.salary*(100+p)/100;

}



}
