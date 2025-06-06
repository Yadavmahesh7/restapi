package com.yadavmahesh.restapi.service.impl;

import com.yadavmahesh.restapi.dto.ProfileDTO;
import com.yadavmahesh.restapi.entity.ProfileEntity;
import com.yadavmahesh.restapi.repository.ProfileRepository;
import com.yadavmahesh.restapi.service.ProfileService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;

    /**
     * It will save the user details to databases
     * @param profileDTO
     * @return ProfileDto
     **/
    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = mapToProfileEntity(profileDTO);
        profileEntity.setProfileId(UUID.randomUUID().toString());
        //Call The Repository Method
        profileEntity = profileRepository.save(profileEntity);
        return mapToProfileDTO(profileEntity);
    }
    /**
     * Mapper method to map values from profile Entity to profile DTO
     * @param profileEntity
     * @return ProfileDTO
     * */
    private ProfileDTO mapToProfileDTO(ProfileEntity profileEntity) {
        return modelMapper.map(profileEntity,ProfileDTO.class);
    }
/**
 * Mapper method to map values from profile dto to profile Entity
 * @param profileDTO
 * @return ProfileEntity
 * */
    private ProfileEntity mapToProfileEntity(ProfileDTO profileDTO) {

             return modelMapper.map(profileDTO, ProfileEntity.class);
    }
}
