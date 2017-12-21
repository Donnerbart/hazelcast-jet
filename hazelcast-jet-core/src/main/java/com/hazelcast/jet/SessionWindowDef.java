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

package com.hazelcast.jet;

import javax.annotation.Nonnull;

import static com.hazelcast.jet.WindowDefinition.WindowKind.SESSION;

/**
 * Javadoc pending.
 */
public class SessionWindowDef<T> implements WindowDefinition {
    private final long sessionTimeout;

    SessionWindowDef(long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    @Nonnull @Override
    public WindowKind kind() {
        return SESSION;
    }

    @Nonnull @Override
    @SuppressWarnings("unchecked")
    public SessionWindowDef<T> downcast() {
        return this;
    }

    public long sessionTimeout() {
        return sessionTimeout;
    }
}
