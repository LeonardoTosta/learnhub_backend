package services;

import dto.UserDTO;
import exceptions.UserNotFoundException;
import model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    public User registerUser(UserDTO userDto) {

        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);

        return modelMapper.map(user, UserDTO.class).toModel();
    }

    public void editUser(UserDTO userDto) {

        User user = findUserById(userDto.getId());
        modelMapper.map(userDto, user);

        userRepository.save(user);
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

//    // Exemplo de método de negócio específico
//    public List<User> findByRole(String role) {
//        return userRepository.findByRole(role);
//    }
}
