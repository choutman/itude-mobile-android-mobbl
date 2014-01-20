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
package com.itude.mobile.mobbl.core.controller;

import static com.itude.mobile.android.util.ComparisonUtil.coalesce;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.itude.mobile.mobbl.core.configuration.mvc.MBConfigurationDefinition;
import com.itude.mobile.mobbl.core.configuration.mvc.MBOutcomeDefinition;
import com.itude.mobile.mobbl.core.controller.exceptions.MBExpressionNotBooleanException;
import com.itude.mobile.mobbl.core.model.MBDocument;
import com.itude.mobile.mobbl.core.services.MBDataManagerService;
import com.itude.mobile.mobbl.core.util.Constants;
import com.itude.mobile.mobbl.core.util.MBCustomAttributeContainer;
import com.itude.mobile.mobbl.core.util.MBParseUtil;

public class MBOutcome extends MBCustomAttributeContainer implements Parcelable
{
  private String     _originName;
  private String     _outcomeName;
  private String     _dialogName;
  private String     _originDialogName;
  private String     _displayMode;
  private String     _path;
  private boolean    _persist;
  private boolean    _transferDocument;
  private boolean    _noBackgroundProcessing;
  private MBDocument _document;
  private String     _preCondition;
  private String     _indicator;
  private String     _action;

  public String getOriginName()
  {
    return _originName;
  }

  public void setOriginName(String originName)
  {
    _originName = originName;
  }

  public String getOutcomeName()
  {
    return _outcomeName;
  }

  public void setOutcomeName(String outcomeName)
  {
    _outcomeName = outcomeName;
  }

  public String getDialogName()
  {
    return _dialogName;
  }

  public void setDialogName(String dialogName)
  {
    _dialogName = dialogName;
  }

  public String getOriginDialogName()
  {
    return _originDialogName;
  }

  public void setOriginDialogName(String originDialogName)
  {
    _originDialogName = originDialogName;
  }

  public String getPath()
  {
    return _path;
  }

  public void setPath(String path)
  {
    _path = path;
  }

  public String getDisplayMode()
  {
    return _displayMode;
  }

  public void setDisplayMode(String displayMode)
  {
    _displayMode = displayMode;
  }

  public String getPreCondition()
  {
    return _preCondition;
  }

  public void setPreCondition(String preCondition)
  {
    _preCondition = preCondition;
  }

  public MBDocument getDocument()
  {
    return _document;
  }

  public void setDocument(MBDocument document)
  {
    _document = document;
  }

  public boolean getPersist()
  {
    return _persist;
  }

  public void setPersist(boolean persist)
  {
    _persist = persist;
  }

  public boolean getTransferDocument()
  {
    return _transferDocument;
  }

  public void setTransferDocument(boolean transferDocument)
  {
    _transferDocument = transferDocument;
  }

  public boolean getNoBackgroundProcessing()
  {
    return _noBackgroundProcessing;
  }

  public void setNoBackgroundProcessing(boolean noBackgroundProcessing)
  {
    _noBackgroundProcessing = noBackgroundProcessing;
  }

  public void setIndicator(String indicator)
  {
    _indicator = indicator;
  }

  public String getAction()
  {
    return _action;
  }

  public void setAction(String action)
  {
    _action = action;
  }

  public String getIndicator()
  {
    return _indicator;
  }

  public MBOutcome(MBOutcome outcome)
  {
    super(outcome);
    _originName = outcome.getOriginName();
    _outcomeName = outcome.getOutcomeName();
    _originDialogName = outcome.getOriginDialogName();
    _dialogName = outcome.getDialogName();
    _displayMode = outcome.getDisplayMode();
    _document = outcome.getDocument();
    _path = outcome.getPath();
    _persist = outcome.getPersist();
    _transferDocument = outcome.getTransferDocument();
    _preCondition = outcome.getPreCondition();
    _noBackgroundProcessing = outcome.getNoBackgroundProcessing();
    _indicator = outcome.getIndicator();
    _action = outcome.getAction();
  }

  public MBOutcome(MBOutcomeDefinition definition)
  {
    super(definition);
    _originName = definition.getOrigin();
    _outcomeName = definition.getName();
    _dialogName = definition.getDialog();
    _displayMode = definition.getDisplayMode();
    _persist = definition.getPersist();
    _transferDocument = definition.getTransferDocument();
    _noBackgroundProcessing = definition.getNoBackgroundProcessing();
    _document = null;
    _path = null;
    _preCondition = definition.getPreCondition();
    _indicator = definition.getIndicator();
    _action = definition.getAction();
  }

  public MBOutcome(String outcomeName, MBDocument document)
  {
    _outcomeName = outcomeName;
    _document = document;
  }

  public MBOutcome(String outcomeName, MBDocument document, String dialogName)
  {
    this(outcomeName, document);
    _dialogName = dialogName;
  }

  public MBOutcome()
  {
  }

  public boolean isPreConditionValid()
  {
    boolean isValid = true;
    if (getPreCondition() != null)
    {
      MBDocument doc = getDocument();
      if (doc == null)
      {
        doc = MBDataManagerService.getInstance().loadDocument(MBConfigurationDefinition.DOC_SYSTEM_EMPTY);
      }
      String result = doc.evaluateExpression(this.getPreCondition());
      Boolean bool = MBParseUtil.strictBooleanValue(result);
      if (bool != null) return bool;
      String msg = "Expression of outcome with origin=" + getOriginName() + " name=" + getOutcomeName() + " precondition="
                   + getPreCondition() + " is not boolean (result=" + result + ")";
      throw new MBExpressionNotBooleanException(msg);
    }

    return isValid;
  }

  // Parcel stuff

  /**
   * Private constructor to create an instance of MBOutcome based on a previously
   * created/written Parcel.
   * @param in
   */
  private MBOutcome(Parcel in)
  {
    Bundle data = in.readBundle();

    _originName = data.getString("originName");
    _outcomeName = data.getString("outcomeName");
    _dialogName = data.getString("dialogName");
    _originDialogName = data.getString("originDialogName");
    _displayMode = data.getString("displayMode");
    _path = data.getString("path");
    _preCondition = data.getString("preCondition");

    _persist = data.getBoolean("persist");
    _transferDocument = data.getBoolean("transferDocument");
    _noBackgroundProcessing = data.getBoolean("noBackgroundProcessing");
    _action = data.getString("action");

    _document = data.getParcelable("document");

    readFromBundle(data);
  }

  @Override
  public int describeContents()
  {
    return Constants.C_PARCELABLE_TYPE_OUTCOME;
  }

  @Override
  public void writeToParcel(Parcel out, int flags)
  {
    Bundle data = new Bundle();

    data.putString("originName", _originName);
    data.putString("outcomeName", _outcomeName);
    data.putString("dialogName", _dialogName);
    data.putString("originDialogName", _originDialogName);
    data.putString("displayMode", _displayMode);
    data.putString("path", _path);
    data.putString("preCondition", _preCondition);

    data.putBoolean("persist", _persist);
    data.putBoolean("transferDocument", _transferDocument);
    data.putBoolean("noBackgroundProcessing", _noBackgroundProcessing);
    data.putString("action", _action);

    data.putParcelable("document", _document);

    writeToBundle(data);

    out.writeBundle(data);
  }

  public static final Parcelable.Creator<MBOutcome> CREATOR = new Creator<MBOutcome>()
                                                            {
                                                              @Override
                                                              public MBOutcome[] newArray(int size)
                                                              {
                                                                return new MBOutcome[size];
                                                              }

                                                              @Override
                                                              public MBOutcome createFromParcel(Parcel in)
                                                              {
                                                                return new MBOutcome(in);
                                                              }
                                                            };

  // End of parcel stuff

  @Override
  public String toString()
  {
    return "Outcome: dialog=" + getDialogName() + " originName=" + getOriginName() + " outcomeName=" + getOutcomeName() + " path="
           + getPath() + " persist=" + getPersist() + " displayMode=" + getDisplayMode() + " preCondition=" + getPreCondition()
           + " noBackgroundProsessing=" + getNoBackgroundProcessing() + " action = " + getAction();
  }

  public MBOutcome createCopy(MBOutcomeDefinition outcomeDef)
  {
    MBOutcome outcomeToProcess = new MBOutcome(outcomeDef);
    outcomeToProcess.setPath(getPath());
    outcomeToProcess.setDocument(getDocument());
    outcomeToProcess.setNoBackgroundProcessing(getNoBackgroundProcessing() || outcomeDef.getNoBackgroundProcessing());
    outcomeToProcess.setPersist(getPersist() || outcomeDef.getPersist());

    // note that the precedence of either this' or outcomeDef's values are not identical; this is not a mistake
    outcomeToProcess.setIndicator(coalesce(getIndicator(), outcomeDef.getIndicator()));
    outcomeToProcess.setDialogName(coalesce(outcomeDef.getDialog(), getDialogName()));
    outcomeToProcess.setDisplayMode(coalesce(getDisplayMode(), outcomeDef.getDisplayMode()));
    outcomeToProcess.setOriginDialogName(coalesce(getOriginDialogName(), outcomeToProcess.getDialogName()));
    outcomeToProcess.setAction(coalesce(getAction(), outcomeDef.getAction()));

    return outcomeToProcess;
  }
}