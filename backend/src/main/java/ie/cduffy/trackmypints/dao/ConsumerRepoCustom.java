package ie.cduffy.trackmypints.dao;

import ie.cduffy.trackmypints.model.Consumer;

public interface ConsumerRepoCustom {

    public Consumer getConsumerByUsername(String username);

    public boolean isConsumerVerified(String username);

    public boolean registerConsumer(Consumer consumer);

    public boolean isConsumerRegistered(String username);
}
