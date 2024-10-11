public class Main {
    public static void main(String[] args) {
        OrganizationTree orgTree = new OrganizationTree();
        Employee emp1 = new Employee(0,"Ahmad",1,10);
        Employee emp2 = new Employee(1,"Khaled",2,8);
        Employee emp3 = new Employee(1,"Bader",3,12);
        Employee emp4 = new Employee(2,"Husam",4,6);
        Employee emp5 = new Employee(2,"Mohammed",5,9);
       


        orgTree.addEmployee(emp1);
        orgTree.addEmployee(emp2);
        orgTree.addEmployee(emp3);
        orgTree.addEmployee(emp4);
        orgTree.addEmployee(emp5);

        orgTree.findAllCombinations();



        //System.out.println(orgTree.findEmployee(3)); //works



        // Print the organization tree
        //orgTree.printTree(orgTree.getRoot(),"");//works
    }
}
