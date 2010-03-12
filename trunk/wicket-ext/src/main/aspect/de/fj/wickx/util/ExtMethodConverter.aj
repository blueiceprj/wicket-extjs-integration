package de.fj.wickx.util;

import static de.fj.wickx.util.ExtPropertyConverter.generateArgs;
import static de.fj.wickx.util.ExtPropertyConverter.generateReferenceObject;
import static de.fj.wickx.util.ExtPropertyConverter.generateStaticPart;

import org.apache.wicket.IRequestTarget;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;

import de.fj.wickx.ExtComponent;

/**
 * @author frido
 * 
 *         adds a javascript method call to request target if java a method
 *         annotated with \@ExtMethod has been called during an ajax request
 */
aspect ExtMethodConverter {

	public pointcut extMethod() : execution(@ExtMethod * *.*(..));

	after() returning : extMethod() {
		IRequestTarget requestTarget = RequestCycle.get().getRequestTarget();
		if (requestTarget instanceof AjaxRequestTarget) {
			AjaxRequestTarget ajaxRT = (AjaxRequestTarget) requestTarget;
			
			String method = thisJoinPoint.getStaticPart().getSignature().getName();
			String args = generateArgs(thisJoinPoint.getArgs());
			String object;
			if (thisJoinPoint.getTarget() != null) {
				object = generateReferenceObject(thisJoinPoint.getTarget());
			}
			else {
				object = generateStaticPart(thisJoinPoint.getSignature().getDeclaringType());
			}
			String jsMethodCall = String.format("%s.%s(%s);", object, method, args);
			ajaxRT.appendJavascript(jsMethodCall);
		}
	}

}
