package business;

import persistence.ReadTable;

import java.io.IOException;
import java.util.*;

public class SelectOperation {

    public void selectQueryOperation(String tableName, String database, String location, List<String> columns_to_print, List<String> conditions) throws IOException {

        //read data using tableName
        Map<String,List<String>> map = new HashMap<>();
        ReadTable readTable = new ReadTable();
        map = readTable.readTableValues(tableName,database,location);

        List<String> column_names = map.get("column");
        int total_columns = column_names.size();

        List<String> rows = map.get("value");
        List<List<String>> row_values = get_row_data(rows, total_columns);
        List<List<String>> result_list = new ArrayList<>();
        List<String> meta = map.get("meta");
        
        if(conditions.size()==0){
            for (List<String> row : row_values) {
                for (String value : row) {
                    System.out.print(value + "  ");
                }
                System.out.println();
            }
        }else {
            String condition = conditions.get(0);
            if (condition.contains(">")) {
                List<String> condition_divided = Arrays.asList(condition.split(">"));
                String column_name = condition_divided.get(0);
                int column_number = get_column_number(column_names, column_name);
                System.out.println(column_name + "  " + column_number);
                String column_type = meta.get(column_number);
                if (column_type.contains("int") || column_type.contains("float")) {
                    //for(List<String> row : row_values){
                    for (int i = 0; i < row_values.size(); i++) {
                        List<String> row = row_values.get(i);
                        int columnValueInt = Integer.parseInt(row.get(column_number));
                        if (columnValueInt > Integer.parseInt(condition_divided.get(1))) {
                            result_list.add(row);
                        }
                    }
                    print_list(result_list, columns_to_print, column_names);
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
                            result_list.add(row);
                        }
                    }
                    print_list(result_list, columns_to_print, column_names);
                } else {
                    System.out.println("Can not use < with string value");
                }

            } else if (condition.contains("=")) {
                List<String> condition_divided = Arrays.asList(condition.split("="));
                String column_name = condition_divided.get(0);
                int column_number = get_column_number(column_names, column_name);
                String column_type = meta.get(column_number);
                if (column_type.contains("int") || column_type.contains("float")) {
                    //for (List<String> row : row_values) {
                    for (int i = 0; i < row_values.size(); i++) {
                        List<String> row = row_values.get(i);
                        int columnValueInt = Integer.parseInt(row.get(column_number));
                        if (columnValueInt == Integer.parseInt(condition_divided.get(1))) {
                            result_list.add(row);
                        }
                    }
                    print_list(result_list, columns_to_print, column_names);
                } else {
                    System.out.println("Can not use = with string value");
                }
            }
        }
    }

    private void print_list(List<List<String>> result_list, List<String> columnList, List<String> column_names) {
        if (columnList.get(0).equals("*")) {
            for (List<String> row : result_list) {
                for (String value : row) {
                    System.out.print(value + "  ");
                }
                System.out.println();
            }
        } else {
            for (List<String> row : result_list) {
                int i=0;
                for (String value : row) {
                    if(columnList.contains(column_names.get(i))) {
                        System.out.print(value + "  ");
                    }
                    i++;
                }
                System.out.println();
            }
        }
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
}
//SELECT * FROM student WHERE number>2236;