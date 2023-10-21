/*
 * @description 
 * bad case class is not serializable. 
 * good case class is serializable.
 * 
 * */

/* note on servlets - when a new Servlet is created, it must be listed in the web.xml
 * file in order to run.  The easy way to do this is in Eclipse:
 *   - right click on testcases in package explorer
 *   - select New->Servlet
 *   - Check the box that says use an existing Servlet class or JSP
 *   - Click browse and select the new Servlet class you have created
 *   - Click OK, Next, then Finish  
 *   - You should now be able to test the servlet by clicking Run in eclipse  
 */

package testcases.CWE579_J2EE_Non_Serializable_in_Session;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testcasesupport.AbstractTestCaseServlet;

public class CWE579_J2EE_Non_Serializable_in_Session__Servlet_01 extends AbstractTestCaseServlet 
{
	private static final long serialVersionUID = 1L;

	class BadObject /* FLAW: non-serializable object being set in session */
	{
		public String bad = "BAD";
	}
	
	class GoodObject implements Serializable /* FIX: serializable object being set in session */
	{
		private static final long serialVersionUID = 7925935052619185041L;	
		public String good = "Good";
	}
	
	public void bad(HttpServletRequest request, HttpServletResponse response)
			throws IOException 
    {

		BadObject b = new BadObject();
		request.getSession(true).setAttribute("BadObject", b);
		
		response.getWriter().println(((BadObject)request.getSession().getAttribute("BadObject")).bad);
	}

	private void good1(HttpServletRequest request, HttpServletResponse response)
			throws IOException 
    {

		GoodObject g = new GoodObject();
		request.getSession(true).setAttribute("GoodObject", g);
		
		response.getWriter().println(((GoodObject)request.getSession().getAttribute("GoodObject")).good);
	}
	
	public void good(HttpServletRequest request, HttpServletResponse response) throws IOException 
    {
        good1(request, response);
    }

}
