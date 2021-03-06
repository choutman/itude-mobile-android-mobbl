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
package com.itude.mobile.mobbl.core.util.resources;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import com.itude.mobile.mobbl.core.configuration.resources.exceptions.MBInvalidItemException;
import com.itude.mobile.mobbl.core.services.MBResourceService;
import com.itude.mobile.mobbl.core.view.MBItem;
import com.itude.mobile.mobbl.core.view.MBResource;

public abstract class MBAbstractStatedResourceBuilder implements MBResourceBuilder.Builder<Drawable>
{
  protected void validateItemInStatedResource(MBResource resource) throws MBInvalidItemException
  {
  }

  protected void processItem(StateListDrawable stateListDrawable, MBItem item, int[] itemStates)
  {
    String itemResource = item.getResource();
    Drawable drawable = MBResourceService.getInstance().getImageByID(itemResource);

    stateListDrawable.addState(itemStates, drawable);
  }
}
