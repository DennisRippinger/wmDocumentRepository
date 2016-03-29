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

import de.drippinger.serviceExtractor.exception.ExtractorException;
import lombok.NonNull;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * XmlUtil
 *
 * @author Dennis Rippinger
 */
public class XmlUtil {

	/**
	 * Parse an XML file via dom4j. Can throw a runtime exception.
	 *
	 * @param xmlFile the xml file.
	 * @return A dom4j document.
	 */
	public static Document parse(@NonNull File xmlFile) throws ExtractorException {
		try {
			SAXReader reader = new SAXReader();

			return reader.read(xmlFile);
		} catch (DocumentException e) {
			String reason = String.format("Unable to parse file '%s'", xmlFile.getAbsolutePath());
			throw new ExtractorException(reason, e);
		}

	}


}
