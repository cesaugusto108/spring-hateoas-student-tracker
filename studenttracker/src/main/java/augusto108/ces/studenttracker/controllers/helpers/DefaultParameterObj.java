package augusto108.ces.studenttracker.controllers.helpers;

import java.util.HashMap;
import java.util.Map;

public class DefaultParameterObj {
    private final int page;
    private final int maxResults;
    private final Map<String, String> map;

    public DefaultParameterObj() {
        this.page = 0;
        this.maxResults = 5;
        this.map = new HashMap<>();
    }

    public int getPage() {
        return page;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public Map<String, String> getMap() {
        return map;
    }
}
