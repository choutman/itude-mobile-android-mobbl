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
package com.itude.mobile.mobbl.core.controller.util.trace;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;

/**
 * StricMode wrapper class
 *
 */
public final class StrictModeWrapper
{

  static
  {
    try
    {
      Class.forName("android.os.StrictMode", true, Thread.currentThread().getContextClassLoader());
    }
    catch (Exception ex)
    {
      throw new RuntimeException(ex);
    }
  }

  private StrictModeWrapper()
  {
  }

  /**
   * Check if StricMode is available
   */
  public static void checkAvailable()
  {
  }

  @TargetApi(Build.VERSION_CODES.GINGERBREAD)
  public static void enableDefaults()
  {
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
  }
}
