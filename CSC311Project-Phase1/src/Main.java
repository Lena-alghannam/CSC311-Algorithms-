public class Main {
    public static void main(String[] args) {
        OrganizationTree orgTree = new OrganizationTree();

        // Add employees as per the input format (ID of parent : Name : ID : skill level)
        orgTree.addEmployee(1, "Ahmad", 10, 0);
        orgTree.addEmployee(2, "Khalid", 8, 1);
        orgTree.addEmployee(3, "Bader", 12, 1);
        orgTree.addEmployee(4, "Husam", 6, 2);
        orgTree.addEmployee(5, "Mohammed", 9, 2);

        // Print the organization tree
        orgTree.printTree(orgTree.getRoot(),"");
    }
}
