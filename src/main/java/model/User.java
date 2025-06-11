package model;

import dto.UserDTO;
import enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class User {

    private Long id;
    private String name;
    private Role role;
    private LocalDateTime createdAt;

    public UserDTO toDto() {
        return UserDTO.builder()
                .id(this.id)
                .name(this.name)
                .role(this.role)
                .createdAt(this.createdAt)
                .build();
    }
}
