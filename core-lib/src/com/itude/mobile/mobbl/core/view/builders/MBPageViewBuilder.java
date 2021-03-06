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
package com.itude.mobile.mobbl.core.view.builders;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;

import com.itude.mobile.mobbl.core.controller.MBApplicationController;
import com.itude.mobile.mobbl.core.util.Constants;
import com.itude.mobile.mobbl.core.view.MBPage;
import com.itude.mobile.mobbl.core.view.components.MBHeader;

public class MBPageViewBuilder extends MBViewBuilder
{

  public ViewGroup buildPageView(MBPage page, boolean buildWithContent)
  {
    boolean buildWithScrollView = page.isScrollable();

    Context context = MBApplicationController.getInstance().getBaseContext();
    MBStyleHandler styleHandler = getStyleHandler();

    /*
     * Our view that will contain our header and potentially the page content
     */
    LinearLayout main = new LinearLayout(context);
    main.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    main.setOrientation(LinearLayout.VERTICAL);

    /*
     * Add a styled header if we have got a title
     */
    if (page.getTitle() != null)
    {
      LinearLayout headerContainer = new LinearLayout(context);
      headerContainer.setOrientation(LinearLayout.VERTICAL);
      headerContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

      MBHeader header = new MBHeader(headerContainer.getContext());
      header.setTag(Constants.C_PAGE_CONTENT_HEADER_VIEW);
      header.getTitleView().setText(page.getTitle());
      styleHandler.stylePageHeader(header);
      styleHandler.stylePageHeaderTitle(header.getTitleView());
      headerContainer.addView(header);

      main.addView(headerContainer);
    }

    /*
     * Create the content of this page if we want to
     */
    LinearLayout view = null;
    if (buildWithContent)
    {
      view = new LinearLayout(context);
      view.setOrientation(LinearLayout.VERTICAL);
      view.setScrollContainer(true);
      view.setFadingEdgeLength(0);
      view.setVerticalFadingEdgeEnabled(false);

      buildChildren(page.getChildren(), view);

      styleHandler.applyStyle(page, view);
    }

    /*
     * If we want to have a scrollview we will create one and add our content to it
     * If we don't want a scrollview but do want content we will add our content directly to our main view
     */
    if (buildWithScrollView)
    {
      ScrollView scrollView = new ScrollView(context);
      scrollView.setTag(Constants.C_PAGE_CONTENT_VIEW);
      scrollView.setFadingEdgeLength(0);
      scrollView.setVerticalFadingEdgeEnabled(false);
      styleHandler.styleMainScrollbarView(page, scrollView);

      if (buildWithContent)
      {
        scrollView.addView(view);
      }

      main.addView(scrollView);
    }
    else if (buildWithContent && view != null)
    {
      styleHandler.styleMainScrollbarView(page, view);
      main.addView(view);
    }

    main.setFadingEdgeLength(0);
    main.setVerticalFadingEdgeEnabled(false);

    return main;
  }

  public ViewGroup buildPageView(MBPage page)
  {
    return buildPageView(page, true);
  }

  public ViewGroup buildPageViewWithoutContent(MBPage page)
  {
    return buildPageView(page, false);
  }

}
