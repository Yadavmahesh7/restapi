package com.yadavmahesh.restapi.service;

import com.yadavmahesh.restapi.dto.ProfileDTO;

public interface ProfileService {

    /**
     * It will save the user details to databases
     * @param profileDTO
     * @return ProfileDto
     **/
    ProfileDTO createProfile(ProfileDTO profileDTO);

}
