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

package org.jboss.snowdrop.springboot.jpa;

import java.net.MalformedURLException;
import java.net.URL;

import io.restassured.RestAssured;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;

/**
 * Class containing common test cases for unit and integration tests.
 *
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
public abstract class AbstractRecordsApplicationTest {

	@Test
	public void shouldGetInitialBands() throws MalformedURLException {
		RestAssured.when()
				.get(new URL(getBaseUrl(), "bands"))
				.then()
				.body("_embedded.bands.name", hasItems("AC/DC", "Iron Maiden"));
	}

	@Test
	public void shouldGetInitialRecords() throws MalformedURLException {
		RestAssured.when()
				.get(new URL(getBaseUrl(), "records"))
				.then()
				.body("_embedded.records.name", hasItems("High Voltage", "T.N.T.", "Fear of the Dark"));
	}

	protected abstract URL getBaseUrl() throws MalformedURLException;

}
