package ConstantValues;

public enum ErrorCode {
    Cannot_Find_File("E001", "Cannot find file: "),
    Unloaded_Images("E002", "Cannot load image files: "),
    No_File_Selected("E010", "There is no selected file. Select a Excel file"),
    Excel_Not_Loaded("E020", "The excel file is not loaded.\n Load a file through setup."),
    Wrong_Number_Error("E021", "Number of entries or tickets can not greater than number of teams.\nCheck the setup values"),
    Sheet_Not_Loaded("E030", "The Sheet is not loaded: "),
    Robotex_Sheet_Not_Loaded("E040", "The sheet: Robotex is not found.\nThe excel file should have a sheet named Robotex."),
    Robotex_Sheet_Wrong_Value("E041", "Check the values on Sheet - Robotex."),

    ;

    public String code;
    public String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
