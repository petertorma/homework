package hu.codingmentor.dto;



import hu.codingmentor.annotations.DateAnnotation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDTO {

    @Min(3)
    @NotNull
    private String username;

    @NotNull
    @Min(6)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[=+<>.,]|(?=.*[0-9]))(?=\\S+$).{6,}$")
    private String passowrd;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @DateAnnotation
    @Pattern(regexp = "YYYY.MM.DD")
    LocalDate dateOfBirth;

    @NotNull
    @Pattern(regexp = "YYYY.MM.DD")
    LocalDate RegistrationDate;

    boolean admin = false;

    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public UserDTO() {
    }

    public UserDTO(String username, String passowrd, String firstName, String lastName, String dateOfBirth, String RegistrationDate) {
        this.username = username;
        this.passowrd = passowrd;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, dtf);
        this.RegistrationDate = LocalDate.parse(RegistrationDate, dtf);
        this.setAdmin();
    }

    public boolean isAdmin() {
        return admin;
    }

    private void setAdmin() {
        admin = "admin".equals(this.getUsername());

    }

    public List<MobileDTO> getCart() {
        return cart;
    }

    public void setCart(List<MobileDTO> cart) {
        this.cart = cart;
    }

    List<MobileDTO> cart = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(LocalDate RegistrationDate) {
        this.RegistrationDate = RegistrationDate;
    }

}
