/*
 * Copyright (c) 2008-2017, Hazelcast, Inc. All Rights Reserved.
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

import com.hazelcast.jet.pipeline.WindowDefinition;
import com.hazelcast.jet.aggregate.AggregateOperation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class CoAggregateTransform<A, R, OUT> extends AbstractTransform implements MultaryTransform<OUT> {
    @Nonnull
    public final AggregateOperation<A, ? extends R> aggrOp;
    @Nullable
    public final WindowDefinition wDef;

    public CoAggregateTransform(
            @Nonnull List<Transform> upstream,
            @Nonnull AggregateOperation<A, ? extends R> aggrOp,
            @Nullable WindowDefinition wDef
    ) {
        super(upstream);
        this.wDef = wDef;
        this.aggrOp = aggrOp;
    }

    public CoAggregateTransform(
            @Nonnull List<Transform> upstream,
            @Nonnull AggregateOperation<A, ? extends R> aggrOp
    ) {
        this(upstream, aggrOp, null);
    }

    @Override
    public String name() {
        return "co-aggregate";
    }

    @Override
    public String toString() {
        return name();
    }
}
