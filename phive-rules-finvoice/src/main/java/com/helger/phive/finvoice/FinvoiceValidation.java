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
package com.helger.phive.finvoice;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.phive.api.executorset.IValidationExecutorSetRegistry;
import com.helger.phive.api.executorset.VESID;
import com.helger.phive.api.executorset.ValidationExecutorSet;
import com.helger.phive.engine.source.IValidationSourceXML;
import com.helger.phive.engine.xsd.ValidationExecutorXSD;

/**
 * Generic Finvoice validation configuration
 *
 * @author Philip Helger
 */
@Immutable
public final class FinvoiceValidation
{
  public static final String GROUP_ID = "fi.finvoice";

  public static final VESID VID_FINVOICE_13 = new VESID (GROUP_ID, "finvoice", "1.3");
  public static final VESID VID_FINVOICE_20 = new VESID (GROUP_ID, "finvoice", "2.0");
  public static final VESID VID_FINVOICE_201 = new VESID (GROUP_ID, "finvoice", "2.0.1");
  public static final VESID VID_FINVOICE_30 = new VESID (GROUP_ID, "finvoice", "3.0");

  private FinvoiceValidation ()
  {}

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return FinvoiceValidation.class.getClassLoader ();
  }

  /**
   * Register all standard Finvoice validation execution sets to the provided
   * registry.
   *
   * @param aRegistry
   *        The registry to add the artefacts. May not be <code>null</code>.
   */
  public static void initFinvoice (@Nonnull final IValidationExecutorSetRegistry <IValidationSourceXML> aRegistry)
  {
    ValueEnforcer.notNull (aRegistry, "Registry");

    final boolean bNotDeprecated = false;

    // No Schematrons here
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_FINVOICE_13,
                                                                           "Finvoice " + VID_FINVOICE_13.getVersion (),
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (new ClassPathResource ("/schemas/Finvoice1.3.xsd",
                                                                                                                                _getCL ()))));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_FINVOICE_20,
                                                                           "Finvoice " + VID_FINVOICE_20.getVersion (),
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (new ClassPathResource ("/schemas/Finvoice2.0.xsd",
                                                                                                                                _getCL ()))));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_FINVOICE_201,
                                                                           "Finvoice " + VID_FINVOICE_201.getVersion (),
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (new ClassPathResource ("/schemas/Finvoice2.01.xsd",
                                                                                                                                _getCL ()))));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_FINVOICE_30,
                                                                           "Finvoice " + VID_FINVOICE_30.getVersion (),
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (new ClassPathResource ("/schemas/Finvoice3.0.xsd",
                                                                                                                                _getCL ()))));
  }
}
