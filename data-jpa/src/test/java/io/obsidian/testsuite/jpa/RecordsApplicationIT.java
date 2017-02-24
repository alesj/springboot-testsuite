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

package io.obsidian.testsuite.jpa;

import java.net.MalformedURLException;
import java.net.URL;

import io.fabric8.kubernetes.assertions.Assertions;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.obsidian.testsuite.common.OpenShiftUtils;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * OpenShift integration test for records application.
 *
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
@RunAsClient
@RunWith(Arquillian.class)
public class RecordsApplicationIT extends AbstractRecordsApplicationTest {

	private static final String ROUTE_NAME = "springboot-data-jpa";

	@ArquillianResource
	private KubernetesClient kubernetesClient;

	@Test
	public void testAppProvisionsRunningPods() throws Exception {
		Assertions.assertThat(this.kubernetesClient).deployments().pods().isPodReadyForPeriod();
	}

	@Override
	protected URL getBaseUrl() throws MalformedURLException {
		return OpenShiftUtils.getRouteUrl(this.kubernetesClient, ROUTE_NAME);
	}

}
