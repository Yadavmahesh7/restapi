package com.yadavmahesh.restapi.repository;

import com.yadavmahesh.restapi.entity.ProfileEntity;
import com.yadavmahesh.restapi.io.ProfileRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {


}
