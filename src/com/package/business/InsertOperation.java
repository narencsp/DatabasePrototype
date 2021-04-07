package business;

import persistence.ReadTable;
import persistence.WriteTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertOperation {
    public void checkInsertValues(String table, String database, String location, List<String> values) throws Exception {

        Map<String,List<String>> map = new HashMap<>();
        ReadTable readTable = new ReadTable();
        map = readTable.readTableValues(table,database,location);

        int indexOfPrimaryKey = getIndexOfPrimaryKey(map.get("meta"));
        String valueToCompare = values.get(indexOfPrimaryKey);

        List<String> column_names = map.get("column");
        int total_columns = column_names.size();

        List<String> rows = map.get("value");
        if(rows.size()==0){
            WriteTable writeTable = new WriteTable();
            writeTable.insertIntoTable(table, database, location, values);
        }else {
            List<List<String>> row_values = get_row_data(rows, total_columns);

            List<String> primayKeyColumnValues = new ArrayList<>();
            for (int i = 0; i < row_values.size(); i++) {
                List<String> row = row_values.get(i);
                primayKeyColumnValues.add(row.get(indexOfPrimaryKey));
            }

            boolean isError = false;
            for (String s : primayKeyColumnValues) {
                if (s.equals(valueToCompare)) {
                    System.out.println("Duplicate values in Primary key! Insertion failed.");
                    isError = true;
                    break;
                }
            }
            if (!isError) {
                WriteTable writeTable = new WriteTable();
                writeTable.insertIntoTable(table, database, location, values);
            }
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
