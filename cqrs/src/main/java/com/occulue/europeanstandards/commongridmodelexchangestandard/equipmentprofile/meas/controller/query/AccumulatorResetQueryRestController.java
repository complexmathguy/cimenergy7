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
 * Implements Spring Controller query CQRS processing for entity AccumulatorReset.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/AccumulatorReset")
public class AccumulatorResetQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a AccumulatorReset using a UUID
   *
   * @param UUID accumulatorResetId
   * @return AccumulatorReset
   */
  @GetMapping("/load")
  public AccumulatorReset load(@RequestParam(required = true) UUID accumulatorResetId) {
    AccumulatorReset entity = null;

    try {
      entity =
          AccumulatorResetBusinessDelegate.getAccumulatorResetInstance()
              .getAccumulatorReset(new AccumulatorResetFetchOneSummary(accumulatorResetId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load AccumulatorReset using Id " + accumulatorResetId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all AccumulatorReset business objects
   *
   * @return Set<AccumulatorReset>
   */
  @GetMapping("/")
  public List<AccumulatorReset> loadAll() {
    List<AccumulatorReset> accumulatorResetList = null;

    try {
      // load the AccumulatorReset
      accumulatorResetList =
          AccumulatorResetBusinessDelegate.getAccumulatorResetInstance().getAllAccumulatorReset();

      if (accumulatorResetList != null)
        LOGGER.log(Level.INFO, "successfully loaded all AccumulatorResets");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all AccumulatorResets ", exc);
      return null;
    }

    return accumulatorResetList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected AccumulatorReset accumulatorReset = null;
  private static final Logger LOGGER =
      Logger.getLogger(AccumulatorResetQueryRestController.class.getName());
}
