/**
 * The contents of this file are subject to the OpenMRS Public License Version
 * 1.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * Copyright (C) OpenMRS, LLC. All Rights Reserved.
 */
package org.openmrs.module.webservices.rest19ext.web.v1_0.resource;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.openmrs.VisitAttributeType;
import org.openmrs.annotation.Handler;
import org.openmrs.api.VisitService;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

/**
 * Allows standard CRUD for the {@link VisitAttributeType} domain object
 */
@Resource("visitattributetype")
@Handler(supports = VisitAttributeType.class, order = 0)
public class VisitAttributeTypeResource extends BaseAttributeTypeCrudResource<VisitAttributeType> {
	
	private VisitService getService() {
		return Context.getVisitService();
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#newDelegate()
	 */
	@Override
	public VisitAttributeType newDelegate() {
		return new VisitAttributeType();
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#getByUniqueId(java.lang.String)
	 */
	@Override
	public VisitAttributeType getByUniqueId(String uniqueId) {
		return getService().getVisitAttributeTypeByUuid(uniqueId);
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource#doGetAll(org.openmrs.module.webservices.rest.web.RequestContext)
	 */
	@Override
	protected NeedsPaging<VisitAttributeType> doGetAll(RequestContext context) throws ResponseException {
		if (context.getIncludeAll())
			return new NeedsPaging<VisitAttributeType>(getService().getAllVisitAttributeTypes(), context);
		
		List<VisitAttributeType> vats = getService().getAllVisitAttributeTypes();
		for (Iterator<VisitAttributeType> iterator = vats.iterator(); iterator.hasNext();) {
			VisitAttributeType visitAttributeType = iterator.next();
			if (visitAttributeType.isRetired())
				iterator.remove();
		}
		return new NeedsPaging<VisitAttributeType>(vats, context);
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceHandler#save(java.lang.Object)
	 */
	@Override
	public VisitAttributeType save(VisitAttributeType delegate) {
		return getService().saveVisitAttributeType(delegate);
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#purge(java.lang.Object,
	 *      org.openmrs.module.webservices.rest.web.RequestContext)
	 */
	@Override
	public void purge(VisitAttributeType delegate, RequestContext context) throws ResponseException {
		getService().purgeVisitAttributeType(delegate);
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource#doSearch(java.lang.String,
	 *      org.openmrs.module.webservices.rest.web.RequestContext)
	 */
	@Override
	protected NeedsPaging<VisitAttributeType> doSearch(String query, RequestContext context) {
		// TODO: Should be a VisitAttributeType search method in VisitService
		List<VisitAttributeType> vats = getService().getAllVisitAttributeTypes();
		for (Iterator<VisitAttributeType> iterator = vats.iterator(); iterator.hasNext();) {
			VisitAttributeType visitAttributeType = iterator.next();
			//find matches excluding retired ones if necessary
			if (!Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE).matcher(visitAttributeType.getName())
			        .find()
			        || (!context.getIncludeAll() && visitAttributeType.isRetired())) {
				iterator.remove();
			}
		}
		return new NeedsPaging<VisitAttributeType>(vats, context);
	}
}
