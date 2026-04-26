package com.example.user.service;

import com.example.user.dto.UserDTO;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO createUser(UserDTO user) throws DataIntegrityViolationException{
        return createUser(user.getEmail(), user.getPassword(),user.getUserName());
    }


    public UserDTO createUser(String email, String password, String userName)throws DataIntegrityViolationException{
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(userName);


        User savedUser = userRepository.save( user);


       return User.toUserDTO( savedUser );
    }

    public List<UserDTO> allUsers(){
        //select * from users;
        List<User> users =userRepository.findAll();

      return users.stream().map(User::toUserDTO).toList();
    }

    public UserDTO getUser(Long id){
        User user = userRepository.findById(id).orElseThrow();
        return User.toUserDTO(user);

    }

}
