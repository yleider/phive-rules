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
package com.helger.phive.peppol.legacy;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.phive.api.executorset.IValidationExecutorSetRegistry;
import com.helger.phive.api.executorset.VESID;
import com.helger.phive.api.executorset.ValidationExecutorSet;
import com.helger.phive.engine.schematron.SchematronNamespaceBeautifier;
import com.helger.phive.engine.schematron.ValidationExecutorSchematron;
import com.helger.phive.engine.source.IValidationSourceXML;
import com.helger.phive.engine.xsd.ValidationExecutorXSD;
import com.helger.ubl21.EUBL21DocumentType;
import com.helger.xml.namespace.MapBasedNamespaceContext;

/**
 * Peppol Singapore (SG) validation configuration
 *
 * @author Philip Helger
 */
@Immutable
public final class PeppolLegacyValidationSG
{
  @Nonnull
  private static ClassLoader _getCL ()
  {
    return PeppolLegacyValidationSG.class.getClassLoader ();
  }

  private static final String BASE_PATH = "schematron/peppol-sg/";

  // 1.0.0 aka 1
  @Deprecated
  public static final VESID VID_OPENPEPPOL_BIS3_SG_UBL_INVOICE_100 = new VESID ("eu.peppol.bis3.sg.ubl",
                                                                                "invoice",
                                                                                "1");
  @Deprecated
  public static final VESID VID_OPENPEPPOL_BIS3_SG_UBL_CREDIT_NOTE_100 = new VESID ("eu.peppol.bis3.sg.ubl",
                                                                                    "creditnote",
                                                                                    "1");
  @Deprecated
  public static final IReadableResource BIS3_BILLING_SG_CEN_100 = new ClassPathResource (BASE_PATH +
                                                                                         "1.0.0/xslt/CEN-EN16931-UBL-SG-Conformant.xslt",
                                                                                         _getCL ());
  @Deprecated
  public static final IReadableResource BIS3_BILLING_SG_PEPPOL_100 = new ClassPathResource (BASE_PATH +
                                                                                            "1.0.0/xslt/PEPPOL-EN16931-UBL-SG-Conformant.xslt",
                                                                                            _getCL ());

  private PeppolLegacyValidationSG ()
  {}

  public static void init (@Nonnull final IValidationExecutorSetRegistry <IValidationSourceXML> aRegistry)
  {
    ValueEnforcer.notNull (aRegistry, "Registry");

    final MapBasedNamespaceContext aNSCtxInvoice = PeppolLegacyValidation.createUBLNSContext (EUBL21DocumentType.INVOICE.getNamespaceURI ());
    final MapBasedNamespaceContext aNSCtxCreditNote = PeppolLegacyValidation.createUBLNSContext (EUBL21DocumentType.CREDIT_NOTE.getNamespaceURI ());

    // For better error messages (merge both)
    SchematronNamespaceBeautifier.addMappings (aNSCtxCreditNote);

    final boolean bDeprecated = true;

    // 1.0.0
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_BIS3_SG_UBL_INVOICE_100,
                                                                           "SG Peppol BIS3 Invoice (UBL) 1.0.0",
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.INVOICE),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_SG_CEN_100,
                                                                                                                    aNSCtxInvoice),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_SG_PEPPOL_100,
                                                                                                                    aNSCtxInvoice)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_BIS3_SG_UBL_CREDIT_NOTE_100,
                                                                           "SG Peppol BIS3 CreditNote (UBL) 1.0.0",
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.CREDIT_NOTE),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_SG_CEN_100,
                                                                                                                    aNSCtxCreditNote),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_SG_PEPPOL_100,
                                                                                                                    aNSCtxCreditNote)));
  }
}
