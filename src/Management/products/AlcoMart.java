package Management.products;

import Management.common.AlcoCategory;
import Management.common.Model;
import Management.common.Role;

public class AlcoMart implements Model {
    private int id;
    private String name;
    private AlcoCategory category;
    private double volume;
    private int year;
    private double price;
    private int percent;

    public AlcoMart(){}

    public AlcoMart(int id, String name, AlcoCategory category, double volume, int year, double price, int percent) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.volume = volume;
        this.year = year;
        this.price = price;
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AlcoCategory getCategory() {
        return category;
    }

    public void setCategory(AlcoCategory category) {
        this.category = category;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    @Override
    public String toString () {
        return "[" + id + "] | " + name + " | " + category.name() + " | " + volume + " l | " + year + " | " + price + " tg | " + percent + "% |";
    }
}
