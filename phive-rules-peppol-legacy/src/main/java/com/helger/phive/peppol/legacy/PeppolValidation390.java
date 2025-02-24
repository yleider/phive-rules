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

import java.time.LocalDate;
import java.time.Month;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.version.Version;
import com.helger.phive.api.executorset.IValidationExecutorSetRegistry;
import com.helger.phive.api.executorset.VESID;
import com.helger.phive.api.executorset.ValidationExecutorSet;
import com.helger.phive.engine.schematron.ValidationExecutorSchematron;
import com.helger.phive.engine.source.IValidationSourceXML;
import com.helger.phive.engine.xsd.ValidationExecutorXSD;
import com.helger.ubl21.EUBL21DocumentType;
import com.helger.ubl21.UBL21NamespaceContext;

/**
 * OpenPeppol validation artefacts release 3.9.0. Fall 2019 release. Valid per
 * November 15th, 2019
 *
 * @author Philip Helger
 */
@Immutable
@Deprecated
public final class PeppolValidation390
{
  // Standard resources
  public static final Version PEPPOL_VALIDATION_ARTEFACT_VERSION = new Version (3, 9, 0);
  public static final String VERSION_STR = PEPPOL_VALIDATION_ARTEFACT_VERSION.getAsString (true);
  public static final LocalDate VALID_PER = PDTFactory.createLocalDate (2019, Month.NOVEMBER, 15);

  // Standard
  public static final VESID VID_OPENPEPPOL_INVOICE_V3 = new VESID ("eu.peppol.bis3", "invoice", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_CREDIT_NOTE_V3 = new VESID ("eu.peppol.bis3", "creditnote", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T01_V3 = new VESID ("eu.peppol.bis3", "t01", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T16_V3 = new VESID ("eu.peppol.bis3", "t16", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T19_V3 = new VESID ("eu.peppol.bis3", "t19", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T58_V3 = new VESID ("eu.peppol.bis3", "t58", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T71_V3 = new VESID ("eu.peppol.bis3", "t71", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T76_V3 = new VESID ("eu.peppol.bis3", "t76", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T77_V3 = new VESID ("eu.peppol.bis3", "t77", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T110_V3 = new VESID ("eu.peppol.bis3", "t110", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T111_V3 = new VESID ("eu.peppol.bis3", "t111", VERSION_STR);

  private static final String PREFIX_XSLT = "schematron/openpeppol/" + VERSION_STR + "/xslt/";

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return PeppolValidation390.class.getClassLoader ();
  }

  // Previously T10 and T14
  public static final IReadableResource INVOICE_CEN = new ClassPathResource (PREFIX_XSLT + "CEN-EN16931-UBL.xslt", _getCL ());
  public static final IReadableResource INVOICE_PEPPOL = new ClassPathResource (PREFIX_XSLT + "PEPPOL-EN16931-UBL.xslt", _getCL ());

  public static final IReadableResource ORDER = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T01.xslt", _getCL ());

  public static final IReadableResource DESPATCH_ADVICE = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T16.xslt", _getCL ());

  public static final IReadableResource CATALOGUE = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T19.xslt", _getCL ());

  public static final IReadableResource CATALOGUE_RESPONSE = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T58.xslt", _getCL ());

  public static final IReadableResource MLR = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T71.xslt", _getCL ());

  public static final IReadableResource ORDER_RESPONSE = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T76.xslt", _getCL ());

  public static final IReadableResource PUNCH_OUT = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T77.xslt", _getCL ());

  public static final IReadableResource ORDER_AGREEMENT = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T110.xslt", _getCL ());

  public static final IReadableResource INVOICE_MESSAGE_RESPONSE = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T111.xslt", _getCL ());

  private PeppolValidation390 ()
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
    final String sAkaVersionBilling = " (aka BIS Billing 3.0.5)";
    final String sAkaVersionBIS = " (aka BIS 3.0.3)";
    final boolean bDeprecated = true;
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_INVOICE_V3,
                                                                           "OpenPeppol Invoice" + sVersion + sAkaVersionBilling,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.INVOICE),
                                                                           _createXSLT (INVOICE_CEN),
                                                                           _createXSLT (INVOICE_PEPPOL)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_CREDIT_NOTE_V3,
                                                                           "OpenPeppol Credit Note" + sVersion + sAkaVersionBilling,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.CREDIT_NOTE),
                                                                           _createXSLT (INVOICE_CEN),
                                                                           _createXSLT (INVOICE_PEPPOL)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T01_V3,
                                                                           "OpenPeppol Order" + sVersion + sAkaVersionBIS,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.ORDER),
                                                                           _createXSLT (ORDER)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T16_V3,
                                                                           "OpenPeppol Despatch Advice" + sVersion + sAkaVersionBIS,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.DESPATCH_ADVICE),
                                                                           _createXSLT (DESPATCH_ADVICE)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T19_V3,
                                                                           "OpenPeppol Catalogue" + sVersion + sAkaVersionBIS,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.CATALOGUE),
                                                                           _createXSLT (CATALOGUE)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T58_V3,
                                                                           "OpenPeppol Catalogue Response" + sVersion + sAkaVersionBIS,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.APPLICATION_RESPONSE),
                                                                           _createXSLT (CATALOGUE_RESPONSE)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T71_V3,
                                                                           "OpenPeppol MLR" + sVersion + sAkaVersionBIS,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.APPLICATION_RESPONSE),
                                                                           _createXSLT (MLR)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T76_V3,
                                                                           "OpenPeppol Order Response" + sVersion + sAkaVersionBIS,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.ORDER_RESPONSE),
                                                                           _createXSLT (ORDER_RESPONSE)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T77_V3,
                                                                           "OpenPeppol Punch Out" + sVersion + sAkaVersionBIS,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.CATALOGUE),
                                                                           _createXSLT (PUNCH_OUT)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T110_V3,
                                                                           "OpenPeppol Order Agreement" + sVersion + sAkaVersionBIS,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.ORDER_RESPONSE),
                                                                           _createXSLT (ORDER_AGREEMENT)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T111_V3,
                                                                           "OpenPeppol Invoice Message Response" +
                                                                                                   sVersion +
                                                                                                   sAkaVersionBIS,
                                                                           bDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.APPLICATION_RESPONSE),
                                                                           _createXSLT (INVOICE_MESSAGE_RESPONSE)));
  }
}
