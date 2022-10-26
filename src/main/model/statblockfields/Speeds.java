package model.statblockfields;

public class Speeds {
    // required fields
    private final int speed;

    // optional fields
    private final int burrow;
    private final int climb;
    private final int fly;
    private final int swim;

    // EFFECTS: constructs a Speeds using a builder
    public Speeds(SpeedsBuilder builder) {
        this.speed = builder.speed;
        this.burrow = builder.burrow;
        this.climb = builder.climb;
        this.fly = builder.fly;
        this.swim = builder.swim;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: gets string representation of Speeds depending on what fields exist
    public String getSpeedsString() {
        String speedsString = speed + " ft";
        if (burrow != 0) {
            speedsString += ", burrow " + burrow + " ft";
        }
        if (climb != 0) {
            speedsString += ", climb " + climb + " ft";
        }
        if (fly != 0) {
            speedsString += ", fly " + fly + " ft";
        }
        if (swim != 0) {
            speedsString += ", swim " + swim + " ft";
        }
        return speedsString;
    }

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

        // constructs a builder with required fields
        public SpeedsBuilder(int speed) {
            this.speed = speed;
        }

        // EFFECTS: returns a new Speeds with required fields,
        //          and any optional fields that had their builder called.
        public Speeds build() {
            return new Speeds(this);
        }

        // EFFECTS: returns a builder that assigns given burrow speed to Speeds
        public SpeedsBuilder burrow(int burrow) {
            this.burrow = burrow;
            return this;
        }

        // EFFECTS: returns a builder that assigns given climbing speed to Speeds
        public SpeedsBuilder climb(int climb) {
            this.climb = climb;
            return this;
        }

        // EFFECTS: returns a builder that assigns given flying speed to Speeds
        public SpeedsBuilder fly(int fly) {
            this.fly = fly;
            return this;
        }

        // EFFECTS: returns a builder that assigns given swimming speed to Speeds
        public SpeedsBuilder swim(int swim) {
            this.swim = swim;
            return this;
        }
    }
}
