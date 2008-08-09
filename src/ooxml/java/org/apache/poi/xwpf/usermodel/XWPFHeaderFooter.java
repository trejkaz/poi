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
package org.apache.poi.xwpf.usermodel;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;

/**
 * Parent of XWPF headers and footers
 */
public abstract class XWPFHeaderFooter {
	protected CTHdrFtr headerFooter;
	
	protected XWPFHeaderFooter(CTHdrFtr hdrFtr) {
		headerFooter = hdrFtr;
	}
	protected XWPFHeaderFooter() {
		headerFooter = CTHdrFtr.Factory.newInstance();
	}
	
	public CTHdrFtr _getHdrFtr() {
		return headerFooter;
	}

	/**
	 * Returns the paragraph(s) that holds
	 *  the text of the header or footer.
	 * Normally there is only the one paragraph, but
	 *  there could be more in certain cases.
	 */
	public XWPFParagraph[] getParagraphs() {
		XWPFParagraph[] paras = 
			new XWPFParagraph[headerFooter.getPArray().length];
		for(int i=0; i<paras.length; i++) {
			paras[i] = new XWPFParagraph(
					headerFooter.getPArray(i)
			);
		}
		return paras;
	}
	
	/**
	 * Returns the textual content of the header/footer,
	 *  by flattening out the text of its paragraph(s)
	 */
	public String getText() {
		StringBuffer t = new StringBuffer();
		XWPFParagraph[] paras = getParagraphs();
		for (int i = 0; i < paras.length; i++) {
			t.append(paras[i].getText());
			t.append('\n');
		}
		return t.toString(); 
	}
}