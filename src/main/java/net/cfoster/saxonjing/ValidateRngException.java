/**
 * Copyright 2016 Charles Foster
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.cfoster.saxonjing;

import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.trans.XPathException;

public class ValidateRngException extends Exception
{
  protected String errorCode;

  public ValidateRngException(String message, String errorCode)
  {
    super(message);
    this.errorCode = errorCode;
  }

  public ValidateRngException(String message, String errorCode, Throwable e)
  {
    super(message, e);
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public XPathException createXPathException(XPathContext context) {
    XPathException ex = new XPathException(getMessage(), getCause());
    ex.setErrorCode(errorCode);
    ex.setXPathContext(context);
    ex.setErrorCodeQName(
      new StructuredQName(
        "rng",
        "http://relaxng.org/ns/structure/1.0",
        errorCode)
    );
    return ex;
  }

}
