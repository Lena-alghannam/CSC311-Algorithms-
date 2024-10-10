// Define the Employee class to represent each node in the tree
class Employee {
    int id;             // Employee's unique ID
    String name;        // Employee's name
    int skillLevel;     // Employee's skill level
    Employee parent;    // Reference to the parent (supervisor)
    Employee left;      // Reference to the left child (first subordinate)
    Employee right;     // Reference to the right sibling (next subordinate of the same parent)

    // Constructor to initialize the employee node
    public Employee(int id, String name, int skillLevel) {
        this.id = id;
        this.name = name;
        this.skillLevel = skillLevel;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return "Employee [ID=" + id + ", Name=" + name + ", SkillLevel=" + skillLevel + "]";
    }
}
