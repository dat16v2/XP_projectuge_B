package model;

/**
 * Created by Søren on 24-03-2017.
 */
public class Reserve {


    private Object[] seatNumber = new Object[240];
    private static int counterNext = 0;

    public Object[] getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Object[] seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int Integer(int seat) {
        return seat;
    }

    public void numberOfReservation(int reservationsNumb){
        for(int i = 0; i < reservationsNumb; i++) {
            setIn();
        }
    }

    //Method used for seat reservation of specific seats ;)
    /*public void setSeat(Integer seat) {

        if(seatNumber != null) {
            System.out.println("Du kan ikke vælge en reserveret plads!!");
        }
        else{
            seatNumber[seat + 1] = seat;
            counter++;
        }

    }*/

    public String countsSeats() {
        int availbleSeats = seatNumber.length - counterNext;
        return "Antal optaget pladser " + counterNext + " Antal ledige pladser " + availbleSeats;
    }

    public void setIn() {
        seatNumber[counterNext] = counterNext;
        counterNext++;
        setSeatNumber(seatNumber);
    }

}
