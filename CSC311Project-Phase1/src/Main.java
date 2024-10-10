public class Main {
    public static void main(String[] args) {
        OrganizationTree orgTree = new OrganizationTree();
        Employee emp1 = new Employee(0,"Ahmad",1,10);
        Employee emp2 = new Employee(1,"Khaled",2,8);
        Employee emp3 = new Employee(1,"Bader",3,12);

        orgTree.addEmployee(emp1);
        orgTree.addEmployee(emp2);
        orgTree.addEmployee(emp3);



        //System.out.println(orgTree.findEmployee(3)); //works



        // Print the organization tree
        //orgTree.printTree(orgTree.getRoot(),"");//works
    }
}
