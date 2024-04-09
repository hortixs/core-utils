package fr.hortis.commands.coins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PlayerManager {
    private final User user;
    private final IUserManager userManager;

    public PlayerManager(User user, IUserManager userManager) {
        this.user = user;
        this.userManager = userManager;
    }

    public String getInfo(String infoPath, String defaultValue) {
        Object result = this.user.getInfos().getObject(infoPath);
        if (!(result instanceof String)) {
            result = defaultValue;
            this.user.getInfos().addObject(infoPath, defaultValue);
        }

        return (String) result;
    }

    public int getUserPrivateMessages() {
        String privateMessages = this.getInfo("privateMessages", "0");
        return Integer.parseInt(privateMessages);
    }

    public void setUserPrivateMessages(int value) {
        this.user.getInfos().addObject("privateMessages", String.valueOf(value));
        this.userManager.updateUser(this.user);
    }

    public int getUserFriendRequests() {
        String friendRequests = this.getInfo("friendRequests", "0");
        return Integer.parseInt(friendRequests);
    }

    public void setUserFriendRequests(int value) {
        this.user.getInfos().addObject("friendRequests", String.valueOf(value));
        this.userManager.updateUser(this.user);
    }

    public int getUserCoins() {
        String userCoins = this.getInfo("coins", "400");
        return Integer.parseInt(userCoins);
    }

    public void setUserCoins(int coins) {
        this.user.getInfos().addObject("coins", String.valueOf(coins));
        this.userManager.updateUser(this.user);
    }

    public void addUserCoins(int coins) {
        this.user.getInfos().addObject("coins", String.valueOf(getUserCoins() + coins));
        this.userManager.updateUser(this.user);
    }




    public List<UUID> getPlayerFriends() {
        List<UUID> friends = new ArrayList();
        Collections.singletonList(this.getInfo("friends", (new ArrayList()).toString())).forEach((f) -> {
            friends.add(UUID.fromString(f));
        });
        return friends;
    }
}
