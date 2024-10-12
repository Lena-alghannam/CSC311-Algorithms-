class Employee {
    int ParentID;             // Employee's parent unique ID
    int id;                   // Employee's unique ID
    String name;              // Employee's name
    int skillLevel;           // Employee's skill level
    Employee parent;          // Reference to the parent (supervisor)
    Employee left;            // Reference to the left child (first subordinate)
    Employee right;           // Reference to the right sibling (next subordinate of the same parent)

    // Constructor to initialize the employee node
    public Employee(int ParentID, String name, int id, int skillLevel) {
        this.ParentID = ParentID;
        this.id = id;
        this.name = name;
        this.skillLevel = skillLevel;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public int getParentID() {
        return ParentID;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    @Override
    public String toString() {
        return "Employee [ID=" + id + ", Name=" + name + ", SkillLevel=" + skillLevel + "]";
    }
}
