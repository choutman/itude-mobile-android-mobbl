/*
 * (C) Copyright Itude Mobile B.V., The Netherlands
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.itude.mobile.mobbl.core.controller.util.indicator;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;

import com.itude.mobile.mobbl.core.util.MBCustomAttributeContainer;

/**
 * {@link MBCountingIndicator} class defines an indeterminate progress indicator
 */
public final class MBIndeterminateProgressIndicator extends MBCountingIndicator
{
  /**
   * Default private constructor
   */
  MBIndeterminateProgressIndicator()
  {

  }

  /**
   * @see com.itude.mobile.mobbl.core.controller.util.indicator.MBCountingIndicator#show(android.app.Activity, com.itude.mobile.mobbl.core.util.MBCustomAttributeContainer)
   */
  @Override
  protected void show(final Activity activity, MBCustomAttributeContainer customAttributes)
  {
    if (activity instanceof ActionBarActivity)
    {
      ((ActionBarActivity) activity).setSupportProgressBarIndeterminate(true);
      ((ActionBarActivity) activity).setSupportProgressBarIndeterminateVisibility(true);

    }
    else
    {
      activity.setProgressBarIndeterminate(true);
      activity.setProgressBarIndeterminateVisibility(true);
    }
  }

  /**
   * @see com.itude.mobile.mobbl.core.controller.util.indicator.MBCountingIndicator#dismiss(android.app.Activity)
   */
  @Override
  protected void dismiss(final Activity activity)
  {
    if (activity instanceof ActionBarActivity)
    {
      ((ActionBarActivity) activity).setSupportProgressBarIndeterminateVisibility(false);

    }
    else activity.setProgressBarIndeterminateVisibility(false);
  }
}
