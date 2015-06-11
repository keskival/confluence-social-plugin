package fi.neter.confluence.social;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.core.ContentEntityObject;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.pages.Comment;
import com.atlassian.confluence.pages.Page;

public class ChildTopicPagesModule implements Macro
{
	@Override
	public String execute(Map<String, String> parameters, String body,
			ConversionContext context) throws MacroExecutionException {
        ContentEntityObject ceo = context.getPageContext().getEntity();
        Page parent = (Page) ceo ;
        List<Page> children = parent.getChildren();
        StringBuilder html = new StringBuilder();
        // Title row.
        html.append("<table class=\"childtopicpages\"><hr><hd>Aihe</hd><hd>Viestejä</hd><hd>Viimeinen viesti</hd></hr>");
        for (Page child : children) {
        	List<Comment> comments = child.getComments();
    		
        	int numberOfComments = comments.size();
        	Date latestCommentDate = null;
        	for (Comment comment : comments) {
        		Date lastModificationDate = comment.getLastModificationDate();
        		Date creationDate = comment.getCreationDate();
        		if (latestCommentDate == null ||
        				(lastModificationDate != null && latestCommentDate.before(lastModificationDate))) {
        			latestCommentDate = lastModificationDate;
        		}
        		if (latestCommentDate == null ||
        				(creationDate != null && latestCommentDate.before(creationDate))) {
        			latestCommentDate = creationDate;
        		}
        	}
        	DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        	html.append("<tr><td>" + child.getLinkWikiMarkup() + "</td><td>"
        	    + numberOfComments + "</td><td>" + df.format(latestCommentDate) + "</td></tr>");
        }
        // Trailer.
        html.append("</table>");
		return html.toString();
	}

	@Override
	public BodyType getBodyType() {
		return BodyType.RICH_TEXT;
	}

	@Override
	public OutputType getOutputType() {
		return OutputType.BLOCK;
	}
}
