/*
 * Copyright (C) 2018-2022 Philip Helger (www.helger.com)
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
package com.helger.phive.teapps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.io.resource.IReadableResource;
import com.helger.phive.api.EValidationType;
import com.helger.phive.api.artefact.IValidationArtefact;
import com.helger.phive.api.execute.IValidationExecutor;
import com.helger.phive.api.executorset.IValidationExecutorSet;
import com.helger.phive.engine.source.IValidationSourceXML;
import com.helger.phive.teapps.mock.CTestFiles;
import com.helger.schematron.pure.SchematronResourcePure;
import com.helger.schematron.sch.SchematronResourceSCH;
import com.helger.schematron.xslt.SchematronResourceXSLT;

/**
 * Test class for class {@link TEAPPSValidation}.
 *
 * @author Philip Helger
 */
public final class TEAPPSValidationTest
{
  @Test
  public void testFilesExist ()
  {
    for (final IValidationExecutorSet <IValidationSourceXML> aVES : CTestFiles.VES_REGISTRY.getAll ())
      for (final IValidationExecutor <IValidationSourceXML> aVE : aVES)
      {
        final IReadableResource aRes = aVE.getValidationArtefact ().getRuleResource ();
        assertTrue (aRes.toString (), aRes.exists ());
      }
  }

  @Test
  public void testSchematronsValid ()
  {
    for (final IValidationExecutorSet <IValidationSourceXML> aVES : CTestFiles.VES_REGISTRY.getAll ())
      for (final IValidationExecutor <IValidationSourceXML> aVE : aVES)
      {
        final IValidationArtefact aVA = aVE.getValidationArtefact ();
        final IReadableResource aRes = aVA.getRuleResource ();

        // Check that the passed Schematron is valid
        if (aVA.getValidationArtefactType () == EValidationType.SCHEMATRON_PURE)
          assertTrue (aRes.toString (), new SchematronResourcePure (aRes).isValidSchematron ());
        else
          if (aVA.getValidationArtefactType () == EValidationType.SCHEMATRON_SCH)
            assertTrue (aRes.toString (), new SchematronResourceSCH (aRes).isValidSchematron ());
          else
            if (aVA.getValidationArtefactType () == EValidationType.SCHEMATRON_XSLT)
              assertTrue (aRes.toString (), new SchematronResourceXSLT (aRes).isValidSchematron ());
      }
  }
}
