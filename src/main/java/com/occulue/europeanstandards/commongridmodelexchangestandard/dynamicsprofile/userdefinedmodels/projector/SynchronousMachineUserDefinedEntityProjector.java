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
 * Projector for SynchronousMachineUserDefined as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by SynchronousMachineUserDefinedAggregate
 *
 * @author your_name_here
 */
@Component("synchronousMachineUserDefined-entity-projector")
public class SynchronousMachineUserDefinedEntityProjector {

  // core constructor
  public SynchronousMachineUserDefinedEntityProjector(
      SynchronousMachineUserDefinedRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a SynchronousMachineUserDefined
   *
   * @param	entity SynchronousMachineUserDefined
   */
  public SynchronousMachineUserDefined create(SynchronousMachineUserDefined entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a SynchronousMachineUserDefined
   *
   * @param	entity SynchronousMachineUserDefined
   */
  public SynchronousMachineUserDefined update(SynchronousMachineUserDefined entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a SynchronousMachineUserDefined
   *
   * @param	id		UUID
   */
  public SynchronousMachineUserDefined delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    SynchronousMachineUserDefined entity = repository.findById(id).get();

    // ------------------------------------------
    // delete what is discovered
    // ------------------------------------------
    repository.delete(entity);

    return entity;
  }

  /*
   * Assign a Proprietary
   *
   * @param	parentId	UUID
   * @param	assignment 	BooleanProxy
   * @return	SynchronousMachineUserDefined
   */
  public SynchronousMachineUserDefined assignProprietary(UUID parentId, BooleanProxy assignment) {
    LOGGER.info("assigning Proprietary as " + assignment.toString());

    SynchronousMachineUserDefined parentEntity = repository.findById(parentId).get();
    assignment = BooleanProxyProjector.find(assignment.getBooleanProxyId());

    // ------------------------------------------
    // assign the Proprietary to the parent entity
    // ------------------------------------------
    parentEntity.setProprietary(assignment);

    // ------------------------------------------
    // save the parent entity
    // ------------------------------------------
    repository.save(parentEntity);

    return parentEntity;
  }

  /*
   * Unassign the Proprietary
   *
   * @param	parentId		UUID
   * @return	SynchronousMachineUserDefined
   */
  public SynchronousMachineUserDefined unAssignProprietary(UUID parentId) {
    SynchronousMachineUserDefined parentEntity = repository.findById(parentId).get();

    LOGGER.info("unAssigning Proprietary on " + parentEntity.toString());

    // ------------------------------------------
    // null out the Proprietary on the parent entithy
    // ------------------------------------------
    parentEntity.setProprietary(null);

    // ------------------------------------------
    // save the parent entity
    // ------------------------------------------
    repository.save(parentEntity);

    return parentEntity;
  }

  /**
   * Method to retrieve the SynchronousMachineUserDefined via an
   * FindSynchronousMachineUserDefinedQuery
   *
   * @return query FindSynchronousMachineUserDefinedQuery
   */
  @SuppressWarnings("unused")
  public SynchronousMachineUserDefined find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(
          Level.WARNING, "Failed to find a SynchronousMachineUserDefined - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all SynchronousMachineUserDefineds
   *
   * @param query FindAllSynchronousMachineUserDefinedQuery
   * @return List<SynchronousMachineUserDefined>
   */
  @SuppressWarnings("unused")
  public List<SynchronousMachineUserDefined> findAll(
      FindAllSynchronousMachineUserDefinedQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(
          Level.WARNING,
          "Failed to find all SynchronousMachineUserDefined - {0}",
          exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final SynchronousMachineUserDefinedRepository repository;

  @Autowired
  @Qualifier(value = "booleanProxy-entity-projector")
  BooleanProxyEntityProjector BooleanProxyProjector;

  private static final Logger LOGGER =
      Logger.getLogger(SynchronousMachineUserDefinedEntityProjector.class.getName());
}
