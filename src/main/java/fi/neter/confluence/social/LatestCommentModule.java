package fi.neter.confluence.social;

import com.atlassian.confluence.api.service.content.*;

public class LatestCommentModule
{
	private ContentService contentService;
	
    public LatestCommentModule(ContentService contentService) {
        this.contentService = contentService;
    }
}
