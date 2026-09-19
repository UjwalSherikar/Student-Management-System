package StudentManagement;
import java.util.*;

public class StudentManagementSystem {
    static class Student {
        int id;
        String name, course;
        double marks;

        Student(int id, String name, String course, double marks) {
            this.id = id;
            this.name = name;
            this.course = course;
            this.marks = marks;
        }

        String grade() {
            if (marks >= 90) return "A+";
            if (marks >= 80) return "A";
            if (marks >= 70) return "B";
            if (marks >= 60) return "C";
            if (marks >= 50) return "D";
            return "F";
        }

        public String toString() {
            return String.format("%-5d %-18s %-12s %-7.1f %-5s",
                    id, name, course, marks, grade());
        }
    }

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    static void addStudent() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt(); sc.nextLine();
        if (find(id) != null) {
            System.out.println("Student ID already exists.");
            return;
        }
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter course: ");
        String course = sc.nextLine();
        System.out.print("Enter marks: ");
        double marks = sc.nextDouble();

        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }
        students.add(new Student(id, name, course, marks));
        System.out.println("Student added successfully.");
    }

    static Student find(int id) {
        for (Student s : students)
            if (s.id == id) return s;
        return null;
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\nID    Name               Course       Marks   Grade");
        System.out.println("------------------------------------------------------");
        for (Student s : students) System.out.println(s);
    }

    static void searchStudent() {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();
        Student s = find(id);
        System.out.println(s == null ? "Student not found." : "\n" + s);
    }

    static void updateMarks() {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();
        Student s = find(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter new marks: ");
        double marks = sc.nextDouble();
        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }
        s.marks = marks;
        System.out.println("Marks updated successfully.");
    }

    static void deleteStudent() {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();
        Student s = find(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        students.remove(s);
        System.out.println("Student deleted successfully.");
    }

    static void showStatistics() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        double total = 0;
        Student top = students.get(0);
        for (Student s : students) {
            total += s.marks;
            if (s.marks > top.marks) top = s;
        }
        System.out.printf("Average Marks: %.2f%n", total / students.size());
        System.out.println("Top Student: " + top.name + " (" + top.marks + ")");
    }

    static void menu() {
        System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Marks");
        System.out.println("5. Delete Student");
        System.out.println("6. Statistics");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }

    public static void main(String[] args) {
        students.add(new Student(101, "Rahul", "CSE", 86));
        students.add(new Student(102, "Ananya", "AI&ML", 92));
        students.add(new Student(103, "Vikram", "CSE", 74));

        System.out.println("Student Management System Started.");
        boolean running = true;
        while (running) {
            menu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> updateMarks();
                case 5 -> deleteStudent();
                case 6 -> showStatistics();
                case 7 -> {
                    running = false;
                    System.out.println("Thank you for using the system!");
                }
                default -> System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
}
