package dto;

import enums.Role;
import lombok.Builder;
import lombok.Data;
import model.User;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String name;
    private Role role;
    private LocalDateTime createdAt;

    public User toModel() {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .role(this.role)
                .createdAt(this.createdAt)
                .build();
    }
}
