package com.exercise.device.database.repositories;

import java.util.List;

import com.exercise.device.database.entities.Device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviceRepository extends JpaRepository<Device, Integer> {

  @Query("SELECT d FROM Device d where (:id IS NULL or d.id = :id) AND (:brand IS NULL or d.brand like %:brand%) AND (:name IS NULL or d.name like %:name%)")
  List<Device> getAllByFilters(@Param("id") Integer aId, @Param("brand") String aBrand, @Param("name") String aName);
}
