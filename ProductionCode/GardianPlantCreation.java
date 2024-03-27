import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GardianPlantCreation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Garden Creation Program!");

        int numberOfPlants = 0;
        do {
            try {
                System.out.print("How many plants do you want to add to the garden? ");
                numberOfPlants = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (numberOfPlants <= 0) {
                    System.out.println("The number of plants must be greater than 0. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numerical value.");
                scanner.next(); // Clear the invalid input
            }
        } while (numberOfPlants <= 0);

        PlantClass[] garden = new PlantClass[numberOfPlants];

        for (int i = 0; i < numberOfPlants; i++) {
            garden[i] = getPlantDetails(scanner, i + 1);
        }

        System.out.print("\nEnter the filename to save the garden data (CSV format): ");
        String fileName = scanner.nextLine();

        saveGardenDataToFile(garden, fileName);
        scanner.close();
    }

    private static PlantClass getPlantDetails(Scanner scanner, int plantIndex) {
        System.out.println("\nEntering details for plant " + plantIndex + ":");
        System.out.print("Enter the plant name: ");
        String plantName = scanner.nextLine();

        int potNumber;
        do {
            System.out.print("Enter the pot number: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a numerical value for the pot number.");
                scanner.next(); // Clear the invalid input
            }
            potNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } while (potNumber <= 0);

        String purpose;
        do {
            System.out.print("Enter the purpose (Medicinal, Culinary, Decorative): ");
            purpose = scanner.nextLine();
            if (!purpose.equalsIgnoreCase("Medicinal") && !purpose.equalsIgnoreCase("Culinary") && !purpose.equalsIgnoreCase("Decorative")) {
                System.out.println("Invalid input. Please enter 'Medicinal', 'Culinary', or 'Decorative'.");
            }
        } while (!purpose.equalsIgnoreCase("Medicinal") && !purpose.equalsIgnoreCase("Culinary") && !purpose.equalsIgnoreCase("Decorative"));

        System.out.print("Enter the last watered date (yyyy-MM-dd), or press Enter for default value: ");
        String lastWateredDateStr = scanner.nextLine();
        if (lastWateredDateStr.isEmpty()) {
            lastWateredDateStr = "Not watered yet"; // Default value if no input is provided
        }

        return new PlantClass(plantName, potNumber, purpose, lastWateredDateStr);
    }

    private static void saveGardenDataToFile(PlantClass[] garden, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (PlantClass plant : garden) {
                writer.println(plant.toStringCsv());
            }
            System.out.println("Garden data saved successfully to " + fileName);
        } catch (FileNotFoundException e) {
            System.err.println("An error occurred while saving the file: " + e.getMessage());
        }
    }
}

