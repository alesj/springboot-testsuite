/*
 * Copyright 2016-2017 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.snowdrop.springboot.rest;

import java.net.MalformedURLException;
import java.net.URL;

import io.fabric8.kubernetes.assertions.Assertions;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.restassured.RestAssured;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.snowdrop.common.OpenShiftUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Simple Boot REST Test case.
 *
 * @author Obsidian Quickstarts
 */
@RunWith(Arquillian.class)
@RunAsClient
public class OpenShiftIT {

	private static final String ROUTE_NAME = "springboot-rest";

	@ArquillianResource
	KubernetesClient client;

	@Test
	public void testAppProvisionsRunningPods() throws Exception {
		Assertions.assertThat(this.client).deployments().pods().isPodReadyForPeriod();
	}

	@Test
	public void should_get_HelloWorld() throws Exception {
		System.out.println("Address : " + String.valueOf(getBaseUrl()));
		RestAssured.given().baseUri(String.valueOf(getBaseUrl())).
			when().get("/greeting").
			then().assertThat().body(Is.is(IsEqual.equalTo("{\"id\":1,\"content\":\"Hello, World!\"}")));

	}

	protected URL getBaseUrl() throws MalformedURLException {
		return OpenShiftUtils.getRouteUrl(this.client, ROUTE_NAME);
	}
}
