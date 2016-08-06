import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Created by jesus on 05/08/16.
 */
public class PicoyPlaca {

    private static final Map<Integer, List<Integer>> PP_DISTRIBUTION = createMap();

    private static Map<Integer, List<Integer> > createMap() {
        Map<Integer, List<Integer> > result = new HashMap<>();
        result.put(1, new ArrayList<>(Arrays.asList(1,2)));
        result.put(2, new ArrayList<>(Arrays.asList(3,4)));
        result.put(3, new ArrayList<>(Arrays.asList(5,6)));
        result.put(4, new ArrayList<>(Arrays.asList(7,8)));
        result.put(5, new ArrayList<>(Arrays.asList(9,0)));
        return Collections.unmodifiableMap(result);
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String plate;
    private String date;
    private String time;

    public PicoyPlaca(String plate, String date, String time) {
        this.plate = plate;
        this.date = date;
        this.time = time;
    }

    public int getLastNumber(){
        try {
            int val = Integer.parseInt(this.plate.substring(this.plate.length() - 1));
            return val;
        } catch (NumberFormatException ex) {
            System.out.printf("%s no es un formato válido!%n", this.plate);
            throw ex;
        }
    }

    public int getDayOfWeek(){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse(this.date, formatter);
            return fecha.getDayOfWeek().getValue();
        } catch (DateTimeParseException ex) {
            System.out.printf("%s no es un formato válido!%n", this.date);
            throw ex;
        }
    }

    private LocalTime convertToTime(){
        try {
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
            return LocalTime.parse(this.time, formatterTime);
        } catch (DateTimeParseException ex) {
            System.out.printf("%s no es un formato válido!%n", this.time);
            throw ex;
        }
    }

    public boolean applyByTime(){
        LocalTime hour = this.convertToTime();

        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime hora_am_inicial = LocalTime.parse("07:00", formatterTime);
        LocalTime hora_am_final = LocalTime.parse("09:30", formatterTime);

        LocalTime hora_pm_inicial = LocalTime.parse("16:00", formatterTime);
        LocalTime hora_pm_final = LocalTime.parse("19:30", formatterTime);

        boolean apply = (hour.isAfter(hora_am_inicial) &&  hour.isBefore(hora_am_final)) ||  (hour.isAfter(hora_pm_inicial)
                &&  hour.isBefore(hora_pm_final));

        return apply;
    }

    public boolean applyByDate(){
        return PP_DISTRIBUTION.get(getDayOfWeek()).contains(this.getLastNumber());
    }

    public String infoNoApply(){
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        String res = "Su automovil no aplica PICO y PLACA los dias: \n";
        for (int i=1; i<=5; i++){
            if (!PP_DISTRIBUTION.get(i).contains(this.getLastNumber()))
                res += " " + dias[i-1] + " - No aplica.\n";
        }
        res += "Recuerde que el horario de pico y placa comprede desde las 07:30 - 09:30 en la mañana y desde las 16:00 - 19:30 en la tarde: \n";
        return res;
    }

    public boolean apply(){
        return applyByDate() && applyByTime();
    }

}
