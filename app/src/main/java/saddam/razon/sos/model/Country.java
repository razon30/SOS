package saddam.razon.sos.model;

import java.util.ArrayList;

public class Country {
    String continent;
    int flagId;
    String name;
    ArrayList<KeyValuePair> numbers = null;

    public Country(String continent, String name, int flagId) {
        this.name = name;
        this.continent = continent;
        this.flagId = flagId;
    }

    public String getContinent() {
        return this.continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getFlagId() {
        return this.flagId;
    }

    public void setFlagId(int flagId) {
        this.flagId = flagId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<KeyValuePair> getNumbers() {
        return this.numbers;
    }

    public void setNumbers(ArrayList<KeyValuePair> numbers) {
        this.numbers = numbers;
    }

    public void addNumbers(String k, String v) {
        addNumbers(new KeyValuePair(k, v));
    }

    public void addNumbers(KeyValuePair kp) {
        if (this.numbers == null) {
            this.numbers = new ArrayList();
        }
        this.numbers.add(kp);
    }
}
