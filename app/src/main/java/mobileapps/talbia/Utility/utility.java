package mobileapps.talbia.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class utility {
    public String ucwords(String name) {
        StringBuilder sb = new StringBuilder(name);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }


    public String duaempatjamformat(String jam) throws ParseException {
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date = parseFormat.parse(jam);
        return displayFormat.format(date).toString();
    }

    public String formattanggalindo(String tanggal) throws ParseException {
        SimpleDateFormat displayFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = parseFormat.parse(tanggal);
        return displayFormat.format(date).toString();
    }

    public String formattanggaldb(String tanggal) throws ParseException {
        SimpleDateFormat parseFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = parseFormat.parse(tanggal);
        return displayFormat.format(date).toString();
    }
}
