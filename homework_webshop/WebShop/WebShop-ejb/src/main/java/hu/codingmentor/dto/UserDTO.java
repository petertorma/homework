package hu.codingmentor.dto;

import hu.codingmentor.annotations.DateAnnotation;
import hu.codingmentor.annotations.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Valid
@DateAnnotation
public class UserDTO {

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

    private String firstname;

    private String lastname;

    private LocalDate dateOfBirth;

    @NotNull(message = "you must give value for ")
    private LocalDate registrationDate;

    private boolean admin = false;

    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    List<MobileDTO> cart = new ArrayList<>();

    public UserDTO() {
        //default
    }

    public UserDTO(String username, String passowrd, String firstName, String lastName, String dateOfBirth, String registrationDate) {
        this.username = username;
        this.passowrd = passowrd;
        this.firstname = firstName;
        this.lastname = lastName;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, dtf);
        this.registrationDate = LocalDate.parse(registrationDate, dtf);
        this.setAdmin();
    }

    public UserDTO(String username, String passowrd) {
        this.username = username;
        this.passowrd = passowrd;
    }

    public boolean isAdmin() {
        return admin;
    }

    private void setAdmin() {
        if ("admin".equals(this.username)) {
            admin = true;
        }
    }

    public List<MobileDTO> getCart() {
        return cart;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
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

    @Override
    public String toString() {
        return "UserDTO{" + "username=" + username + ", passowrd=" + passowrd + ", firstname=" + firstname + ", lastname=" + lastname + ", dateOfBirth=" + dateOfBirth + ", registrationDate=" + registrationDate + ", admin=" + admin + ", dtf=" + dtf + '}';
    }
}
