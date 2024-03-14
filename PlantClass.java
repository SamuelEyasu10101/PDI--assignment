import java.text.*;
import java.util.*;

public class PlantClass {
    private String plantName;
    private int potNumber;
    private String purpose;
    private LocalDate lastWateredDate;	

    public PlantClass() {
        this.plantName = "";
        this.potNumber = 0;
        this.purpose = "";
        this.lastWateredDate = "";
    }

    public PlantClass(String plantName, int potNumber, String purpose, LocalDate lastWateredDate) {
        this.plantName = plantName;
        this.potNumber = potNumber;
        this.purpose = purpose;
        this.lastWateredDate = lastWateredDate;
    }

    public PlantClass(plantName otherPlant){
    	this.plantName = otherPlant.plantName;
	this.potNumber = otherPlant.potNumber;
	this.purpose = otherPlant.purpose;
	this.lastWaterDate = otherPlant.lastWaterDate;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String newPlantName) {
        if (newPlantName != null && !newPlantName.isEmpty()) {
            plantName = newPlantName;
        } else {
            System.out.println("Enter the name of the plant.");
        }
    }

    public int getPotNumber() {
        return potNumber;
    }

    public void setPotNumber(int newPotNumber) {
        if (newPotNumber >= 0) {
            potNumber = newPotNumber;
        } else {
            System.out.println("Enter a valid pot number.");
        }
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String newPurpose) {
        if (newPurpose != null && !newPurpose.isEmpty()) {
            purpose = newPurpose;
        } else {
            System.out.println("Enter the purpose of the plant.");
        }
    }

    public LocalDate getLastWateredDate() {
        return lastWateredDate;
    }

    public void setLastWateredDate(LocalDate newLastWateredDate) {
        if (newLastWateredDate != null && !newLastWateredDate.isAfter(LocalDate.now())) {
            lastWateredDate = newLastWateredDate;
        } else {
            System.out.println("The date is invalid! Try inputting again.");
        }
    }


    public static PlantClass vaidationPurpose(Scanner scanner,int PlantIndex){
    	System.out.println("Enter the details for "+(PlantIndex+1)+": ");
	
	System.out.println("Enter the plant name here: ");
	String PlantName = scanner.nextLine();

	while(PlantName.trim().Empty()){
		System.out.println("Enter a vaild plant name it can't be empthy: ");
		String PlantName = scanner.nextLine();
	}
    }
}

