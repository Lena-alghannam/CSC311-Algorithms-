import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        OrganizationTree orgTree = new OrganizationTree();
        List<Employee> employees = readEmployeesFromFile("example.txt");

        // Add employees to the organization tree
        for (Employee emp : employees) {
            orgTree.addEmployee(emp);
        }

        // Find the optimal team of two members
        orgTree.findAllCombinations();
    }

    // Method to read employees from a file
    public static List<Employee> readEmployeesFromFile(String filename) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" : ");
                int parentId = Integer.parseInt(parts[0]);
                String name = parts[1];
                int id = Integer.parseInt(parts[2]);
                int skillLevel = Integer.parseInt(parts[3]);
                Employee emp = new Employee(parentId, name, id, skillLevel);
                employees.add(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
