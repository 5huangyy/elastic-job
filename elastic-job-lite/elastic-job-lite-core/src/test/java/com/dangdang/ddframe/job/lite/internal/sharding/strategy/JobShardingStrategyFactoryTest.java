/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
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
 * </p>
 */

package com.dangdang.ddframe.job.lite.internal.sharding.strategy;

import com.dangdang.ddframe.job.api.exception.JobConfigurationException;
import com.dangdang.ddframe.job.lite.internal.sharding.strategy.fixture.InvalidJobShardingStrategy;
import com.dangdang.ddframe.job.lite.plugin.sharding.strategy.AverageAllocationJobShardingStrategy;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class JobShardingStrategyFactoryTest {
    
    @Test
    public void assertGetDefaultStrategy() {
        assertThat(JobShardingStrategyFactory.getStrategy(null), instanceOf(AverageAllocationJobShardingStrategy.class));
    }
    
    @Test(expected = JobConfigurationException.class)
    public void assertGetStrategyFailureWhenClassNotFound() {
        JobShardingStrategyFactory.getStrategy("NotClass");
    }
    
    @Test(expected = JobConfigurationException.class)
    public void assertGetStrategyFailureWhenNotStrategyClass() {
        JobShardingStrategyFactory.getStrategy(Object.class.getName());
    }
    
    @Test(expected = JobConfigurationException.class)
    public void assertGetStrategyFailureWhenStrategyClassInvalid() {
        JobShardingStrategyFactory.getStrategy(InvalidJobShardingStrategy.class.getName());
    }
    
    @Test
    public void assertGetStrategySuccess() {
        assertThat(JobShardingStrategyFactory.getStrategy(AverageAllocationJobShardingStrategy.class.getName()), instanceOf(AverageAllocationJobShardingStrategy.class));
    }
}
