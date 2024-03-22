package com.hdbank.convertMTtoMXproject.demo;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    public static XMLGregorianCalendar createDateTimeNow() {
        try {
            // Lấy ra một đối tượng DatatypeFactory để tạo ra XMLGregorianCalendar
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

            // Lấy thời gian hiện tại từ hệ thống
            GregorianCalendar now = new GregorianCalendar();

            // Chuyển đổi thời gian hiện tại sang XMLGregorianCalendar
            XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(now);

            return xmlGregorianCalendar;
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static XMLGregorianCalendar parseDateTime(String dateStr) {
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
            Date date = originalFormat.parse(dateStr);

            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");

            XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(newFormat.format(date));

            return xmlGregorianCalendar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
