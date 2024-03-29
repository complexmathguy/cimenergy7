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
package com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile.controller.command;

import com.occulue.api.*;
import com.occulue.command.*;
import com.occulue.controller.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Implements Spring Controller command CQRS processing for entity CurrentFlow.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/CurrentFlow")
public class CurrentFlowCommandRestController extends BaseSpringRestController {

  /**
   * Handles create a CurrentFlow. if not key provided, calls create, otherwise calls save
   *
   * @param CurrentFlow currentFlow
   * @return CompletableFuture<UUID>
   */
  @PostMapping("/create")
  public CompletableFuture<UUID> create(
      @RequestBody(required = true) CreateCurrentFlowCommand command) {
    CompletableFuture<UUID> completableFuture = null;
    try {

      completableFuture =
          CurrentFlowBusinessDelegate.getCurrentFlowInstance().createCurrentFlow(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage(), exc);
    }

    return completableFuture;
  }

  /**
   * Handles updating a CurrentFlow. if no key provided, calls create, otherwise calls save
   *
   * @param CurrentFlow currentFlow
   * @return CompletableFuture<Void>
   */
  @PutMapping("/update")
  public CompletableFuture<Void> update(
      @RequestBody(required = true) UpdateCurrentFlowCommand command) {
    CompletableFuture<Void> completableFuture = null;
    try {
      // -----------------------------------------------
      // delegate the UpdateCurrentFlowCommand
      // -----------------------------------------------
      completableFuture =
          CurrentFlowBusinessDelegate.getCurrentFlowInstance().updateCurrentFlow(command);
      ;
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "CurrentFlowController:update() - successfully update CurrentFlow - " + exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * Handles deleting a CurrentFlow entity
   *
   * @param command ${class.getDeleteCommandAlias()}
   * @return CompletableFuture<Void>
   */
  @DeleteMapping("/delete")
  public CompletableFuture<Void> delete(@RequestParam(required = true) UUID currentFlowId) {
    CompletableFuture<Void> completableFuture = null;
    DeleteCurrentFlowCommand command = new DeleteCurrentFlowCommand(currentFlowId);

    try {
      CurrentFlowBusinessDelegate delegate = CurrentFlowBusinessDelegate.getCurrentFlowInstance();

      completableFuture = delegate.delete(command);
      LOGGER.log(
          Level.WARNING, "Successfully deleted CurrentFlow with key " + command.getCurrentFlowId());
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * save Value on CurrentFlow
   *
   * @param command AssignValueToCurrentFlowCommand
   */
  @PutMapping("/assignValue")
  public void assignValue(@RequestBody AssignValueToCurrentFlowCommand command) {
    try {
      CurrentFlowBusinessDelegate.getCurrentFlowInstance().assignValue(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "Failed to assign Value", exc);
    }
  }

  /**
   * unassign Value on CurrentFlow
   *
   * @param command UnAssignValueFromCurrentFlowCommand
   */
  @PutMapping("/unAssignValue")
  public void unAssignValue(
      @RequestBody(required = true) UnAssignValueFromCurrentFlowCommand command) {
    try {
      CurrentFlowBusinessDelegate.getCurrentFlowInstance().unAssignValue(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to unassign Value", exc);
    }
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected CurrentFlow currentFlow = null;
  private static final Logger LOGGER =
      Logger.getLogger(CurrentFlowCommandRestController.class.getName());
}
