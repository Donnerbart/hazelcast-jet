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

import java.io.Serializable;
import java.util.List;

/**
 * This is a pure data object and holds no implementation code for the
 * transformation it represents. {@link com.hazelcast.jet.impl.Planner
 * Planner} is the implementation class that creates a Core API DAG for a
 * pipeline.
 */
public interface Transform extends Serializable {
    /**
     * Returns the name of this transformation.
     */
    String name();

    List<? extends Transform> upstream();
}
