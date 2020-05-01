package com.fmway.models.trip;

public class TripParseDefinitions {

    /**
     * each string represents a key on parse database under trip class
     */
    public String className = "Trip";
    public String objectIdKey = "objectId";
    public String TripCreatedByKey = "TripCreatedBy";
    public String DateKey = "Date";
    public String TimeKey = "Time";
    public String FromKey = "From";
    public String DestinationKey = "Destination";
    public String CapacityKey = "Capacity";
    public String PriceKey = "Price";

    public String getClassName() {
        return className;
    }

    public String getObjectIdKey() {
        return objectIdKey;
    }

    public String getTripCreatedByKey() {
        return TripCreatedByKey;
    }

    public String getDateKey() {
        return DateKey;
    }

    public String getTimeKey() {
        return TimeKey;
    }

    public String getFromKey() {
        return FromKey;
    }

    public String getDestinationKey() {
        return DestinationKey;
    }

    public String getCapacityKey() {
        return CapacityKey;
    }

    public String getPriceKey() {
        return PriceKey;
    }
}
