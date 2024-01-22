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
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.overexcitationlimiterdynamics.subscriber;

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
 * Subscriber for OverexcLimX1 related events. .
 *
 * @author your_name_here
 */
@Component("overexcLimX1-subscriber")
public class OverexcLimX1Subscriber extends BaseSubscriber {

  public OverexcLimX1Subscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<OverexcLimX1>, OverexcLimX1> overexcLimX1Subscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllOverexcLimX1Query(),
        ResponseTypes.multipleInstancesOf(OverexcLimX1.class),
        ResponseTypes.instanceOf(OverexcLimX1.class));
  }

  public SubscriptionQueryResult<OverexcLimX1, OverexcLimX1> overexcLimX1Subscribe(
      @DestinationVariable UUID overexcLimX1Id) {
    return queryGateway.subscriptionQuery(
        new FindOverexcLimX1Query(new LoadOverexcLimX1Filter(overexcLimX1Id)),
        ResponseTypes.instanceOf(OverexcLimX1.class),
        ResponseTypes.instanceOf(OverexcLimX1.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
