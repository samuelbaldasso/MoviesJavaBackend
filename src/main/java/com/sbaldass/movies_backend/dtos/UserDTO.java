package com.sbaldass.movies_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserDTO {
        private Long id;
        private String username;
        private String email;
        private String password;
        private boolean isAdmin;
        private String avatar;
        private LocalDate createdAt;
        private LocalDate updatedAt;

        public UserDTO(String email, String password){
                this.email = email;
                this.password = password;
        }

        public UserDTO() {

        }
}
