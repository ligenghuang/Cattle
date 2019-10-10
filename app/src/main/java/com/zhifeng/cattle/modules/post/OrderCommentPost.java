package com.zhifeng.cattle.modules.post;

public class OrderCommentPost {

    private String token;
    private String comments;

    public OrderCommentPost(String token, String comments) {
        this.token = token;
        this.comments = comments;
    }

    public String getToken() {
        return token == null ? "" : token;
    }

    public void setToken(String token) {
        this.token = token == null ? "" : token;
    }

    public String getComments() {
        return comments == null ? "" : comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? "" : comments;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"token\":\"")
                .append(token).append('\"');
        sb.append(",\"comments\":\"")
                .append(comments).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
