package bt03;

import java.text.DecimalFormat;

public class CurrencyConverter {
    private static double rate = 0;

    public static void setRate(double r) {
        if (r <= 0) {
            throw new IllegalArgumentException("Tỉ giá phải lớn hơn 0");
        }
        rate = r;
    }

    public double getRate() {
        return rate;
    }

    public static double toUSD(int vnd) {
        if (vnd < 0) {
            throw new IllegalArgumentException("Số tiền VND không hợp lệ");
        }
        if (rate == 0) {
            throw new IllegalStateException("Chưa thiết lập tỉ giá");
        }
        return vnd / rate;
    }

    public static String formatUSD(double usd) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(usd) + " USD";
    }
}
