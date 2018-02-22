package exodiasolutions.epos.Models;

/**
 * Created by Sunny on 30-01-2018.
 */

public class Bill_Item {
    public String getS_no() {
        return s_no;
    }

    public void setS_no(String s_no) {
        this.s_no = s_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bill_Item(String s_no, String name, String price) {
        this.s_no = s_no;
        this.name = name;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    String s_no,name,price;

}
