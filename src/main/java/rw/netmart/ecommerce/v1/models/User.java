package rw.netmart.ecommerce.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import rw.netmart.ecommerce.v1.enums.EUserStatus;
import rw.netmart.ecommerce.v1.utils.Utility;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity()
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}), @UniqueConstraint(columnNames = {"phone_number"})})
@OnDelete(action = OnDeleteAction.CASCADE)
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name= "first_name")
    private String firstName;

    @Column(name= "last_name")
    private String lastName;

    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private EUserStatus status = EUserStatus.WAIT_EMAIL_VERIFICATION;

    @JsonIgnore
    @Column(name="activation_code")
    private String activationCode = Utility.randomUUID(6, 0, 'N');

    public User(String firstName, String lastName, String email, String phone_number, EUserStatus status, String activationCode, Set<Role> eroles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phone_number;
        this.status = status;
        this.activationCode = activationCode;
        this.roles = eroles;
    }

    public User(String firstName, String lastName, String email, String phone_number, EUserStatus status, String activationCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phone_number;
        this.status = status;
        this.activationCode = activationCode;
    }

    public User updateUser(User user){
        this.setEmail(user.getEmail());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setPhoneNumber(user.getPhoneNumber());
        return user;
    }

    public User() {

    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany
    @JoinTable(name = "user_address", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Role> address = new HashSet<>();




    public String fullName(){
        return this.firstName + " " +this.lastName;
    }




}
