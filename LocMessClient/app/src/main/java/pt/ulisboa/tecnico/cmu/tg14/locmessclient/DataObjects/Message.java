package pt.ulisboa.tecnico.cmu.tg14.locmessclient.DataObjects;

import java.util.UUID;

/**
 * Created by brigadinhos on 26/04/2017.
 */

public class Message {

    private UUID uuid;
    private long creationTime;
    private long startTime;
    private long endTime;
    private String content;
    private String publisher;
    private String location;

    public Message(){}

    public Message(UUID uuid, long creationTime, long startTime, long endTime, String content, String publisher, String location) {
        this.uuid = uuid;
        this.creationTime = creationTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.content = content;
        this.publisher = publisher;
        this.location = location;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
