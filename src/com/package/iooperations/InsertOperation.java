package iooperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertOperation {
    public void checkInsertValues(String table, String database, String location, List<String> values) throws Exception {

        Map<String,List<String>> map = new HashMap<>();
        ReadTable readTable = new ReadTable();
        map = readTable.readTableValues(table);

        /*Map<String, List<String>> map = new HashMap<>();

        List<String> table_name_list = new ArrayList<>();
        table_name_list.add("T1");
        map.put("table", table_name_list);

        List<String> column_list = new ArrayList<>();
        column_list.add("fname");
        column_list.add("lname");
        column_list.add("number");
        map.put("column", column_list);

        List<String> column_tlist = new ArrayList<>();
        column_tlist.add("varchar(100)PK");
        column_tlist.add("varchar(100)");
        column_tlist.add("INT");
        map.put("meta", column_tlist);

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
        map.put("value", value);*/

        int indexOfPrimaryKey = getIndexOfPrimaryKey(map.get("meta"));
        String valueToCompare = values.get(indexOfPrimaryKey);

        List<String> column_names = map.get("column");
        int total_columns = column_names.size();

        List<String> rows = map.get("value");
        List<List<String>> row_values = get_row_data(rows,total_columns);

        List<String> primayKeyColumnValues = new ArrayList<>();
        for(int i = 0; i< row_values.size() ; i++){
            List<String> row = row_values.get(i);
            primayKeyColumnValues.add(row.get(indexOfPrimaryKey));
        }

        boolean isError = false;
        for(String s : primayKeyColumnValues){
            if(s.equals(valueToCompare)){
                System.out.println("Duplicate values in Primary key! Insertion failed.");
                isError = true;
                break;
            }
        }
        if(!isError){
            WriteTable writeTable = new WriteTable();
            writeTable.insertIntoTable(table,values);
        }
    }

    private int getIndexOfPrimaryKey(List<String> meta) {
        int i=0;
        for(String column : meta){
            if(column.contains("PK")){
                return i;
            }
            i++;
        }
        return 0;
    }

    public List<List<String>> get_row_data(List<String> rows, int total_columns){
        List<List<String>> rows_value = new ArrayList<>();
        List<String> temp_list = new ArrayList<>();
        for(int i=1;i<=rows.size();i++){
            if(i % total_columns == 0){
                temp_list.add(rows.get(i-1));
                rows_value.add(temp_list);
                temp_list = new ArrayList<>();
            }else{
                temp_list.add(rows.get(i-1));
            }
        }
        return rows_value;
    }

}
