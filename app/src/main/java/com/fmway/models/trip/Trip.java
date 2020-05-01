package com.fmway.models.trip;

public class Trip {
    public String objectId;
    public String Date;
    public String Time;
    public String From;
    public String Destination;
    public String Capacity;
    public String Price;

    /**
     * This class is for listing the trip list on parse database
     *
     * each parameter represents a key on parse database
     * @param objectId
     * @param Date
     * @param Time
     * @param From
     * @param Destination
     * @param Capacity
     * @param Price
     */
    public Trip(String objectId
                ,String Date
                ,String Time
                ,String From
                ,String Destination
                ,String Capacity
                ,String Price){
        this.objectId = objectId;
        this.Date = Date;
        this.Time = Time;
        this.From = From;
        this.Destination = Destination;
        this.Capacity = Capacity;
        this.Price = Price;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public String getFrom() {
        return From;
    }

    public String getDestination() {
        return Destination;
    }

    public String getCapacity() {
        return Capacity;
    }

    public String getPrice() {
        return Price;
    }
}
