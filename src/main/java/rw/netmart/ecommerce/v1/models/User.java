package rw.netmart.ecommerce.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import rw.netmart.ecommerce.v1.enums.EUserStatus;
import rw.netmart.ecommerce.v1.enums.Erole;
import rw.netmart.ecommerce.v1.utils.Utility;

import javax.persistence.*;
import java.util.List;

@Entity()
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}), @UniqueConstraint(columnNames = {"phone_number"})})
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name= "first_name")
    private String firstName;

    @Column(name= "last_name")
    private String lastName;

    private String email;

    private Number phone_number;

    @Enumerated(EnumType.STRING)
    private EUserStatus status = EUserStatus.WAIT_EMAIL_VERIFICATION;

    @JsonIgnore
    @Column(name="activation_code")
    private String activationCode = Utility.randomUUID(6, 0, 'N');

    @Enumerated(EnumType.STRING)
    private Erole role;

    public User(String firstName, String lastName, String email, Number phone_number, EUserStatus status, String activationCode, Erole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone_number = phone_number;
        this.status = status;
        this.activationCode = activationCode;
        this.role = role;
    }

    public User(String firstName, String lastName, String email, Number phone_number, EUserStatus status, String activationCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone_number = phone_number;
        this.status = status;
        this.activationCode = activationCode;
    }

    public User() {

    }

    public Erole getRole() {
        return role;
    }

    @ManyToOne()
    public Address address;

    public String fullName(){
        return this.firstName + " " +this.lastName;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    public List<User> users;


}
