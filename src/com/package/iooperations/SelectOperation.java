package iooperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectOperation {

    public void selectQueryOperation(String tableName, List<String> columns, List<String> conditions){

        //read data using tableName

        Map<String, List<String>> map = new HashMap<>();

        List<String> table_name_list = new ArrayList<>();
        table_name_list.add("T1");
        map.put("table", table_name_list);

        List<String> column_list = new ArrayList<>();
        column_list.add("fname");
        column_list.add("lname");
        column_list.add("number");
        map.put("column", column_list);

        List<String> column_tlist = new ArrayList<>();
        column_tlist.add("varchar(100)");
        column_tlist.add("varchar(100)");
        column_tlist.add("INT");
        map.put("column", column_tlist);

        List<String> value = new ArrayList<>();
        value.add("kishan");
        value.add("patel");
        value.add("234235");
        value.add("kish");
        value.add("pal");
        value.add("2235");
        value.add("shan");
        value.add("atel");
        value.add("235");
        value.add("kihan");
        value.add("pael");
        value.add("2335");
        map.put("value", value);

        List<String> column_names = map.get("columns");
        int total_columns = column_names.size();

        List<String> rows = map.get("value");
        List<List<String>> row_values = get_row_data(rows,total_columns);


    }

    public List<List<String>> get_row_data(List<String> rows, int total_columns){
        List<List<String>> rows_value = new ArrayList<>();
        List<String> temp_list = new ArrayList<>();
        for(int i=0;i<rows.size();i++){
            if(i % total_columns == 0){
                rows_value.add(temp_list);
                temp_list = new ArrayList<>();
            }else{
                temp_list.add(rows.get(i));
            }
        }
        return rows_value;
    }
}
