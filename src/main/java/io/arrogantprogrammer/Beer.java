package io.arrogantprogrammer;

public class Beer {

    String name;

    String tagline;

    double abv;

    int ibu;

    Beer(String name, String tagline, double abv, int ibu) {
        this.name = name;
        this.tagline = tagline;
        this.abv = abv;
        this.ibu = ibu;
    }

    Beer() {
    }

    @Override
    public String toString() {
        return "Beer{" +
                "name='" + name + '\'' +
                ", tagline='" + tagline + '\'' +
                ", abv=" + abv +
                ", ibu=" + ibu +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public double getAbv() {
        return abv;
    }

    public int getIbu() {
        return ibu;
    }
}
