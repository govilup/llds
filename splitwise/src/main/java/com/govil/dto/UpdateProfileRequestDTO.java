package com.govil.dto;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequestDTO {
    private Long userId;
    private String newPassword;
}
