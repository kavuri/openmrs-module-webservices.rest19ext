/**
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.webservices.rest19ext.web.v1_0.resource;

import org.openmrs.Visit;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResourceTest;
import org.openmrs.module.webservices.rest19ext.test.Rest19ExtTestConstants;

/**
 * Contains tests for the {@link VisitResource}
 */
public class VisitResourceTest extends BaseDelegatingResourceTest<VisitResource, Visit> {
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResourceTest#newObject()
	 */
	@Override
	public Visit newObject() {
		return Context.getVisitService().getVisitByUuid(getUuidProperty());
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResourceTest#validateDefaultRepresentation()
	 */
	@Override
	public void validateDefaultRepresentation() throws Exception {
		super.validateDefaultRepresentation();
		assertPropPresent("patient");
		assertPropPresent("visitType");
		assertPropPresent("indication");
		assertPropPresent("location");
		assertPropPresent("encounters");
		assertPropEquals("startDatetime", getObject().getStartDatetime());
		assertPropEquals("stopDatetime", getObject().getStopDatetime());
		assertPropPresent("attributes");
		assertPropEquals("voided", getObject().isVoided());
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResourceTest#validateFullRepresentation()
	 */
	@Override
	public void validateFullRepresentation() throws Exception {
		super.validateFullRepresentation();
		assertPropPresent("patient");
		assertPropPresent("visitType");
		assertPropPresent("indication");
		assertPropPresent("location");
		assertPropPresent("encounters");
		assertPropEquals("startDatetime", getObject().getStartDatetime());
		assertPropEquals("stopDatetime", getObject().getStopDatetime());
		assertPropPresent("attributes");
		assertPropEquals("voided", getObject().isVoided());
		assertPropPresent("auditInfo");
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResourceTest#getDisplayProperty()
	 */
	@Override
	public String getDisplayProperty() {
		return "Initial HIV Clinic Visit @ Unknown Location - 01/01/2005 00:00";
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResourceTest#getUuidProperty()
	 */
	@Override
	public String getUuidProperty() {
		return Rest19ExtTestConstants.VISIT_UUID;
	}
}
