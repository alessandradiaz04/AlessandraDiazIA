package org.example;


import java.time.LocalDate;

public class Event {

    String eventName;
    LocalDate eventDate;



    public Event (String eventName, LocalDate eventDate) {
        this.eventName = eventName;
        this.eventDate = eventDate;

    }
    // The ChoiceBox uses the toString() method of our object to display options in the dropdown.
    // We need to override this method to return something more helpful.
    public String getEventName() {
        return eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    @Override
    public String toString(){
        return eventName;
    }
}