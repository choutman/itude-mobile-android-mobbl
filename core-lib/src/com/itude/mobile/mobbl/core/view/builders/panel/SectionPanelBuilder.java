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
package com.itude.mobile.mobbl.core.view.builders.panel;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itude.mobile.mobbl.core.controller.MBApplicationController;
import com.itude.mobile.mobbl.core.view.MBPanel;
import com.itude.mobile.mobbl.core.view.builders.MBPanelViewBuilder.BuildState;

public class SectionPanelBuilder extends MBBasePanelBuilder
{

  @Override
  public ViewGroup buildPanel(MBPanel panel, BuildState buildState)
  {
    boolean hasTitle = false;
    Context context = MBApplicationController.getInstance().getBaseContext();

    LinearLayout panelView = new LinearLayout(context);
    panelView.setOrientation(LinearLayout.VERTICAL);

    if (panel.getTitle() != null)
    {
      // Show header at top of the section
      hasTitle = true;

      LinearLayout header = new LinearLayout(context);
      header.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
      header.setOrientation(LinearLayout.VERTICAL);

      TextView title = new TextView(header.getContext());
      title.setText(panel.getTitle());

      getStyleHandler().styleSectionHeaderText(title);
      getStyleHandler().styleSectionHeaderText(title, panel);

      header.addView(title);

      getStyleHandler().styleSectionHeader(header);
      getStyleHandler().styleSectionHeader(header, panel);

      panelView.addView(header);
    }

    buildChildren(panel.getChildren(), panelView);

    getStyleHandler().styleSectionContainer(panelView, hasTitle);
    getStyleHandler().styleSectionContainer(panelView, hasTitle, panel);

    return panelView;

  }

}
