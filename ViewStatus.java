package carslotallocation;

import java.time.LocalDateTime;
import java.util.HashSet;

public class ViewStatus {
    int vehicleNumber;
    boolean found=true;
    HashSet<VehicleInfo> detail=new HashSet<>();
    public ViewStatus(int vehicleNumber){
        this.vehicleNumber=vehicleNumber;
    }
    void status(){
        LocalDateTime time;
        for(VehicleInfo e:Truck.floor1_Premium){
            if(vehicleNumber==e.getVehicleNumber() && e.getCheckOutTime()==null){
                detail.add(e);
                System.out.println("\nThe Truck is parked in ground floor\n");
                found=false;
            }
        }if(found){
            for(VehicleInfo e:Car.floor2_Premium){
                if(vehicleNumber==e.getVehicleNumber() && e.getCheckOutTime()==null){
                    detail.add(e);
                    System.out.println("\nThe Car is parked in first floor\n");
                    found=false;
                }
            }
        }if(found){
            for(VehicleInfo e:Bike.floor3_Premium){
                if(vehicleNumber==e.getVehicleNumber() && e.getCheckOutTime()==null){
                    detail.add(e);
                    System.out.println("\nThe Bike is parked in second floor\n");
                    found=false;
                }
            }
        }if(!found){
            for(VehicleInfo e:detail){
                time=LocalDateTime.now();
                System.out.println("The checkin time is "+e.getCheckinTime()+"\nnow the time is "+time+"\n");
                System.out.println("The fare that is charged until now:"+(((e.getCheckinTime().getMonthValue()-time.getMonthValue())+1)*5000)+".Valid till "+e.getCheckinTime().plusMonths(((e.getCheckinTime().getMonthValue()-time.getMonthValue())+1)));
            }
            return;
        }
        if(found){
            for(VehicleInfo e:Truck.floor1_Unreserved){
                if(vehicleNumber==e.getVehicleNumber() && e.getCheckOutTime()==null){
                    detail.add(e);
                    System.out.println("\nThe Truck is parked in ground floor\n");
                    found=false;
                }
            }if(found){
                for(VehicleInfo e:Car.floor2_Unreserved){
                    if(vehicleNumber==e.getVehicleNumber() && e.getCheckOutTime()==null){
                        detail.add(e);
                        System.out.println("\nThe Car is parked in first floor\n");
                        found=false;
                    }
                }
            }if(found){
                for(VehicleInfo e:Bike.floor3_Unreserved){
                    if(vehicleNumber==e.getVehicleNumber() && e.getCheckOutTime()==null){
                        detail.add(e);
                        System.out.println("\nThe Bike is parked in second floor\n");
                        found=false;
                    }
                }
            }
        }if(!found){
            for(VehicleInfo e:detail){
                time=LocalDateTime.now();
                System.out.println("The checkin time is "+e.getCheckinTime()+"\nnow the time is "+time+"\n");
                System.out.println("The fare that is charged until now:"+java.time.Duration.between(e.getCheckinTime(),time).getSeconds()*100);
            }
        }if(found){
            System.out.println("Vehicle not found in the parking lot");
        }
        return;
    }
}
