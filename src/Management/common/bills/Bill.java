package Management.common.bills;

import Management.common.AlcoCategory;
import Management.common.Model;

public class Bill implements Model {
    private int id;
    private int alcoholId;
    private int userId;

    public Bill(){}

    public Bill(int id, int alcoholId, int userId) {
        this.id = id;
        this.alcoholId = alcoholId;
        this.userId = userId;
    }

    public Bill(int alcoholId, int userId) {
        this.alcoholId = alcoholId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlcoholId() {
        return alcoholId;
    }

    public void setAlcoholId(int alcoholId) {
        this.alcoholId = alcoholId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString () {
        return "Bill[Id: " + id + ", user id: " + userId + ", alcohol id: " + alcoholId + "] ";
    }
}
