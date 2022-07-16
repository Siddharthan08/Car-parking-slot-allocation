package carslotallocation;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class Truck {
    Scanner input=new Scanner(System.in);
    int vehicleNumber;
    int option;
    LocalDateTime checkIn,checkOut;
    long duration,monthDiff;
    boolean found=false;
    static int run=0,temp=0;
    DateTimeFormatter format=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    static HashSet<VehicleInfo> floor1_Premium=new HashSet<>();
    static HashSet<VehicleInfo> floor1_Unreserved=new HashSet<>();
    File file1=new File("Truck_Premium.txt");
    File file2=new File("Truck_Unreserved.txt");
    public Truck() throws FileNotFoundException, ClassNotFoundException, IOException{
        if(file1.exists() && file2.exists()){
            read();
        }
    }
    int premium(int reservedSlots) throws FileNotFoundException, ClassNotFoundException, IOException{
        System.out.println("\n**************************************************************\n");
        reservedSlots=addFloor1ReservedItems(reservedSlots);
        if(run==0){
            for(VehicleInfo e:floor1_Premium){
                if(e.getCheckOutTime()==null){
                    reservedSlots-=1;
                }
                run=1;
            }
            System.out.println("\nAvailable Premium slots in Ground floor:"+reservedSlots);
        }
        System.out.print("\nPress 1 to park the vehicle.\nPress 2 to pickup the vehicle.\nEnter the option:");
        option=input.nextInt();
        System.out.print("\nEnter the vehicle number:");
        vehicleNumber=input.nextInt();
        if(option==1){ 
            if(reservedSlots>0){
                if(!check(floor1_Premium,vehicleNumber)){
                    reservedSlots-=1;
                    checkIn=LocalDateTime.now();
                    VehicleInfo info=new VehicleInfo(vehicleNumber, checkIn, null);
                    floor1_Premium.add(info);
                    System.out.println("\n*****Truck is parked successfully in premium slot.*****\n");
                }else if(checkCheckedOut(floor1_Premium,vehicleNumber)){
                    reservedSlots-=1;
                    checkIn=LocalDateTime.now();
                    VehicleInfo info=new VehicleInfo(vehicleNumber, checkIn, null);
                    floor1_Premium.add(info);
                    System.out.println("\n*****Truck is parked successfullyin premium slot.*****\n");
                }else{
                    System.out.println("\nVehicle is already parked.\n");
                }
            }else{
                System.out.println("\nPremium slots are full.\n");    
            }
        }else if(option ==2){
            if(reservedSlots<5){
                for(VehicleInfo e:floor1_Premium){
                    if(e.getVehicleNumber()==vehicleNumber && e.getCheckOutTime()==null){
                        checkOut=LocalDateTime.now();
                        e.setCheckOutTime(checkOut);
                        reservedSlots+=1;
                        monthDiff=e.checkOut.getMonthValue()-e.checkIn.getMonthValue();
                        System.out.println("\nThe total fare for the vehicle "+e.getVehicleNumber()+" is "+(monthDiff+1)*5000+" rupees.Valid till "+format.format(e.checkIn.plusMonths(monthDiff+1)));
                        found=true;
                    }
                }if(!found){
                    System.out.println(("\nThe mentioned vehicle is not parked.\n"));
                }
                found=false;
            }else{
                System.out.println("\nThere is no vehicle in the slots.\n");
            }  
        }
        display(floor1_Premium);
        return reservedSlots;
    }
    int unreserved(int unreservedSlots) throws FileNotFoundException, ClassNotFoundException, IOException{
        unreservedSlots=addFloor1UnreservedItems(unreservedSlots);
        if(temp==0){
            for(VehicleInfo e:floor1_Unreserved){
                if(e.getCheckOutTime()==null){
                    unreservedSlots-=1;
                }
                temp=1;
            }
            System.out.println("\nAvailable Unreserved slots in Ground floor:"+unreservedSlots);
        }
        System.out.print("\nPress 1 to park the vehicle.\nPress 2 to pickup the vehicle.\nEnter the option:");
        option=input.nextInt();
        System.out.print("\nEnter the vehicle number:");
        vehicleNumber=input.nextInt();
        if(option==1){ 
            if(unreservedSlots>0){
                if(!check(floor1_Unreserved,vehicleNumber)){
                    unreservedSlots-=1;
                    checkIn=LocalDateTime.now();
                    VehicleInfo info=new VehicleInfo(vehicleNumber, checkIn, null);
                    floor1_Unreserved.add(info);
                    System.out.println("\n*****Truck is parked successfully in unreserved slot.*****\n");
                }else if(checkCheckedOut(floor1_Unreserved,vehicleNumber)){
                    unreservedSlots-=1;
                    checkIn=LocalDateTime.now();
                    VehicleInfo info=new VehicleInfo(vehicleNumber, checkIn, null);
                    floor1_Unreserved.add(info);
                    System.out.println("\n*****Truck is parked successfullyin unreserved slot.*****\n");
                }else{
                    System.out.println("\nVehicle is already parked.\n");
                }
            }else{
                System.out.println("\nUnreserved slots are full.\n");    
            }
        }else if(option ==2){
            if(unreservedSlots<5){
                for(VehicleInfo e:floor1_Unreserved){
                    if(e.getVehicleNumber()==vehicleNumber && e.getCheckOutTime()==null){
                        e.setCheckOutTime(LocalDateTime.now());
                        unreservedSlots+=1;
                        checkOut=LocalDateTime.now();
                        duration=java.time.Duration.between(e.checkIn,e.checkOut).getSeconds();
                        System.out.println("\nThe fare for the vehicle "+e.getVehicleNumber()+" is:"+duration*100);
                        found=true;
                    }
                }if(!found){
                    System.out.println(("\nThe mentioned vehicle is not parked.\n"));
                }
                found=false;
            }else{
                System.out.println("\nThere is no vehicle in the slots.\n");
            }  
        }
        display(floor1_Unreserved);
        return unreservedSlots;
    }
    void display(HashSet<VehicleInfo> floor){
        for(VehicleInfo e:floor){
            System.out.println("\nThe vehicle number is "+e.getVehicleNumber()+"\nThe checkin time is "+e.getCheckinTime());
            if(e.getCheckOutTime()==null){
                System.out.println("The vehicle is still in the parking.");
            }else{
                System.out.println("The checkout time is"+e.getCheckOutTime());
            }
        }
    }
    boolean check(HashSet<VehicleInfo> floor,int vehicleNumber){
        for(VehicleInfo e:floor){
            if(e.getVehicleNumber()==vehicleNumber){
                return true;
            }
        }
        return false;
    }
    int addFloor1ReservedItems(int reservedSlots){
        VehicleInfo veh1=new VehicleInfo(1111, LocalDateTime.parse("14/06/2022 17:25:44",format), null);
        VehicleInfo veh2=new VehicleInfo(2222, LocalDateTime.parse("15/05/2022 22:50:44",format), null);
        if(!check(floor1_Premium, veh1.vehicleNumber)){
            floor1_Premium.add(veh1);
        }
        if(!check(floor1_Premium, veh2.vehicleNumber)){
            floor1_Premium.add(veh2);
        }
        return reservedSlots;
    }
    int addFloor1UnreservedItems(int unreservedSlots){
        VehicleInfo veh1=new VehicleInfo(3333, LocalDateTime.parse("14/07/2022 17:25:44",format), null);
        VehicleInfo veh2=new VehicleInfo(4444, LocalDateTime.parse("16/07/2022 22:52:44",format), null);
        if(!check(floor1_Unreserved, veh1.vehicleNumber)){
            floor1_Unreserved.add(veh1);
        }
        if(!check(floor1_Unreserved, veh2.vehicleNumber)){
            floor1_Unreserved.add(veh2);
        }
        return unreservedSlots;
    }
    void file()throws FileNotFoundException,IOException{
        FileOutputStream premium=new FileOutputStream("Truck_Premium.txt");
        FileOutputStream unreserved=new FileOutputStream("Truck_Unreserved.txt");
        ObjectOutputStream pre=new ObjectOutputStream(premium);
        ObjectOutputStream unreserve=new ObjectOutputStream(unreserved);
        pre.writeObject(floor1_Premium);
        unreserve.writeObject(floor1_Unreserved);
        pre.close();
        unreserve.close();
    }
    void read()throws FileNotFoundException,IOException,ClassNotFoundException{
        FileInputStream pre=new FileInputStream("Truck_Premium.txt");
        FileInputStream unreserve=new FileInputStream("Truck_unreserved.txt");
        ObjectInputStream in1=new ObjectInputStream(pre);
        ObjectInputStream in2=new ObjectInputStream(unreserve);
        floor1_Premium=(HashSet<VehicleInfo>)in1.readObject();
        floor1_Unreserved=(HashSet<VehicleInfo>)in2.readObject();
        in1.close();
        in2.close();
    }
    boolean checkCheckedOut(HashSet<VehicleInfo> floor,int vehicleNumber){
        for(VehicleInfo e:floor){
            if(e.vehicleNumber==vehicleNumber && e.getCheckOutTime()!=null){
                return true;
            }
        }
        return false;
    }
}
