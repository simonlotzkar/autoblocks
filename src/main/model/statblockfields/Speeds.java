package model.statblockfields;

import org.json.JSONObject;
import persistence.Writable;

// Represents a base speed and optionally auxiliary speeds
public class Speeds implements Writable {
    // required fields
    private final int speed;

    // optional fields
    private final int burrow;
    private final int climb;
    private final int fly;
    private final int swim;

    // MODIFIES: this
    // EFFECTS: constructs a Speeds using a builder
    public Speeds(SpeedsBuilder builder) {
        this.speed = builder.speed;
        this.burrow = builder.burrow;
        this.climb = builder.climb;
        this.fly = builder.fly;
        this.swim = builder.swim;
    }

    @Override
    // EFFECTS: gets string representation of Speeds depending on what fields exist
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(speed).append("ft");
        if (burrow != 0) {
            stringBuilder.append(", burrow ").append(burrow).append("ft");
        }
        if (climb != 0) {
            stringBuilder.append(", climb ").append(climb).append("ft");
        }
        if (fly != 0) {
            stringBuilder.append(", fly ").append(fly).append("ft");
        }
        if (swim != 0) {
            stringBuilder.append(", swim ").append(swim).append("ft");
        }
        return stringBuilder.toString();
    }

    @Override
    // EFFECTS: constructs a json object with the fields of the speeds
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("speed", speed);
        json.put("burrow", burrow);
        json.put("climb", climb);
        json.put("fly", fly);
        json.put("swim", swim);
        return json;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: get ground speed
    public int getSpeed() {
        return speed;
    }

    // EFFECTS: get burrow speed
    public int getBurrow() {
        return burrow;
    }

    // EFFECTS: get climb speed
    public int getClimb() {
        return climb;
    }

    // EFFECTS: get fly speed
    public int getFly() {
        return fly;
    }

    // EFFECTS: get swim speed
    public int getSwim() {
        return swim;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // builder class
    public static class SpeedsBuilder {
        // required fields
        private final int speed;

        // optional fields
        private int burrow;
        private int climb;
        private int fly;
        private int swim;

        // MODIFIES: this
        // EFFECTS: constructs a builder with required fields and throws exception if given speed is negative
        public SpeedsBuilder(int speed) throws IndexOutOfBoundsException {
            if (speed < 0) {
                throw new IndexOutOfBoundsException("(Speeds) base speed cannot be negative.");
            } else {
                this.speed = speed;
            }
        }

        // EFFECTS: returns a new Speeds with required fields, and any optional fields that had their builder called.
        public Speeds build() {
            return new Speeds(this);
        }

        // EFFECTS: returns a builder that assigns given burrow speed to Speeds
        //          and throws exception if given speed is negative
        public SpeedsBuilder burrow(int burrow) throws IndexOutOfBoundsException {
            if (burrow < 0) {
                throw new IndexOutOfBoundsException("(Speeds) burrow speed cannot be negative.");
            } else {
                this.burrow = burrow;
                return this;
            }
        }

        // EFFECTS: returns a builder that assigns given climbing speed to Speeds
        //          and throws exception if given speed is negative
        public SpeedsBuilder climb(int climb) throws IndexOutOfBoundsException {
            if (climb < 0) {
                throw new IndexOutOfBoundsException("(Speeds) climb speed cannot be negative.");
            } else {
                this.climb = climb;
                return this;
            }
        }

        // EFFECTS: returns a builder that assigns given flying speed to Speeds
        //          and throws exception if given speed is negative
        public SpeedsBuilder fly(int fly) throws IndexOutOfBoundsException {
            if (fly < 0) {
                throw new IndexOutOfBoundsException("(Speeds) fly speed cannot be negative.");
            } else {
                this.fly = fly;
                return this;
            }
        }

        // EFFECTS: returns a builder that assigns given swimming speed to Speeds
        //          and throws exception if given speed is negative
        public SpeedsBuilder swim(int swim) throws IndexOutOfBoundsException {
            if (swim < 0) {
                throw new IndexOutOfBoundsException("(Speeds) swim speed cannot be negative.");
            } else {
                this.swim = swim;
                return this;
            }
        }
    }
}
