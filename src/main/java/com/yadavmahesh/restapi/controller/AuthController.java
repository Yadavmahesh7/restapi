package com.yadavmahesh.restapi.controller;

import com.yadavmahesh.restapi.dto.ProfileDTO;
import com.yadavmahesh.restapi.io.ProfileRequest;
import com.yadavmahesh.restapi.io.ProfileResponce;
import com.yadavmahesh.restapi.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final ProfileService profileService;

    private final ModelMapper modelMapper;

    /**
     * API endpoint to register new User
     * @param profileRequest
     * @return profileResponse
     * */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ProfileResponce createProfile(@Valid @RequestBody ProfileRequest profileRequest){
        ProfileDTO profileDTO=mapToProfileDTO(profileRequest);
        profileDTO=profileService.createProfile(profileDTO);
        return mapToProfileResponse(profileDTO);
    }

    /**
    *Mapper method to map values from profileRequest to profile dto
     * @param profileRequest
     * @return profileDto
    * */
    private ProfileDTO mapToProfileDTO(@Valid ProfileRequest profileRequest) {
        return modelMapper.map(profileRequest, ProfileDTO.class);
    }

    /**
     * mapper method to map values from profile dto to profileResponse
     * @param profileDTO
     * @return profileResponce
     */

    private ProfileResponce mapToProfileResponse(ProfileDTO profileDTO) {
       return modelMapper.map(profileDTO, ProfileResponce.class);
    }
}
