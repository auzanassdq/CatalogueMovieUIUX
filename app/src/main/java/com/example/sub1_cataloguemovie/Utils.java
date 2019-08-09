package com.example.sub1_cataloguemovie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by auzan on 7/16/2019.
 * Github: @auzanassdq
 */
public class Utils {

    public static String getConvertDate(String releaseDate){
        String dateOfRelease = "";
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(releaseDate);
            SimpleDateFormat new_date_format = new SimpleDateFormat("E, MMM dd, yyyy");
            dateOfRelease = new_date_format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateOfRelease;
    }

}
