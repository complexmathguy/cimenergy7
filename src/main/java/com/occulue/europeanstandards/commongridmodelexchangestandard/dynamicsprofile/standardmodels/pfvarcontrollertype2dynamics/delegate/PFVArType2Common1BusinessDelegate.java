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
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.pfvarcontrollertype2dynamics.delegate;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.validator.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;

/**
 * PFVArType2Common1 business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of PFVArType2Common1 related services in the case of a
 *       PFVArType2Common1 business related service failing.
 *   <li>Exposes a simpler, uniform PFVArType2Common1 interface to the business tier, making it easy
 *       for clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill PFVArType2Common1 business
 *       related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class PFVArType2Common1BusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public PFVArType2Common1BusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * PFVArType2Common1 Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return PFVArType2Common1BusinessDelegate
   */
  public static PFVArType2Common1BusinessDelegate getPFVArType2Common1Instance() {
    return (new PFVArType2Common1BusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createPFVArType2Common1(CreatePFVArType2Common1Command command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getPFVArType2Common1Id() == null)
        command.setPFVArType2Common1Id(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      PFVArType2Common1Validator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreatePFVArType2Common1Command - by convention the future return value for a
      // create command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreatePFVArType2Common1Command of PFVArType2Common1 is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create PFVArType2Common1 - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdatePFVArType2Common1Command
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updatePFVArType2Common1(UpdatePFVArType2Common1Command command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      PFVArType2Common1Validator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdatePFVArType2Common1Command and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save PFVArType2Common1 - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeletePFVArType2Common1Command
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeletePFVArType2Common1Command command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      PFVArType2Common1Validator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeletePFVArType2Common1Command and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete PFVArType2Common1 using Id = " + command.getPFVArType2Common1Id();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the PFVArType2Common1 via PFVArType2Common1FetchOneSummary
   *
   * @param summary PFVArType2Common1FetchOneSummary
   * @return PFVArType2Common1FetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public PFVArType2Common1 getPFVArType2Common1(PFVArType2Common1FetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("PFVArType2Common1FetchOneSummary arg cannot be null");

    PFVArType2Common1 entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      PFVArType2Common1Validator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a PFVArType2Common1
      // --------------------------------------
      CompletableFuture<PFVArType2Common1> futureEntity =
          queryGateway.query(
              new FindPFVArType2Common1Query(
                  new LoadPFVArType2Common1Filter(summary.getPFVArType2Common1Id())),
              ResponseTypes.instanceOf(PFVArType2Common1.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate PFVArType2Common1 with id " + summary.getPFVArType2Common1Id();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all PFVArType2Common1s
   *
   * @return List<PFVArType2Common1>
   * @exception ProcessingException Thrown if any problems
   */
  public List<PFVArType2Common1> getAllPFVArType2Common1() throws ProcessingException {
    List<PFVArType2Common1> list = null;

    try {
      CompletableFuture<List<PFVArType2Common1>> futureList =
          queryGateway.query(
              new FindAllPFVArType2Common1Query(),
              ResponseTypes.multipleInstancesOf(PFVArType2Common1.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all PFVArType2Common1";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * assign J on PFVArType2Common1
   *
   * @param command AssignJToPFVArType2Common1Command
   * @exception ProcessingException
   */
  public void assignJ(AssignJToPFVArType2Common1Command command) throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getPFVArType2Common1Id());

    BooleanProxyBusinessDelegate childDelegate =
        BooleanProxyBusinessDelegate.getBooleanProxyInstance();
    PFVArType2Common1BusinessDelegate parentDelegate =
        PFVArType2Common1BusinessDelegate.getPFVArType2Common1Instance();
    UUID childId = command.getAssignment().getBooleanProxyId();
    BooleanProxy child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      PFVArType2Common1Validator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get BooleanProxy using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign J on PFVArType2Common1
   *
   * @param command UnAssignJFromPFVArType2Common1Command
   * @exception ProcessingException
   */
  public void unAssignJ(UnAssignJFromPFVArType2Common1Command command) throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      PFVArType2Common1Validator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign J on PFVArType2Common1";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * assign Ki on PFVArType2Common1
   *
   * @param command AssignKiToPFVArType2Common1Command
   * @exception ProcessingException
   */
  public void assignKi(AssignKiToPFVArType2Common1Command command) throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getPFVArType2Common1Id());

    PUBusinessDelegate childDelegate = PUBusinessDelegate.getPUInstance();
    PFVArType2Common1BusinessDelegate parentDelegate =
        PFVArType2Common1BusinessDelegate.getPFVArType2Common1Instance();
    UUID childId = command.getAssignment().getPUId();
    PU child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      PFVArType2Common1Validator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get PU using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign Ki on PFVArType2Common1
   *
   * @param command UnAssignKiFromPFVArType2Common1Command
   * @exception ProcessingException
   */
  public void unAssignKi(UnAssignKiFromPFVArType2Common1Command command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      PFVArType2Common1Validator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign Ki on PFVArType2Common1";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * assign Kp on PFVArType2Common1
   *
   * @param command AssignKpToPFVArType2Common1Command
   * @exception ProcessingException
   */
  public void assignKp(AssignKpToPFVArType2Common1Command command) throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getPFVArType2Common1Id());

    PUBusinessDelegate childDelegate = PUBusinessDelegate.getPUInstance();
    PFVArType2Common1BusinessDelegate parentDelegate =
        PFVArType2Common1BusinessDelegate.getPFVArType2Common1Instance();
    UUID childId = command.getAssignment().getPUId();
    PU child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      PFVArType2Common1Validator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get PU using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign Kp on PFVArType2Common1
   *
   * @param command UnAssignKpFromPFVArType2Common1Command
   * @exception ProcessingException
   */
  public void unAssignKp(UnAssignKpFromPFVArType2Common1Command command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      PFVArType2Common1Validator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign Kp on PFVArType2Common1";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * assign Max on PFVArType2Common1
   *
   * @param command AssignMaxToPFVArType2Common1Command
   * @exception ProcessingException
   */
  public void assignMax(AssignMaxToPFVArType2Common1Command command) throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getPFVArType2Common1Id());

    PUBusinessDelegate childDelegate = PUBusinessDelegate.getPUInstance();
    PFVArType2Common1BusinessDelegate parentDelegate =
        PFVArType2Common1BusinessDelegate.getPFVArType2Common1Instance();
    UUID childId = command.getAssignment().getPUId();
    PU child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      PFVArType2Common1Validator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get PU using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign Max on PFVArType2Common1
   *
   * @param command UnAssignMaxFromPFVArType2Common1Command
   * @exception ProcessingException
   */
  public void unAssignMax(UnAssignMaxFromPFVArType2Common1Command command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      PFVArType2Common1Validator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign Max on PFVArType2Common1";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * assign Ref on PFVArType2Common1
   *
   * @param command AssignRefToPFVArType2Common1Command
   * @exception ProcessingException
   */
  public void assignRef(AssignRefToPFVArType2Common1Command command) throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getPFVArType2Common1Id());

    PUBusinessDelegate childDelegate = PUBusinessDelegate.getPUInstance();
    PFVArType2Common1BusinessDelegate parentDelegate =
        PFVArType2Common1BusinessDelegate.getPFVArType2Common1Instance();
    UUID childId = command.getAssignment().getPUId();
    PU child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      PFVArType2Common1Validator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get PU using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign Ref on PFVArType2Common1
   *
   * @param command UnAssignRefFromPFVArType2Common1Command
   * @exception ProcessingException
   */
  public void unAssignRef(UnAssignRefFromPFVArType2Common1Command command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      PFVArType2Common1Validator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign Ref on PFVArType2Common1";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return PFVArType2Common1
   */
  private PFVArType2Common1 load(UUID id) throws ProcessingException {
    pFVArType2Common1 =
        PFVArType2Common1BusinessDelegate.getPFVArType2Common1Instance()
            .getPFVArType2Common1(new PFVArType2Common1FetchOneSummary(id));
    return pFVArType2Common1;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private PFVArType2Common1 pFVArType2Common1 = null;
  private static final Logger LOGGER =
      Logger.getLogger(PFVArType2Common1BusinessDelegate.class.getName());
}
