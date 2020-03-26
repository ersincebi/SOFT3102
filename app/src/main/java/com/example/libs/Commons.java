package libs;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Commons {
    private DateFormat dateFormat;
    private Date date;

    /**
     * @param dateFormat takes only one parameter to decide date format
     *                   this format could be decided as 'dd.MM.yyyy' or
     *                   'dd/MM/yy' to be generic the format depends ona caller
     * @return returns the current date
     */
    public String currentDate(String dateFormat){
        dateFormat = String.valueOf(new SimpleDateFormat(dateFormat));
        date = new Date();
        return dateFormat.format(String.valueOf(date));
    }
}
