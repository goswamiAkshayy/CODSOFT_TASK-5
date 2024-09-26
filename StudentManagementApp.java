import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagementApp {
    private static final Scanner scanner = new Scanner(System.in); // Scanner for user input
    private final List<Student> students; // List to hold student data

    public StudentManagementApp() {
        students = new ArrayList<>();
    }

    public static void main(String[] args) {
        StudentManagementApp app = new StudentManagementApp(); // Create the app instance
        boolean running = true; // Control the application loop

        while (running) {
            // Print menu
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Invalid choice. Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }

            switch (choice) {
                case 1 -> app.addStudent(); // Add a new student
                case 2 -> app.removeStudent(); // Remove a student by roll number
                case 3 -> app.searchStudent(); // Search a student by roll number
                case 4 -> app.displayAllStudents(); // Display all students
                case 5 -> {
                    System.out.println("Exiting...");
                    running = false; // Exit the loop and close the app
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to add a student with input validation
    private void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.next();

        int rollNumber = -1;
        while (true) {
            System.out.print("Enter roll number: ");
            if (scanner.hasNextInt()) {
                rollNumber = scanner.nextInt();
                break; // Valid roll number entered
            } else {
                System.out.println("Invalid input. Please enter a valid roll number.");
                scanner.next(); // Clear invalid input
            }
        }

        System.out.print("Enter grade: ");
        String grade = scanner.next();

        // Create new Student object and add to list
        Student student = new Student(name, rollNumber, grade);
        students.add(student);
        System.out.println("Student added: " + student);
    }

    // Method to remove a student by roll number
    private void removeStudent() {
        System.out.print("Enter roll number of the student to remove: ");
        int rollNumber = scanner.nextInt();
        boolean removed = students.removeIf(student -> student.getRollNumber() == rollNumber);

        if (removed) {
            System.out.println("Student with roll number " + rollNumber + " removed.");
        } else {
            System.out.println("No student found with roll number " + rollNumber);
        }
    }

    // Method to search for a student by roll number
    private void searchStudent() {
        System.out.print("Enter roll number of the student to search: ");
        int rollNumber = scanner.nextInt();
        Student foundStudent = null;

        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                foundStudent = student;
                break;
            }
        }

        if (foundStudent != null) {
            System.out.println("Student found: " + foundStudent);
        } else {
            System.out.println("No student found with roll number " + rollNumber);
        }
    }

    // Method to display all students
    private void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            System.out.println("Student List:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Inner class representing a Student
    private static class Student {
        private final String name;
        private final int rollNumber;
        private final String grade;

        public Student(String name, int rollNumber, String grade) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
        }

        public int getRollNumber() {
            return rollNumber;
        }

        @Override
        public String toString() {
            return "Student [Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade + "]";
        }
    }
}
