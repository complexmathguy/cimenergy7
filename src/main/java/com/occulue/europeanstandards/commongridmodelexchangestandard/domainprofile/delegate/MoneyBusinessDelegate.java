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
package com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile.delegate;

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
 * Money business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of Money related services in the case of a Money business related
 *       service failing.
 *   <li>Exposes a simpler, uniform Money interface to the business tier, making it easy for clients
 *       to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill Money business related
 *       services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class MoneyBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public MoneyBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * Money Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return MoneyBusinessDelegate
   */
  public static MoneyBusinessDelegate getMoneyInstance() {
    return (new MoneyBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createMoney(CreateMoneyCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getMoneyId() == null) command.setMoneyId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      MoneyValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateMoneyCommand - by convention the future return value for a create command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO, "return from Command Gateway for CreateMoneyCommand of Money is " + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create Money - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateMoneyCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateMoney(UpdateMoneyCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      MoneyValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateMoneyCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save Money - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteMoneyCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteMoneyCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      MoneyValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteMoneyCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to delete Money using Id = " + command.getMoneyId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the Money via MoneyFetchOneSummary
   *
   * @param summary MoneyFetchOneSummary
   * @return MoneyFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public Money getMoney(MoneyFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("MoneyFetchOneSummary arg cannot be null");

    Money entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      MoneyValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a Money
      // --------------------------------------
      CompletableFuture<Money> futureEntity =
          queryGateway.query(
              new FindMoneyQuery(new LoadMoneyFilter(summary.getMoneyId())),
              ResponseTypes.instanceOf(Money.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg = "Unable to locate Money with id " + summary.getMoneyId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all Moneys
   *
   * @return List<Money>
   * @exception ProcessingException Thrown if any problems
   */
  public List<Money> getAllMoney() throws ProcessingException {
    List<Money> list = null;

    try {
      CompletableFuture<List<Money>> futureList =
          queryGateway.query(
              new FindAllMoneyQuery(), ResponseTypes.multipleInstancesOf(Money.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all Money";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * assign Value on Money
   *
   * @param command AssignValueToMoneyCommand
   * @exception ProcessingException
   */
  public void assignValue(AssignValueToMoneyCommand command) throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getMoneyId());

    DecimalProxyBusinessDelegate childDelegate =
        DecimalProxyBusinessDelegate.getDecimalProxyInstance();
    MoneyBusinessDelegate parentDelegate = MoneyBusinessDelegate.getMoneyInstance();
    UUID childId = command.getAssignment().getDecimalProxyId();
    DecimalProxy child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      MoneyValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get DecimalProxy using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign Value on Money
   *
   * @param command UnAssignValueFromMoneyCommand
   * @exception ProcessingException
   */
  public void unAssignValue(UnAssignValueFromMoneyCommand command) throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      MoneyValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign Value on Money";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return Money
   */
  private Money load(UUID id) throws ProcessingException {
    money = MoneyBusinessDelegate.getMoneyInstance().getMoney(new MoneyFetchOneSummary(id));
    return money;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private Money money = null;
  private static final Logger LOGGER = Logger.getLogger(MoneyBusinessDelegate.class.getName());
}
