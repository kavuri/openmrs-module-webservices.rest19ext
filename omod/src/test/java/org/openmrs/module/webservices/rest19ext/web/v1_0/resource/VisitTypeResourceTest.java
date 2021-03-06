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

import org.openmrs.VisitType;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResourceTest;
import org.openmrs.module.webservices.rest19ext.test.Rest19ExtTestConstants;
import org.openmrs.module.webservices.rest19ext.web.v1_0.resource.VisitTypeResource;

/**
 * Contains tests for the {@link VisitTypeResource}
 */
public class VisitTypeResourceTest extends BaseDelegatingResourceTest<VisitTypeResource, VisitType> {
	
	@Override
	public VisitType newObject() {
		return Context.getVisitService().getVisitTypeByUuid(getUuidProperty());
	}
	
	@Override
	public void validateDefaultRepresentation() throws Exception {
		super.validateDefaultRepresentation();
		assertPropEquals("name", getObject().getName());
		assertPropEquals("description", getObject().getDescription());
		assertPropEquals("retired", getObject().isRetired());
	}
	
	@Override
	public void validateFullRepresentation() throws Exception {
		super.validateFullRepresentation();
		assertPropEquals("name", getObject().getName());
		assertPropEquals("description", getObject().getDescription());
		assertPropEquals("retired", getObject().isRetired());
		assertPropPresent("auditInfo");
	}
	
	@Override
	public String getDisplayProperty() {
		return "Return TB Clinic Visit - Return TB Clinic Visit Description";
	}
	
	@Override
	public String getUuidProperty() {
		return Rest19ExtTestConstants.VISIT_TYPE_UUID;
	}
}
