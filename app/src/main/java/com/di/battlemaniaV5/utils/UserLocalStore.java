// For store login data in local storage
package com.di.battlemaniaV5.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.di.battlemaniaV5.models.CurrentUser;

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    public static String testid="gameid",gamename="gamename";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public void Gameid(String gameid){
        SharedPreferences.Editor editor= userLocalDatabase.edit();
        editor.putString(testid,gameid);
        editor.commit();

    }

    public void GameName(String gamenamE){
        SharedPreferences.Editor editor= userLocalDatabase.edit();
        editor.putString(gamename,gamenamE);
        editor.commit();

    }

    public String getGameId(){
     return  userLocalDatabase.getString(testid,"");
    }

    public String getName(){
        return  userLocalDatabase.getString(gamename,"");
    }

    public void storeUserData(CurrentUser cUser) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("memberid", cUser.memberId);
        userLocalDatabaseEditor.putString("username", cUser.userName);
        userLocalDatabaseEditor.putString("password", cUser.password);
        userLocalDatabaseEditor.putString("email", cUser.email);
        userLocalDatabaseEditor.putString("phone", cUser.phone);
        userLocalDatabaseEditor.putString("token", cUser.token);
        userLocalDatabaseEditor.putString("firstname", cUser.firstName);
        userLocalDatabaseEditor.putString("lastname", cUser.lastName);
        userLocalDatabaseEditor.apply();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.apply();
    }

    public CurrentUser getLoggedInUser() {
        String memberid = userLocalDatabase.getString("memberid", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");
        String email = userLocalDatabase.getString("email", "");
        String phone = userLocalDatabase.getString("phone", "");
        String token = userLocalDatabase.getString("token", "");
        String firstname = userLocalDatabase.getString("firstname", "");
        String lastname = userLocalDatabase.getString("lastname", "");
        CurrentUser cUser = new CurrentUser(memberid, username, password,email,phone,token,firstname,lastname);
        return cUser;
    }
}
