package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.core.mapping.UserMapper;
import liga.medical.medicalmonitoring.core.mapping.UserRoleMapper;
import liga.medical.medicalmonitoring.core.model.Role;
import liga.medical.medicalmonitoring.core.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        List<Role> userRoles = userRoleMapper.getRoles(user.getId());
        Set<GrantedAuthority> grantedAuthorities = userRoles.stream()
                .filter(Objects::nonNull)
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
