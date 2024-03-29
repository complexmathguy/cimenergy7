/**
 * ***************************************************************************** Turnstone Biologics
 * Confidential
 *
 * <p>2018 Turnstone Biologics All Rights Reserved.
 *
 * <p>This file is subject to the terms and conditions defined in file 'license.txt', which is part
 * of this source code package.
 *
 * <p>Contributors : Turnstone Biologics - General Release
 * ****************************************************************************
 */
package com.occulue.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.repository.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Projector for GroundDisconnector as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by GroundDisconnectorAggregate
 *
 * @author your_name_here
 */
@Component("groundDisconnector-entity-projector")
public class GroundDisconnectorEntityProjector {

  // core constructor
  public GroundDisconnectorEntityProjector(GroundDisconnectorRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a GroundDisconnector
   *
   * @param	entity GroundDisconnector
   */
  public GroundDisconnector create(GroundDisconnector entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a GroundDisconnector
   *
   * @param	entity GroundDisconnector
   */
  public GroundDisconnector update(GroundDisconnector entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a GroundDisconnector
   *
   * @param	id		UUID
   */
  public GroundDisconnector delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    GroundDisconnector entity = repository.findById(id).get();

    // ------------------------------------------
    // delete what is discovered
    // ------------------------------------------
    repository.delete(entity);

    return entity;
  }

  /**
   * Method to retrieve the GroundDisconnector via an FindGroundDisconnectorQuery
   *
   * @return query FindGroundDisconnectorQuery
   */
  @SuppressWarnings("unused")
  public GroundDisconnector find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a GroundDisconnector - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all GroundDisconnectors
   *
   * @param query FindAllGroundDisconnectorQuery
   * @return List<GroundDisconnector>
   */
  @SuppressWarnings("unused")
  public List<GroundDisconnector> findAll(FindAllGroundDisconnectorQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all GroundDisconnector - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final GroundDisconnectorRepository repository;

  private static final Logger LOGGER =
      Logger.getLogger(GroundDisconnectorEntityProjector.class.getName());
}
