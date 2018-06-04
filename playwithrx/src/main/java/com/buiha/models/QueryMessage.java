package com.buiha.models;

public class QueryMessage {
    private String query;

    public QueryMessage() {
    }

    public QueryMessage(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "QueryMessage{" + "query='" + query + '\'' + '}';
    }
}
