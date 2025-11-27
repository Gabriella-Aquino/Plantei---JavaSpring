package br.com.gabriella.plantei.service;

import br.com.gabriella.plantei.dtos.User.UserCreateDTO;
import br.com.gabriella.plantei.dtos.User.UserReadDTO;
import br.com.gabriella.plantei.dtos.User.UserUpdateDTO;
import br.com.gabriella.plantei.mapper.UserMapper;
import br.com.gabriella.plantei.model.User;
import br.com.gabriella.plantei.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserReadDTO creatUser (UserCreateDTO data){
        User user = userMapper.toEntity(data);
        userRepository.save(user);
        return userMapper.toReadDTO(user);
    }

    public UserReadDTO getUserById(long id){
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User não encontrado"));
        return userMapper.toReadDTO(user);
    }

    public List<UserReadDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toReadDTO).toList();
    }

    public UserReadDTO updateUser(Long id, UserUpdateDTO data){
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User não encontrado"));
        userMapper.updateEntityFromDTO(data, user);
        User update = userRepository.save(user);
        return userMapper.toReadDTO(update);
    }

    public void delete(long id){
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundException("user não encontrado");
        }
        userRepository.deleteById(id);
    }
}
