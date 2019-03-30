package net.ijiangtao.tech.concurrent.jsd.waitnotify.demo1;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicBoolean;

@Setter
@Getter
public class Message {
    private AtomicBoolean isAvailable = new AtomicBoolean(false);
    private String msg;
    public Message(String str) {
        this.msg = str;
    }
}
