/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.xssf.usermodel.helpers;

import junit.framework.TestCase;

import org.apache.poi.xssf.usermodel.helpers.HeaderFooterHelper;


public class TestHeaderFooterHelper extends TestCase {
    
    public void testGetCenterLeftRightSection() {
        HeaderFooterHelper helper = new HeaderFooterHelper();
        String headerFooter = "&amp;CTest the center section";
        assertEquals("Test the center section", helper.getCenterSection(headerFooter));
        headerFooter = "&amp;CTest the center section&amp;LThe left one&amp;RAnd the right one";
        assertEquals("Test the center section", helper.getCenterSection(headerFooter));
        assertEquals("The left one", helper.getLeftSection(headerFooter));
        assertEquals("And the right one", helper.getRightSection(headerFooter));
    }
    
    public void testSetCenterLeftRightSection() {
        HeaderFooterHelper helper = new HeaderFooterHelper();
        String headerFooter = "";
        headerFooter = helper.setCenterSection(headerFooter, "First added center section");
        assertEquals("First added center section", helper.getCenterSection(headerFooter));
        headerFooter = helper.setLeftSection(headerFooter, "First left");
        assertEquals("First left", helper.getLeftSection(headerFooter));

        headerFooter = helper.setRightSection(headerFooter, "First right");
        assertEquals("First right", helper.getRightSection(headerFooter));
        assertEquals("&amp;CFirst added center section&amp;LFirst left&amp;RFirst right", headerFooter);

        headerFooter = helper.setRightSection(headerFooter, "First right&amp");
        assertEquals("First right&amp", helper.getRightSection(headerFooter));
        assertEquals("&amp;CFirst added center section&amp;LFirst left&amp;RFirst right&amp", headerFooter);
        
        headerFooter = helper.setRightSection(headerFooter, "First right&amp;");
        assertEquals("First right", helper.getRightSection(headerFooter));
        assertEquals("&amp;CFirst added center section&amp;LFirst left&amp;RFirst right&amp;", headerFooter);
    }
    
}