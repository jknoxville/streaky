package com.jknoxville.streaky.lib.event;

public enum StreakUnit {
    DAY("Day"), WEEK("Week"), MONTH("Month"), YEAR("Year");
    private String singular, plural;
    
    private StreakUnit(String sing) {
        this.singular = sing;
        this.plural = sing+"s";
    }
    
    public String getString(int amount) {
        return amount == 1 ? this.singular : this.plural;
    }
}
