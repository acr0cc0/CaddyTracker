// package Java.Caddy;
public class Date {
    private int year, month, day;

    // no args constructor
    public Date () {
        year = 0;
        month = 0;
        day = 0;
    }
    // default constructor
    public Date(int y, int m, int d) {
        year = y;
        month = m;
        day = d;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setYear(int y) {
        year = y;
    } 

    public void setMonth(int m) {
        month = m;
    }

    public void setDay(int d) {
        day = d;
    }

    @Override
    public String toString() {
        if (day < 10 && month < 10) {
            return year + "-0" + month + "-0" + day;
        } else if (day < 10) {
            return year + "-" + month + "-0" + day;
        } else if (month < 10) {
            return year + "-0" + month + "-" + day;
        } else {
            return year + "-" + month + "-" + day;
        }
    }
}
