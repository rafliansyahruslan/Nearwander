package com.nearwander.application.nearwander.tabbed.Utility;

/**
 * Created by Rafli on 4/26/17.
 */

public interface FetchFromServerUser {
    void onPreFetch();
    void onFetchCompletion(String string, int id);
}
