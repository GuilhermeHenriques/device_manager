package com.exercise.device.factories;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CommonFactory<T> {

  /**
   * Repository associated to specific entity (T)
   */
  private JpaRepository<T, Integer> repo;

  public CommonFactory() {
  }

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
   * Build T object.
   * If the repository is defined then save T object into DB
   * 
   * @return
   */
  public T get() {
    T obj = build();

    if (repo != null) {
      obj = repo.save(obj);
    }

    return obj;
  }

  /**
   * Build list of T objects
   * 
   * @param aLength
   * @return
   */
  public List<T> get(int aLength) {
    List<T> result = new ArrayList<T>();

    if (aLength <= 0) {
      return result;
    }

    for (int i = 0; i < aLength; i++) {
      result.add(get());
    }

    return result;
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
