package iooperations;

import java.io.IOException;
import java.util.*;

public class UpdateOperation {
    public void updateOperation(String table, String database, String location, List<String> columnInQuery, List<String> valueInQuery, List<String> conditionFromQuery) throws IOException {

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
        column_tlist.add("varchar(100)");
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

        List<String> column_names = map.get("column");
        int total_columns = column_names.size();
        List<String> rows = map.get("value");
        List<List<String>> row_values = get_row_data(rows, total_columns);
        List<List<String>> result_list = new ArrayList<>();
        List<String> meta = map.get("meta");

        int columnNumberForUpdate = get_column_number(column_names,columnInQuery.get(0));

        if(conditionFromQuery.size()==0){
            String column_name = columnInQuery.get(0);
            int column_number = get_column_number(column_names, column_name);
            List<String> result_list_temp = new ArrayList<>();
            for(List<String> row : row_values){
                for(int i=0;i<row.size();i++){
                    if(i==column_number){
                        result_list_temp.add(valueInQuery.get(0));
                    }else{
                        result_list_temp.add(row.get(i));
                    }
                }
            }
            /*for(String s : result_list_temp){
                System.out.println(s);
            }*/
            map.replace("value", result_list_temp);


        }else{
            String condition = conditionFromQuery.get(0);

            if (condition.contains(">")) {

                List<String> condition_divided = Arrays.asList(condition.split(">"));
                String column_name = condition_divided.get(0);
                int column_number = get_column_number(column_names, column_name);
                System.out.println(column_name + "  " + column_number);
                String column_type = meta.get(column_number);
                if (column_type.contains("int") || column_type.contains("float")) {

                    for (int i = 0; i < row_values.size(); i++) {
                        List<String> row = row_values.get(i);
                        int columnValueInt = Integer.parseInt(row.get(column_number));
                        if (columnValueInt > Integer.parseInt(condition_divided.get(1))) {
                            List<List<String>> finalResultList = new ArrayList<>();
                            finalResultList.add(row);
                            finalResultList = getUpdatedList(finalResultList,columnNumberForUpdate,valueInQuery);
                            result_list.add(finalResultList.get(0));
                        } else {
                            result_list.add(row);
                        }
                    }

                    List<String> result_values = new ArrayList<>();
                    result_values = getRowValuesToWriteInTable(result_list);
                    map.replace("value", result_values);
                } else {
                    System.out.println("Can not use > with string value");
                }

            } else if (condition.contains("<")) {
                List<String> condition_divided = Arrays.asList(condition.split("<"));
                String column_name = condition_divided.get(0);
                int column_number = get_column_number(column_names, column_name);
                String column_type = meta.get(column_number);
                if (column_type.contains("int") || column_type.contains("float")) {
                    for (int i = 0; i < row_values.size(); i++) {
                        List<String> row = row_values.get(i);
                        int columnValueInt = Integer.parseInt(row.get(column_number));
                        if (columnValueInt < Integer.parseInt(condition_divided.get(1))) {
                            List<List<String>> finalResultList = new ArrayList<>();
                            finalResultList.add(row);
                            finalResultList = getUpdatedList(finalResultList,columnNumberForUpdate,valueInQuery);
                            result_list.add(finalResultList.get(0));
                        }else{
                            result_list.add(row);
                        }
                    }

                    //print_list(finalResultList);

                    List<String> result_values = new ArrayList<>();
                    result_values = getRowValuesToWriteInTable(result_list);
                    map.replace("value", result_values);
                } else {
                    System.out.println("Can not use < with string value");
                }

            } else if (condition.contains("=")) {
                List<String> condition_divided = Arrays.asList(condition.split("="));
                String column_name = condition_divided.get(0);
                int column_number = get_column_number(column_names, column_name);
                String column_type = meta.get(column_number);
                if (column_type.contains("int") || column_type.contains("float")) {
                    for (int i = 0; i < row_values.size(); i++) {
                        List<String> row = row_values.get(i);
                        int columnValueInt = Integer.parseInt(row.get(column_number));
                        if (columnValueInt == Integer.parseInt(condition_divided.get(1))) {
                            List<List<String>> finalResultList = new ArrayList<>();
                            finalResultList.add(row);
                            finalResultList = getUpdatedList(finalResultList,columnNumberForUpdate,valueInQuery);
                            result_list.add(finalResultList.get(0));
                        }else{
                            result_list.add(row);
                        }
                    }

                    List<String> result_values = new ArrayList<>();
                    result_values = getRowValuesToWriteInTable(result_list);
                    map.replace("value", result_values);
                } else {
                    System.out.println("Can not use = with string value");
                }
            }
        }

        UpdateTable updateTable = new UpdateTable();
        String result = updateTable.updateOrDelete(map.get("table").get(0),map);
        System.out.println(result);
    }

    private List<List<String>> getUpdatedList(List<List<String>> result_list, int columnNumberForUpdate, List<String> valueInQuery) {
        List<List<String>> temp_list = new ArrayList<>();
        for(List<String> row : result_list){
            row.set(columnNumberForUpdate,valueInQuery.get(0));
            temp_list.add(row);
        }
        return temp_list;
    }

    public List<List<String>> get_row_data(List<String> rows, int total_columns) {
        List<List<String>> rows_value = new ArrayList<>();
        List<String> temp_list = new ArrayList<>();
        for (int i = 1; i <= rows.size(); i++) {
            if (i % total_columns == 0) {
                temp_list.add(rows.get(i - 1));
                rows_value.add(temp_list);
                temp_list = new ArrayList<>();
            } else {
                temp_list.add(rows.get(i - 1));
            }
        }
        return rows_value;
    }

    public int get_column_number(List<String> columns, String column_name) {
        int i = 0;
        for (String name : columns) {
            if (name.equals(column_name)) {
                return i;
            }
            i++;
        }
        System.out.println("Column does not exist!");
        return 0;
    }

    private void print_list(List<List<String>> result_list) {

        for (List<String> row : result_list) {
            for (String value : row) {
                System.out.print(value + "  ");
            }
            System.out.println();
        }
    }

    private List<String> getRowValuesToWriteInTable(List<List<String>> result_list) {
        List<String> temp_list = new ArrayList<>();
        for (List<String> row : result_list) {
            temp_list.addAll(row);
        }
        return temp_list;
    }
}

//UPDATE tabel SET fname=kp WHERE number>2236;
