package carslotallocation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class RentalBuilding {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("\n\n_____WELCOME TO SID PARKINGLOT_____");
            int option;
            int standard;
            int truckTotalSlots=10;
            int reservedSlots=5;
            int unreservedSlots=truckTotalSlots-reservedSlots;
            int truckPremiumSlots=reservedSlots,truckUnreservedSlots=unreservedSlots;
            int carPremiumSlots=reservedSlots,carUnreservedSlots=unreservedSlots;
            int bikePremiumSlots=reservedSlots,bikeUnreservedSlots=unreservedSlots;
            do{
                System.out.print("\nPress 1 to park/pickup Truck.\nPress 2 to park/pickup Car.\nPress 3 to park/pickup Bike.\nPress 4 to view the current status of the vehicle.\nPress 5 to display all the Vehicles in the lot.\nPress 6 to exit.\nEnter your choice:");
                option=input.nextInt();
                Truck floor1=new Truck();
                Car floor2=new Car();
                Bike floor3=new Bike();
                switch(option){
                    case 1:
                    System.out.println("\n**************************************************************\n");
                    System.out.print("Press 1 for the premium plan.\nPress 2 for the unreserved plan.\nEnter your plan:");
                    standard=input.nextInt();
                    if(standard==1){
                        truckPremiumSlots=floor1.premium(truckPremiumSlots);
                        System.out.println("\nRemaining premium slots for Trucks:"+truckPremiumSlots);
                    }else if(standard==2){
                        truckUnreservedSlots=floor1.unreserved(truckUnreservedSlots);
                        System.out.println("\nAvailable unreserved slots for Trucks:"+truckUnreservedSlots);
                    }
                    break;
                    case 2:
                    System.out.println("\n**************************************************************\n");
                    System.out.print("\n1.Premium\n2.Unreserved\nEnter your standard:");
                    standard=input.nextInt();
                    if(standard==1){
                        carPremiumSlots=floor2.premium(carPremiumSlots);
                        System.out.println("\nRemaining premium slots for Cars:"+carPremiumSlots);
                    }else if(standard==2){
                        carUnreservedSlots=floor2.unreserved(carUnreservedSlots);
                        System.out.println("\nAvailable unreserved slots for Cars:"+carUnreservedSlots);
                    }
                    break;
                    case 3:
                    System.out.println("\n**************************************************************\n");
                    System.out.print("\n1.Premium\n2.Unreserved\nEnter your standard:");
                    standard=input.nextInt();
                    if(standard==1){
                        bikePremiumSlots=floor3.premium(bikePremiumSlots);
                        System.out.println("\nRemaining premium slots for Bikes:"+bikePremiumSlots);
                    }else if(standard==2){
                        bikeUnreservedSlots=floor3.unreserved(bikeUnreservedSlots);
                        System.out.println("\nAvailable unreserved slots for Bikes:"+bikeUnreservedSlots);
                    }
                    break;
                    case 4:
                    System.out.print("\nEnter the vehicle number to view it's Status:");
                    int vehicleNumber=input.nextInt();
                    ViewStatus view=new ViewStatus(vehicleNumber);
                    view.status();
                    break;
                    case 5:
                    if(Truck.floor1_Premium.isEmpty() && Truck.floor1_Unreserved.isEmpty()){
                        System.out.println("\nNo vehicles in ground floor.");
                    }else{
                        System.out.println("\nVehicles in ground floor are:\n");
                        floor1.display(Truck.floor1_Premium);
                        floor1.display(Truck.floor1_Unreserved);
                    }
                    if(Car.floor2_Premium.isEmpty() && Car.floor2_Unreserved.isEmpty()){
                        System.out.println("\nNo vehicles in First floor.");
                    }else{
                        System.out.println("\nVehicles in first floor are:\n");
                        floor2.display(Car.floor2_Premium);
                        floor2.display(Car.floor2_Unreserved);
                    }
                    if(Bike.floor3_Premium.isEmpty() && Bike.floor3_Unreserved.isEmpty()){
                        System.out.println("\nNo vehicles in Second floor.");
                    }else{
                        System.out.println("\nVehicles in second floor are:\n");
                        floor3.display(Bike.floor3_Premium);
                        floor3.display(Bike.floor3_Unreserved);
                    }
                    break;
                    case 6:
                    floor1.file();
                    floor2.file();
                    floor3.file();
                    System.out.println("____Thanks for visiting us_____\n");
                    break;
                    default:
                    System.out.println("Enter the valid option.");
                    break;
                }
            }while(option!=6);
        }
    }
}
