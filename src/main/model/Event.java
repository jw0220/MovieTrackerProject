package model;


import java.util.Calendar;
import java.util.Date;

/**
 * Represents an alarm system event.
 */
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    /**
     * Creates an event with the given description
     * and the current date/time stamp.
     * @param description  a description of the event
     */
    //EFFECTS: constructs an Event with a date logged and description
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    /**
     * Gets the date of this event (includes time).
     * @return  the date of the event
     */
    //EFFECTS: Gets the date of this event (includes time).
    public Date getDate() {
        return dateLogged;
    }

    /**
     * Gets the description of this event.
     * @return  the description of the event
     */
    //EFFECTS: Gets the description of this event.
    public String getDescription() {
        return description;
    }

    @Override
    //EFFECTS: override the equals() method so that dateLogged and description fields are used to see if two objects are
    //         equal
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return this.dateLogged.equals(otherEvent.dateLogged) && this.description.equals(otherEvent.description);
    }

    @Override
    //EFFECTS: override hashcode() for dateLogged and description
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    @Override
    //EFFECTS: override toString() method to add description
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}