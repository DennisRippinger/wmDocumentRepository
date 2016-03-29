/**
 * Copyright (C) 2016 Dennis Rippinger (dennis.rippinger@msg-systems.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.drippinger.serviceExtractor.util;

import de.drippinger.serviceExtractor.testUtils.TestUtils;
import de.drippinger.serviceExtractor.testUtils.WMType;
import org.dom4j.Document;
import org.dom4j.io.XMLWriter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * XmlUtil Tester.
 *
 * @author Dennis Rippinger
 */
public class XmlUtilTest {

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	private File file;

	@Before
	public void createXMLFile() throws IOException {
		Document document = TestUtils.createNDFDocument(WMType.FLOW);
		file = testFolder.newFile("test.xml");

		XMLWriter writer = new XMLWriter(new FileWriter(file));
		writer.write(document);
		writer.close();
	}

	@Test
	public void testParseXmlFile() throws Exception {
		Document document = XmlUtil.parse(file);

		assertThat(document).isNotNull();
	}

}
