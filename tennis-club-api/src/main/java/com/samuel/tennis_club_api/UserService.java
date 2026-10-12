package com.samuel.tennis_club_api;

import com.samuel.tennis_club_api.dto.UserRequestDTO;
import com.samuel.tennis_club_api.dto.UserResponseDTO;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> response = new ArrayList<UserResponseDTO>();
        for (int i=0; i < users.size(); i++){
            User user = users.get(i);
            UserResponseDTO currentResponse = new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
            response.add(currentResponse);
        }
        return response;
    }

    public UserResponseDTO getUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("The requested user has not been found.");
        User existingUser = user.get();
        return new UserResponseDTO(existingUser.getId(), existingUser.getName(), existingUser.getEmail());
    }

    public UserResponseDTO createUser(UserRequestDTO request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        userRepository.save(user);
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }

    public UserResponseDTO updateUser(Integer id, UserRequestDTO request) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("The requested user has not been found.");
        User existingUser = user.get();
        existingUser.setName(request.getName());
        existingUser.setEmail(request.getEmail());
        userRepository.save(existingUser);
        return new UserResponseDTO(existingUser.getId(), existingUser.getName(), existingUser.getEmail());
    }

    public void deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("The requested user has not been found.");
        userRepository.deleteById(id);
    }
}
