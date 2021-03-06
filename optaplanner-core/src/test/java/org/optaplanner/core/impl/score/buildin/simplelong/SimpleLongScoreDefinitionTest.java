/*
 * Copyright 2015 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.core.impl.score.buildin.simplelong;

import org.junit.Test;
import org.optaplanner.core.api.score.buildin.simplelong.SimpleLongScore;
import org.optaplanner.core.config.score.trend.InitializingScoreTrendLevel;
import org.optaplanner.core.impl.score.buildin.simple.SimpleScoreDefinition;
import org.optaplanner.core.impl.score.trend.InitializingScoreTrend;

import static org.junit.Assert.*;

public class SimpleLongScoreDefinitionTest {

    @Test
    public void getLevelSize() {
        assertEquals(1, new SimpleLongScoreDefinition().getLevelsSize());
    }

    @Test
    public void getLevelLabels() {
        assertArrayEquals(new String[]{"score"}, new SimpleLongScoreDefinition().getLevelLabels());
    }

    @Test
    public void buildOptimisticBoundOnlyUp() {
        SimpleLongScoreDefinition scoreDefinition = new SimpleLongScoreDefinition();
        SimpleLongScore optimisticBound = scoreDefinition.buildOptimisticBound(
                InitializingScoreTrend.buildUniformTrend(InitializingScoreTrendLevel.ONLY_UP, 1),
                SimpleLongScore.valueOf(-1L));
        assertEquals(Long.MAX_VALUE, optimisticBound.getScore());
    }

    @Test
    public void buildOptimisticBoundOnlyDown() {
        SimpleLongScoreDefinition scoreDefinition = new SimpleLongScoreDefinition();
        SimpleLongScore optimisticBound = scoreDefinition.buildOptimisticBound(
                InitializingScoreTrend.buildUniformTrend(InitializingScoreTrendLevel.ONLY_DOWN, 1),
                SimpleLongScore.valueOf(-1L));
        assertEquals(-1L, optimisticBound.getScore());
    }

    @Test
    public void buildPessimisticBoundOnlyUp() {
        SimpleLongScoreDefinition scoreDefinition = new SimpleLongScoreDefinition();
        SimpleLongScore pessimisticBound = scoreDefinition.buildPessimisticBound(
                InitializingScoreTrend.buildUniformTrend(InitializingScoreTrendLevel.ONLY_UP, 1),
                SimpleLongScore.valueOf(-1L));
        assertEquals(-1L, pessimisticBound.getScore());
    }

    @Test
    public void buildPessimisticBoundOnlyDown() {
        SimpleLongScoreDefinition scoreDefinition = new SimpleLongScoreDefinition();
        SimpleLongScore pessimisticBound = scoreDefinition.buildPessimisticBound(
                InitializingScoreTrend.buildUniformTrend(InitializingScoreTrendLevel.ONLY_DOWN, 1),
                SimpleLongScore.valueOf(-1L));
        assertEquals(Long.MIN_VALUE, pessimisticBound.getScore());
    }

}
