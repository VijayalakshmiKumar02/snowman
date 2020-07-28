/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.entapp.snowman.application.schedule.service;

import com.mycompany.entapp.snowman.application.schedule.ReportingData;
import com.mycompany.entapp.snowman.domain.exception.BusinessException;

public interface ReportingService {
    ReportingData retrieveReportingData() throws BusinessException;
}
