package hu.codingmentor.dto;

import hu.codingmentor.annotations.DateAnnotation;
import hu.codingmentor.annotations.IntValidator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@IntValidator
public class UserDTO {

    public UserDTO(String username, String passowrd) {
        this.username = username;
        this.passowrd = passowrd;
    }

    @Size(min = 3)
    @NotNull
    private String username;

    @NotNull
    @Size(min = 6)
    @Pattern.List({
        @Pattern(regexp = ".*[a-z].*"),
        @Pattern(regexp = ".*[0-9].*"),
        @Pattern(regexp = ".*[=\\<>+.,].*"),
        @Pattern(regexp = ".*[A-Z].*")

    }
    )
    private String passowrd;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @DateAnnotation
    @Pattern(regexp = "YYYY.MM.DD")
    LocalDate dateOfBirth;

    @NotNull
    @Pattern(regexp = "YYYY.MM.DD")
    LocalDate RegistrationDate;

    boolean admin = false;

    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    List<MobileDTO> cart = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(String username, String passowrd, String firstName, String lastName, String dateOfBirth, String RegistrationDate) {
        this.username = username;
        this.passowrd = passowrd;
        this.firstname = firstName;
        this.lastname = lastName;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }
}
