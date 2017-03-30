package model;

import Database.DatabaseConnection;

import java.lang.reflect.Array;

/**
 * Created by Søren on 24-03-2017.
 */
public class Reserve {


    public Object[] smallStage = new Object[240];
    public Object[] bigStage = new Object[400];
    private static int counterNext = 0;
    private DatabaseConnection dbReserve = new DatabaseConnection();

    public int getCounter(){
        return counterNext;
    }

    public void setCounter(int counterNext){
        this.counterNext = counterNext;
    }

    public void setSeatNumber(Object[] seatNumber) {
        this.bigStage = seatNumber;
    }

    public int Integer(int seat) {
        return seat;
    }

    public void numberOfReservation(int reservationsNumb, Object[] whatStage){
        //emptyShow(whatStage);
        for(int i = 0; i < reservationsNumb; i++) {

            setIn();;
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
        int availbleSeats = bigStage.length - counterNext;
        int reserveSeats = counterNext ;
        return "Antal optaget pladser " + reserveSeats + " Antal ledige pladser " + availbleSeats;
    }

    public void setIn() {
        bigStage[counterNext] = counterNext;
        counterNext++;
        setCounter(counterNext);
        setSeatNumber(bigStage);
    }


    public void setIn(int zeroIndex) {
        bigStage[counterNext] = counterNext;
        counterNext++;
        setSeatNumber(bigStage);
    }

    /*public boolean emptyShow(Object[] cinemaStage){
        for(int i = 0; i < cinemaStage.length; i++){
            if(cinemaStage[i] != null) {
                break;
            }
            else
            {
                i++;
            }
        }
        return true;
    }*/

}
