package name.fapi.service.impl;

import name.fapi.module.User;
import name.fapi.module.UserDTO;
import name.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("customUserDetailsService")
public class UserServiceImpl implements UserDetailsService, UserService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User save(User user) {
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/user", user, User.class).getBody();
    }

    @Override
    public User findByLogin(String login){
        RestTemplate restTemplate=new RestTemplate();
        User user = restTemplate.getForObject(backendServerUrl+ "/api/user/login/" + login, User.class);
        return user;
    }

    @Override
    public UserDTO getUserDTO(String login) {
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForEntity(backendServerUrl+"/api/user/username/" + login, User.class).getBody();
        return new UserDTO(user.getUserId(), user.getUserName(), user.getUserLogin(), user.getRole());
    }

    @Override
    public List getUserSubs(String login) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendServerUrl+"/api/user/subs?login="+login, List.class).getBody();
    }

    @Override
    public List getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendServerUrl+"/api/user/all", List.class).getBody();
    }

    @Override
    public void deleteUser(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl+"/api/user/delete?id={id}", id);
    }

    @Override
    public Boolean check(String login) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/user/check?login={login}", Boolean.class, login);
    }

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = findByLogin(login);
        if(user==null) throw new UsernameNotFoundException("Invalid login or password");
        return new org.springframework.security.core.userdetails.User(user.getUserLogin(), user.getUserPassword(), getAuthority(user));
    }

    private Set getAuthority(User user){
        Set authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ user.getRole().getRole()));
        return authorities;
    }
}
