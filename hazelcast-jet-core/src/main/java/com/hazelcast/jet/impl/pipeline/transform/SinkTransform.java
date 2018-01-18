/*
 * Copyright (c) 2008-2018, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.jet.impl.pipeline.transform;

import com.hazelcast.jet.core.Processor;
import com.hazelcast.jet.core.ProcessorMetaSupplier;
import com.hazelcast.jet.core.ProcessorSupplier;
import com.hazelcast.jet.function.DistributedSupplier;
import com.hazelcast.jet.pipeline.Sink;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class SinkTransform<T> extends AbstractTransform implements Sink<T> {
    @Nonnull
    public final ProcessorMetaSupplier metaSupplier;
    @Nonnull
    private final String name;

    public SinkTransform(
            @Nonnull String name,
            @Nonnull ProcessorMetaSupplier metaSupplier
    ) {
        super(new ArrayList<>());
        this.metaSupplier = metaSupplier;
        this.name = name;
    }

    public SinkTransform(
            @Nonnull String name,
            @Nonnull ProcessorSupplier supplier
    ) {
        this(name, ProcessorMetaSupplier.of(supplier));
    }

    public SinkTransform(
            @Nonnull String name,
            @Nonnull DistributedSupplier<Processor> supplier
    ) {
        this(name, ProcessorMetaSupplier.of(supplier));
    }

    public SinkTransform<T> setUpstream(Transform upstream) {
        upstream().add(upstream);
        return this;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
