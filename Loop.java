// package Java.Caddy;

public class Loop {
    private Date date;
    private int money;
    private String name;

    public Loop (Date date, int money, String name) {
        this.date = date;
        this.money = money;
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void setDate(Date newDate) {
        date = newDate;
    }

    public void setMoney(int amt) {
        money = amt;
    }
    
    public void setName (String newName) {
        name = newName;
    }

    public String output () {
        return name + "-" + date + "-" + money;
    }

    @Override
    public String toString() {
        return "You caddied for " + name + " on " + date + " and made $" + money; 
    }
}
