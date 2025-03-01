/*
 * generated by Xtext 2.23.0
 */
package org.tvtower.db.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.tvtower.db.constants.NewsConstants;

/**
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#content-assist
 * on how to customize the content assistant.
 */
public class DatabaseProposalProvider extends AbstractDatabaseProposalProvider {

	//News
	@Override
	public void completeNewsData_Genre(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		NewsConstants.newsGenre.forEach((k,v)->{
			acceptor.accept(createCompletionProposal("\""+k+"\"",v,null, context));
		});
	}
}
