package mypackage;

import utilities.Date;

public class Person implements Comparable<Person>{
    protected Profile profile;

    public Person(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public int compareTo(Person person) {
       int fNameCompare = this.profile.getFname().compareTo(person.getProfile().getFname());
       if (fNameCompare != 0) {
           return fNameCompare;
       }
       int lNameCompare = this.profile.getLname().compareTo(person.getProfile().getLname());
       if (lNameCompare != 0) {
           return lNameCompare;
       }
       return this.getProfile().dob().compareTo(person.getProfile().dob());
    }

    @Override
    public String toString() {
        return this.profile.getFname() + " " + this.profile.getLname() + " " + this.profile.dob();
    }

}
