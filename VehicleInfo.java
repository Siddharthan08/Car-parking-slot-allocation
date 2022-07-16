package carslotallocation;
import java.io.Serializable;
import java.time.LocalDateTime;
public class VehicleInfo implements Serializable{
    int vehicleNumber;
    LocalDateTime checkIn;
    LocalDateTime checkOut;
    public VehicleInfo(int vehicleNumber,LocalDateTime checkIn,LocalDateTime checkOut){
        this.vehicleNumber=vehicleNumber;
        this.checkIn=checkIn;
        this.checkOut=checkOut;
    }
    public int getVehicleNumber(){
        return vehicleNumber;
    }
    public LocalDateTime getCheckinTime(){
        return checkIn;
    }
    public LocalDateTime getCheckOutTime(){
        return checkOut;
    }
    public void setCheckOutTime(LocalDateTime checkOut){
        this.checkOut=checkOut;
    }
}
