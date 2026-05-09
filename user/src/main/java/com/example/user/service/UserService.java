package com.example.user.service;

import com.example.user.dto.UserDTO;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

    public UserDTO update(Long id, UserDTO userDTO){
       User user = userRepository.findById(id).orElseThrow();
       user.setEmail(userDTO.getEmail());
       user.setPassword(userDTO.getPassword());
       user.setUsername(userDTO.getUserName());

       User savedUser = userRepository.save(user);
       return User.toUserDTO(savedUser);


    }
    
    public boolean delete (Long id){
        if(!userRepository.existsById(id))
            throw new NoSuchElementException("해당 id를 찾는 user를 찾을 수 없습니다");
            
        userRepository.deleteById(id);
        return true;
    }


}
