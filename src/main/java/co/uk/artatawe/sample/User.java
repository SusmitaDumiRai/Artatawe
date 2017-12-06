package co.uk.artatawe.sample;

import java.util.ArrayList;


/**
 * The class handles information about a user.
 * @author Tihomir Trendafilov
 * @author 908928
 * @version 1.0
 */


public class User {

  private String userName;
  private String firstName;
  private String surname;
  private String phoneNumber;
  private String address;
  private String postcode;
  private String lastLogin;
  private ArrayList<User> favouriteUsers = new ArrayList<>();
  private String profileImage;


  /**
   * Constructor class for user.
   * @param userName user name of user.
   * @param firstName firstname of user.
   * @param surname surname of user.
   * @param phoneNumber phone number of user.
   * @param address first line of address of user.
   * @param postcode post code of user.
   * @param lastLogin date time of user's last login.
  */
  public User(String userName, String firstName, String surname,
              String phoneNumber, String address, String postcode, String lastLogin, String profileImage) {
    this.userName = userName;
    this.firstName = firstName;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.postcode = postcode;
    this.lastLogin = lastLogin;
    this.profileImage = profileImage;
  }


  /**
   * Empty constructor
   */
  public User() {

  }

  /**
    * Adds a user to the list of favourite users.
   * @param user user that is going to be added
  */
  public void addFavouriteUser(User user) {
    favouriteUsers.add(user);
  }

  /**
   * Removes the specified user from the list of favourite users.
   * @param user user that is going to be removed.
  */
  public void deleteFavouriteUser(User user) {
    favouriteUsers.remove(user);
  }

  /**
   * Returns a list of favourite users.
   * @return the list of favourite users
  */
  public ArrayList<User> getFavouriteUsers() {
    return favouriteUsers;
  }

  /**
   * Returns the user name.
   * @return user name
  */
  public String getUserName() {
    return userName;
  }

  /**
   * Returns the first name of a user.
   * @return first name
  */
  public String getFirstName() {
    return firstName;
  }
    
  /**
   * Returns the last name of a user.
   * @return last name
  */
  public String getLastName() {
    return surname;
  }
    
  /**
   * Returns the time and date of the last Login of the user in the system.
   * @return the time and date of the last log in
  */
  public String getLastLogin() {
    return lastLogin;
  }
    
  /**
   * Returns the phone number of a user.
   * @return phone number
  */
  public String getPhoneNumber() {
    return phoneNumber;
  }
    
  /**
   * Returns the address of a user.
   * @return address
  */
  public String getAddress() {
    return address;
  }
    
  /**
   * Returns the postcode of a user.
   * @return postcode
  */
  public String getPostcode() {
    return postcode;
  }
   
  /**
   * Returns the profile image a user has chosen for their profile.
   * @return a user's profile image
  */
  public String getProfileImage() {
    return profileImage;
  }

  /**
   * Set the variable of a user that stores the date and time of theirs last log in.
   * @param newLastLogin new date and time of a user's last log in
  */
  public void setLastLogin(String newLastLogin) {
    this.lastLogin = newLastLogin;
  }

  @Override
    public String toString() {
    return "User{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", profileImage=" + profileImage +
                '}';
  }

}
