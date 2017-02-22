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

package org.jboss.test.springboot.rest;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import org.jboss.arquillian.ce.adapter.OpenShiftAdapter;
import org.jboss.arquillian.ce.api.OpenShiftHandle;
import org.jboss.arquillian.ce.utils.Operator;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Simple smoke test.
 *
 * @author Ales Justin
 */
@RunWith(Arquillian.class)
@RunAsClient
public class SmokeIT {

	private final Map<String, String> labels = Collections.singletonMap("project", "springboot-rest");

	@ArquillianResource
	OpenShiftHandle handle;

//    @ArquillianResource
//    OpenShiftClient client;

	private void assertResponse(InputStream responseStream) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			int b;
			while ((b = responseStream.read()) != -1) {
				baos.write(b);
			}
		}
		finally {
			responseStream.close();
		}
		String content = baos.toString();
		System.out.println("Response: >>" + content + "<<");
		Assert.assertTrue("Bad response: " + content, content.contains("SmokeIT") && content.contains("Hello"));
	}

	@Test
	@InSequence(1)
	public void delay() throws Exception {
		OpenShiftAdapter.class.cast(this.handle).delay(this.labels, 1, Operator.EQUAL);
	}

	@Test
	@InSequence(2)
	public void testPodsGreetingEndpoint() throws Exception {
		InputStream responseStream = this.handle.execute(this.labels, 0, 8080, "/greeting?name=SmokeIT");
		assertResponse(responseStream);
	}

	@Test
	@InSequence(3)
	public void testPublicGreetingEndpoint() throws Exception {
//        Route route = client.routes().get();
//        System.out.println(route);
//        RouteSpec spec = route.getSpec();
//        URL url = new URL("http", spec.getHost(), spec.getPort().getTargetPort().getIntVal(), "/greeting?name=SmokeIT");
//        assertResponse(url.openStream());
	}

}
