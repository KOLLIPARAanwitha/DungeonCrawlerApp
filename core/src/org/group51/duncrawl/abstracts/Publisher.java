package org.group51.duncrawl.abstracts;

import java.util.List;

public interface Publisher {
    //List subscribers = null;

    void notifySubscribers();
    void addSubscriber(Subscriber e);
    Subscriber removeSubscriber(Subscriber e);
}
