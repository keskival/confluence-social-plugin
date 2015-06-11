package fi.neter.confluence.social;

import java.util.Map;

import com.atlassian.confluence.api.service.content.*;
import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;

public class LatestCommentModule implements Macro
{
	private ContentService contentService;
	
    public LatestCommentModule(ContentService contentService) {
        this.contentService = contentService;
    }

	@Override
	public String execute(Map<String, String> parameters, String body,
			ConversionContext context) throws MacroExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BodyType getBodyType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputType getOutputType() {
		// TODO Auto-generated method stub
		return null;
	}
}
