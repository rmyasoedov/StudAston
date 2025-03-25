package utils;

import java.text.DecimalFormat;

public class Convertor {

    public static String formatAmount(String amount) {
        double value = Double.parseDouble(amount);
        return (new DecimalFormat("0.00").format(value)).replace(",",".");
    }
}
