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
package com.helger.phive.peppol;

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
 * Peppol Australia/New Zealand (AUNZ) validation configuration
 *
 * @author Philip Helger
 */
@Immutable
public final class PeppolValidationAUNZ
{
  @Nonnull
  private static ClassLoader _getCL ()
  {
    return PeppolValidationAUNZ.class.getClassLoader ();
  }

  private static final String BASE_PATH = "schematron/peppol-aunz/";

  // 1.0.7
  @Deprecated
  public static final VESID VID_OPENPEPPOL_BIS3_AUNZ_UBL_INVOICE_107 = new VESID ("eu.peppol.bis3.aunz.ubl",
                                                                                  "invoice",
                                                                                  "1.0.7");
  @Deprecated
  public static final VESID VID_OPENPEPPOL_BIS3_AUNZ_UBL_INVOICE_SELF_BILLING_107 = new VESID ("eu.peppol.bis3.aunz.ubl",
                                                                                               "invoice-self-billing",
                                                                                               "1.0.7");
  @Deprecated
  public static final VESID VID_OPENPEPPOL_BIS3_AUNZ_UBL_CREDIT_NOTE_107 = new VESID ("eu.peppol.bis3.aunz.ubl",
                                                                                      "creditnote",
                                                                                      "1.0.7");
  @Deprecated
  public static final VESID VID_OPENPEPPOL_BIS3_AUNZ_UBL_CREDIT_NOTE_SELF_BILLING_107 = new VESID ("eu.peppol.bis3.aunz.ubl",
                                                                                                   "creditnote-self-billing",
                                                                                                   "1.0.7");

  @Deprecated
  public static final IReadableResource BIS3_BILLING_AUNZ_PEPPOL_SELF_BILLING_107 = new ClassPathResource (BASE_PATH +
                                                                                                           "1.0.7/xslt/AUNZ-PEPPOL-SB-validation.xslt",
                                                                                                           _getCL ());
  @Deprecated
  public static final IReadableResource BIS3_BILLING_AUNZ_PEPPOL_107 = new ClassPathResource (BASE_PATH +
                                                                                              "1.0.7/xslt/AUNZ-PEPPOL-validation.xslt",
                                                                                              _getCL ());
  @Deprecated
  public static final IReadableResource BIS3_BILLING_AUNZ_UBL_107 = new ClassPathResource (BASE_PATH +
                                                                                           "1.0.7/xslt/AUNZ-UBL-validation.xslt",
                                                                                           _getCL ());

  // 1.0.8
  public static final VESID VID_OPENPEPPOL_BIS3_AUNZ_UBL_INVOICE_108 = new VESID ("eu.peppol.bis3.aunz.ubl",
                                                                                  "invoice",
                                                                                  "1.0.8");
  public static final VESID VID_OPENPEPPOL_BIS3_AUNZ_UBL_INVOICE_SELF_BILLING_108 = new VESID ("eu.peppol.bis3.aunz.ubl",
                                                                                               "invoice-self-billing",
                                                                                               "1.0.8");
  public static final VESID VID_OPENPEPPOL_BIS3_AUNZ_UBL_CREDIT_NOTE_108 = new VESID ("eu.peppol.bis3.aunz.ubl",
                                                                                      "creditnote",
                                                                                      "1.0.8");
  public static final VESID VID_OPENPEPPOL_BIS3_AUNZ_UBL_CREDIT_NOTE_SELF_BILLING_108 = new VESID ("eu.peppol.bis3.aunz.ubl",
                                                                                                   "creditnote-self-billing",
                                                                                                   "1.0.8");

  public static final IReadableResource BIS3_BILLING_AUNZ_PEPPOL_SELF_BILLING_108 = new ClassPathResource (BASE_PATH +
                                                                                                           "1.0.8/xslt/AUNZ-PEPPOL-SB-validation.xslt",
                                                                                                           _getCL ());
  public static final IReadableResource BIS3_BILLING_AUNZ_PEPPOL_108 = new ClassPathResource (BASE_PATH +
                                                                                              "1.0.8/xslt/AUNZ-PEPPOL-validation.xslt",
                                                                                              _getCL ());
  public static final IReadableResource BIS3_BILLING_AUNZ_UBL_108 = new ClassPathResource (BASE_PATH +
                                                                                           "1.0.8/xslt/AUNZ-UBL-validation.xslt",
                                                                                           _getCL ());

  private PeppolValidationAUNZ ()
  {}

  public static void init (@Nonnull final IValidationExecutorSetRegistry <IValidationSourceXML> aRegistry)
  {
    ValueEnforcer.notNull (aRegistry, "Registry");

    final MapBasedNamespaceContext aNSCtxInvoice = PeppolValidation.createUBLNSContext (EUBL21DocumentType.INVOICE.getNamespaceURI ());
    final MapBasedNamespaceContext aNSCtxCreditNote = PeppolValidation.createUBLNSContext (EUBL21DocumentType.CREDIT_NOTE.getNamespaceURI ());

    // For better error messages (merge both)
    SchematronNamespaceBeautifier.addMappings (aNSCtxCreditNote.getClone ().setMappings (aNSCtxInvoice));

    final boolean bDeprecated = true;
    final boolean bNotDeprecated = false;

    // 1.0.7
    final String sVersion107 = VID_OPENPEPPOL_BIS3_AUNZ_UBL_INVOICE_107.getVersion ();
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_BIS3_AUNZ_UBL_INVOICE_107,
                                                                           "A-NZ Peppol BIS3 Invoice (UBL) " +
                                                                                                                     sVersion107,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.INVOICE),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_PEPPOL_107,
                                                                                                                    aNSCtxInvoice),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_UBL_107,
                                                                                                                    aNSCtxInvoice)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_BIS3_AUNZ_UBL_CREDIT_NOTE_107,
                                                                           "A-NZ Peppol BIS3 CreditNote (UBL) " +
                                                                                                                         sVersion107,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.CREDIT_NOTE),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_PEPPOL_107,
                                                                                                                    aNSCtxCreditNote),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_UBL_107,
                                                                                                                    aNSCtxCreditNote)));

    // Self-billing
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_BIS3_AUNZ_UBL_INVOICE_SELF_BILLING_107,
                                                                           "A-NZ Peppol BIS3 Invoice Self-Billing (UBL) " +
                                                                                                                                  sVersion107,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.INVOICE),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_PEPPOL_SELF_BILLING_107,
                                                                                                                    aNSCtxInvoice),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_UBL_107,
                                                                                                                    aNSCtxInvoice)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_BIS3_AUNZ_UBL_CREDIT_NOTE_SELF_BILLING_107,
                                                                           "A-NZ Peppol BIS3 CreditNote Self-Billing (UBL) " +
                                                                                                                                      sVersion107,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.CREDIT_NOTE),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_PEPPOL_SELF_BILLING_107,
                                                                                                                    aNSCtxCreditNote),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_UBL_107,
                                                                                                                    aNSCtxCreditNote)));

    // 1.0.8
    final String sVersion108 = VID_OPENPEPPOL_BIS3_AUNZ_UBL_INVOICE_108.getVersion ();
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_BIS3_AUNZ_UBL_INVOICE_108,
                                                                           "A-NZ Peppol BIS3 Invoice (UBL) " +
                                                                                                                     sVersion108,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.INVOICE),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_PEPPOL_108,
                                                                                                                    aNSCtxInvoice),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_UBL_108,
                                                                                                                    aNSCtxInvoice)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_BIS3_AUNZ_UBL_CREDIT_NOTE_108,
                                                                           "A-NZ Peppol BIS3 CreditNote (UBL) " +
                                                                                                                         sVersion108,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.CREDIT_NOTE),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_PEPPOL_108,
                                                                                                                    aNSCtxCreditNote),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_UBL_108,
                                                                                                                    aNSCtxCreditNote)));

    // Self-billing
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_BIS3_AUNZ_UBL_INVOICE_SELF_BILLING_108,
                                                                           "A-NZ Peppol BIS3 Invoice Self-Billing (UBL) " +
                                                                                                                                  sVersion108,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.INVOICE),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_PEPPOL_SELF_BILLING_108,
                                                                                                                    aNSCtxInvoice),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_UBL_108,
                                                                                                                    aNSCtxInvoice)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_BIS3_AUNZ_UBL_CREDIT_NOTE_SELF_BILLING_108,
                                                                           "A-NZ Peppol BIS3 CreditNote Self-Billing (UBL) " +
                                                                                                                                      sVersion108,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.CREDIT_NOTE),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_PEPPOL_SELF_BILLING_108,
                                                                                                                    aNSCtxCreditNote),
                                                                           ValidationExecutorSchematron.createXSLT (BIS3_BILLING_AUNZ_UBL_108,
                                                                                                                    aNSCtxCreditNote)));
  }
}
