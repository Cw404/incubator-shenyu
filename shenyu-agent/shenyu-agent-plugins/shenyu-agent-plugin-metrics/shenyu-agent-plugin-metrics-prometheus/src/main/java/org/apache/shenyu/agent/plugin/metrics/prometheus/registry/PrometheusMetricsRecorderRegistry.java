/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.agent.plugin.metrics.prometheus.registry;

import org.apache.shenyu.agent.plugin.metrics.api.MetricsRecorder;
import org.apache.shenyu.agent.plugin.metrics.api.MetricsRecorderRegistry;
import org.apache.shenyu.agent.api.config.MetricsConfig.Metric;
import org.apache.shenyu.agent.plugin.metrics.api.constant.MetricsConstant;
import org.apache.shenyu.agent.plugin.metrics.prometheus.recorder.MetricsRecorderFactory;
import org.apache.shenyu.spi.Join;

import java.util.Optional;

/**
 * The type Prometheus metrics recorder registry.
 */
@Join
public class PrometheusMetricsRecorderRegistry implements MetricsRecorderRegistry {
    
    @Override
    public Optional<MetricsRecorder> register(final Metric metric) {
        return MetricsRecorderFactory.newInstance(metric);
    }
    
    @Override
    public String named() {
        return MetricsConstant.PROMETHEUS;
    }
}
