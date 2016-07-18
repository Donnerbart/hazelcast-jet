/*
 * Copyright (c) 2008-2016, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.jet.impl.container.task;

import com.hazelcast.jet.counters.Accumulator;
import com.hazelcast.jet.executor.TaskContext;
import com.hazelcast.jet.impl.data.io.JetTupleDataType;
import com.hazelcast.jet.impl.job.JobContext;
import com.hazelcast.jet.io.DataType;
import com.hazelcast.jet.io.IOContext;
import com.hazelcast.jet.io.IOContextImpl;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DefaultTaskContext implements TaskContext {
    private final int taskCount;
    private final int taskNumber;
    private final IOContext ioContext;
    private final ConcurrentMap<String, Accumulator> accumulatorMap;

    public DefaultTaskContext(int taskCount,
                              int taskNumber,
                              JobContext jobContext) {
        this.taskCount = taskCount;
        this.taskNumber = taskNumber;
        this.ioContext = new IOContextImpl(JetTupleDataType.INSTANCE);
        this.accumulatorMap = new ConcurrentHashMap<>();
        jobContext.registerAccumulators(this.accumulatorMap);
    }

    @Override
    public int getTaskCount() {
        return taskCount;
    }

    @Override
    public int getTaskNumber() {
        return taskNumber;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <V, R extends Serializable> Accumulator<V, R> getAccumulator(String key) {
        return accumulatorMap.get(key);
    }

    @Override
    public <V, R extends Serializable> void setAccumulator(String key, Accumulator<V, R> accumulator) {
        accumulatorMap.put(key, accumulator);
    }

    @Override
    public void registerDataType(DataType dataType) {
        ioContext.registerDataType(dataType);
    }

    @Override
    public IOContext getIoContext() {
        return ioContext;
    }
}