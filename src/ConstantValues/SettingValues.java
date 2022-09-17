package ConstantValues;

import Model.SettingModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SettingValues {
    static SettingValues instance = null;
    SettingModel data;
    boolean loaded;
    boolean status;

    private SettingValues() {
        loaded = false;
        status = false;
        LoadFile();
    }

    public static SettingValues getInstance() {
        if (instance == null) {
            instance = new SettingValues();
        }
        return instance;
    }

    private void LoadFile() {
        File file = new File(FilePaths.SETTING_FILE_PATH);

        if (!file.exists()) {
            loaded = false;
            return;
        }
        loaded = true;

        try {
            Gson gson = new Gson();
            JsonReader jsonReader = new JsonReader(new FileReader(file));
            data = gson.fromJson(jsonReader, SettingModel.class);

            status = true;
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        }
    }

    public SettingModel getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Constants{" +
                "data=" + data +
                ", loaded=" + loaded +
                ", status=" + status +
                '}';
    }
}
