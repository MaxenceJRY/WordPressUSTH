package vn.edu.usth.wordpress25.eventbus;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.wordpress25.App;
import vn.edu.usth.wordpress25.model.ChooseTopic;
import vn.edu.usth.wordpress25.model.ChoosedTheme;

public class PreferenceUtils {

    private static final String CHOOSED_THEMES = "CHOOSED_THEMES";


    private static SharedPreferences preferences;

    public static synchronized void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
    }

    public static List<ChooseTopic> getChooseThemes() {
        String data = preferences.getString(CHOOSED_THEMES, "");
        if (!data.isEmpty()) return (new Gson().fromJson(data, ChoosedTheme.class)).datas;
        else return new ArrayList<>();
    }

    public static void saveChooseThemes(List<ChooseTopic> datas) {
        if (datas != null) {
            preferences.edit().putString(CHOOSED_THEMES, new Gson().toJson(new ChoosedTheme(datas))).apply();
        } else {
            preferences.edit().putString(CHOOSED_THEMES, "").apply();
        }
    }

}
