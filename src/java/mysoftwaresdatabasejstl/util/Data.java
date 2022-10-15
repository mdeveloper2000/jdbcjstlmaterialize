package mysoftwaresdatabasejstl.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {

    public static Date stringToDate(String data) {
        Date dataFormatted = null;
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            long timestamp = df.parse(data).getTime();
            dataFormatted = new Date(timestamp);
        }
        catch(ParseException e) {
            e.printStackTrace();
        }
        return dataFormatted;
    }
    
}