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

package com.hazelcast.jet.cascading.planner.rule.tez.annotator;

import cascading.flow.planner.iso.ElementAnnotation;
import cascading.flow.planner.iso.expression.ElementCapture;
import cascading.flow.planner.rule.PlanPhase;
import cascading.flow.planner.rule.RuleExpression;
import cascading.flow.planner.rule.expressiongraph.NoGroupJoinMergeBoundaryTapExpressionGraph;
import cascading.flow.planner.rule.transformer.RuleAnnotationTransformer;
import cascading.flow.stream.annotations.StreamMode;
import com.hazelcast.jet.cascading.planner.rule.tez.expressiongraph.AccumulatedSourceExpressionGraph;

/**
 *
 */
public class AccumulatedPostNodeAnnotator extends RuleAnnotationTransformer {
    public AccumulatedPostNodeAnnotator() {
        super(
                PlanPhase.PostNodes,

                new RuleExpression(
                        new NoGroupJoinMergeBoundaryTapExpressionGraph(),
                        new AccumulatedSourceExpressionGraph()
                ),

                // not marked accumulated unless all paths are blocking
                new ElementAnnotation(ElementCapture.Secondary, StreamMode.Accumulated)
        );
    }
}