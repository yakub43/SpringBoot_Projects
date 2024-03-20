package net.javaguides.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private Long id;
    // User firstName should not be null or empty
    @NotEmpty(message = "User firstName should not be null or empty")
    private String firstName;

    // User last name should not be null or empty
    @NotEmpty(message = "User last name should not be null or empty")
    private String lastName;

    // email should be valid
    // user email should not be null or empty
    @NotEmpty(message = "User email should not be null or empty")
    @Email
    private String email;
}
