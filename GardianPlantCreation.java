import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class GardianPlantCreation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Garden Creation Program!");
        System.out.print("How many plants do you want to add to the garden? ");
        int numberOfPlants = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        PlantClass[] garden = new PlantClass[numberOfPlants];

        for (int i = 0; i < numberOfPlants; i++) {
            garden[i] = getPlantDetails(scanner, i + 1);
        }

        System.out.print("\nEnter the filename to save the garden data (CSV format): ");
        String fileName = scanner.nextLine();

        saveGardenDataToFile(garden, fileName);
    }

    private static PlantClass getPlantDetails(Scanner scanner, int plantIndex) {
        System.out.println("\nEntering details for plant " + plantIndex + ":");
        System.out.print("Enter the plant name: ");
        String plantName = scanner.nextLine();

        System.out.print("Enter the pot number: ");
        int potNumber = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter the purpose (Medicinal, Culinary, Decorative): ");
        String purpose = scanner.nextLine();

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

