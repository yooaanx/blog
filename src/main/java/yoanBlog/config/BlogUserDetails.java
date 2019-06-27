package yoanBlog.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import yoanBlog.entity.Role;
import yoanBlog.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class BlogUserDetails extends User implements UserDetails {

    private ArrayList<String> roles;
    private User user;

    public BlogUserDetails(User user, ArrayList<String> roles) {
        super(user.getEmail(), user.getFullName(), user.getPassword());

        this.roles = roles;
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRoles = StringUtils.collectionToCommaDelimitedString(this.roles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(userRoles);
    }

    @Override
    public Set<Role> getRoles() {
        return super.getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
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

    @Override
    public String getUsername() {
        return null;
    }
}
