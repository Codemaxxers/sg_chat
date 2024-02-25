package com.nighthawk.hacks.classDataStruct;

/*
Adapted from Person POJO, Plain Old Java Object.
 */
public class Person extends Generics{
    // Class data
    private static String classType = "Person";
    public static KeyTypes key = KeyType.title;  // static initializer
	public static void setOrder(KeyTypes key) {Person.key = key;}
	public enum KeyType implements KeyTypes {title, uid, name, dob, age}

    // Instance data
    private String uid;  // user / person id
    private String password;
    private String name;
    private int csaPoints;
    private int cspPoints;
    private int profilePicInt;
    private int accountPoints;
    private int accountLevel;
    private int[][] statsArray;
    

    // Constructor with zero arguments
    public Person() {
        super.setType(classType);
    }

    // Constructor used when building object from an API
    public Person(String uid, String password, String name, int csaPoints, int cspPoints, int profilePicInt, int accountPoints, int accountLevel, int[][] statsArray) {
        this();  // runs zero argument constructor
        this.uid = uid;
        this.password = password;
        this.name = name;
        this.csaPoints = csaPoints;
        this.cspPoints = cspPoints;
        this.profilePicInt = profilePicInt;
        this.accountPoints = accountPoints;
        this.accountLevel = accountLevel;
        this.statsArray = statsArray;
    }

    /* 'Generics' requires getKey to help enforce KeyTypes usage */
	@Override
	protected KeyTypes getKey() { return Person.key; }

    public String getUserID() {
        return uid;
    }

    /* 'Generics' requires toString override
	 * toString provides data based off of Static Key setting
	 */
    @Override
    public String toString() {		
        String output="";
        if (KeyType.uid.equals(this.getKey())) {
            output += this.uid;
        } else if (KeyType.name.equals(this.getKey())) {
            output += this.name;
        } else {
            output = super.getType() + ": " + this.uid + ", " + this.name;
            output += ", csaPoints=" + this.csaPoints;
            output += ", cspPoints=" + this.cspPoints;
            output += ", profilePicInt=" + this.profilePicInt;
            output += ", accountPoints=" + accountPoints;
            output += ", accountLevel=" + accountLevel;
            output += ", statsArray=" + this.statsArray;

        }
        return output;
    }
    

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCsaPoints() {
        return csaPoints;
    }
    
    public void setCsaPoints(int csaPoints) {
        this.csaPoints = csaPoints;
    }

    public int getCspPoints() {
        return cspPoints;
    }

    public void setCspPoints(int cspPoints) {
        this.cspPoints = cspPoints;
    }

    public int getProfilePicInt() {
        return profilePicInt;
    }

    public void setProfilePicInt(int profilePicInt) {
        this.profilePicInt = profilePicInt;
    }

    // No setter for account points because it should be set on cspPoints and csaPoints

    public int getAccountPoints() {
        return cspPoints + csaPoints;
    }

    public int getAccountLevel() {
        return accountLevel;
    }

    public int[][] getStatsArray() {
        return statsArray;
    }

    public void setStatsArray(int[][] statsArray) {
        this.statsArray = statsArray;
    }

    public static int arraySum(int[] arr) {
        int sum = 0;    // sum initializer

        // enhanced for loop as values are needed, not index
        for (int num : arr) {
            sum += num;
            System.out.print(num + "\t");  // debug
        }

        return sum;
    }
    
}