/*
 * Copyright (C) 2020-2022 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.phive.ciuspt;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.phive.api.executorset.IValidationExecutorSetRegistry;
import com.helger.phive.api.executorset.VESID;
import com.helger.phive.api.executorset.ValidationExecutorSet;
import com.helger.phive.engine.schematron.ValidationExecutorSchematron;
import com.helger.phive.engine.source.IValidationSourceXML;
import com.helger.phive.engine.xsd.ValidationExecutorXSD;
import com.helger.ubl21.EUBL21DocumentType;
import com.helger.ubl21.UBL21NamespaceContext;

/**
 * Generic CIUS-PT validation configuration
 *
 * @author Philip Helger
 */
@Immutable
public final class CIUS_PTValidation
{
  public static final String GROUP_ID = "pt.gov.espap.cius-pt";

  // Version 2.0.0
  @Deprecated
  public static final VESID VID_CIUS_PT_UBL_CREDITNOTE_200 = new VESID (GROUP_ID, "ubl-creditnote", "2.0.0");
  @Deprecated
  public static final VESID VID_CIUS_PT_UBL_INVOICE_200 = new VESID (GROUP_ID, "ubl-invoice", "2.0.0");
  /**
   * @deprecated Use {@link #VID_CIUS_PT_UBL_CREDITNOTE_200} instead
   */
  @Deprecated
  public static final VESID VID_TEAPPS_UBL_CREDITNOTE_200 = VID_CIUS_PT_UBL_CREDITNOTE_200;
  /**
   * @deprecated Use {@link #VID_CIUS_PT_UBL_INVOICE_200} instead
   */
  @Deprecated
  public static final VESID VID_TEAPPS_UBL_INVOICE_200 = VID_CIUS_PT_UBL_INVOICE_200;

  // Version 2.1.1
  public static final VESID VID_CIUS_PT_UBL_CREDITNOTE_211 = new VESID (GROUP_ID, "ubl-creditnote", "2.1.1");
  public static final VESID VID_CIUS_PT_UBL_INVOICE_211 = new VESID (GROUP_ID, "ubl-invoice", "2.1.1");

  private static final ClassPathResource RES_200 = new ClassPathResource ("/schematron/2.0.0/urn_feap.gov.pt_CIUS-PT_2.0.0.xslt",
                                                                          _getCL ());
  private static final ClassPathResource RES_211 = new ClassPathResource ("/schematron/2.1.1/urn_feap.gov.pt_CIUS-PT_2.1.1.xslt",
                                                                          _getCL ());

  private CIUS_PTValidation ()
  {}

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CIUS_PTValidation.class.getClassLoader ();
  }

  /**
   * Register all standard CIUS-PT validation execution sets to the provided
   * registry.
   *
   * @param aRegistry
   *        The registry to add the artefacts. May not be <code>null</code>.
   */
  public static void initCIUS_PT (@Nonnull final IValidationExecutorSetRegistry <IValidationSourceXML> aRegistry)
  {
    ValueEnforcer.notNull (aRegistry, "Registry");

    final boolean bDeprecated = true;
    final boolean bNotDeprecated = false;

    // V2.0.0 containing the underlying EN rules
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_CIUS_PT_UBL_CREDITNOTE_200,
                                                                           "CIUS-PT UBL CrediteNote " +
                                                                                                           VID_CIUS_PT_UBL_CREDITNOTE_200.getVersion (),
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.CREDIT_NOTE),
                                                                           ValidationExecutorSchematron.createXSLT (RES_200,
                                                                                                                    UBL21NamespaceContext.getInstance ())));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_CIUS_PT_UBL_INVOICE_200,
                                                                           "CIUS-PT UBL Invoice " +
                                                                                                        VID_CIUS_PT_UBL_INVOICE_200.getVersion (),
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.INVOICE),
                                                                           ValidationExecutorSchematron.createXSLT (RES_200,
                                                                                                                    UBL21NamespaceContext.getInstance ())));

    // V2.1.1 containing the underlying EN rules
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_CIUS_PT_UBL_CREDITNOTE_211,
                                                                           "CIUS-PT UBL CrediteNote " +
                                                                                                           VID_CIUS_PT_UBL_CREDITNOTE_211.getVersion (),
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.CREDIT_NOTE),
                                                                           ValidationExecutorSchematron.createXSLT (RES_211,
                                                                                                                    UBL21NamespaceContext.getInstance ())));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_CIUS_PT_UBL_INVOICE_211,
                                                                           "CIUS-PT UBL Invoice " +
                                                                                                        VID_CIUS_PT_UBL_INVOICE_211.getVersion (),
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.INVOICE),
                                                                           ValidationExecutorSchematron.createXSLT (RES_211,
                                                                                                                    UBL21NamespaceContext.getInstance ())));
  }
}
