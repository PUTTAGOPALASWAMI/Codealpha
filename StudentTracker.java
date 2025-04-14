import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> grades = new ArrayList<>();
        String input;

        // Input loop for grades
        do {
            System.out.print("Enter a student's grade (or type 'exit' to finish): ");
            input = scanner.nextLine();

            if (!input.equalsIgnoreCase("exit")) {
                try {
                    double grade = Double.parseDouble(input);
                    if (grade < 0 || grade > 100) {
                        System.out.println("Please enter a valid grade between 0 and 100.");
                    } else {
                        grades.add(grade);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric grade.");
                }
            }
        } while (!input.equalsIgnoreCase("exit"));

        // Calculate statistics
        if (grades.size() > 0) {
            double sum = 0;
            double highest = grades.get(0);
            double lowest = grades.get(0);

            for (double grade : grades) {
                sum += grade;
                if (grade > highest) {
                    highest = grade;
                }
                if (grade < lowest) {
                    lowest = grade;
                }
            }

            double average = sum / grades.size();

            // Display results
            System.out.printf("Total number of grades: %d%n", grades.size());
            System.out.printf("Average grade: %.2f%n", average);
            System.out.printf("Highest grade: %.2f%n", highest);
            System.out.printf("Lowest grade: %.2f%n", lowest);
        } else {
            System.out.println("No grades were entered.");
        }

        scanner.close();
    }
}
