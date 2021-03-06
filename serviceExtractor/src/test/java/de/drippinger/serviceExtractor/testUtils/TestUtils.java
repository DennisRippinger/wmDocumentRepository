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
package de.drippinger.serviceExtractor.testUtils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * TestUtils
 *
 * @author Dennis Rippinger
 */
public class TestUtils {


	public static final Document createNDFDocument(WMType type) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("Values");

		root.addElement("value")
			.addAttribute("name", "node_type")
			.addText(type.attribute());

		return document;
	}
}
