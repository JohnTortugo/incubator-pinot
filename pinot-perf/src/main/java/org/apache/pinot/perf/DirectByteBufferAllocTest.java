/**
 * Licensed to the Apache Software Foundation (ASF) under one
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
package org.apache.pinot.perf;

import java.nio.ByteOrder;

import org.apache.pinot.core.segment.memory.PinotDataBuffer;

public class DirectByteBufferAllocTest {
  public static void main(String[] args) throws Exception {
    int sum = 0;

    // Run with something like: -Xms1G -Xmx1G -XX:+Use<PreferredGC>GC -XX:MaxDirectMemorySize=1G -XX:+DisableExplicitGC
    for (int i=0; i<100000000; i++) {
      PinotDataBuffer buffer = PinotDataBuffer.allocateDirect(1024 * 1024 * 10 /* 1 MB */, ByteOrder.nativeOrder(), null);
      sum += buffer.size();

      buffer.close();

      System.out.println("Allocated " + i + " buffers so far.");
    }

    System.out.println("Size of it is: " + sum);
  }
}
