/*
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
 */

package com.teradata.presto.yarn.fulfillment

import com.facebook.presto.jdbc.internal.guava.collect.ImmutableSet
import com.teradata.tempto.Requirement
import com.teradata.tempto.context.State
import com.teradata.tempto.fulfillment.RequirementFulfiller
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.slider.funtest.framework.AgentCommandTestBase
import org.apache.slider.funtest.framework.CommandTestBase

import static com.teradata.presto.yarn.fulfillment.SliderClusterFulfiller.SliderClusterRequirement.SLIDER_CLUSTER

@RequirementFulfiller.AutoSuiteLevelFulfiller
@CompileStatic
@Slf4j
public class SliderClusterFulfiller
        implements RequirementFulfiller
{

  public static final String CLUSTER_NAME = 'presto_cluster'

  public static enum SliderClusterRequirement
          implements Requirement {
    SLIDER_CLUSTER;
  }

  @Override
  Set<State> fulfill(Set<Requirement> requirements)
  {
    if (requirements.contains(SLIDER_CLUSTER)) {
      log.info('fulfilling slider cluster')
      CommandTestBase.setupTestBase()
      AgentCommandTestBase.setupAgent()
      CommandTestBase.setupCluster(CLUSTER_NAME)
    }

    return ImmutableSet.of()
  }

  @Override
  void cleanup()
  {
  }
}
