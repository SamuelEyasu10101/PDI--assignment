import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GardianPlantMaintain{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Garden Maintenance Program!");

        System.out.print("Enter the filename to load the garden data (CSV format): ");
        String filename = scanner.nextLine();

        PlantClass[] garden = new PlantClass[100];
        int plantCount = loadGardenData(filename, garden);

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                plantCount = addNewPlant(scanner, garden, plantCount);
            } else if (choice == 2) {
                waterAllPlants(garden, plantCount);
            } else if (choice == 3) {
                searchForPlants(scanner, garden, plantCount);
            } else if (choice == 4) {
                saveGardenData(scanner, garden, plantCount);
                running = false;
            } else if (choice == 5) {
                running = false;
            } else {
                System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();
    }

    private static int loadGardenData(String filename, PlantClass[] garden) {
        int count = 0;
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("The file " + filename + " does not exist.");
            return count;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 4) {
                    garden[count++] = new PlantClass(data[0], Integer.parseInt(data[1]), data[2], data[3]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open the file: " + filename);
        }
        return count;
    }

    private static int addNewPlant(Scanner scanner, PlantClass[] garden, int plantCount) {
        System.out.println("Enter details for the new plant:");
        System.out.print("Plant name: ");
        String name = scanner.nextLine();
        System.out.print("Pot number: ");
        int potNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Purpose(medicinal/culinary/decorative/madilinala): ");
        String purpose = scanner.nextLine();
        System.out.print("Last watered date (yyyy-MM-dd): ");
        String wateredDate = scanner.nextLine();
        garden[plantCount++] = new PlantClass(name, potNumber, purpose, wateredDate);
        System.out.println("Plant added.");
        return plantCount;
    }

    private static void waterAllPlants(PlantClass[] garden, int plantCount) {
        String currentDate = dateFormat.format(new Date());
        for (int i = 0; i < plantCount; i++) {
            garden[i].setLastWateredDate(currentDate);
        }
        System.out.println("All plants have been watered.");
    }

    private static void searchForPlants(Scanner scanner, PlantClass[] garden, int plantCount) {
        System.out.print("Enter plant name to search for: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < plantCount; i++) {
            if (garden[i].getPlantName().equalsIgnoreCase(name)) {
                System.out.println(garden[i].toStringCsv());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No plant found with the name " + name);
        }
    }

    private static void saveGardenData(Scanner scanner, PlantClass[] garden, int plantCount) {
        System.out.print("Enter the filename to save the garden data (CSV format): ");
        String filename = scanner.nextLine();
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (int i = 0; i < plantCount; i++) {
                writer.println(garden[i].toStringCsv());
            }
            System.out.println("Garden data saved to " + filename);
        } catch (FileNotFoundException e) {
            System.err.println("Error saving the file: " + e.getMessage());
        }
    }

    private static void displayMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Add a new plant");
        System.out.println("2. Water all plants");
        System.out.println("3. Search for plants");
        System.out.println("4. Save and exit");
        System.out.println("5. Exit without saving");
        System.out.print("Select an option: ");
    }
}

