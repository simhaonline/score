/*
 * Licensed to Hewlett-Packard Development Company, L.P. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
*/
package org.eclipse.score.samples.openstack.actions;


import com.sun.mail.smtp.SMTPMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.assertFalse;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.mockito.Matchers.anyObject;

import javax.mail.Session;
import javax.mail.Transport;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Date: 8/20/2014
 *
 * @author lesant
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Transport.class, Session.class, SMTPMessage.class, SimpleSendEmail.class })
public class SimpleSendEmailTest {
	private static final long DEFAULT_TIMEOUT = 5000;


	@Test(timeout = DEFAULT_TIMEOUT)
	public void testExecuteWithMissingInputs() throws Exception {
		SimpleSendEmail simpleSendEmail = new SimpleSendEmail();

		PowerMockito.mockStatic(Transport.class);
		doNothing().when(Transport.class, "send", anyObject());

		Map<String, String> returnResult = simpleSendEmail.execute(null, "25", "xjavatestx@gmail.com", "xjavatestx@gmail.com", "subject", "body");
		assertEquals("Result not as expected", "1", returnResult.get("returnCode"));

		returnResult = simpleSendEmail.execute("smtp3.hp.com", "", "xjavatestx@gmail.com", "xjavatestx@gmail.com", "subject", "body");
		assertEquals("Result not as expected", "1", returnResult.get("returnCode"));

		returnResult = simpleSendEmail.execute("smtp3.hp.com", "25", "xjavatestx@gmail.com", "xjavatestx@gmail.com", "subject", "body");
		assertEquals("Result not as expected", "0", returnResult.get("returnCode"));

		returnResult = simpleSendEmail.execute("smtp3.hp.com", "25", "xjavatestx@gmail.com", "xjavatestx@gmail.com", "", "");
		assertEquals("Result not as expected", "0", returnResult.get("returnCode"));

		returnResult = simpleSendEmail.execute("smtp3.hp.com", "25", "xjavatestx@gmail.com", "xjavatestx@gmail.com", "", null);
		assertEquals("Result not as expected", "1", returnResult.get("returnCode"));

		returnResult = simpleSendEmail.execute("smtp3.hp.com", "25", "", "xjavatestx@gmail.com", "", null);
		assertEquals("Result not as expected", "1", returnResult.get("returnCode"));

		returnResult = simpleSendEmail.execute("smtp3.hp.com", "25", "malformedEmail", "xjavatestx@gmail.com", "", null);
		assertEquals("Result not as expected", "1", returnResult.get("returnCode"));


	}
}