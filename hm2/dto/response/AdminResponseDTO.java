package com.ho.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminResponseDTO {

    private Long id;

    private String username;

    private String email;

    private String fullName;
    
    private String contactNumber;
}
