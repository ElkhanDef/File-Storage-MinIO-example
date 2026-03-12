package com.fss.repository;

import com.fss.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey,Long> {

    @Query("SELECT keyValue FROM ApiKey")
    List<String> getApiKeysValues();
}
