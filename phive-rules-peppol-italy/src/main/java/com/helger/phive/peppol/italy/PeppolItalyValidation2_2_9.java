/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.phive.peppol.italy;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.phive.api.executorset.IValidationExecutorSetRegistry;
import com.helger.phive.api.executorset.VESID;
import com.helger.phive.api.executorset.ValidationExecutorSet;
import com.helger.phive.engine.schematron.ValidationExecutorSchematron;
import com.helger.phive.engine.source.IValidationSourceXML;
import com.helger.phive.engine.xsd.ValidationExecutorXSD;
import com.helger.ubl21.EUBL21DocumentType;
import com.helger.ubl21.UBL21NamespaceContext;

/**
 * Italian Peppol validation artefacts based on BIS 3.0.6.
 *
 * @author Philip Helger
 */
@Immutable
@Deprecated
public final class PeppolItalyValidation2_2_9
{
  // Standard resources
  public static final String VERSION_STR = "2.2.9";

  // Standard
  private static final String GROUP_ID = "it.peppol";
  public static final VESID VID_DESPATCH_ADVICE = new VESID (GROUP_ID, "despatch-advice", VERSION_STR);
  public static final VESID VID_ORDER = new VESID (GROUP_ID, "order", VERSION_STR);
  public static final VESID VID_ORDER_RESPONSE = new VESID (GROUP_ID, "order-response", VERSION_STR);

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return PeppolItalyValidation2_2_9.class.getClassLoader ();
  }

  private static final String PREFIX_XSLT = "schematron/peppol-italy/" + VERSION_STR + "/";
  public static final IReadableResource DESPATCH_ADVICE = new ClassPathResource (PREFIX_XSLT + "AGID-PEPPOL-T16.xslt", _getCL ());
  public static final IReadableResource ORDER = new ClassPathResource (PREFIX_XSLT + "AGID-PEPPOL-T01.xslt", _getCL ());
  public static final IReadableResource ORDER_RESPONSE = new ClassPathResource (PREFIX_XSLT + "AGID-PEPPOL-T76.xslt", _getCL ());

  private PeppolItalyValidation2_2_9 ()
  {}

  @Nonnull
  private static ValidationExecutorSchematron _createXSLT (@Nonnull final IReadableResource aRes)
  {
    return ValidationExecutorSchematron.createXSLT (aRes, UBL21NamespaceContext.getInstance ());
  }

  public static void init (@Nonnull final IValidationExecutorSetRegistry <IValidationSourceXML> aRegistry)
  {
    ValueEnforcer.notNull (aRegistry, "Registry");

    final String sVersion = " (" + VERSION_STR + ")";
    final String sAkaVersionBIS = " (for BIS 3.0.6)";

    final boolean bDeprecated = true;

    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_DESPATCH_ADVICE,
                                                                           "AGID Peppol Despatch Advice" + sVersion + sAkaVersionBIS,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.DESPATCH_ADVICE),
                                                                           _createXSLT (DESPATCH_ADVICE)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_ORDER,
                                                                           "AGID Peppol Order" + sVersion + sAkaVersionBIS,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.ORDER),
                                                                           _createXSLT (ORDER)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_ORDER_RESPONSE,
                                                                           "AGID Peppol Order Response" + sVersion + sAkaVersionBIS,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.ORDER_RESPONSE),
                                                                           _createXSLT (ORDER_RESPONSE)));
  }
}
