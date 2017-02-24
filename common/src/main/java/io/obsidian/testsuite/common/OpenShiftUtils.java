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

package io.obsidian.testsuite.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.openshift.api.model.Route;
import io.fabric8.openshift.client.OpenShiftClient;

/**
 * Utilities class with helper method to work with OpenShift.
 *
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
public final class OpenShiftUtils {

	private OpenShiftUtils() {
	}

	public static Route getRoute(KubernetesClient kubernetesClient, String name) {
		return kubernetesClient.adapt(OpenShiftClient.class).routes().withName(name).get();
	}

	public static URL getRouteUrl(KubernetesClient kubernetesClient, String name) {
		Route route = getRoute(kubernetesClient, name);
		try {
			return new URL("http://" + Objects.requireNonNull(route).getSpec().getHost());
		}
		catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
