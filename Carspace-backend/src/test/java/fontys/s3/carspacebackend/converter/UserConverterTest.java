package fontys.s3.carspacebackend.converter;

import fontys.s3.carspacebackend.controller.dto.UserDTO;
import fontys.s3.carspacebackend.persistence.entity.converters.UserConverter;
import fontys.s3.carspacebackend.domain.User;
import fontys.s3.carspacebackend.domain.impl.AdminRole;
import fontys.s3.carspacebackend.domain.impl.UserRole;
import fontys.s3.carspacebackend.persistence.entity.RoleEntity;
import fontys.s3.carspacebackend.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserConverterTest {

    @Test
    void testConvertUserToPOJO(){
        RoleEntity roleE = RoleEntity.builder().id(99L).roleName("user").build();
        UserEntity userE = UserEntity.builder().id(1L).role(roleE).username("jdoe").password("pass").firstName("john").lastName("doe").email("jdoe@gmail.com").address("avenue 123").phone("+311").build();

        User u = UserConverter.convertToPOJO(userE);

        assertEquals(1L, u.getId());
        assertEquals(99L, u.getRole().getRoleId());
        assertEquals("user", u.getRole().getRole());
        assertTrue(u.getRole() instanceof UserRole);
        assertEquals("jdoe", u.getUsername());
        assertEquals("pass", u.getPassword());
        assertEquals("john", u.getFirstName());
        assertEquals("doe", u.getLastName());
        assertEquals("jdoe@gmail.com", u.getEmail());
        assertEquals("avenue 123", u.getAddress());
        assertEquals("+311", u.getPhone());
    }

    @Test
    void testConvertToPOJO_null(){

        UserEntity userE = null;

        User u = UserConverter.convertToPOJO(userE);

        assertNull(u);
    }
    @Test
    void testConvertAdminToPOJO(){
        RoleEntity roleE = RoleEntity.builder().id(99L).roleName("admin").build();
        UserEntity userE = UserEntity.builder().id(1L).role(roleE).username("jdoe").password("pass").firstName("john").lastName("doe").email("jdoe@gmail.com").address("avenue 123").phone("+311").build();

        User u = UserConverter.convertToPOJO(userE);

        assertEquals(1L, u.getId());
        assertEquals(99L, u.getRole().getRoleId());
        assertEquals("admin", u.getRole().getRole());
        assertTrue(u.getRole() instanceof AdminRole);
        assertEquals("jdoe", u.getUsername());
        assertEquals("pass", u.getPassword());
        assertEquals("john", u.getFirstName());
        assertEquals("doe", u.getLastName());
        assertEquals("jdoe@gmail.com", u.getEmail());
        assertEquals("avenue 123", u.getAddress());
        assertEquals("+311", u.getPhone());
    }

    @Test
    void testConvertToEntity_notnull(){
        AdminRole role = AdminRole.builder().id(99L).role("admin").build();
        User user = User.builder().id(1L).role(role).username("jdoe").password("pass").firstName("john").lastName("doe").email("jdoe@gmail.com").address("avenue 123").phone("+311").build();

        UserEntity userE = UserConverter.convertToEntity(user);

        assertEquals(1L, userE.getId());
        assertEquals(99L, userE.getRole().getId());
        assertEquals("admin", userE.getRole().getRoleName());
        assertEquals("jdoe", userE.getUsername());
        assertEquals("pass", userE.getPassword());
        assertEquals("john", userE.getFirstName());
        assertEquals("doe", userE.getLastName());
        assertEquals("jdoe@gmail.com", userE.getEmail());
        assertEquals("avenue 123", userE.getAddress());
        assertEquals("+311", userE.getPhone());
    }

    @Test
    void testConvertToEntity_null(){

        User user = null;

        UserEntity userE = UserConverter.convertToEntity(user);

        assertNull(userE);
    }

    @Test
    void testConvertToDTO(){
        AdminRole role = AdminRole.builder().id(99L).role("admin").build();
        User user = User.builder().id(1L).role(role).username("jdoe").password("pass").firstName("john").lastName("doe").email("jdoe@gmail.com").address("avenue 123").phone("+311").build();

        UserDTO dto = UserConverter.convertToDTO(user);

        assertEquals(1L, dto.getId());
        assertEquals("admin", dto.getRole());
        assertEquals("jdoe", dto.getUsername());
        assertEquals("john", dto.getFirstName());
        assertEquals("doe", dto.getLastName());
        assertEquals("jdoe@gmail.com", dto.getEmail());
        assertEquals("avenue 123", dto.getAddress());
        assertEquals("+311", dto.getPhone());
    }

    @Test
    void testConvertToDTO_null(){
        User user = null;

        UserDTO dto = UserConverter.convertToDTO(user);

        assertNull(dto);
    }
}
