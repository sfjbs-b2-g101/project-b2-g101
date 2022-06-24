package com.processpension.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.processpension.entity.PensionDetail;

@Repository
public interface PensionDetailsRepo extends JpaRepository<PensionDetail, Integer>{

}
