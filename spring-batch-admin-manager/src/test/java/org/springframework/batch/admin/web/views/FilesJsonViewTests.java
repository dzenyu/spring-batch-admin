/*
 * Copyright 2009-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.batch.admin.web.views;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.admin.service.FileInfo;
import org.springframework.batch.admin.web.JsonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.WebApplicationContextLoader;
import org.springframework.web.servlet.View;

@ContextConfiguration(loader = WebApplicationContextLoader.class, inheritLocations = false, locations = "AbstractManagerViewTests-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FilesJsonViewTests extends AbstractManagerViewTests {

	private final HashMap<String, Object> model = new HashMap<String, Object>();

	@Autowired
	@Qualifier("files.json")
	private View view;

	@Test
	public void testListViewWithJobs() throws Exception {
		model.put("files", Arrays.asList(new FileInfo("foo"), new FileInfo("bar")));
		model.put("baseUrl", "http://localhost:8080/springsource");
		view.render(model, request, response);
		String content = response.getContentAsString();
		// System.err.println(content);
		JsonWrapper wrapper = new JsonWrapper(content);
		assertEquals(2, wrapper.get("files.uploaded", Map.class).size());
	}

}
