package fi.neter.confluence.social;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.core.ContentEntityObject;
import com.atlassian.renderer.RenderContext;
import com.atlassian.renderer.TokenType;
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
        html.append("<table class=\"childtopicpages\"><tr><th>Aihe</th><th>Viestejä</th><th>Viimeinen viesti</th></tr>");
        if (children != null) for (Page child : children) {
        	List<Comment> comments = child.getComments();
    		
        	int numberOfComments = 0;
        	if (comments != null) numberOfComments = comments.size();
        	Date latestCommentDate = null;
        	if (comments != null) for (Comment comment : comments) {
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
        	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	html.append("<tr><td><a href=\"" + child.getUrlPath() + "\">" + child.getTitle() + "</a></td><td>"
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
