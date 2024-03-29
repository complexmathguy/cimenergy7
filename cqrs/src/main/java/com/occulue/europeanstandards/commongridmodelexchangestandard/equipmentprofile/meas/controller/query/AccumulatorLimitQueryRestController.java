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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.meas.controller.query;

import com.occulue.api.*;
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
 * Implements Spring Controller query CQRS processing for entity AccumulatorLimit.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/AccumulatorLimit")
public class AccumulatorLimitQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a AccumulatorLimit using a UUID
   *
   * @param UUID accumulatorLimitId
   * @return AccumulatorLimit
   */
  @GetMapping("/load")
  public AccumulatorLimit load(@RequestParam(required = true) UUID accumulatorLimitId) {
    AccumulatorLimit entity = null;

    try {
      entity =
          AccumulatorLimitBusinessDelegate.getAccumulatorLimitInstance()
              .getAccumulatorLimit(new AccumulatorLimitFetchOneSummary(accumulatorLimitId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load AccumulatorLimit using Id " + accumulatorLimitId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all AccumulatorLimit business objects
   *
   * @return Set<AccumulatorLimit>
   */
  @GetMapping("/")
  public List<AccumulatorLimit> loadAll() {
    List<AccumulatorLimit> accumulatorLimitList = null;

    try {
      // load the AccumulatorLimit
      accumulatorLimitList =
          AccumulatorLimitBusinessDelegate.getAccumulatorLimitInstance().getAllAccumulatorLimit();

      if (accumulatorLimitList != null)
        LOGGER.log(Level.INFO, "successfully loaded all AccumulatorLimits");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all AccumulatorLimits ", exc);
      return null;
    }

    return accumulatorLimitList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected AccumulatorLimit accumulatorLimit = null;
  private static final Logger LOGGER =
      Logger.getLogger(AccumulatorLimitQueryRestController.class.getName());
}
