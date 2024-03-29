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
 * Projector for AngleRadians as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by AngleRadiansAggregate
 *
 * @author your_name_here
 */
@Component("angleRadians-entity-projector")
public class AngleRadiansEntityProjector {

  // core constructor
  public AngleRadiansEntityProjector(AngleRadiansRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a AngleRadians
   *
   * @param	entity AngleRadians
   */
  public AngleRadians create(AngleRadians entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a AngleRadians
   *
   * @param	entity AngleRadians
   */
  public AngleRadians update(AngleRadians entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a AngleRadians
   *
   * @param	id		UUID
   */
  public AngleRadians delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    AngleRadians entity = repository.findById(id).get();

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
   * @param	assignment 	FloatProxy
   * @return	AngleRadians
   */
  public AngleRadians assignValue(UUID parentId, FloatProxy assignment) {
    LOGGER.info("assigning Value as " + assignment.toString());

    AngleRadians parentEntity = repository.findById(parentId).get();
    assignment = FloatProxyProjector.find(assignment.getFloatProxyId());

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
   * @return	AngleRadians
   */
  public AngleRadians unAssignValue(UUID parentId) {
    AngleRadians parentEntity = repository.findById(parentId).get();

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
   * Method to retrieve the AngleRadians via an FindAngleRadiansQuery
   *
   * @return query FindAngleRadiansQuery
   */
  @SuppressWarnings("unused")
  public AngleRadians find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a AngleRadians - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all AngleRadianss
   *
   * @param query FindAllAngleRadiansQuery
   * @return List<AngleRadians>
   */
  @SuppressWarnings("unused")
  public List<AngleRadians> findAll(FindAllAngleRadiansQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all AngleRadians - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final AngleRadiansRepository repository;

  @Autowired
  @Qualifier(value = "floatProxy-entity-projector")
  FloatProxyEntityProjector FloatProxyProjector;

  private static final Logger LOGGER =
      Logger.getLogger(AngleRadiansEntityProjector.class.getName());
}
