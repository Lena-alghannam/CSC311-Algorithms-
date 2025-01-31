# Optimal Project Team Formation - Brute Force Approach

## Overview

This project implements a brute force algorithm to find the optimal team of employees based on their skill levels, with the goal of identifying the pair with the highest total skill level and the broadest skill set. The algorithm processes employee data stored in a text file and generates all possible pairs of employees while ensuring the pairs meet specific conditions.
For the Dynamic programming approach please [click here](https://github.com/AichaRi/CSC311ProjectDyamicProgramming)



## Algorithm Design (Pseudo-code)

### Brute Force Algorithm Pseudo-code:
1. **Read employee data** from the input text file.
2. **Build an organization tree**, where each node represents an employee.
3. **Generate all possible pairs** of employees.
4. For each pair:
   - Ensure that the employees are **not direct supervisor and subordinate**.
   - Check if the **skill range is higher than 0**.
   - Calculate the **total skill level** for the pair.
   - Track the pair with the **highest total skill level**.
5. Return the **optimal team** with the highest total skill and the broadest skill set.

### Helper Methods Pseudo-code:

#### `findAllCombinations()`
```pseudo
if root = null then return
call outerBFS()
if optimalEmployee1 ≠ null and optimalEmployee2 ≠ null then
    print "Optimal Combination Based on Total Skill Level"
    print "Combination: " + optimalEmployee1.name + ", " + optimalEmployee2.name
    print "Total Skill Level: " + highestOverallSkillLevel
    print "Skill Difference: " + (max(optimalEmployee1.skillLevel, optimalEmployee2.skillLevel) - min(optimalEmployee1.skillLevel, optimalEmployee2.skillLevel))
else
    print "No valid combinations found."
```


#### `outerBFS()`
```pseudo
initialize outerQueue
enqueue root into outerQueue
while outerQueue is not empty do
    selectedNode ← dequeue outerQueue
    call innerBFS(selectedNode)
    subordinate ← selectedNode.left
    while subordinate ≠ null do
        enqueue subordinate into outerQueue
        subordinate ← subordinate.right
```

#### `innerBFS()`
```pseudo
initialize innerQueue
enqueue root into innerQueue
while innerQueue is not empty do
    current ← dequeue innerQueue
    if current.id ≠ selectedNode.id 
        and current.parentID ≠ selectedNode.id 
        and selectedNode.parentID ≠ current.id 
        and selectedNode.id < current.id then
        skillLevel ← selectedNode.skillLevel + current.skillLevel
        skillRange ← abs(selectedNode.skillLevel - current.skillLevel)
        if skillLevel > highestOverallSkillLevel 
            or (skillLevel = highestOverallSkillLevel and skillRange > highestSkillRange) then
            highestOverallSkillLevel ← skillLevel
            highestSkillRange ← skillRange
            optimalEmployee1 ← selectedNode
            optimalEmployee2 ← current
    subordinate ← current.left
    while subordinate ≠ null do
        enqueue subordinate into innerQueue
        subordinate ← subordinate.right

```

## Implementation

### Employee Class
Represents an individual employee, containing:
- ID
- Name
- Skill level
- Parent-child relationships

### OrganizationTree Class
Manages the hierarchy of employees and provides methods for:
- Adding employees
- Finding specific employees

### Main Class
Contains the brute force logic:
- Reads the employee data from a file
- Generates all possible teams
- Identifies the optimal team

## Time and Space Complexity

### Time Complexity:
- **Best case O(n²)**: Even if the first generated team is optimal, the algorithm still checks the entire tree, invoking `outerBFS()` and `innerBFS()` for each node.  
  Example for n = 1: O(1²) = 1.
  
- **Worst case O(n²)**: Every node in the tree will check every other node for compatibility.  
  For example, with 5 nodes, there will be 25 comparisons (O(n²)), and with 7 nodes, there will be 49 comparisons.

- **Overall time complexity**: O(n²)

### Space Complexity:
The space complexity is O(n), as the algorithm only stores employee data and a list of valid pairs (teams) during computation.

## Experimental Results

### Example 1:
For the input file:
```yaml
0 : Ahmad : 1 : 5
1 : Khalid : 2 : 11
1 : Bader : 3 : 8
2 : Husam : 4 : 9
2 : Mohammed : 5 : 7
```

Output:
```
Ahmad, Husam Skill Level = 5 + 9 = 14 (Skill Difference = 4)
Ahmad, Mohammed Skill Level = 5 + 7 = 12 (Skill Difference = 2)
Khalid, Bader Skill Level = 11 + 8 = 19 (Skill Difference = 3)
Bader, Husam Skill Level = 8 + 9 = 17 (Skill Difference = 1)
Bader, Mohammed Skill Level = 8 + 7 = 15 (Skill Difference = 1)
Husam, Mohammed Skill Level = 9 + 7 = 16 (Skill Difference = 2)

Optimal Combination Based on Total Skill Level
Among the valid combinations: The combination Khalid, Bader has the highest total skill level of 19

Optimal Team: Khalid, Bader
Total Skill Level: 19
Skill Difference (broad skill set): 11 - 8 = 3
```

### Example 2:
For the input file:
```yaml
0 : Ali : 1 : 9
1 : Saad : 2 : 8
```

Output:
```
No valid combinations found.
```

### Example 3:
For the input file:
```yaml
0 : Waleed : 1 : 5
1 : Omar : 2 : 5
1 : Salem : 3 : 5
2 : Khaled : 4 : 5
```

Output:
```
Waleed, Khaled Skill Level = 5 + 5 = 10 (Skill Difference = 0)
Omar, Salem Skill Level = 5 + 5 = 10 (Skill Difference = 0)
Salem, Khaled Skill Level = 5 + 5 = 10 (Skill Difference = 0)
no optimal Combination since there is no broad skill set
```



