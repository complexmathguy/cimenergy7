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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.controlarea.subscriber;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.subscriber.*;
import java.util.*;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Component;

/**
 * Subscriber for ControlAreaGeneratingUnit related events. .
 *
 * @author your_name_here
 */
@Component("controlAreaGeneratingUnit-subscriber")
public class ControlAreaGeneratingUnitSubscriber extends BaseSubscriber {

  public ControlAreaGeneratingUnitSubscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<ControlAreaGeneratingUnit>, ControlAreaGeneratingUnit>
      controlAreaGeneratingUnitSubscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllControlAreaGeneratingUnitQuery(),
        ResponseTypes.multipleInstancesOf(ControlAreaGeneratingUnit.class),
        ResponseTypes.instanceOf(ControlAreaGeneratingUnit.class));
  }

  public SubscriptionQueryResult<ControlAreaGeneratingUnit, ControlAreaGeneratingUnit>
      controlAreaGeneratingUnitSubscribe(@DestinationVariable UUID controlAreaGeneratingUnitId) {
    return queryGateway.subscriptionQuery(
        new FindControlAreaGeneratingUnitQuery(
            new LoadControlAreaGeneratingUnitFilter(controlAreaGeneratingUnitId)),
        ResponseTypes.instanceOf(ControlAreaGeneratingUnit.class),
        ResponseTypes.instanceOf(ControlAreaGeneratingUnit.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
