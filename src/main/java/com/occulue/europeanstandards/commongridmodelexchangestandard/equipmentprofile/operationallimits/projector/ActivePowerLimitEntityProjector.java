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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Projector for ActivePowerLimit as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by ActivePowerLimitAggregate
 *
 * @author your_name_here
 */
@Component("activePowerLimit-entity-projector")
public class ActivePowerLimitEntityProjector {

  // core constructor
  public ActivePowerLimitEntityProjector(ActivePowerLimitRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a ActivePowerLimit
   *
   * @param	entity ActivePowerLimit
   */
  public ActivePowerLimit create(ActivePowerLimit entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a ActivePowerLimit
   *
   * @param	entity ActivePowerLimit
   */
  public ActivePowerLimit update(ActivePowerLimit entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a ActivePowerLimit
   *
   * @param	id		UUID
   */
  public ActivePowerLimit delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    ActivePowerLimit entity = repository.findById(id).get();

    // ------------------------------------------
    // delete what is discovered
    // ------------------------------------------
    repository.delete(entity);

    return entity;
  }

  /*
   * Assign a Value
   *
   * @param	parentId	UUID
   * @param	assignment 	ActivePower
   * @return	ActivePowerLimit
   */
  public ActivePowerLimit assignValue(UUID parentId, ActivePower assignment) {
    LOGGER.info("assigning Value as " + assignment.toString());

    ActivePowerLimit parentEntity = repository.findById(parentId).get();
    assignment = ActivePowerProjector.find(assignment.getActivePowerId());

    // ------------------------------------------
    // assign the Value to the parent entity
    // ------------------------------------------
    parentEntity.setValue(assignment);

    // ------------------------------------------
    // save the parent entity
    // ------------------------------------------
    repository.save(parentEntity);

    return parentEntity;
  }

  /*
   * Unassign the Value
   *
   * @param	parentId		UUID
   * @return	ActivePowerLimit
   */
  public ActivePowerLimit unAssignValue(UUID parentId) {
    ActivePowerLimit parentEntity = repository.findById(parentId).get();

    LOGGER.info("unAssigning Value on " + parentEntity.toString());

    // ------------------------------------------
    // null out the Value on the parent entithy
    // ------------------------------------------
    parentEntity.setValue(null);

    // ------------------------------------------
    // save the parent entity
    // ------------------------------------------
    repository.save(parentEntity);

    return parentEntity;
  }

  /**
   * Method to retrieve the ActivePowerLimit via an FindActivePowerLimitQuery
   *
   * @return query FindActivePowerLimitQuery
   */
  @SuppressWarnings("unused")
  public ActivePowerLimit find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a ActivePowerLimit - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all ActivePowerLimits
   *
   * @param query FindAllActivePowerLimitQuery
   * @return List<ActivePowerLimit>
   */
  @SuppressWarnings("unused")
  public List<ActivePowerLimit> findAll(FindAllActivePowerLimitQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all ActivePowerLimit - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final ActivePowerLimitRepository repository;

  @Autowired
  @Qualifier(value = "activePower-entity-projector")
  ActivePowerEntityProjector ActivePowerProjector;

  private static final Logger LOGGER =
      Logger.getLogger(ActivePowerLimitEntityProjector.class.getName());
}
