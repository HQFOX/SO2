package clube_desportivo.beans;

import java.time.Instant;

public class Reserve {

    private int code;
    private String space;
    private String person;
    private int phone;
    private double cost;
    private int users;
    private Instant checkin;
    private Instant checkout;

    public Reserve() {
    }

    public Reserve(int cd, String p, Instant chki, Instant chko) {
        code = cd;
        person = p;
        checkin = chki;
        checkout = chko;
    }

    public Reserve(String s, String p, int ph, int us, Instant chki, Instant chko) {
        space = s;
        person = p;
        phone = ph;
        users = us;
        checkin = chki;
        checkout = chko;
    }

    public void setCode(int c) {
        this.code = c;
    }

    public int getCode() {
        return this.code;
    }

    public void setSpace(String n) {
        this.space = n;
    }

    public String getSpace() {
        return this.space;
    }

    public void setPerson(String n) {
        this.person = n;
    }

    public String getPerson() {
        return this.person;
    }

    public void setPhone(int f) {
        this.code = f;
    }

    public int getPhone() {
        return this.phone;
    }

    public void setCost(double c) {
        this.cost = c;
    }

    public double getCost() {
        return this.cost;
    }

    public void setUsers(int u) {
        this.users = u;
    }

    public int getUsers() {
        return this.users;
    }

    public void setCheckin(Instant ci) {
        this.checkin = ci;
    }

    public Instant getCheckin() {
        return this.checkin;
    }

    public void setCheckout(Instant co) {
        this.checkin = co;
    }

    public Instant getCheckout() {
        return this.checkout;
    }

    public String toString() {
        return "Reserve: " + getCode() + ", " + getSpace()
                + ", " + getPerson() + ", " + getPhone()
                + ", " + getCost() + ", " + getUsers()
                + ", " + getCheckin() + ", " + getCheckout();
    }

}
