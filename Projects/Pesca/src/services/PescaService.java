package services;

import common.specification.Boat;
import common.specification.Fish;
import common.specification.StatisticResult;
import common.specification.User;

import java.io.IOException;

public interface PescaService {
    boolean getUserByIdentifier(String user) throws IOException;
    void registerUser(String identifier) throws Exception;
    void deleteUser(String identifier) throws Exception;
    User getUser(String identifier) throws Exception;
    void registerNewAction(String user, Fish fish) throws Exception;
    Fish getFish(String key) throws IOException;
    StatisticResult getStatistics() throws IOException;
    StatisticResult getStatistics(String user) throws IOException;
    boolean getBoatByIdentifier(String boat) throws IOException;
    void registerBoat(String identifier) throws Exception;
    Boat getBoat(String identifier) throws Exception;
    void registerUserInBoat(Boat boat,User user) throws Exception;
    void deleteUserInBoat(Boat boat, User user) throws Exception;
    void replaceBoat(Boat boat) throws IOException;
}
