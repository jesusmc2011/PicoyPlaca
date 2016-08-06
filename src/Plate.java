/**
 * Created by jesus on 05/08/16.
 */
public class Plate{

    private String plateNumber;


    public Plate(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void canRoad(String date, String time){
        PicoyPlaca pp = new PicoyPlaca(this.getPlateNumber(), date, time);
        if (pp.apply())
            System.out.printf("Hoy Aplica Pico y Placa, no puede circular a esa hora %s", time);
        else
            System.out.println("Hoy no Aplica Pico y Placa");

        System.out.println(pp.infoNoApply());


    }
}
