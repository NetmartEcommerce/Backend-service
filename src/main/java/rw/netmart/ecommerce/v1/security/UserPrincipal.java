package rw.netmart.ecommerce.v1.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rw.netmart.ecommerce.v1.enums.EUserStatus;
import rw.netmart.ecommerce.v1.models.Address;
import rw.netmart.ecommerce.v1.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Address address;

    private String password;

    private EUserStatus status;


    private List<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                        user.getPhoneNumber(),
                user.getAddress(),
                        user.getPassword(),
                user.getStatus(),
                        authorities);
    }
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
