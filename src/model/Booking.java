package model;

/**
 * Created by Søren on 28-03-2017.
 */
public class Booking {

    private int id;
    private String name;
    private int phoneNumber;
    private int idShow;
    private int ticketAmount;

    public Booking(int id, String name, int phoneNumber, int idShow, int ticketAmount){
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.idShow = idShow;
        this.ticketAmount = ticketAmount;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getIdShow() {
        return idShow;
    }

    public void setIdShow(int idShow) {
        this.idShow = idShow;
    }

    public int getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(int ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

}
