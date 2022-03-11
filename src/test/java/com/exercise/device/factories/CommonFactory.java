package com.exercise.device.factories;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CommonFactory<T> {

  /**
   * Repository associated to specific entity (T)
   */
  private JpaRepository<T, Integer> repo;

  /**
   * Constrcutor
   * 
   * @param aRepo
   */
  public CommonFactory(JpaRepository<T, Integer> aRepo) {
    repo = aRepo;
  }

  /**
   * Build entity object that is gonna be saved on db
   * 
   * @return
   */
  protected abstract T build();

  /**
   * Build and save T entity object into DB
   * 
   * @return
   */
  public T get() {
    return repo.save(build());
  }

  /**
   * Generate random str
   * 
   * @return
   */
  protected String generateRandomStr(int aLength) {
    return RandomStringUtils.randomAlphanumeric(aLength);
  }
}
