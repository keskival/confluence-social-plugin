package fi.neter.confluence.social;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.atlassian.confluence.api.service.content.*;
import com.atlassian.plugin.web.model.WebPanel;

public class ChildTopicPagesModule implements WebPanel
{
	private ContentService contentService;
	
    public ChildTopicPagesModule(ContentService contentService) {
        this.contentService = contentService;
    }

	@Override
	public String getHtml(Map<String, Object> context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeHtml(Writer writer, Map<String, Object> context)
			throws IOException {
		// TODO Auto-generated method stub
		
	}
}
