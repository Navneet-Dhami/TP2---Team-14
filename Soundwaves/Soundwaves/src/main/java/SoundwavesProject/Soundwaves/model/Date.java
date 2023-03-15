package SoundwavesProject.Soundwaves.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Date {
    private String date;
    private String time;

    public Date() { 
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate localDate = LocalDate.now();
        String currentDate = (dateFormatter.format(localDate));  
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();
        String currentTime = (timeFormatter.format(localTime));
        this.date = currentDate;
        this.time = currentTime;
    }

    public String getDate() { 
        return this.date;
    }

    public String getTime() { 
        return this.time;
    }
}