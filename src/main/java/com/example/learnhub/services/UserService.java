package com.example.learnhub.services;

import com.example.learnhub.dto.UserDTO;
import com.example.learnhub.exceptions.UserNotFoundException;
import com.example.learnhub.model.User;
import com.example.learnhub.repository.UserRepository;
import com.example.learnhub.utils.PasswordUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void registerUser(UserDTO userDto) {

        User user = modelMapper.map(userDto, User.class);

        user.setPassword(PasswordUtils.encrypt(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void editUser(UserDTO userDto) {
        User user = findUserById(userDto.getId())
                .orElseThrow(() -> new UserNotFoundException(String.format("User with ID %d not found.", userDto.getId())));

        modelMapper.map(userDto, user);
        userRepository.save(user); // O save é opcional se o método é @Transactional pois ele já salvaria automaticamente
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UserDTO> findUserDtoById(Long id) {
        return userRepository.findById(id).map(User::toDto);
    }

    public List<UserDTO> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
