package br.com.gabriella.plantei.test;

import br.com.gabriella.plantei.dtos.User.UserCreateDTO;
import br.com.gabriella.plantei.dtos.User.UserReadDTO;
import br.com.gabriella.plantei.dtos.User.UserUpdateDTO;
import br.com.gabriella.plantei.mapper.UserMapper;
import br.com.gabriella.plantei.model.User;
import br.com.gabriella.plantei.repository.UserRepository;
import br.com.gabriella.plantei.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserReadDTO readDTO;

    @BeforeEach
    void setup() {
        user = new User();
        user.setId(1L);
        user.setUsername("gaby");
        user.setEmail("gaby@gmail.com");
        user.setPassword("123456");
        user.setCreatedAt(LocalDateTime.now());

        readDTO = new UserReadDTO(
                1L,
                "gaby",
                "gaby@gmail.com",
                user.getCreatedAt()
        );
    }

    // CREATE ------------------------------
    @Test
    void testCreateUser_success() {
        UserCreateDTO createDTO = new UserCreateDTO("gaby", "gaby@gmail.com", "123456");

        when(userMapper.toEntity(createDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toReadDTO(user)).thenReturn(readDTO);

        UserReadDTO result = userService.creatUser(createDTO);

        assertNotNull(result);
        assertEquals("gaby", result.getUsername());
        verify(userRepository).save(user);
    }

    @Test
    void testCreateUser_duplicateError() {
        UserCreateDTO createDTO = new UserCreateDTO("gaby", "gaby@gmail.com", "123456");

        when(userMapper.toEntity(createDTO)).thenReturn(user);
        when(userRepository.save(user)).thenThrow(new DataIntegrityViolationException("duplicado"));

        assertThrows(RuntimeException.class,
                () -> userService.creatUser(createDTO)
        );
    }


    // GET BY ID ------------------------------
    @Test
    void testGetUserById_found() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toReadDTO(user)).thenReturn(readDTO);

        UserReadDTO result = userService.getUserById(1L);

        assertEquals("gaby", result.getUsername());
    }

    @Test
    void testGetUserById_notFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> userService.getUserById(99L)
        );
    }

    // UPDATE ------------------------------
    @Test
    void testUpdateUser_success() {
        UserUpdateDTO updateDTO = new UserUpdateDTO("gaby_new", "novo@gmail.com", "321");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        doAnswer(invocation -> {
            user.setUsername(updateDTO.getUsername());
            user.setEmail(updateDTO.getEmail());
            user.setPassword(updateDTO.getPassword());
            return null;
        }).when(userMapper).updateEntityFromDTO(updateDTO, user);

        when(userRepository.save(user)).thenReturn(user);

        UserReadDTO updatedDTO = new UserReadDTO(
                1L,
                "gaby_new",
                "novo@gmail.com",
                user.getCreatedAt()
        );

        when(userMapper.toReadDTO(user)).thenReturn(updatedDTO);

        UserReadDTO result = userService.updateUser(1L, updateDTO);

        assertEquals("gaby_new", result.getUsername());
        assertEquals("novo@gmail.com", result.getEmail());
    }

    @Test
    void testUpdateUser_notFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> userService.updateUser(99L, new UserUpdateDTO())
        );
    }

    @Test
    void testUpdateUser_duplicateError() {
        UserUpdateDTO updateDTO = new UserUpdateDTO("novo", "email@gmail.com", "123");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userMapper).updateEntityFromDTO(updateDTO, user);
        when(userRepository.save(user)).thenThrow(new DataIntegrityViolationException("duplicado"));

        assertThrows(RuntimeException.class,
                () -> userService.updateUser(1L, updateDTO)
        );
    }

    // DELETE ------------------------------
    @Test
    void testDelete_success() {
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.delete(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    void testDelete_notFound() {
        when(userRepository.existsById(99L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class,
                () -> userService.delete(99L)
        );
    }

}
