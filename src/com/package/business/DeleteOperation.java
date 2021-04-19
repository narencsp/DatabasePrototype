package business;

import persistence.ReadTable;
import persistence.UpdateTable;

import java.io.IOException;
import java.util.*;

public class DeleteOperation {
    public void deleteOperation(String table, String database, String location, List<String> conditions) throws Exception {

        Map<String,List<String>> map = new HashMap<>();
        ReadTable readTable = new ReadTable();
        map = readTable.readTableValues(table,database,location);

        List<String> column_names = map.get("column");
        int total_columns = column_names.size();
        List<String> rows = map.get("value");
        List<List<String>> row_values = get_row_data(rows, total_columns);
        List<List<String>> result_list = new ArrayList<>();
        List<String> meta = map.get("meta");

        if (conditions.size() == 0) {
            List<String> blank_list = new ArrayList<>();
            map.replace("value", blank_list);
        } else {
            String condition = conditions.get(0);
            if (condition.contains(">")) {
                List<String> condition_divided = Arrays.asList(condition.split(">"));
                String column_name = condition_divided.get(0);
                int column_number = get_column_number(column_names, column_name);
                String column_type = meta.get(column_number);
                if (column_type.contains("int") || column_type.contains("float")) {
                    //for(List<String> row : row_values){
                    for (int i = 0; i < row_values.size(); i++) {
                        List<String> row = row_values.get(i);
                        int columnValueInt = Integer.parseInt(row.get(column_number));
                        if (columnValueInt > Integer.parseInt(condition_divided.get(1))) {
                            continue;
                        } else {
                            result_list.add(row);
                        }
                    }
                    print_list(result_list);
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
                    //for (List<String> row : row_values) {
                    for (int i = 0; i < row_values.size(); i++) {
                        List<String> row = row_values.get(i);
                        int columnValueInt = Integer.parseInt(row.get(column_number));
                        if (columnValueInt < Integer.parseInt(condition_divided.get(1))) {
                            continue;
                        } else {
                            result_list.add(row);
                        }
                    }
                    print_list(result_list);
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
                if (column_type.toLowerCase().contains("int") || column_type.toLowerCase().contains("float")) {
                    //for (List<String> row : row_values) {
                    for (int i = 0; i < row_values.size(); i++) {
                        List<String> row = row_values.get(i);
                        String columnValueInt = row.get(column_number);
                        if (columnValueInt.equals(condition_divided.get(1))) {
                            continue;
                        } else {
                            result_list.add(row);
                        }
                    }
                    print_list(result_list);
                    List<String> result_values = new ArrayList<>();
                    result_values = getRowValuesToWriteInTable(result_list);
                    map.replace("value", result_values);
                } else {
                    System.out.println("Can not use = with string value");
                }
            }
        }

        //write to table
        UpdateTable updateTable = new UpdateTable();
        String result = updateTable.updateOrDelete(map.get("table").get(0),map.get("database").get(0),location,map);
        System.out.println(result);
    }

    private List<String> getRowValuesToWriteInTable(List<List<String>> result_list) {
        List<String> temp_list = new ArrayList<>();
        for (List<String> row : result_list) {
            temp_list.addAll(row);
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
}
