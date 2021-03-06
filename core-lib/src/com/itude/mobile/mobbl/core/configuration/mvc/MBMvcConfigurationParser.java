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
package com.itude.mobile.mobbl.core.configuration.mvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itude.mobile.android.util.ComparisonUtil;
import com.itude.mobile.android.util.StringUtil;
import com.itude.mobile.mobbl.core.MBException;
import com.itude.mobile.mobbl.core.configuration.MBConfigurationParser;
import com.itude.mobile.mobbl.core.configuration.MBDefinition;
import com.itude.mobile.mobbl.core.configuration.mvc.MBPageDefinition.MBPageType;
import com.itude.mobile.mobbl.core.configuration.mvc.exceptions.MBInvalidPageTypeException;
import com.itude.mobile.mobbl.core.controller.MBOutcome;
import com.itude.mobile.mobbl.core.services.MBDataManagerService;
import com.itude.mobile.mobbl.core.services.datamanager.handlers.MBMetadataDataHandler;
import com.itude.mobile.mobbl.core.util.MBParseUtil;
import com.itude.mobile.mobbl.core.view.builders.MBDialogContentBuilder;

/**
 * {@link MBDefinition} Class for a configuration file
 *
 */
public class MBMvcConfigurationParser extends MBConfigurationParser
{
  private List<String> _configAttributes;
  private List<String> _documentAttributes;
  private List<String> _elementAttributes;
  private List<String> _attributeAttributes;
  private List<String> _actionAttributes;
  private List<String> _outcomeAttributes;
  private List<String> _pageStackAttributes;
  private List<String> _dialogAttributes;
  private List<String> _pageAttributes;
  private List<String> _panelAttributes;
  private List<String> _forEachAttributes;
  private List<String> _fieldAttributes;
  private List<String> _domainAttributes;
  private List<String> _domainValidatorAttributes;
  private List<String> _variableAttributes;
  private List<String> _toolAttributes;
  private List<String> _alertAttributes;

  @Override
  public MBDefinition parseData(byte[] data, String documentName)
  {
    if (_configAttributes == null)
    {
      _configAttributes = new ArrayList<String>();
      _configAttributes.add("xmlns");
    }
    if (_documentAttributes == null)
    {
      _documentAttributes = new ArrayList<String>();
      _documentAttributes.add("xmlns");
      _documentAttributes.add("name");
      _documentAttributes.add("dataManager");
      _documentAttributes.add("autoCreate");
      _documentAttributes.add("rootElement");
    }
    if (_elementAttributes == null)
    {
      _elementAttributes = new ArrayList<String>();
      _elementAttributes.add("xmlns");
      _elementAttributes.add("name");
      _elementAttributes.add("minOccurs");
      _elementAttributes.add("maxOccurs");
    }
    if (_attributeAttributes == null)
    {
      _attributeAttributes = new ArrayList<String>();
      _attributeAttributes.add("xmlns");
      _attributeAttributes.add("name");
      _attributeAttributes.add("type");
      _attributeAttributes.add("required");
      _attributeAttributes.add("defaultValue");
    }
    if (_actionAttributes == null)
    {
      _actionAttributes = new ArrayList<String>();
      _actionAttributes.add("xmlns");
      _actionAttributes.add("name");
      _actionAttributes.add("className");
    }
    if (_outcomeAttributes == null)
    {
      _outcomeAttributes = new ArrayList<String>();
      _outcomeAttributes.add("xmlns");
      _outcomeAttributes.add("origin");
      _outcomeAttributes.add("name");
      _outcomeAttributes.add("action");
      _outcomeAttributes.add("dialog");
      _outcomeAttributes.add("stack");
      _outcomeAttributes.add("displayMode");
      _outcomeAttributes.add("persist");
      _outcomeAttributes.add("transferDocument");
      _outcomeAttributes.add("preCondition");
      _outcomeAttributes.add("noBackgroundProcessing");
      _outcomeAttributes.add("indicator");
    }
    if (_pageStackAttributes == null)
    {
      _pageStackAttributes = new ArrayList<String>();
      _pageStackAttributes.add("xmlns");
      _pageStackAttributes.add("name");
      _pageStackAttributes.add("mode");
      _pageStackAttributes.add("preCondition");
      _pageStackAttributes.add("localName");
    }
    if (_dialogAttributes == null)
    {
      _dialogAttributes = new ArrayList<String>();
      _dialogAttributes.add("xmlns");
      _dialogAttributes.add("name");
      _dialogAttributes.add("title");
      _dialogAttributes.add("mode");
      _dialogAttributes.add("icon");
      _dialogAttributes.add("showAs");
      _dialogAttributes.add("domain");
      _dialogAttributes.add("preCondition");
      _dialogAttributes.add("contentType");
      _dialogAttributes.add("decorator");
    }
    if (_pageAttributes == null)
    {
      _pageAttributes = new ArrayList<String>();
      _pageAttributes.add("xmlns");
      _pageAttributes.add("name");
      _pageAttributes.add("type");
      _pageAttributes.add("document");
      _pageAttributes.add("title");
      _pageAttributes.add("titlePath");
      _pageAttributes.add("width");
      _pageAttributes.add("height");
      _pageAttributes.add("preCondition");
      _pageAttributes.add("style");
      _pageAttributes.add("orientationPermissions");
      _pageAttributes.add("scrollable");
      _pageAttributes.add("reloadOnDocChange");
    }
    if (_panelAttributes == null)
    {
      _panelAttributes = new ArrayList<String>();
      _panelAttributes.add("xmlns");
      _panelAttributes.add("name");
      _panelAttributes.add("type");
      _panelAttributes.add("style");
      _panelAttributes.add("title");
      _panelAttributes.add("titlePath");
      _panelAttributes.add("width");
      _panelAttributes.add("height");
      _panelAttributes.add("preCondition");
      _panelAttributes.add("outcome");
      _panelAttributes.add("path");
      _panelAttributes.add("mode");
      _panelAttributes.add("focused");
    }
    if (_forEachAttributes == null)
    {
      _forEachAttributes = new ArrayList<String>();
      _forEachAttributes.add("xmlns");
      _forEachAttributes.add("name");
      _forEachAttributes.add("value");
      _forEachAttributes.add("suppressRowComponent");
      _forEachAttributes.add("preCondition");
    }
    if (_variableAttributes == null)
    {
      _variableAttributes = new ArrayList<String>();
      _variableAttributes.add("xmlns");
      _variableAttributes.add("name");
      _variableAttributes.add("expression");
    }
    if (_fieldAttributes == null)
    {
      _fieldAttributes = new ArrayList<String>();
      _fieldAttributes.add("xmlns");
      _fieldAttributes.add("name");
      _fieldAttributes.add("label");
      _fieldAttributes.add("labelAttrs");
      _fieldAttributes.add("source");
      _fieldAttributes.add("path");
      _fieldAttributes.add("type");
      _fieldAttributes.add("dataType");
      _fieldAttributes.add("outcome");
      _fieldAttributes.add("style");
      _fieldAttributes.add("width");
      _fieldAttributes.add("height");
      _fieldAttributes.add("formatMask");
      _fieldAttributes.add("alignment");
      _fieldAttributes.add("valueIfNil");
      _fieldAttributes.add("hidden");
      _fieldAttributes.add("preCondition");
      _fieldAttributes.add("hint");
    }
    if (_domainAttributes == null)
    {
      _domainAttributes = new ArrayList<String>();
      _domainAttributes.add("xmlns");
      _domainAttributes.add("name");
      _domainAttributes.add("type");
      _domainAttributes.add("maxLength");
    }
    if (_domainValidatorAttributes == null)
    {
      _domainValidatorAttributes = new ArrayList<String>();
      _domainValidatorAttributes.add("xmlns");
      _domainValidatorAttributes.add("name");
      _domainValidatorAttributes.add("title");
      _domainValidatorAttributes.add("value");
      _domainValidatorAttributes.add("lowerBound");
      _domainValidatorAttributes.add("upperBound");
    }
    if (_toolAttributes == null)
    {
      _toolAttributes = new ArrayList<String>();
      _toolAttributes.add("xmlns");
      _toolAttributes.add("name");
      _toolAttributes.add("outcome");
      _toolAttributes.add("type");
      _toolAttributes.add("icon");
      _toolAttributes.add("title");
      _toolAttributes.add("preCondition");
      _toolAttributes.add("visibility");
    }
    if (_alertAttributes == null)
    {
      _alertAttributes = new ArrayList<String>();
      _alertAttributes.add("xmlns");
      _alertAttributes.add("type");
      _alertAttributes.add("name");
      _alertAttributes.add("document");
      _alertAttributes.add("style");
      _alertAttributes.add("title");
      _alertAttributes.add("titlePath");
    }

    MBDefinition definition = super.parseData(data, documentName);
    MBConfigurationDefinition conf = (MBConfigurationDefinition) definition;

    if (conf == null) throw new MBException("Could not open configuration file " + documentName);

    if (conf.getDefinitionForDocumentName(MBConfigurationDefinition.DOC_SYSTEM_EXCEPTION) == null)
    {
      addSystemDocuments(conf);
    }

    return conf;
  }

  private void addAttribute(MBElementDefinition elementDef, String name, String type)
  {
    MBAttributeDefinition attributeDef = new MBAttributeDefinition();
    attributeDef.setName(name);
    attributeDef.setType(type);
    elementDef.addAttribute(attributeDef);
  }

  @Override
  public boolean processElement(String elementName, Map<String, String> attributeDict)
  {
    if (super.processElement(elementName, attributeDict))
    {
      return true;
    }

    if (elementName.equals("Configuration"))
    {
      checkAttributesForElement(elementName, attributeDict, _configAttributes);

      MBConfigurationDefinition confDef = new MBConfigurationDefinition();
      getStack().add(confDef);
      setRootConfig(confDef);
    }
    else if (elementName.equals("Document"))
    {
      checkAttributesForElement(elementName, attributeDict, _documentAttributes);

      MBDocumentDefinition docDef = new MBDocumentDefinition();
      docDef.setName(attributeDict.get("name"));
      docDef.setDataManager(attributeDict.get("dataManager"));
      docDef.setAutoCreate(Boolean.parseBoolean(attributeDict.get("autoCreate")));
      docDef.setRootElement(attributeDict.get("rootElement"));

      notifyProcessed(docDef);
    }
    else if (elementName.equals("Element"))
    {
      checkAttributesForElement(elementName, attributeDict, _elementAttributes);

      MBElementDefinition elementDef = new MBElementDefinition();
      elementDef.setName(attributeDict.get("name"));
      if (attributeDict.containsKey("minOccurs"))
      {
        elementDef.setMinOccurs(Integer.parseInt(attributeDict.get("minOccurs")));
      }
      if (attributeDict.containsKey("maxOccurs"))
      {
        elementDef.setMaxOccurs(Integer.parseInt(attributeDict.get("maxOccurs")));
      }

      notifyProcessed(elementDef);
    }
    else if (elementName.equals("Attribute"))
    {
      checkAttributesForElement(elementName, attributeDict, _attributeAttributes);

      MBAttributeDefinition attributeDef = new MBAttributeDefinition();
      attributeDef.setName(attributeDict.get("name"));
      attributeDef.setType(attributeDict.get("type"));
      attributeDef.setDefaultValue(attributeDict.get("defaultValue"));
      attributeDef.setRequired(Boolean.parseBoolean(attributeDict.get("required")));

      notifyProcessed(attributeDef);
    }
    else if (elementName.equals("Action"))
    {
      checkAttributesForElement(elementName, attributeDict, _actionAttributes);

      MBActionDefinition actionDef = new MBActionDefinition();
      actionDef.setName(attributeDict.get("name"));
      actionDef.setClassName(attributeDict.get("className"));

      notifyProcessed(actionDef);
    }
    else if (elementName.equals("Outcome"))
    {
      checkAttributesForElement(elementName, attributeDict, _outcomeAttributes);

      MBOutcomeDefinition outcomeDef = new MBOutcomeDefinition();
      outcomeDef.setOrigin(attributeDict.get("origin"));
      outcomeDef.setName(attributeDict.get("name"));
      outcomeDef.setAction(attributeDict.get("action"));
      outcomeDef.setPageStack(ComparisonUtil.coalesce(attributeDict.get("stack"), attributeDict.get("dialog")));
      outcomeDef.setDisplayMode(attributeDict.get("displayMode"));
      outcomeDef.setPreCondition(attributeDict.get("preCondition"));
      outcomeDef.setPersist(Boolean.parseBoolean(attributeDict.get("persist")));
      outcomeDef.setTransferDocument(Boolean.parseBoolean(attributeDict.get("transferDocument")));
      outcomeDef.setNoBackgroundProcessing(Boolean.parseBoolean(attributeDict.get("noBackgroundProcessing")));
      outcomeDef.setIndicator(attributeDict.get("indicator"));

      outcomeDef.setCustom(extractCustomAttributes(attributeDict, _outcomeAttributes));

      notifyProcessed(outcomeDef);
    }
    else if (elementName.equals("PageStack"))
    {
      checkAttributesForElement(elementName, attributeDict, _pageStackAttributes);

      MBPageStackDefinition pagestackDef = new MBPageStackDefinition();
      pagestackDef.setName(attributeDict.get("name"));
      pagestackDef.setMode(attributeDict.get("mode"));
      pagestackDef.setPreCondition(attributeDict.get("preCondition"));
      pagestackDef.setLocalName(attributeDict.get("localName"));

      notifyProcessed(pagestackDef);
    }
    else if (elementName.equals("Dialog"))
    {
      checkAttributesForElement(elementName, attributeDict, _dialogAttributes);

      MBDialogDefinition dialogDef = new MBDialogDefinition();
      dialogDef.setName(attributeDict.get("name"));
      dialogDef.setTitle(attributeDict.get("title"));
      dialogDef.setTitlePortrait(attributeDict.get("titlePortrait"));
      dialogDef.setMode(attributeDict.get("mode"));
      dialogDef.setIcon(attributeDict.get("icon"));
      dialogDef.setShowAs(attributeDict.get("showAs"));
      dialogDef.setDomain(attributeDict.get("domain"));
      dialogDef.setPreCondition(attributeDict.get("preCondition"));
      dialogDef.setContentType(attributeDict.get("contentType"));
      dialogDef.setDecorator(ComparisonUtil.coalesce(attributeDict.get("decorator"), "DEFAULT"));

      dialogDef.setCustom(extractCustomAttributes(attributeDict, _dialogAttributes));

      notifyProcessed(dialogDef);
    }
    else if (elementName.equals("Page"))
    {
      checkAttributesForElement(elementName, attributeDict, _pageAttributes);

      MBPageDefinition pageDef = new MBPageDefinition();
      pageDef.setName(attributeDict.get("name"));
      pageDef.setDocumentName(attributeDict.get("document"));
      pageDef.setTitle(attributeDict.get("title"));
      pageDef.setTitlePath(attributeDict.get("titlePath"));
      pageDef.setScrollable(MBParseUtil.booleanValue(attributeDict.get("scrollable"), true));
      pageDef.setReloadOnDocChange(MBParseUtil.booleanValue(attributeDict.get("reloadOnDocChange"), false));
      if (attributeDict.containsKey("width"))
      {
        pageDef.setWidth(Integer.parseInt(attributeDict.get("width")));
      }
      if (attributeDict.containsKey("height"))
      {
        pageDef.setHeight(Integer.parseInt(attributeDict.get("height")));
      }
      pageDef.setPreCondition(attributeDict.get("preCondition"));
      pageDef.setStyle(attributeDict.get("style"));

      String type = attributeDict.get("type");
      if (type != null)
      {
        if (type.equals("normal"))
        {
          pageDef.setPageType(MBPageType.MBPageTypesNormal);
        }
        else if (type.equals("popup"))
        {
          pageDef.setPageType(MBPageType.MBPageTypesPopup);
        }
        else if (type.equals("error"))
        {
          pageDef.setPageType(MBPageType.MBPageTypesErrorPage);
        }
        else
        {
          throw new MBInvalidPageTypeException(type);
        }
      }
      pageDef.setOrientationPermissions(attributeDict.get("orientationPermissions"));

      notifyProcessed(pageDef);
    }
    else if (elementName.equals("Panel"))
    {
      MBPanelDefinition panelDef = new MBPanelDefinition();
      panelDef.setType(attributeDict.get("type"));
      panelDef.setName(attributeDict.get("name"));
      panelDef.setStyle(attributeDict.get("style"));
      panelDef.setTitle(attributeDict.get("title"));
      panelDef.setTitlePath(attributeDict.get("titlePath"));
      if (attributeDict.containsKey("width"))
      {
        panelDef.setWidth(Integer.parseInt(attributeDict.get("width")));
      }
      if (attributeDict.containsKey("height"))
      {
        panelDef.setHeight(Integer.parseInt(attributeDict.get("height")));
      }
      panelDef.setPreCondition(attributeDict.get("preCondition"));
      panelDef.setOutcomeName(attributeDict.get("outcome"));
      panelDef.setPath(attributeDict.get("path"));
      panelDef.setMode(attributeDict.get("mode"));
      panelDef.setFocused(Boolean.parseBoolean(attributeDict.get("focused")));
      panelDef.setCustom(extractCustomAttributes(attributeDict, _panelAttributes));

      notifyProcessed(panelDef);
    }
    else if (elementName.equals("ForEach"))
    {
      checkAttributesForElement(elementName, attributeDict, _forEachAttributes);

      MBForEachDefinition forEachDef = new MBForEachDefinition();
      forEachDef.setValue(attributeDict.get("value"));
      forEachDef.setSuppressRowComponent(Boolean.parseBoolean(attributeDict.get("suppressRowComponent")));
      forEachDef.setPreCondition(attributeDict.get("preCondition"));

      notifyProcessed(forEachDef);
    }
    else if (elementName.equals("Variable"))
    {
      checkAttributesForElement(elementName, attributeDict, _variableAttributes);

      MBVariableDefinition variableDef = new MBVariableDefinition();
      variableDef.setName(attributeDict.get("name"));
      variableDef.setExpression(attributeDict.get("expression"));

      notifyProcessed(variableDef);
    }
    else if (elementName.equals("Field"))
    {
      MBFieldDefinition fieldDef = new MBFieldDefinition();
      fieldDef.setName(attributeDict.get("name"));
      fieldDef.setLabel(attributeDict.get("label"));
      fieldDef.setLabelAttrs(attributeDict.get("labelAttrs"));
      fieldDef.setSource(attributeDict.get("source"));
      fieldDef.setPath(attributeDict.get("path"));
      fieldDef.setDisplayType(attributeDict.get("type"));
      fieldDef.setDataType(attributeDict.get("dataType"));
      fieldDef.setStyle(attributeDict.get("style"));
      fieldDef.setOutcomeName(attributeDict.get("outcome"));
      fieldDef.setWidth(attributeDict.get("width"));
      fieldDef.setHeight(attributeDict.get("height"));
      fieldDef.setFormatMask(attributeDict.get("formatMask"));
      fieldDef.setAlignment(attributeDict.get("alignment"));
      fieldDef.setValueIfNil(attributeDict.get("valueIfNil"));
      fieldDef.setHidden(attributeDict.get("hidden"));
      fieldDef.setPreCondition(attributeDict.get("preCondition"));
      fieldDef.setHint(attributeDict.get("hint"));
      fieldDef.setCustom(extractCustomAttributes(attributeDict, _fieldAttributes));

      notifyProcessed(fieldDef);
    }
    else if (elementName.equals("Domain"))
    {
      checkAttributesForElement(elementName, attributeDict, _domainAttributes);

      MBDomainDefinition domainDef = new MBDomainDefinition();
      domainDef.setName(attributeDict.get("name"));
      domainDef.setType(attributeDict.get("type"));
      if (attributeDict.containsKey("maxLength"))
      {
        domainDef.setMaxLength(new BigDecimal(attributeDict.get("maxLength")));
      }

      notifyProcessed(domainDef);
    }
    else if (elementName.equals("DomainValidator"))
    {
      checkAttributesForElement(elementName, attributeDict, _domainValidatorAttributes);

      MBDomainValidatorDefinition validatorDef = new MBDomainValidatorDefinition();
      validatorDef.setName(attributeDict.get("name"));
      validatorDef.setTitle(attributeDict.get("title"));
      validatorDef.setValue(attributeDict.get("value"));
      if (attributeDict.containsKey("lowerBound"))
      {
        validatorDef.setLowerBound(new BigDecimal(attributeDict.get("lowerBound")));
      }
      if (attributeDict.containsKey("upperBound"))
      {
        validatorDef.setUpperBound(new BigDecimal(attributeDict.get("upperBound")));
      }

      notifyProcessed(validatorDef);
    }
    else if (elementName.equals("Tool"))
    {
      checkAttributesForElement(elementName, attributeDict, _toolAttributes);

      MBToolDefinition toolDef = new MBToolDefinition();
      toolDef.setName(attributeDict.get("name"));
      toolDef.setType(attributeDict.get("type"));
      toolDef.setOutcomeName(attributeDict.get("outcome"));
      toolDef.setIcon(attributeDict.get("icon"));
      toolDef.setTitle(attributeDict.get("title"));
      toolDef.setPreCondition(attributeDict.get("preCondition"));
      toolDef.setVisibility(attributeDict.get("visibility"));

      notifyProcessed(toolDef);
    }
    else if (elementName.equals("Alert"))
    {
      checkAttributesForElement(elementName, attributeDict, _alertAttributes);

      MBAlertDefinition alertDef = new MBAlertDefinition();
      alertDef.setType(attributeDict.get("type"));
      alertDef.setName(attributeDict.get("name"));
      alertDef.setDocumentName(attributeDict.get("document"));
      alertDef.setStyle(attributeDict.get("style"));
      alertDef.setTitle(attributeDict.get("title"));
      alertDef.setTitlePath(attributeDict.get("titlePath"));

      notifyProcessed(alertDef);
    }
    else
    {
      return false;
    }

    return true;
  }

  private Map<String, String> extractCustomAttributes(Map<String, String> attributes, List<String> predefinedAttributes)
  {
    Map<String, String> custom = new HashMap<String, String>(attributes);
    for (String attribute : predefinedAttributes)
      custom.remove(attribute);

    return custom;
  }

  @Override
  public void didProcessElement(String elementName)
  {
    if (elementName.equals("Field"))
    {
      MBFieldDefinition fieldDef = (MBFieldDefinition) getStack().get(getStack().size() - 1);
      fieldDef.setText(getCharacters());
    }
    else if ("Dialog".equals(elementName))
    {
      MBDefinition configDef = getStack().elementAt(getStack().size() - 2);
      MBDialogDefinition groupDef = (MBDialogDefinition) getStack().peek();
      if (groupDef.getChildren().isEmpty())
      {
        MBPageStackDefinition dialogDef = new MBPageStackDefinition();
        dialogDef.setName(groupDef.getName());
        groupDef.addPageStack(dialogDef);
      }

      if (StringUtil.isEmpty(groupDef.getContentType()))
      {
        groupDef.setContentType(groupDef.getChildren().size() == 1
            ? MBDialogContentBuilder.DEFAULT_SINGLE
            : MBDialogContentBuilder.DEFAULT_SPLIT);
      }

      for (MBPageStackDefinition dialogDef : groupDef.getChildren())
        configDef.addChildElement(dialogDef);

      createImplicitOutcomeForDialog(groupDef);
    }

    if (!elementName.equals("Configuration") && !elementName.equals("Include"))
    {
      getStack().remove(getStack().size() - 1);
    }

  }

  private void createImplicitOutcomeForDialog(MBDialogDefinition dialog)
  {
    List<MBOutcomeDefinition> def = ((MBConfigurationDefinition) getRootConfig()).getOutcomeDefinitionsForOrigin(MBOutcome.Origin.WILDCARD,
                                                                                                                 dialog.getName());
    if (def.isEmpty())
    {
      MBOutcomeDefinition definition = new MBOutcomeDefinition();
      definition.setAction("none");
      definition.setPageStack(dialog.getChildren().get(0).getName());
      definition.setName(dialog.getName());
      definition.setNoBackgroundProcessing(true);
      definition.setOrigin(MBConfigurationDefinition.ORIGIN_WILDCARD);
      ((MBDefinition) getRootConfig()).addChildElement(definition);
    }
  }

  @Override
  public boolean isConcreteElement(String element)
  {
    return super.isConcreteElement(element) || element.equals("Configuration") || element.equals("Document") || element.equals("Element")
           || element.equals("Attribute") || element.equals("Action") || element.equals("Outcome") || element.equals("Page")
           || element.equals("PageStack") || element.equals("Dialog") || element.equals("ForEach") || element.equals("Variable")
           || element.equals("Panel") || element.equals("Field") || element.equals("Domain") || element.equals("DomainValidator")
           || element.equals("Tool") || element.equals("Alert");
  }

  @Override
  public boolean isIgnoredElement(String element)
  {
    return element.equals("Model") || element.equals("Dialogs") || element.equals("Domains") || element.equals("Documents")
           || element.equals("Controller") || element.equals("Actions") || element.equals("Wiring") || element.equals("View")
           || element.equals("Toolbar") || element.equals("Alerts");
  }

  private void addExceptionDocument(MBConfigurationDefinition conf)
  {
    MBDocumentDefinition docDef = new MBDocumentDefinition();
    docDef.setName(MBConfigurationDefinition.DOC_SYSTEM_EXCEPTION);
    docDef.setDataManager(MBDataManagerService.DATA_HANDLER_MEMORY);
    docDef.setAutoCreate(true);

    MBElementDefinition elementDef = new MBElementDefinition();
    elementDef.setName("Exception");
    elementDef.setMinOccurs(1);

    docDef.addElement(elementDef);
    addAttribute(elementDef, "name", "string");
    addAttribute(elementDef, "description", "string");
    addAttribute(elementDef, "origin", "string");
    addAttribute(elementDef, "outcome", "string");
    addAttribute(elementDef, "path", "string");

    MBElementDefinition stackLine = new MBElementDefinition();
    stackLine.setName("Stackline");
    stackLine.setMinOccurs(0);
    addAttribute(stackLine, "line", "string");
    elementDef.addElement(stackLine);

    conf.addDocument(docDef);
  }

  private void addEmptyDocument(MBConfigurationDefinition conf)
  {
    MBDocumentDefinition docDef = new MBDocumentDefinition();
    docDef.setName(MBConfigurationDefinition.DOC_SYSTEM_EMPTY);
    docDef.setDataManager(MBDataManagerService.DATA_HANDLER_MEMORY);
    docDef.setAutoCreate(true);

    MBElementDefinition elementDef = new MBElementDefinition();
    elementDef.setName("Empty");
    elementDef.setMinOccurs(1);

    docDef.addElement(elementDef);
    conf.addDocument(docDef);
  }

  private void addLanguageDocument(MBConfigurationDefinition conf)
  {
    MBDocumentDefinition docDef = new MBDocumentDefinition();
    docDef.setName(MBConfigurationDefinition.DOC_SYSTEM_LANGUAGE);
    docDef.setDataManager(MBDataManagerService.DATA_HANDLER_MEMORY);
    docDef.setAutoCreate(true);

    MBElementDefinition elementDef = new MBElementDefinition();
    elementDef.setName("Text");
    addAttribute(elementDef, "key", "string");
    addAttribute(elementDef, "value", "string");
    elementDef.setMinOccurs(0);

    docDef.addElement(elementDef);
    conf.addDocument(docDef);
  }

  private void addPropertiesDocument(MBConfigurationDefinition conf)
  {
    MBDocumentDefinition docDef = new MBDocumentDefinition();
    docDef.setName(MBConfigurationDefinition.DOC_SYSTEM_PROPERTIES);
    docDef.setDataManager(MBDataManagerService.DATA_HANDLER_SYSTEM);
    docDef.setAutoCreate(true);

    MBElementDefinition elementDef = new MBElementDefinition();
    elementDef.setMinOccurs(1);
    elementDef.setName("System");

    MBElementDefinition propDef = new MBElementDefinition();
    propDef.setMinOccurs(0);
    propDef.setName("Property");
    addAttribute(propDef, "name", "string");
    addAttribute(propDef, "value", "string");
    elementDef.addElement(propDef);
    docDef.addElement(elementDef);

    MBElementDefinition elementDef2 = new MBElementDefinition();
    elementDef2.setMinOccurs(1);
    elementDef2.setName("Application");

    MBElementDefinition propDef2 = new MBElementDefinition();
    propDef2.setMinOccurs(0);
    propDef2.setName("Property");
    addAttribute(propDef2, "name", "string");
    addAttribute(propDef2, "value", "string");
    elementDef2.addElement(propDef2);

    docDef.addElement(elementDef2);
    conf.addDocument(docDef);
  }

  private void addDeviceDocument(MBConfigurationDefinition conf)
  {
    MBDocumentDefinition docDef = new MBDocumentDefinition();
    docDef.setName(MBConfigurationDefinition.DOC_SYSTEM_DEVICE);
    docDef.setDataManager(MBDataManagerService.DATA_HANDLER_MEMORY);
    docDef.setAutoCreate(true);

    MBElementDefinition elementDef = new MBElementDefinition();
    elementDef.setMinOccurs(1);
    elementDef.setName("Device");

    MBElementDefinition propDef = new MBElementDefinition();
    propDef.setMinOccurs(0);
    propDef.setName("Property");
    addAttribute(propDef, "name", "string");
    addAttribute(propDef, "value", "string");
    elementDef.addElement(propDef);
    docDef.addElement(elementDef);
  }

  private void addDialogsDocument(MBConfigurationDefinition conf)
  {
    MBDocumentDefinition docDef = new MBDocumentDefinition();
    docDef.setName(MBMetadataDataHandler.DIALOGS_DOCUMENT);
    docDef.setDataManager(MBDataManagerService.DATA_HANDLER_METADATA);
    docDef.setAutoCreate(true);

    MBElementDefinition elementDef = new MBElementDefinition();
    elementDef.setName("Dialog");
    addAttribute(elementDef, "name", "string");
    addAttribute(elementDef, "mode", "string");
    addAttribute(elementDef, "icon", "string");
    addAttribute(elementDef, "showAs", "string");
    addAttribute(elementDef, "title", "string");
    docDef.addElement(elementDef);
    conf.addDocument(docDef);
  }

  private void addSystemDocuments(MBConfigurationDefinition conf)
  {
    addExceptionDocument(conf);
    addEmptyDocument(conf);
    addPropertiesDocument(conf);
    addLanguageDocument(conf);
    addDeviceDocument(conf);
    addDialogsDocument(conf);
  }
}
