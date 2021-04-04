package iooperations;

import java.util.*;

public class SelectOperation {

    public void selectQueryOperation(String tableName, List<String> columns_to_print, List<String> conditions){

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
        map.put("value", value);

        List<String> column_names = map.get("column");
        int total_columns = column_names.size();

        List<String> rows = map.get("value");
        List<List<String>> row_values = get_row_data(rows,total_columns);
        System.out.println(row_values.size()+ "  "+row_values.get(0).size());
        List<List<String>> result_list = new ArrayList<>();

        List<String> meta = map.get("meta");

        String condition = conditions.get(0);
        if(condition.contains(">")){
            List<String> condition_divided = Arrays.asList(condition.split(">"));
            String column_name = condition_divided.get(0);
            int column_number = get_column_number(column_names,column_name);
            System.out.println(column_name+"  "+column_number);
            String column_type = meta.get(column_number);
            if(column_type.equalsIgnoreCase("int")){
                //for(List<String> row : row_values){
                for(int i = 0; i< row_values.size() ; i++){
                    List<String> row = row_values.get(i);
                    int columnValueInt = Integer.parseInt(row.get(column_number));
                    if(columnValueInt > Integer.parseInt(condition_divided.get(1))){
                        result_list.add(row);
                    }
                }
                print_list(result_list);
            }else{
                System.out.println("Can not use > with string value");
            }

        }else if(condition.contains("<")){
            List<String> condition_divided = Arrays.asList(condition.split("<"));
            String column_name = condition_divided.get(0);
            int column_number = get_column_number(column_names,column_name);
            String column_type = meta.get(column_number);
            if(column_type.equalsIgnoreCase("int")){
                for(List<String> row : row_values){
                    int columnValueInt = Integer.parseInt(row.get(column_number));
                    if(columnValueInt < Integer.parseInt(condition_divided.get(1))){
                        result_list.add(row);
                    }
                }
                print_list(result_list);
            }else{
                System.out.println("Can not use < with string value");
            }

        }else if(condition.contains("=")){
            List<String> condition_divided = Arrays.asList(condition.split("="));
            String column_name = condition_divided.get(0);
            int column_number = get_column_number(column_names,column_name);
            String column_type = meta.get(column_number);
            if(column_type.equalsIgnoreCase("int")){
                for(List<String> row : row_values){
                    int columnValueInt = Integer.parseInt(row.get(column_number));
                    if(columnValueInt == Integer.parseInt(condition_divided.get(1))){
                        result_list.add(row);
                    }
                }
                print_list(result_list);
            }else{
                System.out.println("Can not use = with string value");
            }

        }
    }

    private void print_list(List<List<String>> result_list) {
        for(List<String> row : result_list){
            for(String value : row){
                System.out.print(value+"  ");
            }
            System.out.println();
        }
    }

    public int get_column_number(List<String> columns, String column_name){
        int i=0;
        for(String name : columns){
            if (name.equals(column_name)){
                return i;
            }
            i++;
        }
        System.out.println("Column does not exist!");
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
//SELECT * FROM student WHERE number>2236;