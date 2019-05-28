/*
 * Copyright © 2014-2017 EntIT Software LLC, a Micro Focus company (L.P.)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.cloudslang.worker.management.delegate;

import io.cloudslang.engine.queue.entities.ExecutionMessage;
import io.cloudslang.orchestrator.entities.Message;
import io.cloudslang.worker.management.services.InBuffer;
import io.cloudslang.worker.management.services.OutboundBuffer;
import org.apache.log4j.Logger;


public class ContinuationOutbufferInbufferRunnable implements Runnable {

    private static final Logger logger = Logger.getLogger(ContinuationOutbufferInbufferRunnable.class);

    private final OutboundBuffer outBuffer;
    private final Message[] outBufferMessages;

    private final InBuffer inBuffer;
    private final ExecutionMessage inbufferMessage;

    public ContinuationOutbufferInbufferRunnable(OutboundBuffer outBuffer,
            Message[] outBufferMessages,
            InBuffer inBuffer,
            ExecutionMessage inbufferMessage) {

        this.outBuffer = outBuffer;
        this.outBufferMessages = outBufferMessages;
        this.inBuffer = inBuffer;
        this.inbufferMessage = inbufferMessage;
    }

    @Override
    public void run() {
        try {
            // The order is important
            outBuffer.put(outBufferMessages);
            inBuffer.addExecutionMessage(inbufferMessage);
        } catch (InterruptedException interruptedEx) {
            logger.warn("Thread was interrupted! Exiting the execution... ", interruptedEx);
        }
    }

}