package amaragya.bahaso.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class utility {
    public String ucwords(String name) {
        StringBuilder sb = new StringBuilder(name);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }


    String[] monthName = {"January", "February",
            "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"};

    public String duaempatjamformat(String jam) throws ParseException {
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date = parseFormat.parse(jam);
        return displayFormat.format(date);
    }

    public String formattanggalindo(String tanggal) throws ParseException {
        SimpleDateFormat displayFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = parseFormat.parse(tanggal);
        return displayFormat.format(date);
    }

    public String formattanggaldb(String tanggal) throws ParseException {
        SimpleDateFormat parseFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = parseFormat.parse(tanggal);
        return displayFormat.format(date);
    }
}
